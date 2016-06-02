import algorithms.Algorithm;
import algorithms.MiniMax;
import algorithms.RandomMove;
import entities.Action;
import entities.Game;
import entities.State;
import tictactoe.TTTAction;
import tictactoe.TTTState;
import tictactoe.TicTacToe;

public class Main {

    public static void main(String[] args) {
        Game<? extends State, ? extends Action> g = new TicTacToe();
        TTTState s = new TTTState();
        Algorithm mm = new MiniMax((Game<State, Action>) g);
        Algorithm rnd = new RandomMove(g);

        playMatch((Game<State, Action>) g, s, mm, rnd);
    }

    private static void playMatch(Game<State, Action> g, State s, Algorithm p1, Algorithm p2) {
        while (true) {
            if (p1.hasNextMove(s)) {
                System.out.println(s);
                Action a = p1.nextMove(s);
                s = g.apply(a, s);
            } else {
                System.out.println(s);
                break;
            }

            if (p2.hasNextMove(s)) {
                System.out.println(s);
                TTTAction a = (TTTAction) p2.nextMove(s);
                s = g.apply(a, s);
            } else {
                System.out.println(s);
                break;
            }
        }
    }
}
