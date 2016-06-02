package algorithms;

import entities.Action;
import entities.Game;
import entities.State;

public class MiniMax implements Algorithm {
    private class Comparison<V, A> {
        final V val;
        final A arg;

        Comparison(V val, A arg) {
            this.val = val;
            this.arg = arg;
        }
    }

    Game<State, Action> game;

    public MiniMax(Game<State, Action> game) {
        this.game = game;
    }

    public boolean hasNextMove(State s) {
        return !game.isTerminal(s);
    }

    public Action nextMove(State s) {
        if (game.isPlayerMax(s)) {
            return argmax(s);
        } else {
            return argmin(s);
        }
    }

    private Action argmax(State s) {
        return combiMax(s).arg;
    }

    private Action argmin(State s) {
        return combiMin(s).arg;
    }

    private int max(State s) {
        return combiMax(s).val;
    }

    private int min(State s) {
        return combiMin(s).val;
    }

    private Comparison<Integer, Action> combiMax(State s) {
        if (game.isTerminal(s)) {
            return new Comparison<>(game.utility(s), null);
        }

        int max = Integer.MIN_VALUE;
        Action argmax = null;

        for (Action a : game.actions(s)) {
            int v = min(game.apply(a, s));

            if (v > max) {
                max = v;
                argmax = a;
            }
        }

        return new Comparison<>(max, argmax);
    }

    private Comparison<Integer, Action> combiMin(State s) {
        if (game.isTerminal(s)) {
            return new Comparison<>(game.utility(s), null);
        }

        int min = Integer.MAX_VALUE;
        Action argmin = null;

        for (Action a : game.actions(s)) {
            int v = max(game.apply(a, s));

            if (v < min) {
                min = v;
                argmin = a;
            }
        }

        return new Comparison<>(min, argmin);
    }
}