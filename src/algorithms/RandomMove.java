package algorithms;

import entities.Action;
import entities.Game;
import entities.State;

import java.util.List;
import java.util.Random;

public class RandomMove implements Algorithm {

    Game<State, Action> game;

    public RandomMove(Game<? extends State, ? extends Action> game) {
        this.game = (Game<State, Action>) game;
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
