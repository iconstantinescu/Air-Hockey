package menu;

import static java.lang.System.exit;

import client.ConnectionFactory;
import client.LeaderboardController;
import client.LeaderboardInstance;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.Render;
import game.RenderGame;
import game.Renderer;
import java.util.List;
import objects.Puck;
import objects.ScoreBoard;

/**
 * The specific renderer of the Game Menu.
 * Here the menu screen, all buttons and button actions are created and set.
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
    private transient Puck puck;
    private transient ScoreBoard scoreBoard;
    private transient ConnectionFactory connectionFactory;
    private static List<LeaderboardInstance> leaderboard;
    private transient boolean showScores;
    private transient RenderGame renderGame = new RenderGame();

    /**
     * This is the renderer for the menu.
     * In here the following will happen:
     * Create the new sprite batches for all parts of the menu screen.
     * Assign the button variables and set the location of the field.
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
        showScores = false;
        connectionFactory = new ConnectionFactory();
        scoreBoard = new ScoreBoard();
        puck = new Puck(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 15, 4, 4,
                scoreBoard);

    }

    /**
     * Run the menu for in the main game renderer.
     * Call the methods to set the homeScreen, the playButton, the scoresButton and the QuitButton.
     * Once play is pressed, you will leave the menu and go into the Game.
     * Once quit is pressed, you will exit the application with exit code: 0
     * This means that everything went fine.
     */
    public void run() {
        setHomeScreen(homeSprite, homeBatch);
        setPlayButton(playSprite, playBatch);
        setScoresButton(scoresSprite, scoresBatch);
        setQuitButton(quitSprite, quitBatch);

        updatePuckMenu();
        renderGame.drawGameObject(-1, puck.getposX(), puck.getposY(), puck.getRadius());

        if (scoresButtonPressed(scoresSprite)) {
            showScores = !showScores;
        }
        if (showScores) {
            drawLeaderboard(Gdx.graphics.getWidth() / 2 + 150, Gdx.graphics.getHeight() - 150);
        }
        if (playPressed(playSprite)) {
            Render.changeGameState(Render.GameState.GAME);
        }
        if (quitButtonPressed(quitSprite)) {
            exit(0);
        }
    }

    /**
     * This method changes the puck position according to the rules of Air Hockey.
     */
    public void updatePuckMenu() {
        puck.translateMenu(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
     * Sets the play sprite and batch.
     * Here the play button will be displayed just above the middle of the menu screen.
     * This method will be called in render as long as you are on the menu screen.
     * At start of the method the batch will begin to draw and at the end it will dispose.
     *
     * @param sprite the sprite to draw
     * @param batch  the batch to render
     */
    public static void setPlayButton(Sprite sprite, SpriteBatch batch) {
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
     * This method will be called in render as long as you are on the menu screen.
     *
     * @param sprite the sprite to draw
     * @return a boolean whether play is pressed
     */
    public static boolean playPressed(Sprite sprite) {
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
     * Here the scores button will be displayed on the middle of the menu screen.
     * This method will be called in render as long as you are on the menu screen.
     * At start of the method the batch will begin to draw and at the end it will dispose.
     *
     * @param sprite the sprite to draw
     * @param batch  the batch to render
     */
    public static void setScoresButton(Sprite sprite, SpriteBatch batch) {
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
     * This method will return true when 'scores' is pressed and false otherwise.
     * This method will be called in render as long as you are on the menu screen.
     * Once scores is pressed, the render method will make sure you will be shown the leader board.
     *
     * @param sprite the sprite to draw.
     * @return a boolean, true if 'quit' is pressed or not.
     */
    public static boolean scoresButtonPressed(Sprite sprite) {
        if (Gdx.input.justTouched() && Gdx.input.getX()
                > (sprite.getX() - sprite.getWidth() / 2)
                && Gdx.input.getX() < (sprite.getX() + sprite.getWidth() / 2)
                && Gdx.input.getY() > (sprite.getY() - sprite.getHeight() / 2)
                && Gdx.input.getY() < (sprite.getY() + sprite.getHeight() / 2)) {
            return true;
        }
        return false;
    }

    /**
     * Sets the quit sprite and batch.
     * Here the quit button is displayed on just below the middle of the menu screen.
     * This method will be called in render as long as you are on the menu screen.
     * At start of the method the batch will begin to draw and at the end it will dispose.
     *
     * @param sprite the sprite to draw
     * @param batch  the batch to render
     */
    public static void setQuitButton(Sprite sprite, SpriteBatch batch) {
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

        batch.end();
    }

    /**
     * This method will return true when 'quit' is pressed and false otherwise.
     * This method will be called in render as long as you are on the menu screen.
     * Once quit is pressed, the render method will make sure you will leave the game.
     *
     * @param sprite the sprite to draw.
     * @return a boolean, true if 'quit' is pressed or not.
     */
    public static boolean quitButtonPressed(Sprite sprite) {
        if (Gdx.input.justTouched() && Gdx.input.getX()
                > (sprite.getX() - sprite.getWidth() / 2)
                && Gdx.input.getX() < (sprite.getX() + sprite.getWidth() / 2)
                && Gdx.graphics.getHeight() - Gdx.input.getY()
                > (sprite.getY() - sprite.getHeight() * 3 / 2)
                && Gdx.graphics.getHeight() - Gdx.input.getY()
                < (sprite.getY() - sprite.getHeight() / 2)) {
            return true;
        }
        return false;
    }

    /**
     * Method for Drawing the Top 10 Scores.
     * When you press scores on the menu screen you will be shown them.
     * Press it another time and they'll disappear.
     *
     * @param posX The x coordinate of the first score
     * @param posY The y coordinate of the first score
     */
    public void drawLeaderboard(float posX, float posY) {
        if (leaderboard == null) {
            LeaderboardController leaderboardController =
                    new LeaderboardController(connectionFactory);

            leaderboard = leaderboardController.getTopTen();
        }
        for (int i = 0; i < 10; i++) {
            LeaderboardInstance score = leaderboard.get(i);
            setText((i + 1) + ". " + score.getNickname() + " " + score.getPoints(),
                    posX, posY);

            posY -= 50;
        }
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
        font.getData().setScale(4);
        font.draw(homeBatch, str, posX,
                posY);
        homeBatch.end();
    }
}
