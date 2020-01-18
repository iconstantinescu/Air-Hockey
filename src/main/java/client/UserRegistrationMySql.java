package client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class that contains the methods required to register a new user in the database.
 */
public class UserRegistrationMySql extends DatabaseControllerMySql implements UserRegistration {

    public UserRegistrationMySql(Connection conn) {
        super(conn);
    }

    /**
     * Method to insert a new user details into database.
     * @param username username used for login
     * @param password password used for login
     * @param nickname name displayed in game
     * @return true if the new user was created successfully or false otherwise
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public boolean createNewUser(String username, String password, String nickname) {

        boolean created = false;
        try {

            String query = "insert into user_data (username, password, salt, nickname)"
                    + "values (?,?,?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            String hashedPwd = BcryptHashing.hashPasswordWithGeneratedSalt(password);
            String salt = BcryptHashing.getSalt();

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPwd);
            preparedStatement.setString(3, salt);
            preparedStatement.setString(4, nickname);

            preparedStatement.execute();

            created = true;

        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return created;
    }

}
