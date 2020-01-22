package client;

import java.sql.Connection;

abstract class AbstractDatabaseInteraction {

    /**
     * The connection object is injected in this class in order to improve testability.
     */
    protected transient Connection conn;

    public AbstractDatabaseInteraction(Connection connection) {
        this.conn = connection;
    }

}
