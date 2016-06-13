package adv.qbf;

import java.util.Comparator;
import java.util.Random;

import static java.util.Arrays.stream;

public class UCTQBF {
    private final double c;

    public UCTQBF(double c) {
        this.c = c;
    }

    public void start(QBFState s) {
        Node root = new Node(s);

        while (!root.areAllChildrenClosed()) {
            UCTRecurse(root);
        }

        System.out.println("Formula is false!");
    }

    private int UCTRecurse(Node node) {
        int r;

        if (node.n == 0) {
            r = utility(node.state);

            if (r == +1) {
                System.out.println("Formula is true!");
                System.exit(0);
            } else if (r == -1) {
                node.close();
                System.out.println("Formula is false!");
                System.exit(0);
            } else {
                r = estimatedUtility(node.state);
                node.addChildren();
            }
        } else {
            Node child = selectMove(node);

            r = UCTRecurse(child);

            if (r == -1 && node.areAllChildrenClosed()) {
                node.close();
            }
        }
        node.n++;
        node.Q = node.Q + (r - node.Q) / node.n;

        return r;
    }

    private Node selectMove(Node node) {
        if (node.state.isOutermostQuantifierExistential()) {
            return stream(node.children())
                    .filter(child -> !child.isClosed())
                    .max(Comparator.comparing(child -> utility(child.state) + c * Math.sqrt(Math.log(node.n) / child.n)))
                    .orElse(null);
        } else {
            return stream(node.children())
                    .filter(child -> !child.isClosed())
                    .min(Comparator.comparing(child -> utility(child.state) - c * Math.sqrt(Math.log(node.n) / child.n)))
                    .orElse(null);
        }
    }

    private int estimatedUtility(QBFState s) {
        while (s.isDetermined() == Result.Undetermined) {
            s = new QBFState(s, new QBFAction(s.outermostVariable(), (new Random()).nextBoolean()));
        }

        return utility(s);
    }

    private int utility(QBFState s) {
        switch (s.isDetermined()) {
            case True:
                return +1;
            case False:
                return -1;
        }
        return 0;
    }
}

class Node {
    public final QBFState state;
    public int n;
    public double Q;
    private boolean closed;
    private Node[] children;

    public Node(QBFState state) {
        this.state = state;
        this.n = 0;
        this.Q = 0;
        this.closed = false;
    }

    public void close() {
        this.closed = true;
    }

    public Node[] children() {
        return children;
    }

    public void addChildren() {
        int v = state.outermostVariable();
        children = new Node[2];
        children[0] = new Node(new QBFState(state, new QBFAction(v, true)));
        children[1] = new Node(new QBFState(state, new QBFAction(v, false)));
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean areAllChildrenClosed() {
        if (children == null) {
            return false;
        } else if (closed) {
            return true;
        } else {
            return stream(children).allMatch(Node::isClosed);
        }
    }
}