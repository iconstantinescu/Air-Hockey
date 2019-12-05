package client;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationController extends DatabaseController {

    public RegistrationController(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    /**
     * Method to insert a new user into database.
     * @param username username used for login
     * @param password password used for login
     * @param nickname name dispalyed in game
     * @return true if the new user was created successfully or false otherwise
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public boolean createNewUser(String username, String password, String nickname) {

        boolean created = false;
        try {

            conn = connectionFactory.createConnection(URL);

            String query = "insert into user_data (username, password, salt, nickname)"
                    + "values (?,?,?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            String hashedPwd = BcryptHashing.hashPassword(password);
            String salt = BcryptHashing.getSalt();

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPwd);
            preparedStatement.setString(3, salt);
            preparedStatement.setString(4, nickname);

            preparedStatement.execute();

            created = true;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.toString());
        } finally {
            closeConnections();
        }

        return created;
    }


}
