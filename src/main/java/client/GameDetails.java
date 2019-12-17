package client;

import java.sql.Timestamp;

/**
 * Class that contains the details of a single match between two players.
 */
public class GameDetails {

    /**
     * The username of the two players playing the game.
     */
    private transient String username1;
    private transient String username2;

    /**
     * The scores of the two players playing the game.
     */
    private transient int scoreUser1;
    private transient int scoreUser2;


    private transient Timestamp timestamp;

    /**
     * Constructor for client.GameDetails class.
     * @param username1 username of first player
     * @param username2 username of second player
     * @param scoreUser1 score of the first player
     * @param scoreUser2 score of the second player
     * @param timestamp timestamp of the game
     */
    public GameDetails(String username1, String username2,
                       int scoreUser1, int scoreUser2,
                       Timestamp timestamp) {

        this.scoreUser1 = scoreUser1;
        this.scoreUser2 = scoreUser2;
        this.username1 = username1;
        this.username2 = username2;
        this.timestamp = timestamp;
    }

    public String getUsername1() {
        return username1;
    }

    public String getUsername2() {
        return username2;
    }

    public int getScoreUser1() {
        return scoreUser1;
    }

    public int getScoreUser2() {
        return scoreUser2;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

}
