package adv.qbf;

import adv.entities.State;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class QBFState implements State {
    private LinkedList<Quantifier> quantifiers;
    private List<Clause> clauses;
    private Map<String, Boolean> assignments;

    public QBFState(LinkedList<Quantifier> quantifiers, List<Clause> clauses) {
        this.quantifiers = quantifiers;
        this.clauses = clauses;
        this.assignments = new HashMap<>();
    }

    public QBFState(QBFState s, QBFAction a) {
        if (!s.quantifiers.getFirst().var().equals(a.var)) {
            throw new IllegalArgumentException();
        }
        this.quantifiers = new LinkedList<>(s.quantifiers);
        this.quantifiers.pollFirst();
        this.clauses = new LinkedList<>(s.clauses);
        this.assignments = new HashMap<>(s.assignments);
        this.assignments.put(a.var, a.val);
    }

    public String outermostVariable() {
        return quantifiers.getFirst().var();
    }

    public boolean isOutermostQuantifierExistential() {
        return !quantifiers.isEmpty() && quantifiers.getFirst().isExistential();
    }

    public boolean isDetermined() {
        for (Clause c : clauses) {
            if (!c.isDetermined(assignments)) {
                return false;
            }
        }
        return true;
    }

    public boolean isTrue() {
        for (Clause c : clauses) {
            if (!c.isTrue(assignments)) {
                return false;
            }
        }
        return true;
    }
}
