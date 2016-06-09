package adv.qbf;

class Quantifier {
    private final boolean ex;
    private final String var;

    Quantifier(boolean ex, String var) {
        this.ex = ex;
        this.var = var;
    }

    boolean isExistential() {
        return ex;
    }

    String var() {
        return var;
    }
}
