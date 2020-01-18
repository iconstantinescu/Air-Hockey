package client;

import java.sql.Connection;
import java.util.List;

public class UserDaoMySql implements UserDao {

    private transient UserGameTracker userGameTracker;
    private transient UserRegistration userRegistration;
    private transient UserAuthentication userAuthentication;

    /**
     * The constructor that initializes the class fields with
     * the corresponding interface implementations.
     */
    public UserDaoMySql(Connection conn) {
        this.userAuthentication = new UserAuthenticationMySql(conn);
        this.userRegistration = new UserRegistrationMySql(conn);
        this.userGameTracker = new UserGameTrackerMySql(conn);
    }

    @Override
    public User authenticate(String username, String password) {
        return userAuthentication.authenticate(username, password);
    }

    @Override
    public boolean createNewUser(String username, String password, String nickname) {
        return userRegistration.createNewUser(username, password, nickname);
    }

    @Override
    public boolean updateUser(User user) {
        return userGameTracker.updateUserStats(user);
    }

    @Override
    public boolean saveGame(int userId1, int userId2, int score1, int score2) {
        return userGameTracker.saveGame(userId1, userId2, score1, score2);
    }

    @Override
    public List<GameDetails> getGameHistory(int userId, int size) {
        return userGameTracker.getGameHistory(userId, size);
    }

}
