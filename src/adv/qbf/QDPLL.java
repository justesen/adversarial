package adv.qbf;

public class QDPLL {

    public boolean evaluate(QBFState s) {
        switch (s.isDetermined()) {
            case True:
                return true;
            case False:
                return false;
        }

        QBFState t = new QBFState(s, true);

        if (!s.isOutermostQuantifierExistential() && !evaluate(t)) {
            return false;
        }

        if (s.isOutermostQuantifierExistential() && evaluate(t)) {
            return true;
        }

        return evaluate(new QBFState(s, false));
    }
}
