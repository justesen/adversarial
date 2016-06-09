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
        return s.isDetermined() != Result.Undetermined;
    }

    @Override
    public int utility(QBFState s) {
        switch (s.isDetermined()) {
            case True:
                return +1;
            case False:
                return -1;
        }
        return 0;
    }
}
