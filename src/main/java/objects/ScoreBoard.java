package objects;

import client.Leaderboard;
import client.LeaderboardEntry;
import com.badlogic.gdx.Gdx;
import game.Render;
import utilities.InformationDrawer;

/**
 * Class for keeping track of the player scores.
 */
public class ScoreBoard {

    private transient int player1Score;
    private transient int player2Score;
    private transient InformationDrawer informationDrawer;

    public transient Leaderboard leaderboard;
    public transient boolean matchUploaded = false;
    private static final short endScore = 5;

    public ScoreBoard() {
        this.player1Score = 0;
        this.player2Score = 0;
    }

    public ScoreBoard(int player1Score, int player2Score) {
        this.player1Score = player1Score;
        this.player2Score = player2Score;
    }

    /**
     * Score board constructor.
     * @param player1Score score of player 1
     * @param player2Score score of player 2
     */
    public ScoreBoard(int player1Score, int player2Score, InformationDrawer informationDrawer) {
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        this.informationDrawer = informationDrawer;
    }

    /**
     * Method returning whether the game is over or not.
     * @return True if it is over, False otherwise
     */
    public boolean isGameOver() {
        return player1Score == 5 || player2Score == 5;
    }

    /**
     * Method returning the winner between Player1 and Player2.
     * @return True for Player 1, false for Player 2
     */
    public boolean getWinner() {
        return player1Score == endScore;
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
        if (player1Score == endScore) {
            matchUploaded = uploadMatch(matchUploaded);
        }
    }

    /**
     * Method that increments the points of Player2.
     */
    public void pointP2() {
        player2Score++;
        if (player2Score == endScore) {
            matchUploaded = uploadMatch(matchUploaded);
        }
    }

    /**
     * Method that uploads the match into the match history of the database,
     * and adds points to the winner of the game.
     */
    public boolean uploadMatch(boolean matchUploaded) {
        if (matchUploaded == false) {
            int addPoints = 10 * Math.abs(getPlayer1Score()
                    - getPlayer2Score());

            // ADD POINTS TO WINNER
            if (getWinner()) {
                Render.user1.addPoints(addPoints);
                Render.user1.addWonGame();
                Render.user2.addLostGame();
                Render.userDao.updateUser(Render.user1);
            } else {
                Render.user2.addPoints(addPoints);
                Render.user2.addWonGame();
                Render.user1.addLostGame();
                Render.userDao.updateUser(Render.user2);
            }

            // SAVE GAME INTO HISTORY
            return Render.userDao.saveGame(
                    Render.user1.getUserID(),
                    Render.user2.getUserID(),
                    getPlayer1Score(),
                    getPlayer2Score());

        }
        return true;
    }

    /**
     * Method for Drawing the Top 5 Scores.
     * @param posX The x coordinate of the first score
     * @param posY The y coordinate of the first score
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void drawTopScores(int size, float posX, float posY,
                              float screenWidth, float screenHeight) {
        if (leaderboard == null) {
            leaderboard = Render.leaderboardDao.getLeaderboard(size);
        }

        informationDrawer.drawText("Player " + winnerNumber() + " Won", (screenWidth / 2) - 150,
                screenHeight - 100, 4);



        int i = 1;
        for (LeaderboardEntry entry : leaderboard.getLeaderboardList()) {

            informationDrawer.drawText(i + ". " + entry.getNickname() + " " + entry.getPoints(),
                    posX, posY, 4);

            posY -= 50;
            i++;
        }


        informationDrawer.drawText("Press ENTER to go back to menu", posX - 250, posY - 100, 4);
    }

    /**
     * Get the winner number (either 1 or 2).
     */
    public int winnerNumber() {
        if (getWinner()) {
            return 1;
        }
        return 2;
    }

}
