package adv.qbf.formula;

import adv.qbf.Result;

import java.util.Map;

public class ExprTrue implements Expr {

    @Override
    public Result eval(Map<Integer, Boolean> assignments) {
        return Result.True;
    }

    @Override
    public int assign(boolean value) {
        return 0;
    }

    @Override
    public Expr simplify(Map<Integer, Boolean> assignments) {
        return this;
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
