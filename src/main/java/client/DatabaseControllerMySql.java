package client;

import java.sql.Connection;

/**
 * Abstract class that includes the basic fields and methods
 * used by all other classes that connect to the database.
 * The purpose of the class is to improve modularity and code reuse.
 */
abstract class DatabaseControllerMySql {

    /**
     * The connection object is injected in this class in order to improve testability.
     */
    protected transient Connection conn;

    public DatabaseControllerMySql(Connection connection) {
        this.conn = connection;
    }

}
