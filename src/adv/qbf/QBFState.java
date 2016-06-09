package adv.qbf;

import adv.entities.State;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class QBFState implements State {
    private LinkedList<Quantifier> quantifiers;
    private List<Clause> clauses;
    private Map<Integer, Boolean> assignments;

    QBFState(LinkedList<Quantifier> quantifiers, List<Clause> clauses) {
        this.quantifiers = quantifiers;
        this.clauses = clauses;
        this.assignments = new HashMap<>();
    }

    QBFState(QBFState s, QBFAction a) {
        // Action should be variable of outermost quantifier
        if (s.quantifiers.getFirst().variable != a.variable) {
            throw new IllegalArgumentException();
        }

        this.quantifiers = new LinkedList<>(s.quantifiers);
        this.quantifiers.pollFirst();
        this.clauses = new LinkedList<>(s.clauses);
        this.assignments = new HashMap<>(s.assignments);
        this.assignments.put(a.variable, a.value);
    }

    int outermostVariable() {
        return quantifiers.getFirst().variable;
    }

    boolean isOutermostQuantifierExistential() {
        return !quantifiers.isEmpty() && quantifiers.getFirst().isExistential();
    }

    Result isDetermined() {
        boolean allTrue = true;

        for (Clause c : clauses) {
            switch (c.isDetermined(assignments)) {
                case Undetermined:
                    allTrue = false;
                    break;
                case False:
                    return Result.False;
            }
        }

        return allTrue ? Result.True : Result.Undetermined;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (Quantifier q : quantifiers) {
            s.append(q.isExistential() ? "\u2203" : "\u2200");
            s.append(q.variable);
            s.append(".");
        }

        if (!clauses.isEmpty()) {
            s.append(clauses.get(0).toString(assignments));

            for (Clause c : clauses.subList(1, clauses.size())) {
                s.append(" \u2227 ");

                s.append(c.toString(assignments));
            }
        }

        s.append("\n");

        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QBFState s = (QBFState) o;

        return assignments != null ? assignments.equals(s.assignments) : s.assignments == null;
    }

    @Override
    public int hashCode() {
        return assignments != null ? assignments.hashCode() : 0;
    }
}
