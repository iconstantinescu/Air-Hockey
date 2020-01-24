package game;

import client.JdbcSingleton;
import client.LeaderboardDao;
import client.LeaderboardDaoMySql;
import client.User;
import client.UserDao;
import client.UserDaoMySql;
import com.badlogic.gdx.ApplicationAdapter;

import java.sql.Connection;
import java.sql.SQLException;
import menu.RenderLogin;
import menu.RenderMenu;

/**
 * The render method is the one passed to the Game class such
 * that the render method can be called in a loop.
 */
public class Render extends ApplicationAdapter {

    public static User user1;
    public static User user2;
    public static UserDao userDao;
    public static LeaderboardDao leaderboardDao;
    public static boolean secondAuthentication;
    private transient Connection connection;

    /**
     *  An enumeration of the possible render strategies of the application.
     */
    public enum ApplicationStrategy {
        LOGIN,
        MENU,
        GAME
    }

    private static ApplicationStrategy applicationStrategy;
    private static RenderStrategy renderStrategy;

    /**
     * This method is only being run once, initializing the application.
     */
    @Override
    public void create() {
        // Initialize the login as the first login.

        this.user1 = new User();
        this.user2 = new User();

        try {

            // class that creates the jdbc connection that will be reused
            JdbcSingleton jdbcSingleton = JdbcSingleton.getInstance();
            connection = jdbcSingleton.getConnection();
            this.userDao = new UserDaoMySql(connection);
            this.leaderboardDao = new LeaderboardDaoMySql(connection);
        } catch (SQLException e) {
            System.out.println("Something went wrong when connecting to the database");
            dispose(); //application will close if we cannot connect
        }

        renderStrategy = new RenderLogin();
        this.secondAuthentication = false;

    }

    /**
     * The render method is being invoked in a loot.
     */
    @Override
    public void render() {
        renderStrategy.run();
    }

    /**
     * Method that changes the render strategy of the application.
     * @param newApplicationStrategy The New Render Strategy
     */
    public static void changeGameStrategy(ApplicationStrategy newApplicationStrategy) {
        applicationStrategy = newApplicationStrategy;
        switch (applicationStrategy) {
            case LOGIN:
                renderStrategy = new RenderLogin();
                break;
            case MENU:
                if (renderStrategy instanceof RenderLogin) {
                    renderStrategy.dispose();
                }
                renderStrategy = new RenderMenu();
                break;
            case GAME:
                renderStrategy = new RenderGame();
                break;
            default:
                break;
        }

    }

    /**
     * Method being used to dispose of all the created object
     * before closing the application.
     */
    @Override
    public void dispose() {
        if (renderStrategy != null) {
            renderStrategy.dispose();

            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Could not close the connection");
                dispose(); //try again
            }

        }
    }

}