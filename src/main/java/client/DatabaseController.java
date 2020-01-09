package client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Abstract class that includes the basic fields and methods
 * used by all other classes that connect to the database.
 * The purpose of the class is to improve modularity and code reuse.
 */
abstract class DatabaseController {

    /**
     * The connectionFactory object is injected in this class in order to improve testability.
     */
    protected transient ConnectionFactory connectionFactory;

    protected transient Connection conn;
    protected transient PreparedStatement ps;
    protected transient ResultSet rs;

    /**
     * This is the URL that is used to access the database used for our application.
     * The URL contains the user and password
     */
    protected static final String URL = "jdbc:mysql://"
            + "projects-db.ewi.tudelft.nl/"
            + "projects_sem-project-42"
            + "?sslmode=require"
            + "&user=pu_f1X2r36H3wklF"
            + "&password=ZL3Al54DKc66"
            + "&serverTimezone=UCT";

    /**
     * Constructor of the class.
     * @param connectionFactory the connectionFactory object used to create
     *                          new database connections.
     */
    public DatabaseController(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * This method closes the database connections after use.
     * The purpose of the method is to eliminate code duplication.
     */
    protected void closeConnections() {
        try {
            //System.out.println(conn.isClosed());
            conn.close();
            System.out.println(conn.isClosed());
        } catch (Exception e) {
            System.out.println(e.toString());

        }
    }
}
