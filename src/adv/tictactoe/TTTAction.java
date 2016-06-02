package adv.tictactoe;

import adv.entities.Action;
import adv.entities.Player;

public class TTTAction implements Action {

    public final Cell cell;
    public final Player player;

    public TTTAction(Cell cell, Player player) {
        this.cell = cell;
        this.player = player;
    }
}
