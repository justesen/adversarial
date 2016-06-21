package adv.qbf;

class Quantifier {
    private final int variable;
    private final boolean isExistential;

    Quantifier(boolean isExistential, int variable) {
        this.isExistential = isExistential;
        this.variable = variable;
    }

    int variable() {
        return variable;
    }

    boolean isExistential() {
        return isExistential;
    }
}