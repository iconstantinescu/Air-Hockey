package menu;

import static java.lang.System.exit;

import client.GameDetails;
import client.Leaderboard;

import client.LeaderboardEntry;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import game.Render;
import game.RenderGame;
import game.RenderStrategy;
import java.util.List;

import objects.Puck;
import objects.ScoreBoard;



/**
 * The specific renderer of the Main Menu.
 * Here the menu screen, all buttons and button actions are created and set.
 */
public class RenderMenu implements RenderStrategy {
    private transient Skin skin;
    private transient Stage stage;
    private transient TextField nickname;
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
    private transient Puck puck;
    private transient ScoreBoard scoreBoard;
    private transient Leaderboard leaderboard;
    private transient String leaderboardString;
    private transient boolean showScores;
    private transient boolean showDetails;
    private transient RenderGame renderGame = new RenderGame();
    private static final int offSetX = 150;
    private static final int offSetY = 110;
    private transient List<GameDetails> history;
    private transient TextButton nicknameButton;

    /**
     * This is the renderer for the menu.
     * In here the following will happen:
     * Create the new sprite batches for all parts of the menu screen.
     * Assign the button variables and set the location of the field.
     */
    public RenderMenu() {
        skin = new Skin(Gdx.files.internal("assets/ui/skin/uiskin.json"));
        stage = new Stage(new ScreenViewport());

        float nicknameX = 30;
        float nicknameY = Gdx.graphics.getHeight() - 80;
        nickname = new TextField("", skin);
        nickname.setPosition(nicknameX, nicknameY);
        nickname.setHeight(40);
        nickname.setWidth(200);

        nicknameButton = new TextButton("ChangeNickname", skin, "default");
        nicknameButton.setWidth(200);
        nicknameButton.setHeight(40);

        // register button position under password and username fields on right side
        nicknameButton.setPosition(nicknameX, nicknameY - nickname.getHeight() - 10);

        nicknameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                nicknameChange();
            }
        });

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
        scoreBoard = new ScoreBoard();
        puck = new Puck(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 15, 4, 4,
                scoreBoard);

        history = Render.userDao.getGameHistory(Render.user1.getUserID());

        stage.addActor(nickname);
        stage.addActor(nicknameButton);
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Run the menu for in the main game renderer.
     * Call the methods to set the homeScreen, the playButton, the scoresButton and the QuitButton.
     * Once play is pressed, you will leave the menu and go into the Main.
     * Once quit is pressed, you will exit the application with exit code: 0
     * This means that everything went fine.
     */
    public void run() {
        setHomeScreen(homeSprite, homeBatch);
        setButton(playSprite, playBatch);
        setButton(scoresSprite, scoresBatch);
        setButton(detailsSprite, detailsBatch);
        setButton(quitSprite, quitBatch);

        updatePuckMenu();
        renderGame.drawGameObject(-1, puck.getposX(), puck.getposY(), puck.getRadius());

        if (leaderboardString == null) {
            leaderboardString = drawLeaderboard();
        }

        if (inRange(scoresSprite) && Gdx.input.justTouched()) {
            showScores = !showScores;
            leaderboard = Render.leaderboardDao.getLeaderboard(10);
        }
        if (showScores) {
            setText(leaderboardString, Gdx.graphics.getWidth() / 2 + offSetX,
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
            drawDetails(Gdx.graphics.getWidth() / 2 - 4 * offSetX,
                    Gdx.graphics.getHeight() - offSetX);
        }
        if (inRange(quitSprite) && Gdx.input.justTouched()) {
            exit(0);
        }


    }

    /**
     * Function for changing the nickname of the player in the database.
     */
    public void nicknameChange() {
        String nicknameInput = nickname.getText();

        if (nicknameInput.equals("")) {
            return;
        }

        Render.user1.setNickname(nicknameInput);
        Render.userDao.updateUser(Render.user1);
        history = Render.userDao.getGameHistory(Render.user1.getUserID());
        leaderboard = Render.leaderboardDao.getLeaderboard(10);
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
        detailsBatch.dispose();
        quitBatch.dispose();
        home.dispose();
        play.dispose();
        scores.dispose();
        details.dispose();
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
     * Method for Drawing the Top 10 Scores.
     * When you press scores on the menu screen you will be shown them.
     * Press it another time and they'll disappear.
     *
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public String drawLeaderboard() {
        if (leaderboard == null) {
            leaderboard = Render.leaderboardDao.getLeaderboard(10);
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (leaderboard != null) {
            int i = 1;
            for (LeaderboardEntry entry : leaderboard.getLeaderboardList()) {

                stringBuilder.append(i + ". " + entry.getNickname() + " "
                        + entry.getPoints() + "\n");

                i++;
            }
        }
        return stringBuilder.toString();
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
     * This method will draw the details of the current logged in player to the screen.
     * @param posX The X coordinate on the screen.
     * @param posY The Y coordinate on the screen.
     */
    public void drawDetails(float posX, float posY) {
        StringBuilder buildString = new StringBuilder("Game History:\n");


        for (int i = 0; i < 5 && i < history.size(); i++) {
            // Append first User
            buildString.append(history.get(i).getNickname1()
                    + " " + history.get(i).getScoreUser1() + " : ");
            // Append second User
            buildString.append(history.get(i).getScoreUser2()
                    + " " + history.get(i).getNickname2() + "\n");
        }

        setText("Games Played: " + Render.user1.getNumOfGamesPlayed() + "\n"
                + "Games Lost: " + Render.user1.getNumOfLostGames() + "\n"
                + "Games Won: " + Render.user1.getNumOfWonGames() + "\n"
                + "Points: " + Render.user1.getPoints() + "\n"
                + buildString.toString(), posX, posY);

        stage.draw();
    }

}
