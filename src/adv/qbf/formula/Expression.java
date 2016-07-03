package adv.qbf.formula;

import adv.qbf.Result;

import java.util.HashMap;
import java.util.Map;

public class Expression implements Formula {
    private final Expr expr;
    private final Map<Integer, Boolean> assignments;
    private final boolean fullyInstantiated;

    public Expression(Expression e, boolean value) {
        this.expr = e.expr;
        this.assignments = new HashMap<>(e.assignments);
        int variable = this.expr.assign(value);

        if (variable == 0) {
            fullyInstantiated = true;
        } else {
            fullyInstantiated = false;
            assignments.put(variable, value);
        }
    }

    @Override
    public boolean isFullyInstantiated() {
        return fullyInstantiated;
    }

    @Override
    public Result isDetermined() {
        return expr.eval(assignments);
    }

    @Override
    public int outermostVariable() {
        return 0;
    }

    @Override
    public boolean valueOf(int variable) {
        return false;
    }

    @Override
    public void simplify() {
    }

    @Override
    public boolean isExistential() {
        return expr instanceof ExprExists;
    }

    @Override
    public boolean isUniversal() {
        return expr instanceof ExprForAll;
    }

    public Result eval() {
        return expr.eval(assignments);
    }
}
