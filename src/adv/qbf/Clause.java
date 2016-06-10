package adv.qbf;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Clause {
    private final List<Integer> literals;

    Clause(List<Integer> literals) {
        this.literals = literals;
    }

    Result isDetermined(Map<Integer, Boolean> assignments) {
        boolean allFalse = true;

        for (int v : literals) {
            if (v > 0) {
                if (assignments.containsKey(v)) {
                    if (assignments.get(v)) {
                        return Result.True;
                    }
                } else {
                    allFalse = false;
                }
            } else {
                if (assignments.containsKey(-v)) {
                    if (!assignments.get(-v)) {
                        return Result.True;
                    }
                } else {
                    allFalse = false;
                }
            }
        }

        return allFalse ? Result.False : Result.Undetermined;
    }

    boolean isEmpty() {
        return literals.isEmpty();
    }

    String toString(Map<Integer, Boolean> assignments) {
        switch (isDetermined(assignments)) {
            case True:
                return "T";
            case False:
                return "F";
        }

        StringBuilder s = new StringBuilder();
        List<String> variables = literals.stream()
                .filter(v -> !assignments.containsKey(Math.abs(v)))
                .map(v -> v < 0 ? "\u00AC" + (-v) : "" + v)
                .collect(Collectors.toList());

        if (variables.size() == 1) {
            s.append(variables.get(0));
        } else {
            s.append("(");
            s.append(variables.get(0));

            for (String v : variables.subList(1, variables.size())) {
                s.append(" \u2228 ");
                s.append(v);
            }
            s.append(")");
        }

        return s.toString();
    }
}
