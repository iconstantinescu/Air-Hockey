package client;

import java.util.List;

/**
 * Interface for methods that consume the GameDetails object.
 */
public interface UserGameTracker {

    boolean updateUserStats(User user);

    boolean saveGame(int userId1, int userId2, int score1, int score2);

    List<GameDetails> getGameHistory(int userId, int size);

}
