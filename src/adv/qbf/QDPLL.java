package adv.qbf;

public class QDPLL {
    public int nodes;

    public QDPLL() {
        nodes = 0;
    }

    public boolean evaluate(QBFState s) {
        nodes++;

        s.simplify();

        switch (s.isDetermined()) {
            case True:
                return true;
            case False:
                return false;
        }

        int variable = s.outermostQuantifierSet().stream().findAny().get();
        QBFState t = new QBFState(s, variable, true);

        if (s.isUniversal() && !evaluate(t)) {
            return false;
        }

        if (s.isExistential() && evaluate(t)) {
            return true;
        }

        return evaluate(new QBFState(s, variable, false));
    }
}
