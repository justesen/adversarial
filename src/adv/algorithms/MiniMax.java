package adv.algorithms;

import adv.entities.Action;
import adv.entities.Game;
import adv.entities.State;

public class MiniMax implements Algorithm {
    private final Game<State, Action> game;

    public MiniMax(Game<State, Action> game) {
        this.game = game;
    }

    public Action nextMove(State s) {
        if (game.isPlayerMax(s)) {
            return argmax(s);
        } else {
            return argmin(s);
        }
    }

    private Action argmax(State s) {
        return combiMax(s, Integer.MIN_VALUE, Integer.MAX_VALUE).arg;
    }

    private Action argmin(State s) {
        return combiMin(s, Integer.MIN_VALUE, Integer.MAX_VALUE).arg;
    }

    private int max(State s, int alpha, int beta) {
        return combiMax(s, alpha, beta).val;
    }

    private int min(State s, int alpha, int beta) {
        return combiMin(s, alpha, beta).val;
    }

    private Comparison combiMax(State s, int alpha, int beta) {
        if (game.isTerminal(s)) {
            return new Comparison(game.utility(s), null);
        }

        int max = Integer.MIN_VALUE;
        Action argmax = null;

        for (Action a : game.actions(s)) {
            State t = game.apply(a, s);
            int v = game.isPlayerMax(t) ? max(t, alpha, beta) : min(t, alpha, beta);

            if (v > max) {
                max = v;
                argmax = a;
            }
            if (max >= beta) {
                break;
            }
            alpha = Math.max(alpha, max);
        }

        return new Comparison(max, argmax);
    }

    private Comparison combiMin(State s, int alpha, int beta) {
        if (game.isTerminal(s)) {
            return new Comparison(game.utility(s), null);
        }

        int min = Integer.MAX_VALUE;
        Action argmin = null;

        for (Action a : game.actions(s)) {
            State t = game.apply(a, s);
            int v = game.isPlayerMax(t) ? max(t, alpha, beta) : min(t, alpha, beta);

            if (v < min) {
                min = v;
                argmin = a;
            }
            if (min <= alpha) {
                break;
            }
            beta = Math.min(beta, min);
        }

        return new Comparison(min, argmin);
    }

    private class Comparison {
        final int val;
        final Action arg;

        Comparison(int val, Action arg) {
            this.val = val;
            this.arg = arg;
        }
    }
}