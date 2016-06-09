package adv.algorithms;

import adv.entities.Action;
import adv.entities.Game;
import adv.entities.State;
import adv.util.Timer;

import java.util.*;

public class UCT implements Algorithm {
    private final Game<State, Action> game;
    private final double c;
    private final long timeCap;
    private final Set<State> searchTree;
    private final Map<State, Integer> visits;
    private final Map<State, Double> utilities;

    public UCT(Game<State, Action> game, double c, long timeCap) {
        this.game = game;
        this.c = c;
        this.timeCap = timeCap;
        this.searchTree = new HashSet<>();
        this.visits = new HashMap<>();
        this.utilities = new HashMap<>();
    }

    @Override
    public Action nextMove(State s) {
        Timer t = new Timer(timeCap);

        t.start();

        while (t.isTimeRemaining()) {
            UCTRecurse(s);
        }

        return selectMove(s, 0);
    }

    private int UCTRecurse(State s) {
        int r;

        if (game.isTerminal(s)) {
            r = game.utility(s);
            searchTree.add(s);
        } else if (!searchTree.contains(s)) {
            r = randomPlayout(s);
            searchTree.add(s);
        } else {
            State t = game.apply(selectMove(s, c), s);
            r = UCTRecurse(t);
        }

        increaseVisits(s);
        updateUtility(s, r);

        return r;
    }

    private Action selectMove(State s, double c) {
        if (game.isPlayerMax(s)) {
            return game.actions(s).stream()
                    .max(Comparator.comparing(a -> {
                        State t = game.apply(a, s);

                        return utility(t) + c * Math.sqrt(Math.log(visits(s)) / visits(t));
                    }))
                    .get();
        } else {
            return game.actions(s).stream()
                    .min(Comparator.comparing(a -> {
                        State t = game.apply(a, s);

                        return utility(t) - c * Math.sqrt(Math.log(visits(s)) / visits(t));
                    }))
                    .get();
        }
    }

    private int randomPlayout(State s) {
        Random p = new Random(game);

        while (!game.isTerminal(s)) {
            Action a = p.nextMove(s);
            s = game.apply(a, s);
        }

        return game.utility(s);
    }

    private double utility(State s) {
        return utilities.getOrDefault(s, 0.0);
    }

    private void updateUtility(State s, int r) {
        double Q = utilities.getOrDefault(s, 0.0);

        utilities.put(s, Q + (r - Q) / visits(s));
    }

    private int visits(State s) {
        return visits.getOrDefault(s, 0);
    }

    private void increaseVisits(State s) {
        int n = visits.getOrDefault(s, 0);

        visits.put(s, n + 1);
    }
}
