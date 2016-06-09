package adv.algorithms;

import adv.entities.Action;
import adv.entities.State;

public interface Algorithm {
    Action nextMove(State s);
}
