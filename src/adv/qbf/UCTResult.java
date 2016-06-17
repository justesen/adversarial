package adv.qbf;

public class UCTResult {
    private final Result value;
    private final double utility;

    UCTResult(boolean value) {
        this.value = value ? Result.True : Result.False;
        this.utility = Double.NaN;
    }

    UCTResult(double utility) {
        this.value = Result.Undetermined;
        this.utility = utility;
    }

    boolean isTrue() {
        return value == Result.True;
    }

    boolean isFalse() {
        return value == Result.False;
    }

    boolean isUndetermined() {
        return value == Result.Undetermined;
    }

    double utility() {
        if (value == Result.Undetermined) {
            return utility;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        if (value == Result.True) {
            return "SAT";
        } else if (value == Result.False) {
            return "UNSAT";
        } else {
            return "" + utility;
        }
    }
}
