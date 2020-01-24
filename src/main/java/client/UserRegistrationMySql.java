package client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utilities.BcryptHashing;

/**
 * Class that contains the methods required to register a new user in the database.
 * MySql implementation of the UserRegistration interface.
 */
public class UserRegistrationMySql extends AbstractDatabaseInteraction implements UserRegistration {

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
    public boolean createNewUser(String username, String password, String nickname) {

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

            return preparedStatement.execute();

        } catch (SQLException e) {
            return false;
        }

    }

}
