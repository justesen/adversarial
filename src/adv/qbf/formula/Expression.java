package adv.qbf.formula;

import adv.qbf.Result;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class Expression implements Formula {
    private final Map<Integer, Boolean> assignments;
    private final boolean fullyInstantiated;
    public Expr expr;

    public Expression(Expr expr) {
        this.expr = expr;
        this.assignments = new HashMap<>();
        fullyInstantiated = false;
    }

    public Expression(Expression e) {
        this.expr = e.expr;
        this.assignments = new HashMap<>();
        fullyInstantiated = false;
    }

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
        throw new NotImplementedException();
    }

    @Override
    public boolean valueOf(int variable) {
        return false;
    }

    @Override
    public void simplify() {
        expr = expr.simplify(assignments);
    }

    @Override
    public boolean isExistential() {
        return expr instanceof ExprExists;
    }

    @Override
    public boolean isUniversal() {
        return expr instanceof ExprForAll;
    }
}
