import java.util.Scanner;
import java.util.Random;

class Game {
    // creating 3x3 array
    static char[][] board; // declaring

    public Game() // using constructor so that during object creation only obj gets intialized
    {
        board = new char[3][3]; // now 3 x 3 array is ready
        initializeboard();
    }

    /*
     * the char board created will have default null value, so now that board will
     * be
     * replaced or initialized with SPACE ' '
     */

    void initializeboard() {
        for (int i = 0; i < board.length; i++) // row i
        {
            for (int j = 0; j < board[i].length; j++) // col j
            {
                board[i][j] = ' ';
            }
        }
    }

    // now displaying the board
    static void dispboard() {
        System.out.println("-------------"); // before entering row

        for (int i = 0; i < board.length; i++) // enters row and attches | before printing char
        {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) // enters col
            {
                System.out.print(board[i][j] + " | "); // space for char to print and attaching |
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // once board is ready,place the mark ('x' or 'o')
    static void placemark(int row, int col, char mark) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) // before placing mark making sure row and col has valid
                                                          // boundary
        {
            board[row][col] = mark;
        } else {
            System.out.println("Invalid Position");
        }
    }

    // checking col , row and diagonal wins // (board[0][j] != ' ')1st char
    // shouldn't be empty is the first condition and then proceed with the rule of
    // the game
    static boolean check_col_Win() {
        for (int j = 0; j <= 2; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    static boolean check_row_Win() {
        for (int i = 0; i <= 2; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    static boolean check_diag_Win() {
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]
                || board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    static boolean chech_draw() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

// inheritance
abstract class Player {
    String name;
    char mark;

    abstract void make_Move();

    boolean is_ValidMove(int row, int col) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            if (Game.board[row][col] == ' ') {
                return true;
            }
        }
        return false;
    }
}

class HumanPlayer extends Player {
    HumanPlayer(String name, char mark) // constructor
    {
        this.name = name;
        this.mark = mark;
    }

    void make_Move() {
        int row;
        int col;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("enter row and col");
            row = sc.nextInt();
            col = sc.nextInt();
        } while (!is_ValidMove(row, col));
        Game.placemark(row, col, mark);

    }
}

class AI_Player extends Player {
    AI_Player(String name, char mark) // constructor
    {
        this.name = name;
        this.mark = mark;
    }

    void make_Move() {
        Random rand = new Random();
        int row;
        int col;
        do {
            System.out.println("enter row and col");
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (!is_ValidMove(row, col));
        Game.placemark(row, col, mark);
    }
}

public class TicTacToe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        do {
            Game g = new Game(); // Game object
            HumanPlayer Person_1 = new HumanPlayer("Mahe", 'X');
            AI_Player Person_2 = new AI_Player("AI", 'O');
            Player cp;
            cp = Person_1;

            while (true) {
                System.out.println(cp.name + "'s " + "turn ");
                cp.make_Move();// after move display and then check the condition win or draw
                Game.dispboard();
                if (Game.check_col_Win() || Game.check_row_Win() || Game.check_diag_Win()) {
                    System.out.println(cp.name + " wins the game");
                    break;
                } else if (Game.chech_draw()) {
                    System.out.println("It's a draw");
                    break;
                } else {
                    if (cp == Person_1) {
                        cp = Person_2;
                    } else {
                        cp = Person_1;
                    }
                }
            }

            System.out.println("Would u like to play again? (yes/no)");
            String playAgain = sc.nextLine().trim();
            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        } while (true);

        System.out.println("Game over.");
        sc.close();
    }
}
