package adv.qbf;

import adv.qbf.formula.Formula;
import adv.qbf.formula.PrenexCNF;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class QDPLL implements QBFAlgorithm {
    private int nodes;
    private QDPLLNode root;

    public UCTResult evaluate(Formula s) {
        nodes = 0;
        root = new QDPLLNode((PrenexCNF) s);

        return new UCTResult(recurse(root));
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

        PrenexCNF t = new PrenexCNF(node.state, true);
        node.trueChild = new QDPLLNode(t);

        if (node.state.isUniversal() && !recurse(node.trueChild)) {
            return false;
        }

        if (node.state.isExistential() && recurse(node.trueChild)) {
            return true;
        }

        node.falseChild = new QDPLLNode(new PrenexCNF(node.state, false));

        return recurse(node.falseChild);
    }

    @Override
    public int generatedNodes() {
        return nodes;
    }

    @Override
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
}
