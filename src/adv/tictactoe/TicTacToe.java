package adv.tictactoe;

import adv.entities.Game;
import adv.entities.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TicTacToe implements Game<TTTState, TTTAction> {

    @Override
    public List<TTTAction> actions(TTTState s) {
        return s.cells().stream()
                .filter(Cell::isFree)
                .map(c -> new TTTAction(c, s.currentPlayer()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public boolean isPlayerMax(TTTState s) {
        return s.currentPlayer() == Player.MAX;
    }

    @Override
    public TTTState apply(TTTAction a, TTTState s) {
        return new TTTState(s, a);
    }

    @Override
    public boolean isTerminal(TTTState s) {
        return s.isWinning() || s.isBoardFull();
    }

    @Override
    public int utility(TTTState s) {
        if (s.isWinning() && s.currentPlayer() == Player.MIN) {
            return +1;
        } else if (s.isWinning() && s.currentPlayer() == Player.MAX) {
            return -1;
        } else {
            return 0;
        }
    }
}
