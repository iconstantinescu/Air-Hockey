import java.util.ArrayList;

public class User {

    private int userID;
    private String username;
    private long points;
    private int gamesLost;
    private int gamesWon;
    private ArrayList<GameDetails> gameHistory;

    public User(int userID, String username, long points, int gamesLost, int gamesWon) {
        this.userID = userID;
        this.points = points;
        this.username = username;
        this.gamesLost = gamesLost;
        this.gamesWon = gamesWon;
        this.gameHistory = new ArrayList<>();
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getPoints() {
        return points;
    }

    public void addPoints(int newPoints) {
        this.points += newPoints;
    }

    public ArrayList<GameDetails> getGameHistory() {
        return gameHistory;
    }

    public void addGame(GameDetails newGame) {
        this.gameHistory.add(newGame);
    }

    public void addLostGame() {
        this.gamesLost ++;
    }

    public void addWonGame() {
        this.gamesWon ++;
    }

    public int getNumOfGamesPlayed() {
        return  this.gamesLost + this.gamesWon;
    }

}
