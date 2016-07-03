package adv.qbf.formula;

import adv.qbf.Result;

import java.util.Map;

public class ExprAnd implements Expr {
    Expr left;
    Expr right;

    ExprAnd(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Result eval(Map<Integer, Boolean> assignments) {
        if (left.eval(assignments) == Result.False) {
            return Result.False;
        } else {
            return right.eval(assignments);
        }
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
}
