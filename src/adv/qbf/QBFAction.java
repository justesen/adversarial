package adv.qbf;

import adv.entities.Action;

class QBFAction implements Action {
    final int variable;
    final boolean value;

    QBFAction(int variable, boolean value) {
        this.variable = variable;
        this.value = value;
    }

    @Override
    public String toString() {
        return variable + " = " + (value ? "T" : "F");
    }
}
