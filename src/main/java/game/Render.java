package game;

import client.User;
import com.badlogic.gdx.ApplicationAdapter;
import menu.RenderLogin;
import menu.RenderMenu;

/**
 * The render method is the one passed to the Game class such
 * that the render method can be called in a loop.
 */
public class Render extends ApplicationAdapter {

    public static int userID1;
    public static int userID2;
    public static boolean secondAuthentication;

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
        renderStrategy = new RenderLogin();
        this.userID1 = -1;
        this.userID2 = -1;
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
        }
    }

}