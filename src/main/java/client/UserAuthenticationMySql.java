package client;

import java.sql.SQLException;

/**
 * Class that contains the methods required to authenticate the user with the database.
 */
public class UserAuthenticationMySql extends DatabaseControllerMySql
        implements  UserAuthentication {

    public UserAuthenticationMySql(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    /**
     * Get the salt used for the specific user.
     * The salt is a string added to the hash password to improve security.
     * @param username The nickname of the user
     * @return The salt stored in the database as a String
     */
    private String getSalt(String username) {
        try {

            String query = "select salt from user_data"
                    + " where username = ?";
            ps = conn.prepareStatement(query);

            ps.setString(1, username);

            rs = ps.executeQuery();
            rs.next();

            return rs.getString(1);

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return "";
    }

    /**
     * Method that checks if the given username and password match with the ones in the database.
     * @param username username provided via login form
     * @param password password provided via login form
     * @return true if the user and password match and false otherwise
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public User authenticate(String username, String password) {

        try {

            conn = connectionFactory.createConnection(URL);
            String query = "select user_id, nickname, points, games_won, games_lost "
                    + " from user_data"
                    + " where username = ? and password = ?";
            ps = conn.prepareStatement(query);

            String salt = this.getSalt(username);
            String hashedPwd = BcryptHashing.hashPasswordWithSalt(password, salt);

            ps.setString(1, username);
            ps.setString(2, hashedPwd);

            rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setNickname(rs.getString("nickname"));
                user.addPoints(rs.getLong("points"));
                user.setNumOfWonGames(rs.getInt("games_won"));
                user.setNumOfLostGames(rs.getInt("games_lost"));
                return user;
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }

        return null;
    }

}
