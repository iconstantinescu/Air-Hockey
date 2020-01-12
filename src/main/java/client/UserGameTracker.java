package client;

public interface UserGameTracker {

    boolean updateUserStats(User user);
    boolean saveGame(int userId1, int userId2, int score1, int score2);
}
