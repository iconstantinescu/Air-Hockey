package client;

public interface UserDao {

    User getUserByUsernameAndPassword();
    User createNewUser();
    boolean updateNickname();
    boolean updatePassword();
    boolean updatePoints();
    boolean deleteUser();
    boolean saveGame();
    boolean getGameHistory();
    boolean getGamesWon();
    boolean getGamesLost();
    boolean getLeaderboardPosition();

}
