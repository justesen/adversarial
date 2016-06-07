package adv;

import adv.algorithms.Algorithm;
import adv.algorithms.MiniMax;
import adv.algorithms.Random;
import adv.algorithms.UTC;
import adv.entities.Action;
import adv.entities.Game;
import adv.entities.State;
import adv.tictactoe.TTTState;
import adv.tictactoe.TicTacToe;

public class Main {
    public static void main(String[] args) {
        Game<? extends State, ? extends Action> game = new TicTacToe();
        State s = new TTTState();
        Algorithm mm = new MiniMax((Game<State, Action>) game);
        Algorithm rnd = new Random((Game<State, Action>) game);
        Algorithm utc = new UTC((Game<State, Action>) game, Math.sqrt(2), 100);

        playMatch((Game<State, Action>) game, s, mm, utc);
    }

    private static void playMatch(Game<State, Action> g, State s, Algorithm p1, Algorithm p2) {
        System.out.println("Initial state:");

        while (true) {
            if (p1.hasNextMove(s)) {
                System.out.println(s);

                Action a = p1.nextMove(s);
                s = g.apply(a, s);

                System.out.println(a);
            } else {
                break;
            }

            if (p2.hasNextMove(s)) {
                System.out.println(s);

                Action a = p2.nextMove(s);
                s = g.apply(a, s);

                System.out.println(a);
            } else {
                break;
            }
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
