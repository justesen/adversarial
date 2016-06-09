package adv.qbf;

import adv.entities.Game;

import java.util.LinkedList;
import java.util.List;

public class QBFSAT implements Game<QBFState, QBFAction> {
    public static QBFState parse(String s) {
        String[] t = s.split("\\.");
        LinkedList<Quantifier> quantifiers = new LinkedList<>();
        List<Clause> clauses = new LinkedList<>();

        for (int i = 0; i < t.length - 1; i++) {
            String[] q = t[i].split(" ");
            quantifiers.add(new Quantifier(q[0].equals("E"), q[1]));
        }

        String f = t[t.length - 1];
        String[] c = f.split(" and ");

        for (String d : c) {
            String[] v = d.split(" ");
            List<String> p = new LinkedList<>();
            List<String> n = new LinkedList<>();

            for (String w : v) {
                if (w.charAt(0) == '~') {
                    n.add(w.substring(1));
                } else {
                    p.add(w);
                }
            }
            clauses.add(new Clause(p, n));
        }

        return new QBFState(quantifiers, clauses);
    }

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
