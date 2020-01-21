package client;

import java.sql.Connection;

abstract class AbstractDatabase {

    /**
     * The connection object is injected in this class in order to improve testability.
     */
    protected transient Connection conn;

    public AbstractDatabase(Connection connection) {
        this.conn = connection;
    }

}
