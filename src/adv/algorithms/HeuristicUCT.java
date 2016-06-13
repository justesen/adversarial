package adv.algorithms;

import adv.entities.Action;
import adv.entities.Game;
import adv.entities.State;

import java.util.function.Function;

public class HeuristicUCT extends UCT {
    private final Function<State, Integer> heuristic;

    public HeuristicUCT(Game<State, Action> game, double c, long timeCap, Function<State, Integer> heuristic) {
        super(game, c, timeCap);
        this.heuristic = heuristic;
    }

    @Override
    protected int estimatedUtility(State s) {
        return heuristic.apply(s);
    }
}
