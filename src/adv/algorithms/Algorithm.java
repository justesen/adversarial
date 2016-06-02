package adv.algorithms;

import adv.entities.Action;
import adv.entities.State;

public interface Algorithm {

    public boolean hasNextMove(State s);

    public Action nextMove(State s);
}
