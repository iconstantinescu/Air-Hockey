package client;

import java.util.Objects;

/**
 * Class that encapsulates user details from database in a user object.
 */
public class User {

    private transient int userID;
    private String nickname;
    private transient long points;
    private transient int gamesLost;
    private transient int gamesWon;

    /**
     * Constructor for client.User class.
     * @param userID id of the user (as stored in database)
     * @param nickname the name of the user that appears in game
     * @param points number of points the user has
     * @param gamesLost number of games lost
     * @param gamesWon number of games won
     */
    public User(int userID, String nickname, long points, int gamesLost, int gamesWon) {
        this.userID = userID;
        this.points = points;
        this.nickname = nickname;
        this.gamesLost = gamesLost;
        this.gamesWon = gamesWon;
    }

    /**
     * Constructor that initializes everything with "0" or empty values.
     */
    public User() {
        this.userID = 0;
        this.points = 0;
        this.nickname = "";
        this.gamesLost = 0;
        this.gamesWon = 0;

    }

    public int getUserID() {
        return userID;
    }

    public void setUserId(int userID) {
        this.userID = userID;
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

    public void addPoints(long newPoints) {
        this.points += newPoints;
    }

    public int getNumOfLostGames() {
        return  this.gamesLost;
    }

    public void addNumOfLostGames(int gamesLost) {
        this.gamesLost += gamesLost;
    }

    public int getNumOfWonGames() {
        return  this.gamesWon;
    }

    public void addNumOfWonGames(int gamesWon) {
        this.gamesWon += gamesWon;
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
        User user = (User) o;
        return userID == user.userID
                && points == user.points
                && gamesLost == user.gamesLost
                && gamesWon == user.gamesWon
                && Objects.equals(nickname, user.nickname);
    }

}
