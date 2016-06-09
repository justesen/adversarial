package adv.qbf;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Clause {
    private final List<String> positives;
    private final List<String> negatives;

    Clause(List<String> positives, List<String> negatives) {
        this.positives = positives;
        this.negatives = negatives;
    }

    Result isDetermined(Map<String, Boolean> assignments) {
        boolean allFalse = true;

        for (String x : positives) {
            if (assignments.containsKey(x)) {
                if (assignments.get(x)) {
                    return Result.True;
                }
            } else {
                allFalse = false;
            }
        }
        for (String x : negatives) {
            if (assignments.containsKey(x)) {
                if (!assignments.get(x)) {
                    return Result.True;
                }
            } else {
                allFalse = false;
            }
        }
        return allFalse ? Result.False : Result.Undetermined;
    }

    String toString(Map<String, Boolean> assignments) {
        switch (isDetermined(assignments)) {
            case True:
                return "T";
            case False:
                return "F";
        }

        StringBuilder s = new StringBuilder();
        List<String> literals = new LinkedList<>();

        literals.addAll(positives.stream()
                .filter(v -> !assignments.containsKey(v))
                .collect(Collectors.toList()));

        literals.addAll(negatives.stream()
                .filter(v -> !assignments.containsKey(v))
                .map(v -> "\u00AC" + v)
                .collect(Collectors.toList()));

        if (literals.size() == 1) {
            s.append(literals.get(0));
        } else {
            s.append("(");
            s.append(literals.get(0));

            for (String v : literals.subList(1, literals.size())) {
                s.append(" \u2228 ");
                s.append(v);
            }
            s.append(")");
        }

        return s.toString();
    }
}
