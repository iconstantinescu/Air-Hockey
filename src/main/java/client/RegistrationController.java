package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationController extends DatabaseController {

    public RegistrationController(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    public boolean createNewUser(String username, String password, String nickname) {

        boolean created = false;
        try {

            conn = connectionFactory.createConnection(URL);

            String query = "insert into user_data (username, password, nickname)"
                    + "values (?,?,?)";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3,nickname);

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
