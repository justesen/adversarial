package adv.qbf.formula;

import adv.qbf.Result;

import java.util.Map;

public class ExprForAll implements Expr {
    public final int variable;
    public final Expr expr;

    public ExprForAll(int variable, Expr expr) {
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
            return new ExprForAll(variable, e);
        }
    }

    @Override
    public Expr pushNegationInwards() {
        return new ExprForAll(variable, expr.pushNegationInwards());
    }

    @Override
    public Expr extractQuantifiers() {
        return new ExprForAll(variable, expr.extractQuantifiers());
    }

    @Override
    public Expr distribute() {
        return new ExprForAll(variable, expr.distribute());
    }
}
