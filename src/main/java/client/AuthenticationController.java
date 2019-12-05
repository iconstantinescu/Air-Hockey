package client;

import java.sql.SQLException;

public class AuthenticationController extends DatabaseController {

    public AuthenticationController(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    public boolean authenticate(String username, String password) {

        boolean valid = false;
        try {

            conn = connectionFactory.createConnection(URL);
            String query = "select count(user_id) from user_data"
                    + " where username = ? and password = ?";
            ps = conn.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2,password);

            rs = ps.executeQuery();
            rs.next();
            if(rs.getInt(1) == 1)
                valid = true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }

        return valid;
    }
}
