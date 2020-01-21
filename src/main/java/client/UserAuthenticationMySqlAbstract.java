package client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.BcryptHashing;

/**
 * Class that contains the methods required to authenticate the user with the database.
 */
public class UserAuthenticationMySqlAbstract extends AbstractDatabase
        implements  UserAuthentication {

    public UserAuthenticationMySqlAbstract(Connection conn) {
        super(conn);
    }

    /**
     * Get the salt used for the specific user.
     * The salt is a string added to the hash password to improve security.
     * @param username The nickname of the user
     * @return The salt stored in the database as a String
     */
    // We are actually closing the resultSet
    // but the PMD does not see this for some reason
    @SuppressWarnings("PMD.CloseResource")
    private String getSalt(String username) {
        try {

            String query = "select salt from user_data"
                    + " where username = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getString("salt");


        } catch (SQLException e) {
            return "";
        }
    }

    /**
     * Method that checks if the given username and password match with the ones in the database.
     * @param username username provided via login form
     * @param password password provided via login form
     * @return true if the user and password match and false otherwise
     */
    // We are actually closing the resultSet
    // but the PMD does not see this for some reason
    @SuppressWarnings("PMD.CloseResource")
    public User authenticate(String username, String password) {

        try {

            //This should be after the connection creation
            //and before this method's preparedStatement initialization
            String salt = getSalt(username);

            String query = "select user_id, nickname, points, games_won, games_lost "
                    + " from user_data"
                    + " where username = ? and password = ?";


            PreparedStatement ps = conn.prepareStatement(query);

            String hashedPwd = BcryptHashing.hashPasswordWithGivenSalt(password, salt);

            ps.setString(1, username);
            ps.setString(2, hashedPwd);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setNickname(rs.getString("nickname"));
                user.addPoints(rs.getLong("points"));
                user.addNumOfWonGames(rs.getInt("games_won"));
                user.addNumOfLostGames(rs.getInt("games_lost"));
                return user;
            }

            rs.close();
            return null;

        } catch (SQLException e) {
            return null;
        }
    }

}
