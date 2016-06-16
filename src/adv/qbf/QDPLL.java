package adv.qbf;

public class QDPLL {
    public int nodes;

    public boolean evaluate(QBFState s) {
        nodes = 0;

        return recurse(s);
    }

    private boolean recurse(QBFState s) {
        nodes++;

        s.simplify();

        switch (s.isDetermined()) {
            case True:
                return true;
            case False:
                return false;
        }

        int variable = s.outermostVariable();
        QBFState t = new QBFState(s, variable, true);

        if (s.isUniversal() && !recurse(t)) {
            return false;
        }

        if (s.isExistential() && recurse(t)) {
            return true;
        }

        return recurse(new QBFState(s, variable, false));
    }
}
