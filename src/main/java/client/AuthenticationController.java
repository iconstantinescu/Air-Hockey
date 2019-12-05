package client;

import java.sql.SQLException;

public class AuthenticationController extends DatabaseController {

    public AuthenticationController(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    /**
     * Methods that checks if the username and password match with the ones in the database.
     * @param username username provided via login form
     * @param password password provided via login form
     * @return true if the user and password match and false otherwise
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public boolean authenticate(String username, String password) {

        boolean valid = false;
        final int EXPECTED_COUNT = 1;

        try {

            conn = connectionFactory.createConnection(URL);
            String query = "select count(user_id) from user_data"
                    + " where username = ? and password = ?";
            ps = conn.prepareStatement(query);

            String hashedPwd = BcryptHashing.hashPassword(password);

            ps.setString(1, username);
            ps.setString(2, hashedPwd);

            rs = ps.executeQuery();
            rs.next();

            if (rs.getInt(1) == EXPECTED_COUNT) {
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
