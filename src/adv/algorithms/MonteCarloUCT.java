package adv.algorithms;

import adv.entities.Action;
import adv.entities.Game;
import adv.entities.State;

public class MonteCarloUCT extends UCT {
    public MonteCarloUCT(Game<State, Action> game, double c, long timeCap) {
        super(game, c, timeCap);
    }

    @Override
    protected int estimatedUtility(State s) {
        Random p = new Random(game);

        while (!game.isTerminal(s)) {
            Action a = p.nextMove(s);
            s = game.apply(a, s);
        }

        return game.utility(s);
    }
}
