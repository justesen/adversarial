package adv;

import adv.algorithms.*;
import adv.entities.Action;
import adv.entities.Game;
import adv.entities.State;
import adv.qbf.*;
import adv.tictactoe.TTTState;
import adv.tictactoe.TicTacToe;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings({"UnusedAssignment", "unchecked"})
public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length > 0 && args[0].equals("ttt")) {
            Game<? extends State, ? extends Action> game = new TicTacToe();
            State init = new TTTState();

            Algorithm mm = new MiniMax((Game<State, Action>) game);
            Algorithm rnd = new Random((Game<State, Action>) game);
            Algorithm hmn = new Human((Game<State, Action>) game);
            Algorithm uct1 = new MonteCarloUCT((Game<State, Action>) game, Math.sqrt(2), 100);
            Algorithm uct2 = new HeuristicUCT((Game<State, Action>) game, Math.sqrt(2), 100, (s -> 0));

            playMatch((Game<State, Action>) game, init, mm, uct1);
        } else if (args.length > 0 && args[0].equals("qdpll")) {
            QBFAlgorithm qdpll = new QDPLL();
            evaluateFormula(qdpll, QDIMACS.parse(args[1]));
        } else if (args.length > 0 && args[0].equals("uct")) {
            QBFAlgorithm uct = new UCTQBF(0, 5, -1);
            evaluateFormula(uct, QDIMACS.parse(args[1]));
        } else {
            Collection<Formula> formulas = new LinkedList<>();
            formulas.add(QDIMACS.generate(45, 130, 5));
            formulas.add(QDIMACS.generate(45, 130, 5));
            formulas.add(QDIMACS.generate(45, 130, 5));
            formulas.add(QDIMACS.parse("/home/mths/git/adversarial/instances/qbf/3_sat.qdimacs"));
            formulas.add(QDIMACS.parse("/home/mths/git/adversarial/instances/qbf/4_unsat.qdimacs"));
            formulas.add(QDIMACS.parse("/home/mths/git/adversarial/instances/qbf/5_sat.qdimacs"));
            formulas.add(QDIMACS.parse("/home/mths/git/adversarial/instances/qbf/preliminary2006/Adder2-2-c.qdimacs"));
            formulas.add(QDIMACS.parse("/home/mths/git/adversarial/instances/qbf/preliminary2006/Adder2-2-s.qdimacs"));
//            formulas.add(QDIMACS.parse("instances/qbf/preliminary2006/adder-2-unsat.qdimacs"));
            formulas.add(QDIMACS.parse("/home/mths/git/adversarial/instances/qbf/preliminary2006/adder-2-sat.qdimacs"));
//            formulas.add(QDIMACS.parse("instances/qbf/preliminary2006/counter_2.qdimacs"));

            QBFAlgorithm qdpll = new QDPLL();
            QBFAlgorithm uct = new UCTQBF(0, 5, -1);

//            evaluateFormulas(qdpll, formulas);
//            System.out.println();
//            evaluateFormulas(uct, formulas);

            List<Boolean> assignments = new LinkedList<>();
            assignments.add(true);
            assignments.add(false);
            assignments.add(false);
            assignments.add(false);
            SearchTraps.lookForSearchTrap(QDIMACS.parse("/home/mths/git/adversarial/instances/qbf/level3trap_sat.qdimacs"), assignments, 3);
        }
    }

    private static void evaluateFormula(QBFAlgorithm alg, Formula formula) {
        UCTResult result;
        long startTime, endTime;

        startTime = System.currentTimeMillis();
        result = alg.evaluate(formula);
        endTime = System.currentTimeMillis();

        System.out.printf("%s\t%s\t%s\n", result, alg.generatedNodes(), (endTime - startTime));
    }

    private static void evaluateFormulas(QBFAlgorithm alg, Collection<Formula> formulas) {
        int nodesTotal = 0;
        long startTime, endTime;

        startTime = System.currentTimeMillis();

        for (Formula f : formulas) {
            evaluateFormula(alg, f);
            nodesTotal += alg.generatedNodes();
        }

        endTime = System.currentTimeMillis();

        System.out.printf("Summary: %s explored %s nodes in %s ms\n", alg, nodesTotal, (endTime - startTime));
    }

    private static void playMatch(Game<State, Action> g, State s, Algorithm p1, Algorithm p2) {
        System.out.println("Initial state:");

        while (!g.isTerminal(s)) {
            System.out.println(s);

            Action a = g.isPlayerMax(s) ? p1.nextMove(s) : p2.nextMove(s);
            s = g.apply(a, s);

            System.out.println(a);
        }

        System.out.println(s);

        if (g.utility(s) > 0) {
            System.out.println("Player 1 won");
        } else if (g.utility(s) < 0) {
            System.out.println("Player 2 won");
        } else {
            System.out.println("Game is draw");
        }
    }
}
