package adv.qbf.formula;

import adv.qbf.Result;

import java.util.Map;

public class ExprExists implements Expr {
    int variable;
    boolean value;
    Expr expr;

    public ExprExists(int variable, boolean value, Expr expr) {
        this.variable = variable;
        this.value = value;
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
}
