package adv.qbf;

public class QDPLL implements QBFAlgorithm {
    private int nodes;

    public UCTResult evaluate(Formula s) {
        nodes = 0;

        return new UCTResult(recurse(s));
    }

    @Override
    public int generatedNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        return "QDPLL";
    }

    private boolean recurse(Formula s) {
        nodes++;

        s.simplify();

        switch (s.isDetermined()) {
            case True:
                return true;
            case False:
                return false;
        }

        Formula t = new Formula(s, true);

        if (s.isUniversal() && !recurse(t)) {
            return false;
        }

        if (s.isExistential() && recurse(t)) {
            return true;
        }

        return recurse(new Formula(s, false));
    }
}
