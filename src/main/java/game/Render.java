package game;

import com.badlogic.gdx.ApplicationAdapter;
import menu.RenderLogin;
import menu.RenderMenu;

/**
 * The render method is the one passed to the Game class such
 * that the render method can be called in a loop.
 */
public class Render extends ApplicationAdapter {

    /**
     *  An enumeration of the possible states of the application.
     */
    public enum GameState {
        LOGIN,
        MENU,
        GAME
    }

    protected static GameState gameState;
    private static Renderer renderer;

    /**
     * This method is only being run once, initializing the application.
     */
    @Override
    public void create() {
        gameState = GameState.GAME;

        renderer = new RenderLogin();

    }

    /**
     * The render method is being invoked in a loot.
     */
    @Override
    public void render() {
        renderer.run();
    }

    /**
     * Method that changes the state of the Game.
     * @param newGameState The New Game State
     */
    public static void changeGameState(GameState newGameState) {
        gameState = newGameState;
        switch (gameState) {
            case LOGIN:
                renderer = new RenderLogin();
                break;
            case MENU:
                renderer = new RenderMenu();
                break;
            case GAME:
                renderer = new RenderGame();
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
        if (renderer != null) {
            renderer.dispose();
        }
    }

}