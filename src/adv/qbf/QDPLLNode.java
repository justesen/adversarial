package adv.qbf;

import adv.qbf.formula.PrenexCNF;

class QDPLLNode {
    final PrenexCNF state;
    QDPLLNode trueChild;
    QDPLLNode falseChild;

    QDPLLNode(PrenexCNF state) {
        this.state = state;
        this.trueChild = null;
        this.falseChild = null;
    }
}
