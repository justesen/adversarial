package adv.algorithms;

import adv.entities.Action;
import adv.entities.Game;
import adv.entities.State;

import java.util.List;
import java.util.Random;

public class RandomMove implements Algorithm {
    private final Game<State, Action> game;

    public RandomMove(Game<State, Action> game) {
        this.game = game;
    }

    @Override
    public boolean hasNextMove(State s) {
        return !game.isTerminal(s);
    }

    @Override
    public Action nextMove(State s) {
        List<Action> actions = game.actions(s);
        int elem = new Random().nextInt(actions.size());

        return actions.get(elem);
    }
}
