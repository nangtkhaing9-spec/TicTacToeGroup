
package dk.easv.tictactoe.bll;

/**
 *
 * @author EASV
 */
public class GameBoard implements IGameBoard
{
    private int[][] board;       // 3x3 grid: -1 = Empty, 0 = Player 0, 1 = Player 1
    private int currentPlayer;  // Tracks whose turn it is (0 or 1)
    private int winner;         // Stores the winner's ID, or -1 if no winner yet

    public GameBoard() {
        newGame();
    }

    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    public int getNextPlayer()
    {

        return currentPlayer; // returns current player
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is successfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row)
    {
        // 1. Reject move if game is over OR if selected spot is already taken
        if (isGameOver() || board[row][col] != -1) {
            return false;
        }

        // 2. Mark selected spot with current player's ID
        board[row][col] = currentPlayer;

        // 3. Check all 3 Horizontal Rows for 3-in-a-row
        for (int r = 0; r < 3; r++) {
            if (board[r][0] != -1 && board[r][0] == board[r][1] && board[r][1] == board[r][2]) {
                winner = board[r][0];
            }
        }

        // 4. Check all 3 Vertical Columns for 3-in-a-row
        for (int c = 0; c < 3; c++) {
            if (board[0][c] != -1 && board[0][c] == board[1][c] && board[1][c] == board[2][c]) {
                winner = board[0][c];
            }
        }

        // 5. Check Main Diagonal (top-left to bottom-right)
        if (board[0][0] != -1 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            winner = board[0][0];
        }

        // 6. Check Anti-Diagonal (top-right to bottom-left)
        if (board[0][2] != -1 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            winner = board[0][2];
        }

        // 7. If nobody won yet, switch turns (Player 0 -> Player 1, or Player 1 -> Player 0)
        if (winner == -1) {
            if (currentPlayer == 0) {
                currentPlayer = 1;
            } else {
                currentPlayer = 0;
            }
        }

        return true; // Move executed successfully
    }


    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will return false.
     */
    public boolean isGameOver()
    {
        //TODO Implement this method
        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        //TODO Implement this method
        return -1;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        board = new int[][] {
                {-1, -1, -1},
                {-1, -1, -1},
                {-1, -1, -1}
        };
        currentPlayer = 0;
        winner = -1;
        }
    }
