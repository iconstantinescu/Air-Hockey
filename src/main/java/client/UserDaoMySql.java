package client;

public class UserDaoMySql implements UserDao{


    @Override
    public User authenticate(String username, String password) {
        if (username.equals("") || password.equals("")) {
            System.out.println("empty field");
            return null;
        }
        UserAuthentication userAuthentication = new UserAuthenticationMySql(new ConnectionFactory());
        return userAuthentication.authenticate(username, password);
    }

    @Override
    public boolean createNewUser(String username, String password, String nickname) {
        UserRegistrationMySql userRegistration = new UserRegistrationMySql(new ConnectionFactory());
        return userRegistration.createNewUser(username, password, nickname);
    }

    @Override
    public boolean updateUser(String nickname, long points, int gamesWon, int gamesLost) {
        return false;
    }

    @Override
    public boolean deleteUser(int userId) {
        return false;
    }

    @Override
    public boolean saveGame(int userId1, int userId2, int score1, int score2) {
        return false;
    }

    @Override
    public boolean getGameHistory(int userId) {
        return false;
    }

    @Override
    public boolean getLeaderboardPosition(int userId) {
        return false;
    }

}
