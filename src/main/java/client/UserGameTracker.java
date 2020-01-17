package client;

import java.util.List;

public interface UserGameTracker {

    boolean updateUserStats(User user);

    boolean saveGame(int userId1, int userId2, int score1, int score2);

    List<GameDetails> getGameHistory(int userId);

}
