package adv.qbf;

public class QDPLL {
    public int nodes;

    public QDPLL() {
        nodes = 0;
    }

    public boolean evaluate(QBFState s) {
        nodes++;

        switch (s.isDetermined()) {
            case True:
                return true;
            case False:
                return false;
        }

        QBFState t = new QBFState(s, true);

        if (s.isUniversal() && !evaluate(t)) {
            return false;
        }

        if (s.isExistential() && evaluate(t)) {
            return true;
        }

        return evaluate(new QBFState(s, false));
    }
}
