package client;

public class LeaderboardEntry {

    private String nickname;
    private long points;

    /**
     * Class constructor.
     * @param nickname user nickname
     * @param points the amount of points
     */
    public LeaderboardEntry(String nickname, long points) {
        this.nickname = nickname;
        this.points = points;
    }

    /**
     * Empty Constructor.
     */
    public LeaderboardEntry(){
        this.nickname = "";
        this.points = 0;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

}
