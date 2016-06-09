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

    public Result isDetermined() {
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
            s.append(q.var());
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
}
