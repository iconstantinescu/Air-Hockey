package utilities;

import client.*;
import game.Render;
import objects.ScoreBoard;

public class DatabaseInteraction {

    /**
     * Method that uploads the match into the match history of the database,
     * and adds points to the winner of the game.
     */
    public boolean uploadMatch(boolean matchUploaded, ScoreBoard scoreBoard) {
        if (matchUploaded == false) {
            int addPoints = 10 * Math.abs(scoreBoard.getPlayer1Score()
                    - scoreBoard.getPlayer2Score());

            // ADD POINTS TO WINNER
            if (scoreBoard.getWinner()) {
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
                    scoreBoard.getPlayer1Score(),
                    scoreBoard.getPlayer2Score());

        }
        return true;
    }

    /**
     * Method for Drawing the Top 5 Scores.
     * @param posX The x coordinate of the first score
     * @param posY The y coordinate of the first score
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void drawTopScores(Leaderboard leaderboard, InformationDrawer informationDrawer,
                              int size, float posX, float posY) {
        if (leaderboard == null) {
            leaderboard = Render.leaderboardDao.getLeaderboard(size);
        }

        if (leaderboard != null) {

            int i = 1;
            for (LeaderboardEntry entry : leaderboard.getLeaderboardList()) {

                informationDrawer.drawText(i + ". " + entry.getNickname() + " " + entry.getPoints(),
                        posX, posY, 4);

                posY -= 50;
                i++;
            }
        }

        informationDrawer.drawText("Press ENTER to go back to menu", posX - 250, posY - 100, 4);
    }



}
