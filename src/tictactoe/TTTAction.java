package tictactoe;

import entities.Action;
import entities.Player;

public class TTTAction implements Action {

    public Cell cell;
    public Player player;

    public TTTAction(Cell cell, Player player) {
        this.cell = cell;
        this.player = player;
    }
}
