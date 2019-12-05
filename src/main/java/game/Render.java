package game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Render extends ApplicationAdapter {

    enum GameState {
        LOGIN,
        MENU,
        GAME
    }

    protected static GameState gameState;
    private static Renderer renderer;
    private static SpriteBatch homeBatch;
    private static SpriteBatch playBatch;
    private static SpriteBatch scoresBatch;
    private static SpriteBatch quitBatch;

    private static Texture home;
    private static Texture play;
    private static Texture scores;
    private static Texture quit;

    private static Sprite homeSprite;
    private static Sprite playSprite;
    private static Sprite scoresSprite;
    private static Sprite quitSprite;

    @Override
    public void create() {


        homeBatch = new SpriteBatch();
        playBatch = new SpriteBatch();
        scoresBatch = new SpriteBatch();
        quitBatch = new SpriteBatch();

        home = new Texture("home.png");
        homeSprite = new Sprite(home);
        homeSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        play = new Texture("play.png");
        playSprite = new Sprite(play);
        playSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        playSprite.setPosition(playSprite.getX(),
                playSprite.getY() + playSprite.getHeight() / 2);
        scores = new Texture("scores.png");
        scoresSprite = new Sprite(scores);
        scoresSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        quit = new Texture("quit.png");
        quitSprite = new Sprite(quit);
        quitSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        quitSprite.setPosition(quitSprite.getX(),
                quitSprite.getY() - quitSprite.getHeight() / 2);

        gameState = GameState.GAME;

        renderer = new RenderGame();

    }

    @Override
    public void render() {
        renderer.run();
    }

    /**
     * Change Game State.
     * @param newGameState The New Game State
     */
    public void changeGameState(GameState newGameState) {
        gameState = newGameState;
        switch (gameState) {
            case LOGIN:
                break;
            case MENU:
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
        homeBatch.dispose();
        playBatch.dispose();
        scoresBatch.dispose();
        quitBatch.dispose();
        home.dispose();
        play.dispose();
        scores.dispose();
        quit.dispose();
        //                img.dispose();
        //                shape.dispose();
        //                hitSound.dispose();
    }

}