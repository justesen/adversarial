package adv;

import adv.algorithms.Algorithm;
import adv.algorithms.MiniMax;
import adv.algorithms.Random;
import adv.algorithms.UCT;
import adv.entities.Action;
import adv.entities.Game;
import adv.entities.State;
import adv.qbf.QBFSAT;
import adv.qbf.QDIMACS;
import adv.tictactoe.TTTState;
import adv.tictactoe.TicTacToe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Game<? extends State, ? extends Action> game;
        State init;

        if (args.length > 0 && args[0].equals("ttt")) {
            game = new TicTacToe();
            init = new TTTState();
        } else {
            game = new QBFSAT();
            init = QDIMACS.parse(new BufferedReader(new FileReader("instances/qbf/2.qbf")));
        }

        Algorithm mm = new MiniMax((Game<State, Action>) game);
        Algorithm rnd = new Random((Game<State, Action>) game);
        Algorithm utc = new UCT((Game<State, Action>) game, Math.sqrt(2), 100);

        playMatch((Game<State, Action>) game, init, mm, mm);
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
