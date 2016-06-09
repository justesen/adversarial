package adv.qbf;

class Quantifier {
    final int variable;
    private final boolean isExistential;

    Quantifier(boolean isExistential, int variable) {
        this.isExistential = isExistential;
        this.variable = variable;
    }

    boolean isExistential() {
        return isExistential;
    }
}
