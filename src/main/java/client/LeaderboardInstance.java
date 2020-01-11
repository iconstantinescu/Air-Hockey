package client;

public class LeaderboardInstance {

    private String nickname;
    private long points;

    /**
     * Class constructor.
     * @param nickname user nickname
     * @param points the amount of points
     */
    public LeaderboardInstance(String nickname, long points) {
        this.nickname = nickname;
        this.points = points;
    }

    /**
     * Empty Constructor.
     */
    public LeaderboardInstance(){

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
