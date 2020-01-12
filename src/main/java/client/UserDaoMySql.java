package client;

import java.util.List;

public class UserDaoMySql implements UserDao {

    private transient UserGameTracker userGameTracker;
    private transient UserRegistration userRegistration;
    private transient UserAuthentication userAuthentication;

    public UserDaoMySql() {
        this.userAuthentication = new UserAuthenticationMySql(new ConnectionFactory());
        this.userRegistration = new UserRegistrationMySql(new ConnectionFactory());
        this.userGameTracker = new UserGameTrackerMySql(new ConnectionFactory());
    }
    @Override
    public User authenticate(String username, String password) {
        if (username.equals("") || password.equals("")) {
            System.out.println("empty field");
            return null;
        }
        return userAuthentication.authenticate(username, password);
    }

    @Override
    public boolean createNewUser(String username, String password, String nickname) {
        if (username.equals("") || password.equals("")) {
            System.out.println("empty field");
            return false;
        }
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
    public List<GameDetails> getGameHistory(int userId) {
        return userGameTracker.getGameHistory(userId);
    }

}
