package adv.tictactoe;

import adv.entities.Action;
import adv.entities.Player;

class TTTAction implements Action {
    final int row;
    final int col;
    final Player player;

    TTTAction(int row, int col, Player player) {
        this.row = row;
        this.col = col;
        this.player = player;
    }

    @Override
    public String toString() {
        return "Place " + (player == Player.MAX ? "X" : "O") + " on (" + ((char) (col + 'a')) + ", " + (row + 1) + ")";
    }
}
