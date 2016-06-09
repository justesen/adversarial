package adv.qbf;

import adv.entities.Action;

class QBFAction implements Action {
    final String variable;
    final boolean value;

    QBFAction(String variable, boolean value) {
        this.variable = variable;
        this.value = value;
    }

    @Override
    public String toString() {
        return variable + " = " + (value ? "T" : "F");
    }
}
