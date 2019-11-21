public class ScoreBoard {

    private transient int player1Score;
    private transient int player2Score;


    private Puck puck;

    /**
     * Score board constructor.
     * @param player1Score score of player 1
     * @param player2Score score of player 2
     * @param puck puck position
     */
    public ScoreBoard(int player1Score, int player2Score, Puck puck) {
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        this.puck = puck;
    }


    public Puck getPuck() {
        return puck;
    }

    public void setPuck(Puck puck) {
        this.puck = puck;
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
}
