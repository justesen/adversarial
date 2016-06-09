package adv.qbf;

import adv.entities.Action;

class QBFAction implements Action {
    final String var;
    final boolean val;

    QBFAction(String var, boolean val) {
        this.var = var;
        this.val = val;
    }

    @Override
    public String toString() {
        return var + " = " + (val ? "T" : "F");
    }
}
