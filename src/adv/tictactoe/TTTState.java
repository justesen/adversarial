package adv.tictactoe;

import adv.entities.Player;
import adv.entities.State;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class TTTState implements State {
    private static final int SIZE = 3;
    private final Value[][] board;
    private final Player player;

    public TTTState() {
        player = Player.MAX;
        board = new Value[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = Value.EMPTY;
            }
        }
    }

    TTTState(TTTState s, TTTAction a) {
        player = (s.player == Player.MAX ? Player.MIN : Player.MAX);
        board = new Value[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(s.board[i], 0, board[i], 0, SIZE);
        }

        board[a.row][a.col] = (a.player == Player.MAX ? Value.X : Value.O);
    }

    Player currentPlayer() {
        return player;
    }

    Collection<Cell> cells() {
        Collection<Cell> cells = new LinkedList<>();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells.add(new adv.tictactoe.Cell(i, j, board[i][j]));
            }
        }
        return cells;
    }

    boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == Value.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean isWinning() {
        return // Check rows
                winning(board[0][0], board[0][1], board[0][2])
                        || winning(board[2][0], board[2][1], board[2][2])
                        || winning(board[1][0], board[1][1], board[1][2])

                        // Check columns
                        || winning(board[0][0], board[1][0], board[2][0])
                        || winning(board[0][1], board[1][1], board[2][1])
                        || winning(board[0][2], board[1][2], board[2][2])

                        // Check diagonals
                        || winning(board[0][0], board[1][1], board[2][2])
                        || winning(board[2][0], board[1][1], board[0][2]);
    }

    private boolean winning(Value v1, Value v2, Value v3) {
        return v1 != Value.EMPTY && v1 == v2 && v2 == v3;
    }

    @Override
    public String toString() {
        String s = "  a b c\n";

        for (int i = 0; i < SIZE; i++) {
            s = s + (i + 1) + " ";

            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == Value.X) {
                    s = s + "X ";
                } else if (board[i][j] == Value.O) {
                    s = s + "O ";
                } else {
                    s = s + "  ";
                }
            }
            s = s + "\n";
        }

        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TTTState s = (TTTState) o;

        return Arrays.deepEquals(board, s.board) && player == s.player;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board) * 31 + player.hashCode();
    }
}
