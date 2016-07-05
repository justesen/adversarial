package adv.qbf.formula;

import adv.qbf.Result;

import java.util.Map;

public class ExprVariable implements Expr {
    public final int variable;

    public ExprVariable(int variable) {
        this.variable = variable;
    }

    @Override
    public Result eval(Map<Integer, Boolean> assignments) {
        if (assignments.containsKey(variable)) {
            if (assignments.get(variable)) {
                return Result.True;
            } else {
                return Result.False;
            }
        } else {
            return Result.Undetermined;
        }
    }

    @Override
    public int assign(boolean value) {
        return variable;
    }

    @Override
    public Expr simplify(Map<Integer, Boolean> assignments) {
        if (assignments.containsKey(variable)) {
            if (assignments.get(variable)) {
                return new ExprTrue();
            } else {
                return new ExprFalse();
            }
        } else {
            return this;
        }
    }

    @Override
    public Expr pushNegationInwards() {
        return this;
    }

    @Override
    public Expr extractQuantifiers() {
        return this;
    }

    @Override
    public Expr distribute() {
        return this;
    }
}
