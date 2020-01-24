package client;

import java.sql.Connection;

/**
 * Every class that connects to the database and executes a query should extend this class.
 * It contains the connection object required for a connection with the database.
 */
abstract class AbstractDatabaseInteraction {

    transient Connection conn;

    /**
     * The connection object is injected in this class in order to improve testability.
     */
    AbstractDatabaseInteraction(Connection connection) {
        this.conn = connection;
    }

}
