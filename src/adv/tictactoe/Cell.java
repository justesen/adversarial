package adv.tictactoe;

class Cell {
    final int row;
    final int col;
    private final Value val;

    Cell(int row, int col, Value val) {
        this.row = row;
        this.col = col;
        this.val = val;
    }

    boolean isFree() {
        return val == Value.EMPTY;
    }
}
