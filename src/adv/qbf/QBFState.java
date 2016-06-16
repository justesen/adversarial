package adv.qbf;

import adv.entities.State;
import adv.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class QBFState implements State {
    private LinkedList<QuantifierSet> quantifierSets;
    private List<Clause> clauses;
    private Map<Integer, Boolean> assignments;
    private Set<Integer> unassignedVariables;

    QBFState(LinkedList<QuantifierSet> quantifierSets, List<Clause> clauses) {
        this.quantifierSets = quantifierSets;
        this.clauses = clauses;
        this.assignments = new HashMap<>();
        this.unassignedVariables = new HashSet<>();

        for (QuantifierSet q : quantifierSets) {
            unassignedVariables.addAll(q.variables());
        }
    }

    QBFState(QBFState s, int variable, boolean value) {
        if (!s.quantifierSets.getFirst().contains(variable)) {
            throw new IllegalArgumentException("Variable " + variable + " should be in outermost quantifier set");
        }

        this.quantifierSets = new LinkedList<>();
        this.quantifierSets.addAll(s.quantifierSets.stream()
                .map(q -> new QuantifierSet(q.isExistential(), q.variables()))
                .collect(Collectors.toList()));

        this.clauses = new LinkedList<>();
        this.clauses.addAll(s.clauses.stream()
                .map(Clause::new)
                .collect(Collectors.toList()));

        this.assignments = new HashMap<>(s.assignments);
        this.unassignedVariables = new HashSet<>(s.unassignedVariables);
        assign(variable, value);
    }

    QBFState(QBFState s, QBFAction a) {
        this(s, a.variable, a.value);
    }

    private void assign(int variable, boolean value) {
        assignments.put(variable, value);
        unassignedVariables.remove(variable);

        for (QuantifierSet q : quantifierSets) {
            if (q.remove(variable)) {
                break;
            }
        }

        while (!quantifierSets.isEmpty() && quantifierSets.getFirst().isEmpty()) {
            quantifierSets.removeFirst();
        }
    }

    private boolean isExistential(int var) {
        for (QuantifierSet q : quantifierSets) {
            if (q.contains(var)) {
                return q.isExistential();
            }
        }

        // Happens only if variable does not occur in matrix, i.e., it has already been removed as pure symbol
        return false;
    }

    boolean isExistential() {
        return quantifierSets.getFirst().isExistential();
    }

    boolean isUniversal() {
        return !quantifierSets.getFirst().isExistential();
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

    Collection<Integer> outermostQuantifierSet() {
        return quantifierSets.getFirst().variables();
    }

    void simplify() {
        clauses = clauses.stream()
                .filter(c -> c.isDetermined(assignments) != Result.True)
                .collect(Collectors.toList());

        while (removeUnitClauses()) ;

        while (removeMonotoneLiterals()) ;
    }

    private boolean removeUnitClauses() {
        boolean foundUnitClause = false;

        for (Clause c : clauses) {
            if (c.isDetermined(assignments) == Result.Undetermined) {
                LinkedList<Pair<Integer, Boolean>> unassignedVariables = c.unassignedVariables(assignments);

                if (unassignedVariables.size() == 1) {
                    int var = unassignedVariables.getFirst().getFirst();
                    boolean val = unassignedVariables.getFirst().getSecond();

                    if (isExistential(var)) {
                        foundUnitClause = true;
                        assign(var, val);
                    } else {
                        clauses = new LinkedList<>();
                        clauses.add(new Clause(new LinkedList<>()));
                        return false;
                    }
                }
            }
        }

        return foundUnitClause;
    }

    private boolean removeMonotoneLiterals() {
        boolean foundMonotoneLiteral = false;
        Set<Integer> positives = new HashSet<>(unassignedVariables);
        Set<Integer> negatives = new HashSet<>(unassignedVariables);

        clauses.stream()
                .filter(c -> c.isDetermined(assignments) == Result.Undetermined)
                .forEach(c -> {
                    for (int l : c.literals) {
                        if (l < 0) {
                            positives.remove(-l);
                        } else {
                            negatives.remove(l);
                        }
                    }
                });

        for (int var : positives) {
            if (isExistential(var)) {
                assign(var, true);
            } else {
                assign(var, false);
            }

            foundMonotoneLiteral = true;
        }

        for (int var : negatives) {
            if (isExistential(var)) {
                assign(var, false);
            } else {
                assign(var, true);
            }

            foundMonotoneLiteral = true;
        }

        return foundMonotoneLiteral;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (QuantifierSet q : quantifierSets) {
            for (int v : q.variables()) {
                s.append(q.isExistential() ? "\u2203" : "\u2200");
                s.append(v);
                s.append(".");
            }
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
