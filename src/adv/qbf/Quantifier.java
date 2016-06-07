package adv.qbf;

public class Quantifier {
    private boolean ex;
    private String var;

    public Quantifier(boolean ex, String var) {
        this.ex = ex;
        this.var = var;
    }

    public boolean isExistential() {
        return ex;
    }

    public String var() {
        return var;
    }
}
