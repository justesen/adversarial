package adv.qbf;

class Node {
    public final Formula state;
    private int visits;
    private double Q;
    private Node[] children;
    private Result mark;

    Node(Formula state) {
        this.state = state;
        this.visits = 0;
        this.Q = 0;
        this.mark = Result.Undetermined;
    }

    Node[] children() {
        return children;
    }

    void addChildren() {
        children = new Node[2];
        children[0] = new Node(new Formula(state, true));
        children[1] = new Node(new Formula(state, false));
    }

    void mark(boolean mark) {
        this.mark = mark ? Result.True : Result.False;
    }

    boolean isMarked() {
        return mark == Result.True || mark == Result.False;
    }

    boolean allChildrenAreFalse() {
        return children[0].mark == Result.False && children[1].mark == Result.False;
    }

    boolean allChildrenAreTrue() {
        return children[0].mark == Result.True && children[1].mark == Result.True;
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
