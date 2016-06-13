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
        Game<? extends State, ? extends Action> game;
        State init;

        if (args.length > 0 && args[0].equals("qbf")) {
            QBFState formula = QDIMACS.parse("instances/qbf/2_sat.qbf");
            QDPLL qdpll = new QDPLL();
            System.out.println("QDPLL: " + qdpll.evaluate(formula));
            UCTQBF uctqbf = new UCTQBF(Math.sqrt(2));
            uctqbf.start(formula);
        } else {
            if (args.length > 0 && args[0].equals("ttt")) {
                game = new TicTacToe();
                init = new TTTState();
            } else {
                QBFState formula = QDIMACS.parse("instances/qbf/eval2012r2/adder-10-sat.qdimacs");
                QDPLL qdpll = new QDPLL();
                System.out.println("QDPLL: " + qdpll.evaluate(formula));

                game = new QBFSAT();
                init = formula;
            }

            Algorithm mm = new MiniMax((Game<State, Action>) game);
            Algorithm rnd = new Random((Game<State, Action>) game);
            Algorithm hmn = new Human((Game<State, Action>) game);
            Algorithm uct = new MonteCarloUCT((Game<State, Action>) game, Math.sqrt(2), 100);

            playMatch((Game<State, Action>) game, init, uct, uct);
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
