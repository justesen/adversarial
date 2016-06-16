package adv.qbf;

import java.util.ArrayList;
import java.util.List;

class Node {
    public final QBFState state;
    private int visits;
    private double Q;
    private List<Node> children;
    private Result mark;

    Node(QBFState state) {
        this.state = state;
        this.visits = 0;
        this.Q = 0;
        this.mark = Result.Undetermined;
    }

    List<Node> children() {
        return children;
    }

    void addChildren() {
        children = new ArrayList<>(2 * state.outermostQuantifierSet().size());

        for (int v : state.outermostQuantifierSet()) {
            children.add(new Node(new QBFState(state, v, true)));
            children.add(new Node(new QBFState(state, v, false)));
        }
    }

    void mark(boolean mark) {
        this.mark = mark ? Result.True : Result.False;
    }

    boolean isMarked() {
        return mark == Result.True || mark == Result.False;
    }

    boolean allChildrenAreFalse() {
        return children.stream().allMatch(c -> c.mark == Result.False);
    }

    boolean allChildrenAreTrue() {
        return children.stream().allMatch(c -> c.mark == Result.True);
    }

    boolean isUnvisited() {
        return visits == 0;
    }

    void visit() {
        visits++;
    }

    int visits() {
        return visits;
    }

    void updateUtility(double r) {
        Q = Q + (r - Q) / visits;
    }

    double utility() {
        return Q;
    }
}
