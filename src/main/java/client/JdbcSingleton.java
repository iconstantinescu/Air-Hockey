package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class used to create the jdbc connection given the url path to a database.
 * The class can be created just once.
 * <p>
 * Example usage:
 * JdbcSingleton jdbcSingleton = JdbcSingleton.getInstance();
 * Connection conn = jdbcSingleton.getConnection();
 * </p>
 */
public class JdbcSingleton {


    private transient Connection conn;
    private static JdbcSingleton instance;

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
     * The private constructor does not allow the class to be instantiated.
     * It will also create a new database connection with jdbc DriverManager.
     * @throws SQLException if could not create the connection with the database
     */
    private JdbcSingleton() throws SQLException {
        conn = DriverManager.getConnection(URL);
    }

    /**
     * Global point of access for the JdbcSingleton class.
     * @return the unique JdbcSingleton object
     * @throws SQLException if could not create the connection
     */
    public static JdbcSingleton getInstance() throws SQLException {
        if (instance == null) {
            instance = new JdbcSingleton();
        }
        return instance;
    }

    /**
     * @return The Connection object that was already created.
     */
    public Connection getConnection() {
        return conn;
    }

}
