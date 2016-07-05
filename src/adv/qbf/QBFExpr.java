package adv.qbf;

import adv.qbf.exprparser.ParserExprLexer;
import adv.qbf.exprparser.ParserExprParser;
import adv.qbf.formula.*;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;

public class QBFExpr {
    public static Expression parse(String path) throws Exception {
        InputStream in = new FileInputStream(path);
        Expr e = parse(in);
        in.close();

        return new Expression(e);
    }

    private static Expr parse(InputStream in) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(in);
        ParserExprLexer lexer = new ParserExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ParserExprParser parser = new ParserExprParser(tokens);

        return parser.prog();
    }

    public static PrenexCNF convert(Expression e) {
        Expr expr = e.expr;
        expr = expr.pushNegationInwards();
        expr = expr.extractQuantifiers();
        expr = expr.distribute();

        LinkedList<Quantifier> quantifiers = new LinkedList<>();

        while (expr instanceof ExprExists || expr instanceof ExprForAll) {
            if (expr instanceof ExprExists) {
                ExprExists f = (ExprExists) expr;
                quantifiers.add(new Quantifier(true, f.variable));
                expr = f.expr;
            } else {
                ExprForAll f = (ExprForAll) expr;
                quantifiers.add(new Quantifier(false, f.variable));
                expr = f.expr;
            }
        }

        LinkedList<Clause> clauses = cnfToClauses(expr);

        return new PrenexCNF(quantifiers, clauses);
    }

    private static LinkedList<Clause> cnfToClauses(Expr expr) {
        if (expr instanceof ExprAnd) {
            ExprAnd f = (ExprAnd) expr;
            LinkedList<Clause> cl = cnfToClauses(f.left);
            LinkedList<Clause> cr = cnfToClauses(f.right);
            cl.addAll(cr);

            return cl;
        } else {
            LinkedList<Clause> cs = new LinkedList<>();
            cs.add(new Clause(disjunctionToClause(expr)));

            return cs;
        }
    }

    private static LinkedList<Integer> disjunctionToClause(Expr expr) {
        if (expr instanceof ExprOr) {
            ExprOr f = (ExprOr) expr;
            LinkedList<Integer> ll = disjunctionToClause(f.left);
            LinkedList<Integer> lr = disjunctionToClause(f.right);
            ll.addAll(lr);

            return ll;
        } else if (expr instanceof ExprVariable) {
            ExprVariable f = (ExprVariable) expr;
            LinkedList<Integer> ls = new LinkedList<>();
            ls.add(f.variable);

            return ls;
        } else if (expr instanceof ExprNot) {
            ExprNot f = (ExprNot) expr;
            ExprVariable v = (ExprVariable) f.expr;
            LinkedList<Integer> ls = new LinkedList<>();
            ls.add(-v.variable);

            return ls;
        }

        throw new IllegalArgumentException();
    }
}
