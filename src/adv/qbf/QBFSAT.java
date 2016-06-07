package adv.qbf;

import adv.entities.Game;

import java.util.LinkedList;
import java.util.List;

public class QBFSAT implements Game<QBFState, QBFAction> {

    @Override
    public List<QBFAction> actions(QBFState s) {
        List<QBFAction> actions = new LinkedList<>();
        String v = s.outermostVariable();

        actions.add(new QBFAction(v, true));
        actions.add(new QBFAction(v, false));

        return actions;
    }

    @Override
    public boolean isPlayerMax(QBFState s) {
        return s.isOutermostQuantifierExistential();
    }

    @Override
    public QBFState apply(QBFAction a, QBFState s) {
        return new QBFState(s, a);
    }

    @Override
    public boolean isTerminal(QBFState s) {
        return s.isDetermined();
    }

    @Override
    public int utility(QBFState s) {
        if (s.isDetermined()) {
            if (s.isTrue()) {
                return +1;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }
}
