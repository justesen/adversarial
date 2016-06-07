package adv.qbf;

import java.util.List;
import java.util.Map;

public class Clause {
    private final List<String> positives;
    private final List<String> negatives;

    public Clause(List<String> positives, List<String> negatives) {
        this.positives = positives;
        this.negatives = negatives;
    }

    public boolean isTrue(Map<String, Boolean> assignments) {
        for (String x : positives) {
            if (assignments.containsKey(x) && assignments.get(x)) {
                return true;
            }
        }
        for (String x : negatives) {
            if (assignments.containsKey(x) && !assignments.get(x)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDetermined(Map<String, Boolean> assignments) {
        boolean allFalse = true;

        for (String x : positives) {
            if (assignments.containsKey(x)) {
                if (assignments.get(x)) {
                    return true;
                }
            } else {
                allFalse = false;
            }
        }
        for (String x : positives) {
            if (assignments.containsKey(x)) {
                if (!assignments.get(x)) {
                    return true;
                }
            } else {
                allFalse = false;
            }
        }
        return allFalse;
    }
}
