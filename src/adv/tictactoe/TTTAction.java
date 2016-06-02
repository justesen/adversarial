package adv.tictactoe;

import adv.entities.Action;
import adv.entities.Player;

public class TTTAction implements Action {
    public final int row;
    public final int col;
    public final Player player;

    public TTTAction(int row, int col, Player player) {
        this.row = row;
        this.col = col;
        this.player = player;
    }
}
