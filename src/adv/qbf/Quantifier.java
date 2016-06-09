package adv.qbf;

class Quantifier {
    private final boolean isExistential;
    private final String variable;

    Quantifier(boolean isExistential, String variable) {
        this.isExistential = isExistential;
        this.variable = variable;
    }

    boolean isExistential() {
        return isExistential;
    }

    String variable() {
        return variable;
    }
}
