package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Factory class used to create new jdbc connections given the url path to a database.
 * The purpose of this class is to encapsulate the static DriverManager.getConnection method
 * in order to improve testability (this allows us to mock this object and call the method)
 *
 */
public class ConnectionFactory {

    /**
     * This is the URL that is used to access the database used for our application.
     * The URL contains the user and password
     */
    private static final String URL = "jdbc:mysql://"
            + "projects-db.ewi.tudelft.nl/"
            + "projects_sem-project-42"
            + "?sslmode=require"
            + "&user=pu_f1X2r36H3wklF"
            + "&password=ZL3Al54DKc66"
            + "&serverTimezone=UCT";

    /**
     * Use the DriverManager to create a new connection.
     * @return New Connection
     * @throws SQLException An SQl Error can occur while trying to initialize the connection.
     */
    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

}
