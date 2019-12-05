package game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import menu.RenderLogin;
import menu.RenderMenu;

public class Render extends ApplicationAdapter {

    public enum GameState {
        LOGIN,
        MENU,
        GAME
    }

    protected static GameState gameState;
    private static Renderer renderer;

    @Override
    public void create() {



        gameState = GameState.GAME;

        renderer = new RenderLogin();

    }

    @Override
    public void render() {
        renderer.run();
    }

    /**
     * Change Game State.
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

    @Override
    public void dispose() {
        //        batch.dispose();
        if (renderer != null) {
            renderer.dispose();
        }

        //                img.dispose();
        //                shape.dispose();
        //                hitSound.dispose();
    }

}