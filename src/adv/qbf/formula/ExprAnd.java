package adv.qbf.formula;

import adv.qbf.Result;

import java.util.Map;

public class ExprAnd implements Expr {
    public final Expr left;
    public final Expr right;

    public ExprAnd(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Result eval(Map<Integer, Boolean> assignments) {
        switch (left.eval(assignments)) {
            case False:
                return Result.False;
            case Undetermined:
                return Result.Undetermined;
        }

        return right.eval(assignments);
    }

    @Override
    public int assign(boolean value) {
        int variable = left.assign(value);

        if (variable == 0) {
            return right.assign(value);
        } else {
            return variable;
        }
    }

    @Override
    public Expr simplify(Map<Integer, Boolean> assignments) {
        Expr l = left.simplify(assignments);

        if (l instanceof ExprFalse) {
            return l;
        }

        Expr r = right.simplify(assignments);

        if (r instanceof ExprFalse) {
            return r;
        }

        return new ExprAnd(l, r);
    }

    @Override
    public Expr pushNegationInwards() {
        return new ExprAnd(left.pushNegationInwards(), right.pushNegationInwards());
    }

    @Override
    public Expr extractQuantifiers() {
        Expr l = left.extractQuantifiers();
        Expr r = right.extractQuantifiers();

        if (l instanceof ExprForAll) {
            ExprForAll f = (ExprForAll) l;
            return (new ExprExists(f.variable, new ExprAnd(f.expr, r))).extractQuantifiers();
        } else if (l instanceof ExprExists) {
            ExprExists f = (ExprExists) l;
            return (new ExprForAll(f.variable, new ExprAnd(f.expr, r))).extractQuantifiers();
        }

        if (r instanceof ExprForAll) {
            ExprForAll f = (ExprForAll) r;
            return (new ExprExists(f.variable, new ExprAnd(l, f.expr))).extractQuantifiers();
        } else if (r instanceof ExprExists) {
            ExprExists f = (ExprExists) r;
            return (new ExprForAll(f.variable, new ExprAnd(l, f.expr))).extractQuantifiers();
        }

        return new ExprAnd(l, r);
    }

    @Override
    public Expr distribute() {
        return new ExprAnd(left.distribute(), right.distribute());
    }
}
