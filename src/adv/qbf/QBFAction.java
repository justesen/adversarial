package adv.qbf;

import adv.entities.Action;

public class QBFAction implements Action {
    public final String var;
    public final boolean val;

    public QBFAction(String var, boolean val) {
        this.var = var;
        this.val = val;
    }
}
