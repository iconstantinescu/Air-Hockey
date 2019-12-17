package menu;

import static java.lang.System.exit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.Render;
import game.Renderer;

/**
 * The specific renderer of the Game Menu.
 */
public class RenderMenu implements Renderer {
    private transient SpriteBatch homeBatch;
    private transient SpriteBatch playBatch;
    private transient SpriteBatch scoresBatch;
    private transient SpriteBatch quitBatch;
    private transient Texture home;
    private transient Texture play;
    private transient Texture scores;
    private transient Texture quit;
    private transient Sprite homeSprite;
    private transient Sprite playSprite;
    private transient Sprite scoresSprite;
    private transient Sprite quitSprite;

    /**
     * Set everything in this class.
     */
    public RenderMenu() {
        homeBatch = new SpriteBatch();
        playBatch = new SpriteBatch();
        scoresBatch = new SpriteBatch();
        quitBatch = new SpriteBatch();

        home = new Texture("media/home.png");
        homeSprite = new Sprite(home);
        homeSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        homeSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3);
        play = new Texture("media/play.png");
        playSprite = new Sprite(play);
        playSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        playSprite.setPosition(playSprite.getX(),
                playSprite.getY() + playSprite.getHeight() / 2);
        scores = new Texture("media/scores.png");
        scoresSprite = new Sprite(scores);
        scoresSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        quit = new Texture("media/quit.png");
        quitSprite = new Sprite(quit);
        quitSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        quitSprite.setPosition(quitSprite.getX(),
                quitSprite.getY() - quitSprite.getHeight() / 2);
    }

    /**
     * Run the menu for in the render.
     */
    public void run() {
        setHome(homeSprite, homeBatch);
        setPlay(playSprite, playBatch);
        setScores(scoresSprite, scoresBatch);
        setQuit(quitSprite, quitBatch);
        if (playPressed(playSprite, playBatch)) {
            Render.changeGameState(Render.GameState.GAME);
        }
    }

    /**
     * Dispose everything of the menu on closing.
     */
    public void dispose() {
        homeBatch.dispose();
        playBatch.dispose();
        scoresBatch.dispose();
        quitBatch.dispose();
        home.dispose();
        play.dispose();
        scores.dispose();
        quit.dispose();
    }

    /**
     * Sets the home sprite and batch.
     *
     * @param sprite the sprite to draw
     * @param batch  the batch to render
     */
    public static void setHome(Sprite sprite, SpriteBatch batch) {
        batch.begin();
        batch.draw(sprite, sprite.getX() - sprite.getWidth() / 2,
                sprite.getY() - sprite.getHeight() / 3, sprite.getWidth(),
                sprite.getHeight(), sprite.getWidth(),
                sprite.getHeight(), sprite.getScaleX(),
                sprite.getScaleY(), sprite.getRotation() / 2);
        batch.end();
    }

    /**
     * Sets the play sprite and batch.
     *
     * @param sprite the sprite to draw
     * @param batch  the batch to render
     */
    public static void setPlay(Sprite sprite, SpriteBatch batch) {
        batch.begin();
        batch.draw(sprite, sprite.getX() - sprite.getWidth() / 2,
                sprite.getY() + sprite.getHeight() / 2);

        if (Gdx.input.getX() > (sprite.getX() - sprite.getWidth() / 2)
                && Gdx.input.getX() < (sprite.getX()
                + sprite.getWidth() / 2)
                && Gdx.graphics.getHeight() - Gdx.input.getY()
                > (sprite.getY() + sprite.getHeight() / 2)
                && Gdx.graphics.getHeight() - Gdx.input.getY()
                < (sprite.getY() + sprite.getHeight() * 3 / 2)
        ) {
            batch.setColor(Color.SKY);
        } else {
            batch.setColor(Color.WHITE);
        }
        batch.end();
    }

    /**
     * When 'play' is pressed, return true, else return false.
     *
     * @param sprite the sprite to draw
     * @param batch  the batch to render
     * @return a boolean whether play is pressed
     */
    public static boolean playPressed(Sprite sprite, SpriteBatch batch) {
        if (Gdx.input.justTouched() && Gdx.input.getX() > (sprite.getX() - sprite.getWidth() / 2)
                && Gdx.input.getX() < (sprite.getX()
                + sprite.getWidth() / 2)
                && Gdx.graphics.getHeight() - Gdx.input.getY()
                > (sprite.getY() + sprite.getHeight() / 2)
                && Gdx.graphics.getHeight() - Gdx.input.getY()
                < (sprite.getY() + sprite.getHeight() * 3 / 2)
        ) {
            return true;
        }
        return false;
    }

    /**
     * Sets the scores sprite and batch.
     *
     * @param sprite the sprite to draw
     * @param batch  the batch to render
     */
    public static void setScores(Sprite sprite, SpriteBatch batch) {
        batch.begin();
        batch.draw(sprite, sprite.getX() - sprite.getWidth() / 2,
                sprite.getY() - sprite.getHeight() / 2);

        if (Gdx.input.getX() > (sprite.getX() - sprite.getWidth() / 2)
                && Gdx.input.getX() < (sprite.getX() + sprite.getWidth() / 2)
                && Gdx.input.getY() > (sprite.getY() - sprite.getHeight() / 2)
                && Gdx.input.getY() < (sprite.getY() + sprite.getHeight() / 2)
        ) {
            batch.setColor(Color.SKY);
        } else {
            batch.setColor(Color.WHITE);
        }
        batch.end();
    }

    /**
     * Sets the quit sprite and batch.
     *
     * @param sprite the sprite to draw
     * @param batch  the batch to render
     */
    public static void setQuit(Sprite sprite, SpriteBatch batch) {
        batch.begin();
        batch.draw(sprite, sprite.getX() - sprite.getWidth() / 2,
                sprite.getY() - sprite.getHeight() * 2);

        if (Gdx.input.getX()
                > (sprite.getX() - sprite.getWidth() / 2)
                && Gdx.input.getX() < (sprite.getX() + sprite.getWidth() / 2)
                && Gdx.graphics.getHeight() - Gdx.input.getY()
                > (sprite.getY() - sprite.getHeight() * 3 / 2)
                && Gdx.graphics.getHeight() - Gdx.input.getY()
                < (sprite.getY() - sprite.getHeight() / 2)
        ) {
            batch.setColor(Color.SKY);
        } else {
            batch.setColor(Color.WHITE);
        }

        if (Gdx.input.justTouched() && Gdx.input.getX()
                > (sprite.getX() - sprite.getWidth() / 2)
                && Gdx.input.getX() < (sprite.getX() + sprite.getWidth() / 2)
                && Gdx.graphics.getHeight() - Gdx.input.getY()
                > (sprite.getY() - sprite.getHeight() * 3 / 2)
                && Gdx.graphics.getHeight() - Gdx.input.getY()
                < (sprite.getY() - sprite.getHeight() / 2)) {
            exit(0);
        }
        batch.end();
    }


}
