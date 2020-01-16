package client;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Class that contains the details of a single match between two players.
 */
public class GameDetails {

    /**
     * The username of the two players playing the game.
     */
    private transient String nickname1;
    private transient String nickname2;

    /**
     * The scores of the two players playing the game.
     */
    private transient int scoreUser1;
    private transient int scoreUser2;


    private transient Timestamp timestamp;

    /**
     * Constructor for client.GameDetails class.
     * @param nickname1 username of first player
     * @param nickname2 username of second player
     * @param scoreUser1 score of the first player
     * @param scoreUser2 score of the second player
     * @param timestamp timestamp of the game
     */
    public GameDetails(String nickname1, String nickname2,
                       int scoreUser1, int scoreUser2,
                       Timestamp timestamp) {

        this.scoreUser1 = scoreUser1;
        this.scoreUser2 = scoreUser2;
        this.nickname1 = nickname1;
        this.nickname2 = nickname2;
        this.timestamp = timestamp;

    }

    /**
     * Constructor that initializes everything with "0" values.
     */
    public GameDetails() {
        this.scoreUser1 = 0;
        this.scoreUser2 = 0;
        this.nickname1 = "";
        this.nickname2 = "";
        this.timestamp = new Timestamp(0);
    }

    public String getNickname1() {
        return nickname1;
    }

    public String getNickname2() {
        return nickname2;
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

    public void setNickname1(String nickname1) {
        this.nickname1 = nickname1;
    }

    public void setNickname2(String nickname2) {
        this.nickname2 = nickname2;
    }

    public void setScoreUser1(int scoreUser1) {
        this.scoreUser1 = scoreUser1;
    }

    public void setScoreUser2(int scoreUser2) {
        this.scoreUser2 = scoreUser2;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    //The hashCode is not needed for our application.
    //We just use the equals method.
    @SuppressWarnings("PMD.OverrideBothEqualsAndHashcode")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameDetails that = (GameDetails) o;
        return scoreUser1 == that.scoreUser1
                && scoreUser2 == that.scoreUser2
                && Objects.equals(nickname1, that.nickname1)
                && Objects.equals(nickname2, that.nickname2)
                && Objects.equals(timestamp, that.timestamp);
    }

}
