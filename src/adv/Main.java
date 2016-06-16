package adv;

import adv.algorithms.*;
import adv.entities.Action;
import adv.entities.Game;
import adv.entities.State;
import adv.qbf.*;
import adv.tictactoe.TTTState;
import adv.tictactoe.TicTacToe;

import java.io.IOException;

@SuppressWarnings({"UnusedAssignment", "unchecked"})
public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            Game<? extends State, ? extends Action> game;
            State init;

            if (args[0].equals("ttt")) {
                game = new TicTacToe();
                init = new TTTState();
            } else { // if (args[0].equals("qbf")) {
                QBFState formula = QDIMACS.parse("instances/qbf/3_sat.qdimacs");
                QDPLL qdpll = new QDPLL();
                System.out.println("QDPLL: " + qdpll.evaluate(formula));

                game = new QBFSAT();
                init = formula;
            }

            Algorithm mm = new MiniMax((Game<State, Action>) game);
            Algorithm rnd = new Random((Game<State, Action>) game);
            Algorithm hmn = new Human((Game<State, Action>) game);
            Algorithm uct = new MonteCarloUCT((Game<State, Action>) game, Math.sqrt(2), 100);

            playMatch((Game<State, Action>) game, init, mm, uct);
        } else {
            QBFState formula1 = QDIMACS.parse("instances/qbf/3_sat.qdimacs");
            QBFState formula2 = QDIMACS.parse("instances/qbf/4_unsat.qdimacs");
            QBFState formula3 = QDIMACS.parse("instances/qbf/preliminary2006/Adder2-2-c.qdimacs");
            QBFState formula4 = QDIMACS.parse("instances/qbf/preliminary2006/Adder2-2-s.qdimacs");
            QBFState formula5 = QDIMACS.parse("instances/qbf/preliminary2006/adder-2-unsat.qdimacs");
            QBFState formula6 = QDIMACS.parse("instances/qbf/preliminary2006/adder-2-sat.qdimacs");

            QDPLL qdpll = new QDPLL();
            UCTQBF uctqbf = new UCTQBF(Math.sqrt(2), 20000);

            long startTime, endTime;

            startTime = System.currentTimeMillis();
            System.out.printf("QDPLL:\t%s\t(explored %s nodes)\n", qdpll.evaluate(formula1), qdpll.nodes);
            System.out.printf("QDPLL:\t%s\t(explored %s nodes)\n", qdpll.evaluate(formula2), qdpll.nodes);
            System.out.printf("QDPLL:\t%s\t(explored %s nodes)\n", qdpll.evaluate(formula3), qdpll.nodes);
            System.out.printf("QDPLL:\t%s\t(explored %s nodes)\n", qdpll.evaluate(formula4), qdpll.nodes);
//            System.out.printf("QDPLL:\t%s\t(explored %s nodes)\n", qdpll.evaluate(formula5), qdpll.nodes);
            System.out.printf("QDPLL:\t%s\t(explored %s nodes)\n", qdpll.evaluate(formula6), qdpll.nodes);
            endTime = System.currentTimeMillis();
            System.out.printf("QDPLL explored %s nodes in %s ms\n\n", qdpll.nodes, (endTime - startTime));

            startTime = System.currentTimeMillis();
            System.out.printf("UCT:\t%s\t(explored %s nodes)\n", uctqbf.evaluate(formula1), uctqbf.nodes);
            System.out.printf("UCT:\t%s\t(explored %s nodes)\n", uctqbf.evaluate(formula2), uctqbf.nodes);
            System.out.printf("UCT:\t%s\t(explored %s nodes)\n", uctqbf.evaluate(formula3), uctqbf.nodes);
            System.out.printf("UCT:\t%s\t(explored %s nodes)\n", uctqbf.evaluate(formula4), uctqbf.nodes);
//            System.out.printf("UCT:\t%s\t(explored %s nodes)\n", uctqbf.evaluate(formula5), uctqbf.nodes);
            System.out.printf("UCT:\t%s\t(explored %s nodes)\n", uctqbf.evaluate(formula6), uctqbf.nodes);
            endTime = System.currentTimeMillis();
            System.out.printf("UCT explored %s nodes in %s ms\n\n", uctqbf.nodes, (endTime - startTime));
        }
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
