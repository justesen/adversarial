package adv.qbf.formula;

import adv.qbf.Result;

import java.util.Map;

public interface Expr {
    Result eval(Map<Integer, Boolean> assignments);

    int assign(boolean value);

    Expr simplify(Map<Integer, Boolean> assignments);

    Expr pushNegationInwards();

    Expr extractQuantifiers();

    Expr distribute();
}
