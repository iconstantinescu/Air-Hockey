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
     * Use the DriverManager to create a new connection.
     * @param url The url of the database.
     * @return New Connection
     * @throws SQLException An SQl Error can occur while trying to initialize the connection.
     */
    Connection createConnection(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }

}
