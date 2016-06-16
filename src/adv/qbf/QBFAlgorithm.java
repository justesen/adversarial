package adv.qbf;

public interface QBFAlgorithm {
    UCTResult evaluate(Formula formula);

    int generatedNodes();
}
