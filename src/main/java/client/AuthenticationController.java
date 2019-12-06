package client;

import java.sql.SQLException;

public class AuthenticationController extends DatabaseController {

    public AuthenticationController(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    /**
     * Get the salt used for the specific user.
     * @param username The nickname of the user
     * @return The salt
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


        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }
        return "";
    }

    /**
     * Methods that checks if the username and password match with the ones in the database.
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
            System.out.println(hashedPwd);

            ps.setString(1, username);
            ps.setString(2, hashedPwd);

            rs = ps.executeQuery();
            rs.next();

            if (rs.getInt(1) == expectedCount) {
                valid = true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }

        return valid;
    }
}