package adv.qbf.formula;

import adv.qbf.Result;

import java.util.Map;

public class ExprOr implements Expr {
    public Expr left;
    public Expr right;

    public ExprOr(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Result eval(Map<Integer, Boolean> assignments) {
        switch (left.eval(assignments)) {
            case True:
                return Result.True;
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

        return new ExprOr(l, r);
    }

    @Override
    public Expr pushNegationInwards() {
        return new ExprOr(left.pushNegationInwards(), right.pushNegationInwards());
    }

    @Override
    public Expr extractQuantifiers() {
        Expr l = left.extractQuantifiers();
        Expr r = right.extractQuantifiers();

        if (l instanceof ExprForAll) {
            ExprForAll f = (ExprForAll) l;
            return (new ExprExists(f.variable, new ExprOr(f.expr, r))).extractQuantifiers();
        } else if (l instanceof ExprExists) {
            ExprExists f = (ExprExists) l;
            return (new ExprForAll(f.variable, new ExprOr(f.expr, r))).extractQuantifiers();
        }

        if (r instanceof ExprForAll) {
            ExprForAll f = (ExprForAll) r;
            return (new ExprExists(f.variable, new ExprOr(l, f.expr))).extractQuantifiers();
        } else if (r instanceof ExprExists) {
            ExprExists f = (ExprExists) r;
            return (new ExprForAll(f.variable, new ExprOr(l, f.expr))).extractQuantifiers();
        }

        return new ExprOr(l, r);
    }

    @Override
    public Expr distribute() {
        Expr l = left.distribute();
        Expr r = right.distribute();

        if (l instanceof ExprAnd) {
            ExprAnd f = (ExprAnd) l;
            return new ExprAnd((new ExprOr(f.left, r)).distribute(), (new ExprOr(f.right, r)).distribute());
        }

        if (r instanceof ExprAnd) {
            ExprAnd f = (ExprAnd) r;
            return new ExprAnd((new ExprOr(l, f.left)).distribute(), (new ExprOr(l, f.right)).distribute());
        }

        return new ExprOr(l, r);
    }
}
