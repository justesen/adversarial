package adv.qbf;

import adv.qbf.formula.Formula;
import adv.qbf.formula.PrenexCNF;

import java.io.FileNotFoundException;

public interface QBFAlgorithm {
    UCTResult evaluate(Formula formula);

    int generatedNodes();

    void gameTreeToDot(String filename) throws FileNotFoundException;
}
