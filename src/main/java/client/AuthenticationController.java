package client;

import java.sql.SQLException;

/**
 * Class that contains the methods required to authenticate the user with the database.
 */
public class AuthenticationController extends DatabaseController {

    public AuthenticationController(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    /**
     * Get the salt used for the specific user.
     * The salt is a string added to the hash password to improve security.
     * @param username The nickname of the user
     * @return The salt stored in the database as a String
     */
    public String getSalt(String username) {
        try {

            conn = connectionFactory.createConnection(URL);
            String query = "select salt from user_data"
                    + " where username = ?";
            ps = conn.prepareStatement(query);

            ps.setString(1, username);

            rs = ps.executeQuery();
            rs.next();

            return rs.getString(1);


        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
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
    public boolean authenticate(String username, String password, String salt) {

        boolean valid = false;
        final int expectedCount = 1;

        try {

            conn = connectionFactory.createConnection(URL);
            String query = "select count(user_id) from user_data"
                    + " where username = ? and password = ?";
            ps = conn.prepareStatement(query);

            String hashedPwd = BcryptHashing.hashPasswordWithSalt(password, salt);

            ps.setString(1, username);
            ps.setString(2, hashedPwd);

            rs = ps.executeQuery();
            rs.next();

            if (rs.getInt(1) == expectedCount) {
                valid = true;
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }

        return valid;
    }

    /**
     * When you authenticate get the userID of the user as stored in the database.
     * To be used for further database queries.
     * @param username the username of the player authenticating
     * @return player ID as stored in the Database
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public int getUserId(String username) {

        int userId = -1;
        try {

            conn = connectionFactory.createConnection(URL);
            String query = "select user_id from user_data"
                    + " where username = ?";
            ps = conn.prepareStatement(query);

            ps.setString(1, username);

            rs = ps.executeQuery();
            rs.next();

            userId = rs.getInt(1);

        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }

        return userId;
    }
}
