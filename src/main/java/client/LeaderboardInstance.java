package client;

public class LeaderboardInstance {

    private String nickname;
    private long points;
    private int gamesPlayes;

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

    public int getGamesPlayes() {
        return gamesPlayes;
    }

    public void setGamesPlayes(int gamesPlayes) {
        this.gamesPlayes = gamesPlayes;
    }
}
