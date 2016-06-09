package adv.algorithms;

import adv.entities.Action;
import adv.entities.Game;
import adv.entities.State;

import java.util.List;
import java.util.Scanner;

public class Human implements Algorithm {
    private final Game<State, Action> game;

    public Human(Game<State, Action> game) {
        this.game = game;
    }

    @Override
    public Action nextMove(State s) {
        List<Action> actions = game.actions(s);

        for (int i = 0; i < actions.size(); i++) {
            System.out.printf("%2d. %s\n", i + 1, actions.get(i));
        }

        System.out.print("\nChoose an action: ");

        Scanner in = new Scanner(System.in);
        int i = in.nextInt();

        System.out.println();

        return actions.get(i - 1);
    }
}
