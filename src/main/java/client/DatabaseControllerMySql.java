package client;

import java.sql.Connection;

abstract class DatabaseControllerMySql {

    /**
     * The connection object is injected in this class in order to improve testability.
     */
    protected transient Connection conn;

    public DatabaseControllerMySql(Connection connection) {
        this.conn = connection;
    }

}
