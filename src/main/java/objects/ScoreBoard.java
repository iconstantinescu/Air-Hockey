package objects;

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

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public void pointP1() {
        player1Score++;
    }

    public void pointP2() {
        player2Score++;
    }
}
