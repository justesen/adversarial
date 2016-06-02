package adv.tictactoe;

import adv.entities.Action;
import adv.entities.Player;

public class TTTAction implements Action {

    public adv.tictactoe.Cell cell;
    public Player player;

    public TTTAction(adv.tictactoe.Cell cell, Player player) {
        this.cell = cell;
        this.player = player;
    }
}
