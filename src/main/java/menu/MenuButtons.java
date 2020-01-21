package menu;

import static java.lang.System.exit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.Render;

public class MenuButtons {
    private transient SpriteBatch homeBatch;
    private transient SpriteBatch playBatch;
    private transient SpriteBatch scoresBatch;
    private transient SpriteBatch detailsBatch;
    private transient SpriteBatch quitBatch;
    private transient Texture home;
    private transient Texture play;
    private transient Texture scores;
    private transient Texture details;
    private transient Texture quit;
    private transient Sprite homeSprite;
    private transient Sprite playSprite;
    private transient Sprite scoresSprite;
    private transient Sprite detailsSprite;
    private transient Sprite quitSprite;
    private transient boolean showScores;
    private transient boolean showDetails;
    private static final int offSetX = 150;
    private static final int offSetY = 110;

    /**
     * The constructor of the MenuButtons which initializes all the
     * sprtes of the menu buttons.
     */
    public MenuButtons() {
        homeBatch = new SpriteBatch();
        playBatch = new SpriteBatch();
        scoresBatch = new SpriteBatch();
        detailsBatch = new SpriteBatch();
        quitBatch = new SpriteBatch();

        home = new Texture("media/home.png");
        homeSprite = new Sprite(home);
        homeSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        homeSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3);
        play = new Texture("media/play.png");
        playSprite = new Sprite(play);
        playSprite.setPosition(Gdx.graphics.getWidth() / 2 - offSetX,
                Gdx.graphics.getHeight() / 2 + offSetY);
        scores = new Texture("media/scores.png");
        scoresSprite = new Sprite(scores);
        scoresSprite.setPosition(Gdx.graphics.getWidth() / 2 - offSetX,
                Gdx.graphics.getHeight() / 2);
        details = new Texture("media/details.png");
        detailsSprite = new Sprite(details);
        detailsSprite.setPosition(Gdx.graphics.getWidth() / 2 - offSetX,
                Gdx.graphics.getHeight() / 2 - offSetY);
        quit = new Texture("media/quit.png");
        quitSprite = new Sprite(quit);
        quitSprite.setPosition(Gdx.graphics.getWidth() / 2 - offSetX,
                Gdx.graphics.getHeight() / 2 - offSetY * 2);

        showScores = false;
        showDetails = false;
    }

    /**
     * Sets the home sprite and batch, to show the home screen once this method is called.
     * This method will be called in render as long as you are on the menu screen.
     * At start of the method the batch will begin to draw and at the end it will dispose.
     *
     * @param sprite the sprite to draw
     * @param batch  the batch to render
     */
    public static void setHomeScreen(Sprite sprite, SpriteBatch batch) {
        batch.begin();
        batch.draw(sprite, sprite.getX() - sprite.getWidth() / 2,
                sprite.getY() - sprite.getHeight() / 3, sprite.getWidth(),
                sprite.getHeight(), sprite.getWidth(),
                sprite.getHeight(), sprite.getScaleX(),
                sprite.getScaleY(), sprite.getRotation() / 2);
        batch.end();
    }

    /**
     * Method for drawing all the buttons.
     */
    public void setButtons() {
        setHomeScreen(homeSprite, homeBatch);
        setButton(playSprite, playBatch);
        setButton(scoresSprite, scoresBatch);
        setButton(detailsSprite, detailsBatch);
        setButton(quitSprite, quitBatch);
    }

    /**
     * Method checking the hovering of the mouse on the Menu buttons.
     * @param renderMenu The renderMenu strategy used.
     */
    public void buttonsHover(RenderMenu renderMenu) {
        if (inRange(scoresSprite) && Gdx.input.justTouched()) {
            showScores = !showScores;
            renderMenu.leaderboard = Render.leaderboardDao.getLeaderboard(10);
        }
        if (showScores) {
            setText(renderMenu.leaderboardString, Gdx.graphics.getWidth() / 2 + offSetX,
                    Gdx.graphics.getHeight() - offSetX);
        }

        if (inRange(playSprite) && Gdx.input.justTouched() && Render.secondAuthentication) {
            Render.changeGameStrategy(Render.ApplicationStrategy.GAME);
        } else if (inRange(playSprite) && Gdx.input.justTouched()) {
            Render.changeGameStrategy(Render.ApplicationStrategy.LOGIN);
        }
        if (inRange(detailsSprite) && Gdx.input.justTouched()) {
            showDetails = !showDetails;
        }
        if (showDetails) {
            renderMenu.drawDetails(Gdx.graphics.getWidth() / 2 - 4 * offSetX,
                    Gdx.graphics.getHeight() - offSetX);
        }
        if (inRange(quitSprite) && Gdx.input.justTouched()) {
            exit(0);
        }
    }

    /**
     * Sets the sprite and batch of the button.
     * This method will be called in render as long as you are on the menu screen.
     * At start of the method the batch will begin to draw and at the end it will dispose.
     * If the mouse is in the range of the button, it will turn sky blue.
     *
     * @param sprite the sprite to draw
     * @param batch  the batch to render
     */
    public static void setButton(Sprite sprite, SpriteBatch batch) {
        batch.begin();
        batch.draw(sprite, sprite.getX(), sprite.getY());

        if (inRange(sprite)) {
            batch.setColor(Color.SKY);
        } else {
            batch.setColor(Color.WHITE);
        }
        batch.end();
    }

    /**
     * Check if the mouse is inside of a sprite. If it is, return true.
     * Else return false.
     *
     * @param sprite the sprite to get the range from
     * @return true if the mouse is inside of the sprite
     */
    public static boolean inRange(Sprite sprite) {
        return sprite.getBoundingRectangle().contains(Gdx.input.getX(),
                Gdx.graphics.getHeight() - Gdx.input.getY());
    }

    /**
     * Draw text on the screen.
     * @param str Text to display on screen
     * @param posX The x coordinate of the text
     * @param posY The y coordinate of the text
     */
    public void setText(String str, float posX, float posY) {
        BitmapFont font = new BitmapFont();
        homeBatch.begin();
        font.setColor(0,0,0,1);
        font.getData().setScale(3);
        font.draw(homeBatch, str, posX, posY);
        homeBatch.end();
    }

    /**
     * The method that disposes of all the sprite batches.
     */
    public void dispose() {
        homeBatch.dispose();
        playBatch.dispose();
        scoresBatch.dispose();
        detailsBatch.dispose();
        quitBatch.dispose();
        home.dispose();
        play.dispose();
        scores.dispose();
        details.dispose();
        quit.dispose();
    }
}
