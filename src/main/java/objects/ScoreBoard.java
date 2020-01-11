package objects;

/**
 * Class for keeping track of the player scores.
 */
public class ScoreBoard {

    private transient int player1Score;
    private transient int player2Score;

    /**
     * Score board constructor.
     * @param player1Score score of player 1
     * @param player2Score score of player 2
     */
    public ScoreBoard(int player1Score, int player2Score) {
        this.player1Score = player1Score;
        this.player2Score = player2Score;
    }

    /**
     * Method returning whether the game is over or not.
     * @return True if it is over, False otherwise
     */
    public boolean isGameOver() {
        return player1Score == 5 || player2Score == 5;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    /**
     * Method that increments the points of Player1.
     */
    public void pointP1() {
        player1Score++;
    }

    /**
     * Method that increments the points of Player2.
     */
    public void pointP2() {
        player2Score++;
    }
}
