package adv.qbf.formula;

import adv.qbf.Result;

public class Expr implements Formula {
    @Override
    public boolean isFullyInstantiated() {
        return false;
    }

    @Override
    public Result isDetermined() {
        return null;
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
        return false;
    }

    @Override
    public boolean isUniversal() {
        return false;
    }
}
