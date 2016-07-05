package adv.qbf.formula;

import adv.qbf.Result;

import java.util.Map;

public class ExprNot implements Expr {
    public final Expr expr;

    public ExprNot(Expr expr) {
        this.expr = expr;
    }

    @Override
    public Result eval(Map<Integer, Boolean> assignments) {
        Result r = expr.eval(assignments);

        if (r == Result.True) {
            return Result.False;
        } else if (r == Result.False) {
            return Result.True;
        } else {
            return Result.Undetermined;
        }
    }

    @Override
    public int assign(boolean value) {
        return expr.assign(value);
    }

    @Override
    public Expr simplify(Map<Integer, Boolean> assignments) {
        Expr e = expr.simplify(assignments);

        if (e instanceof ExprTrue) {
            return new ExprFalse();
        } else if (e instanceof ExprFalse) {
            return new ExprTrue();
        } else {
            return new ExprNot(e);
        }
    }

    @Override
    public Expr pushNegationInwards() {
        if (expr instanceof ExprForAll) {
            ExprForAll f = (ExprForAll) expr;

            return new ExprExists(f.variable, f.expr.pushNegationInwards());
        } else if (expr instanceof ExprExists) {
            ExprExists f = (ExprExists) expr;

            return new ExprForAll(f.variable, f.expr.pushNegationInwards());
        } else if (expr instanceof ExprAnd) {
            ExprAnd f = (ExprAnd) expr;

            return new ExprOr(f.left.pushNegationInwards(), f.right.pushNegationInwards());
        } else if (expr instanceof ExprOr) {
            ExprOr f = (ExprOr) expr;

            return new ExprAnd(f.left.pushNegationInwards(), f.right.pushNegationInwards());
        } else if (expr instanceof ExprNot) {
            ExprNot f = (ExprNot) expr;

            return f.expr.pushNegationInwards();
        } else if (expr instanceof ExprVariable) {
            return this;
        } else if (expr instanceof ExprTrue) {
            return new ExprFalse();
        } else if (expr instanceof ExprFalse) {
            return new ExprTrue();
        }

        throw new IllegalArgumentException();
    }

    @Override
    public Expr extractQuantifiers() {
        // Should already have been pushed inwards
        return this;
    }

    @Override
    public Expr distribute() {
        // Should already have been pushed inwards
        return this;
    }
}
