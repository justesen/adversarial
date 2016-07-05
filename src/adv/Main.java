package adv;

import adv.algorithms.*;
import adv.entities.Action;
import adv.entities.Game;
import adv.entities.State;
import adv.qbf.*;
import adv.qbf.formula.*;
import adv.tictactoe.TTTState;
import adv.tictactoe.TicTacToe;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings({"UnusedAssignment", "unchecked"})
public class Main {
    public static void main(String[] args) throws Exception {
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
            System.out.println();
        } else if (args.length > 0 && args[0].equals("uct")) {
            double c = Double.parseDouble(args[1]);
            int numberOfPlayouts = Integer.parseInt(args[2]);
            long timeCap = -1;
            int level = Integer.parseInt(args[3]);
            UCTQBF uct = new UCTQBF(c, numberOfPlayouts, timeCap);
            PrenexCNF f = QDIMACS.parse(args[4]);

            evaluateFormula(uct, f);
            System.out.print("\t");
            List<Boolean> assignments = uct.getAssignmentsInOrder();
            System.out.println(SearchTraps.lookForSearchTrap(f, assignments, level));
        } else if (args.length > 0 && args[0].equals("graph")) {
            double c = Double.parseDouble(args[1]);
//            UCTQBF uct = new UCTQBF(c, 5, -1);
            QDPLL uct = new QDPLL();
            PrenexCNF f = QDIMACS.parse(args[2]);
            uct.evaluate(f);
            uct.gameTreeToDot(args[3]);
        } else if (args.length > 0 && args[0].equals("expr")) {
            double c = Double.parseDouble(args[1]);
            int numberOfPlayouts = Integer.parseInt(args[2]);
            long timeCap = -1;
            UCTQBF uct = new UCTQBF(c, numberOfPlayouts, timeCap);
//            Formula f = QBFExpr.parse(args[3]);
//            Formula f = QBFExpr.parse("/home/mths/git/adversarial/instances/qbf/NPNCNF2008/counter4_2-bin.qbf");
            Formula f = QBFExpr.parse("/home/mths/git/adversarial/instances/qbf/test.qbf");

            evaluateFormula(uct, f);
            System.out.println();
        } else if (args.length > 0 && args[0].equals("convert")) {
            String from = args[1];
            String[] path = from.split("\\.qbf");
            String to = path[0] + ".qdimacs";
            Expression e = QBFExpr.parse(from);
            System.out.println("Converting...");
            PrenexCNF f = QBFExpr.convert(e);
            System.out.println("Writing...");
            QDIMACS.toFile(f, to);
        } else {
            Expr e = createDnf(2);
            Expr f = e.distribute();

            Collection<PrenexCNF> formulas = new LinkedList<>();
//            formulas.add(QDIMACS.generate(45, 130, 5));
//            formulas.add(QDIMACS.generate(45, 130, 5));
//            formulas.add(QDIMACS.generate(45, 130, 5));
            formulas.add(QDIMACS.parse("/home/mths/git/adversarial/instances/qbf/3_sat.qdimacs"));
            formulas.add(QDIMACS.parse("/home/mths/git/adversarial/instances/qbf/4_unsat.qdimacs"));
            formulas.add(QDIMACS.parse("/home/mths/git/adversarial/instances/qbf/5_sat.qdimacs"));
            formulas.add(QDIMACS.parse("/home/mths/git/adversarial/instances/qbf/testsuite/Adder2-2-c.qdimacs"));
            formulas.add(QDIMACS.parse("/home/mths/git/adversarial/instances/qbf/testsuite/Adder2-2-s.qdimacs"));
//            formulas.add(QDIMACS.parse("/home/mths/git/adversarial/instances/qbf/testsuite/adder-2-unsat.qdimacs"));
            formulas.add(QDIMACS.parse("/home/mths/git/adversarial/instances/qbf/testsuite/adder-2-sat.qdimacs"));
//            formulas.add(QDIMACS.parse("instances/qbf/preliminary2006/counter_2.qdimacs"));

            QBFAlgorithm qdpll = new QDPLL();
            QBFAlgorithm uct = new UCTQBF(0, 5, -1);

            evaluateFormulas(qdpll, formulas);
            System.out.println();
            evaluateFormulas(uct, formulas);

//            PrenexCNF f = QDIMACS.parse("/home/mths/git/adversarial/instances/qbf/testsuite/s27_d2_s.qdimacs");
//            qdpll.evaluate(f);
//            qdpll.gameTreeToDot("home/mths/git/adversarial/");
//            List<Boolean> assignments = uct.getAssignmentsInOrder();
//                    new LinkedList<>();
//            assignments.add(true);
//            assignments.add(false);
//            assignments.add(false);
//            assignments.add(false);
//            SearchTraps.lookForSearchTrap(f, assignments, 7);
        }
    }

    private static Expr createDnf(int i) {
        if (i == 1) {
            return new ExprAnd(new ExprVariable(10), new ExprVariable(11));
        } else {
            return new ExprOr(new ExprAnd(new ExprVariable(i * 10), new ExprVariable(i * 10 + 1)), createDnf(i - 1));
        }
    }

    private static void evaluateFormula(QBFAlgorithm alg, Formula formula) {
        UCTResult result;
        long startTime, endTime;

        startTime = System.currentTimeMillis();
        result = alg.evaluate(formula);
        endTime = System.currentTimeMillis();

        System.out.printf("%s\t%s\t%s", result, alg.generatedNodes(), (endTime - startTime));
    }

    private static void evaluateFormulas(QBFAlgorithm alg, Collection<PrenexCNF> formulas) {
        int nodesTotal = 0;
        long startTime, endTime;

        startTime = System.currentTimeMillis();

        for (PrenexCNF f : formulas) {
            evaluateFormula(alg, f);
            System.out.println();
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
