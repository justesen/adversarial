package adv.qbf.formula;

public class Quantifier {
    private final int variable;
    private final boolean isExistential;

    public Quantifier(boolean isExistential, int variable) {
        this.isExistential = isExistential;
        this.variable = variable;
    }

    public int variable() {
        return variable;
    }

    public boolean isExistential() {
        return isExistential;
    }
}