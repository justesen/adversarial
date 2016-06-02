package adv.tictactoe;

import adv.entities.Game;
import adv.entities.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TicTacToe implements Game<adv.tictactoe.TTTState, adv.tictactoe.TTTAction> {

    @Override
    public List<adv.tictactoe.TTTAction> actions(adv.tictactoe.TTTState s) {
        return s.cells().stream()
                .filter(c -> c.isFree())
                .map(c -> new adv.tictactoe.TTTAction(c, s.currentPlayer()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public boolean isPlayerMax(adv.tictactoe.TTTState s) {
        return s.currentPlayer() == Player.MAX;
    }

    @Override
    public boolean isPlayerMin(adv.tictactoe.TTTState s) {
        return !isPlayerMax(s);
    }

    @Override
    public adv.tictactoe.TTTState apply(adv.tictactoe.TTTAction a, adv.tictactoe.TTTState s) {
        return new adv.tictactoe.TTTState(s, a);
    }

    @Override
    public boolean isTerminal(adv.tictactoe.TTTState s) {
        return s.isWinning() || s.isBoardFull();
    }

    @Override
    public int utility(adv.tictactoe.TTTState s) {
        if (s.isWinning() && s.currentPlayer() == Player.MIN) {
            return +1;
        } else if (s.isWinning() && s.currentPlayer() == Player.MAX) {
            return -1;
        } else {
            return 0;
        }
    }
}
