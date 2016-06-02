package adv.entities;

import java.util.List;

public interface Game<S extends State, A extends Action> {
    List<A> actions(S s);

    boolean isPlayerMax(S s);

    S apply(A a, S s);

    boolean isTerminal(S s);

    int utility(S s);
}
