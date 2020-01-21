package menu;



import client.GameDetails;
import client.Leaderboard;

import client.LeaderboardEntry;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import game.Render;
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
    private transient MenuButtons menuButtons;

    private transient Puck puck;
    private transient ScoreBoard scoreBoard;
    protected transient Leaderboard leaderboard;
    protected transient String leaderboardString;


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


        scoreBoard = new ScoreBoard();
        puck = new Puck(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 15, 4, 4,
                scoreBoard);

        history = Render.userDao.getGameHistory(Render.user1.getUserID(),5);

        menuButtons = new MenuButtons();

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
        menuButtons.setButtons();

        updatePuckMenu();
        puck.drawGameObject();

        if (leaderboardString == null) {
            leaderboardString = drawLeaderboard();
        }

        menuButtons.buttonsHover(this);
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
        history = Render.userDao.getGameHistory(Render.user1.getUserID(), 5);
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
        menuButtons.dispose();
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

        menuButtons.setText("Games Played: " + Render.user1.getNumOfGamesPlayed() + "\n"
                + "Games Lost: " + Render.user1.getNumOfLostGames() + "\n"
                + "Games Won: " + Render.user1.getNumOfWonGames() + "\n"
                + "Points: " + Render.user1.getPoints() + "\n"
                + buildString.toString(), posX, posY);

        stage.draw();
    }

}
