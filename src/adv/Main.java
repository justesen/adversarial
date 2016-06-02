package adv;

import adv.algorithms.Algorithm;
import adv.algorithms.MiniMax;
import adv.algorithms.RandomMove;
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
        Algorithm rnd = new RandomMove((Game<State, Action>) game);

        playMatch((Game<State, Action>) game, s, rnd, mm);
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
        System.out.println("Game over");
    }
}
