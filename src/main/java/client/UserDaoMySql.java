package client;

public class UserDaoMySql implements UserDao{


    @Override
    public User getUserByUsernameAndPassword() {
        return null;
    }

    @Override
    public User createNewUser() {
        return null;
    }

    @Override
    public boolean updateNickname() {
        return false;
    }

    @Override
    public boolean updatePassword() {
        return false;
    }

    @Override
    public boolean updatePoints() {
        return false;
    }

    @Override
    public boolean deleteUser() {
        return false;
    }

    @Override
    public boolean saveGame() {
        return false;
    }

    @Override
    public boolean getGameHistory() {
        return false;
    }

    @Override
    public boolean getGamesWon() {
        return false;
    }

    @Override
    public boolean getGamesLost() {
        return false;
    }
}
