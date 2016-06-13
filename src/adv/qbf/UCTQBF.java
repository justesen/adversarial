package adv.qbf;

import adv.util.Timer;

import java.util.Arrays;
import java.util.Comparator;

public class UCTQBF {
    private final double c;
    private final Timer timer;
    public int nodes;

    public UCTQBF(double c, long timeCap) {
        this.c = c;
        this.timer = new Timer(timeCap);
    }

    public UCTResult evaluate(QBFState s) {
        Node root = new Node(s);
        UCTResult r = null;

        timer.start();

        while (r == null || (r.isUndetermined() && timer.isTimeRemaining())) {
            nodes++;
            r = UCTRecurse(root);
        }

        return r;
    }

    private UCTResult UCTRecurse(Node node) {
        UCTResult r = null;

        if (node.isUnvisited()) {
            switch (node.state.isDetermined()) {
                case True:
                    node.mark(true);
                    return new UCTResult(true);
                case False:
                    node.mark(false);
                    return new UCTResult(false);
                case Undetermined:
                    node.addChildren();
                    r = new UCTResult(estimatedUtility(node.state));
            }
        } else {
            Node child = selectChild(node);
            UCTResult s = UCTRecurse(child);

            if (node.state.isExistential() && s.isTrue()) {
                node.mark(true);
                return s;
            } else if (node.state.isExistential() && node.allChildrenAreFalse()) {
                node.mark(false);
                return s;
            } else if (node.state.isUniversal() && s.isFalse()) {
                node.mark(false);
                return s;
            } else if (node.state.isUniversal() && node.allChildrenAreTrue()) {
                node.mark(true);
                return s;
            } else {
                if (s.isTrue()) {
                    r = new UCTResult(+1.0);
                } else if (s.isFalse()) {
                    r = new UCTResult(-1.0);
                } else {
                    r = new UCTResult(s.utility());
                }
            }
        }

        node.visit();
        node.updateUtility(r.utility());

        return r;
    }

    private Node selectChild(Node node) {
        if (node.state.isExistential()) {
            return Arrays.stream(node.children())
                    .filter(child -> !child.isMarked())
                    .max(Comparator.comparing(child ->
                            child.utility() + c * Math.sqrt(Math.log(node.visits()) / child.visits())))
                    .orElse(null);
        } else {
            return Arrays.stream(node.children())
                    .filter(child -> !child.isMarked())
                    .min(Comparator.comparing(child ->
                            child.utility() - c * Math.sqrt(Math.log(node.visits()) / child.visits())))
                    .orElse(null);
        }
    }

    private double estimatedUtility(QBFState s) {
        return (s.trueClauses() - s.falseClauses()) / s.clauses();

//        while (s.isDetermined() == Result.Undetermined) {
//            s = new QBFState(s, (new Random()).nextBoolean());
//        }
//
//        if (s.isDetermined() == Result.True) {
//            return +1;
//        } else {
//            return -1;
//        }
    }
}
