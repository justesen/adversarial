package algorithms;

import entities.Action;
import entities.State;

public interface Algorithm {

    public boolean hasNextMove(State s);

    public Action nextMove(State s);
}
