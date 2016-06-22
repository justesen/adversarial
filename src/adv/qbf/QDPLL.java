package adv.qbf;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class QDPLL implements QBFAlgorithm {
    private int nodes;
    private QDPLLNode root;

    public UCTResult evaluate(Formula s) {
        nodes = 0;
        root = new QDPLLNode(s);

        return new UCTResult(recurse(root));
    }

    @Override
    public int generatedNodes() {
        return nodes;
    }

    public void gameTreeToDot(String filename) throws FileNotFoundException {
        PrintStream out = new PrintStream(filename);

        out.println("strict graph {");
        out.println("root = " + nodeToString(root));
        gameTreeToDot(out, root);
        out.println("}");
    }

    private void gameTreeToDot(PrintStream out, QDPLLNode node) {
        if (node.trueChild != null) {
            out.println(nodeToString(node) + " -- " + nodeToString(node.trueChild));
            gameTreeToDot(out, node.trueChild);
        }
        if (node.falseChild != null) {
            out.println(nodeToString(node) + " -- " + nodeToString(node.falseChild));
            gameTreeToDot(out, node.falseChild);
        }
    }

    private String nodeToString(QDPLLNode node) {
        String[] s = node.toString().split("@");

        return "a" + s[s.length - 1];
    }

    @Override
    public String toString() {
        return "QDPLL";
    }

    private boolean recurse(QDPLLNode node) {
        nodes++;

        node.state.simplify();

        switch (node.state.isDetermined()) {
            case True:
                return true;
            case False:
                return false;
        }

        Formula t = new Formula(node.state, true);
        node.trueChild = new QDPLLNode(t);

        if (node.state.isUniversal() && !recurse(node.trueChild)) {
            return false;
        }

        if (node.state.isExistential() && recurse(node.trueChild)) {
            return true;
        }

        node.falseChild = new QDPLLNode(new Formula(node.state, false));

        return recurse(node.falseChild);
    }
}

class QDPLLNode {
    final Formula state;
    QDPLLNode trueChild;
    QDPLLNode falseChild;

    QDPLLNode(Formula state) {
        this.state = state;
        this.trueChild = null;
        this.falseChild = null;
    }
}
