package client;

import java.util.List;

public interface UserDao {

    User authenticate(String username, String password);

    boolean createNewUser(String username, String password, String nickname);

    boolean updateUser(User user);

    boolean saveGame(int userId1, int userId2, int score1, int score2);

    List<GameDetails> getGameHistory(int userId, int size);

}
