package client;

public interface UserDao {

    User authenticate(String username, String password);

    boolean createNewUser(String username, String password, String nickname);

    boolean updateUser(String nickname, long points, int gamesWon, int gamesLost);

    boolean deleteUser(int userId);

    boolean saveGame(int userId1, int userId2, int score1, int score2);

    boolean getGameHistory(int userId);

    boolean getLeaderboardPosition(int userId);

}
