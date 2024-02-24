import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char EMPTY = ' ';
    private static final char PLAYER1 = 'X';
    private static final char PLAYER2 = 'O';

    private char[][] board;

    public ConnectFour() {
        board = new char[ROWS][COLUMNS];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    private void printBoard() {
        System.out.println("  1   2   3   4   5   6   7");
        System.out.println("  ----------------------------");
        for (int row = 0; row < ROWS; row++) {
            System.out.print(row + 1);
            System.out.print(" |");
            for (int col = 0; col < COLUMNS; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
            System.out.println("  ----------------------------");
        }

    }

    private boolean dropPiece(int column, char player) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][column] == EMPTY) {
                board[row][column] = player;
                return true;
            }
        }
        return false;
    }

    private boolean checkWin(char player) {
        // Check horizontal
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] == player && board[row][col + 1] == player && board[row][col + 2] == player
                        && board[row][col + 3] == player) {
                    return true;
                }
            }
        }

        // Check vertical
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row <= ROWS - 4; row++) {
                if (board[row][col] == player && board[row + 1][col] == player && board[row + 2][col] == player
                        && board[row + 3][col] == player) {
                    return true;
                }
            }
        }

        // Check diagonal (from bottom left to top right)
        for (int row = ROWS - 1; row >= 3; row--) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] == player && board[row - 1][col + 1] == player && board[row - 2][col + 2] == player
                        && board[row - 3][col + 3] == player) {
                    return true;
                }
            }
        }

        // Check diagonal (from top left to bottom right)
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] == player && board[row + 1][col + 1] == player && board[row + 2][col + 2] == player
                        && board[row + 3][col + 3] == player) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int col = 0; col < COLUMNS; col++) {
            if (board[0][col] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        char currentPlayer = PLAYER1;

        while (true) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", choose a column (1-" + COLUMNS + "):");
            int column = scanner.nextInt() - 1;

            if (column < 0 || column >= COLUMNS) {
                System.out.println("Invalid column. Please choose again.");
                continue;
            }

            if (dropPiece(column, currentPlayer)) {
                if (checkWin(currentPlayer)) {
                    printBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    break;
                }

                currentPlayer = (currentPlayer == PLAYER1) ? PLAYER2 : PLAYER1;
            } else {
                System.out.println("Column is full. Please choose again.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        game.play();
    }
}
