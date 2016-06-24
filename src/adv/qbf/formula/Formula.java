package adv.qbf.formula;

import adv.qbf.Result;

public interface Formula {

    boolean isFullyInstantiated();

    Result isDetermined();

    int outermostVariable();

    boolean valueOf(int variable);

    void simplify();

    boolean isExistential();

    boolean isUniversal();
}
