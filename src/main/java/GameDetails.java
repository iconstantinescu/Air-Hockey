import java.sql.Timestamp;

public class GameDetails {

    private String username1;
    private String username2;
    private int scoreUser1;
    private int scoreUser2;
    private Timestamp timestamp;

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
