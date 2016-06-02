package adv.tictactoe;

public class Cell {

    public final int row;
    public final int col;
    private final Value val;

    public Cell(int row, int col, Value val) {
        this.row = row;
        this.col = col;
        this.val = val;
    }

    public boolean isFree() {
        return val == Value.EMPTY;
    }
}
