package adv.qbf.formula;

import adv.qbf.Result;

import java.util.Map;

public class ExprExists implements Expr {
    public final int variable;
    public final Expr expr;

    public ExprExists(int variable, Expr expr) {
        this.variable = variable;
        this.expr = expr;
    }

    @Override
    public Result eval(Map<Integer, Boolean> assignments) {
        return expr.eval(assignments);
    }

    @Override
    public int assign(boolean value) {
        return variable;
    }

    @Override
    public Expr simplify(Map<Integer, Boolean> assignments) {
        Expr e = expr.simplify(assignments);

        if (assignments.containsKey(variable)) {
            return e;
        } else {
            return new ExprExists(variable, e);
        }
    }

    @Override
    public Expr pushNegationInwards() {
        return new ExprExists(variable, expr.pushNegationInwards());
    }

    @Override
    public Expr extractQuantifiers() {
        return new ExprExists(variable, expr.extractQuantifiers());
    }

    @Override
    public Expr distribute() {
        return new ExprExists(variable, expr.distribute());
    }
}
