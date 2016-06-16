package adv.qbf;

import adv.entities.State;
import adv.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class QBFState implements State {
    private LinkedList<Quantifier> quantifiers;
    private List<Clause> clauses;
    private Map<Integer, Boolean> assignments;
    private Set<Integer> unassignedVariables;

    QBFState(LinkedList<Quantifier> quantifiers, List<Clause> clauses) {
        this.quantifiers = new LinkedList<>(quantifiers);
        this.clauses = clauses;
        this.assignments = new HashMap<>();
        this.unassignedVariables = new HashSet<>();

        unassignedVariables.addAll(quantifiers.stream()
                .map(q -> q.variable)
                .collect(Collectors.toList()));
    }

    QBFState(QBFState s, int variable, boolean value) {
        if (s.quantifiers.getFirst().variable != variable) {
            throw new IllegalArgumentException("Variable " + variable + " should be in outermost quantifier set");
        }

        this.quantifiers = new LinkedList<>(s.quantifiers);
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
        Quantifier toBeRemoved = null;

        for (Quantifier q : quantifiers) {
            if (q.variable == variable) {
                toBeRemoved = q;
                break;
            }
        }

        quantifiers.remove(toBeRemoved);
    }

    private boolean isExistential(int variable) {
        for (Quantifier q : quantifiers) {
            if (q.variable == variable) {
                return q.isExistential();
            }
        }

        // Happens only if variable does not occur in matrix, i.e., it has already been removed as pure symbol
        return false;
    }

    boolean isExistential() {
        return quantifiers.getFirst().isExistential();
    }

    boolean isUniversal() {
        return !quantifiers.getFirst().isExistential();
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

    int outermostVariable() {
        return quantifiers.getFirst().variable;
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
