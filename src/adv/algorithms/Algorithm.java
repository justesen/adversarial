package adv.algorithms;

import adv.entities.Action;
import adv.entities.State;

public interface Algorithm {
    boolean hasNextMove(State s);

    Action nextMove(State s);
}
