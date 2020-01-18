package game;

import static com.badlogic.gdx.Input.Keys.ENTER;

import client.Leaderboard;
import client.LeaderboardEntry;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

import objects.*;
import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;

/**
 * The specific Main renderer inheriting from the general Renderer.
 */
public class RenderGame implements RenderStrategy {
    private transient ObjectDrawer objectDrawer;
    private transient InformationDrawer informationDrawer;
    private transient Pusher pusher1;
    private transient Pusher pusher2;
    private transient Vector2f pusher1Reset;
    private transient Vector2f pusher2Reset;
    private transient Puck puck;
    private transient ScoreBoard scoreBoard;
    private static final Music backSound =
            Gdx.audio.newMusic(Gdx.files.internal("media/song.wav"));
    private transient boolean matchUploaded;
    private transient Leaderboard leaderboard;
    private static final Music hitSound =
            Gdx.audio.newMusic(Gdx.files.internal("media/hit.wav"));
    private static final Music goalSound =
            Gdx.audio.newMusic(Gdx.files.internal("media/airhorn.wav"));

    /**
     * Constructor for the Renderer.
     */
    public RenderGame() {
        // Set pusher 1 and 2 reset positions
        pusher1Reset = new Vector2f(Gdx.graphics.getWidth() / 4f,
                Gdx.graphics.getHeight() / 2f);
        pusher2Reset = new Vector2f(Gdx.graphics.getWidth() * (3f / 4f),
                Gdx.graphics.getHeight() / 2f);

        // Set pusher 1 and 2 positions
        pusher1 = new Pusher(pusher1Reset.x, pusher1Reset.y, 40);
        pusher2 = new Pusher(pusher2Reset.x, pusher2Reset.y, 40);

        scoreBoard = new ScoreBoard();
        float rand = (Math.random() < 0.5) ? 1f : -1f;
        puck = new Puck(Gdx.graphics.getWidth() / 2
                + 100 * rand, Gdx.graphics.getHeight() / 2, 15, 0, 0,
                scoreBoard);

        // Initiate the Background Sound
        backSound.setLooping(true);
        backSound.play();

        matchUploaded = false;

        objectDrawer = new ObjectDrawer("media/field.png");
        informationDrawer = new InformationDrawer();

    }

    /**
     * Method that runs the rendering of the game.
     */
    public void run() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // DRAWS THE BOARD
        objectDrawer.drawBackground();

        // DRAW THE PUCK OR GAME OVER
        if (scoreBoard.isGameOver()) {
            informationDrawer.drawText("Player " + winnerNumber() + " Won", (Gdx.graphics.getWidth() / 2) - 150,
                    Gdx.graphics.getHeight() - 100, 4);
            // DRAW TOP SCORES
            uploadMatch();
            drawTopScores(Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() - 150);
            // GO BACK TO MENU IF ENTER IS PRESSED
            waitForEnter();
        } else {
            // CALCULATE THE POSITIONS OF THE PUCK
            updatePuck();
            objectDrawer.drawGameObject(puck);
        }

        checkPushersReset();

        // CHANGE PUSHER1 POSITION ACCORDING TO KEYBOARD INPUT
        updatePusher(pusher1, true);
        // CHANGE PUSHER2 POSITION ACCORDING TO KEYBOARD INPUT
        updatePusher(pusher2, false);

        // DRAW PUSHER 1
        objectDrawer.drawGameObject(pusher1);
        // DRAW PUSHER 2
        objectDrawer.drawGameObject(pusher2);

        // PLAY SOUNDS
        if (GateAlignedState.playGoalSound) {
            goalSound.play();
            GateAlignedState.playGoalSound = false;
        }
        if (Pusher.playHitSound) {
            hitSound.play();
            Pusher.playHitSound = false;
        }

        // RENDER THE SCORE
        informationDrawer.drawText(scoreBoard.getPlayer1Score() + " : "
                        + scoreBoard.getPlayer2Score(), (Gdx.graphics.getWidth() / 2) - 50,
                Gdx.graphics.getHeight() - 20, 4);

        objectDrawer.drawWallsAndGates(new ArrayList<Wall>());
        informationDrawer.drawText("Player 1", 80, Gdx.graphics.getHeight() - 20,2);
        informationDrawer.drawText("Player 2", Gdx.graphics.getWidth() - 180, Gdx.graphics.getHeight() - 20, 2);
    }

    /**
     * Method that uploads the match into the match history of the database,
     * and adds points to the winner of the game.
     */
    public void uploadMatch() {
        if (matchUploaded == false) {
            int addPoints = 10 * Math.abs(scoreBoard.getPlayer1Score()
                    - scoreBoard.getPlayer2Score());

            // ADD POINTS TO WINNER
            if (scoreBoard.getWinner()) {
                Render.user1.addPoints(addPoints);
                Render.user1.addWonGame();
                Render.user2.addLostGame();
                Render.userDao.updateUser(Render.user1);
            } else {
                Render.user2.addPoints(addPoints);
                Render.user2.addWonGame();
                Render.user1.addLostGame();
                Render.userDao.updateUser(Render.user2);
            }

            // SAVE GAME INTO HISTORY
            matchUploaded = Render.userDao.saveGame(
                    Render.user1.getUserID(),
                    Render.user2.getUserID(),
                    scoreBoard.getPlayer1Score(),
                    scoreBoard.getPlayer2Score());

        }
    }

    /**
     * Method that checks if the pusher positions need to be reset.
     */
    public void checkPushersReset() {
        if (Pusher.resetPusher) {
            pusher1.setPosX(pusher1Reset.x);
            pusher1.setPosY(pusher1Reset.y);
            pusher2.setPosX(pusher2Reset.x);
            pusher2.setPosY(pusher2Reset.y);
            Pusher.resetPusher = !Pusher.resetPusher;
        }
    }

    /**
     * Get the winner number (either 1 or 2).
     */
    public int winnerNumber() {
        if (scoreBoard.getWinner()) {
            return 1;
        }
        return 2;
    }

    /**
     * Method for Drawing the Top 5 Scores.
     * @param posX The x coordinate of the first score
     * @param posY The y coordinate of the first score
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void drawTopScores(float posX, float posY) {
        if (leaderboard == null) {
            leaderboard = Render.leaderboardDao.getLeaderboard(5);
        }

        if (leaderboard != null) {

            int i = 1;
            for (LeaderboardEntry entry : leaderboard.getLeaderboardList()) {

                informationDrawer.drawText(i + ". " + entry.getNickname() + " " + entry.getPoints(),
                        posX, posY, 4);

                posY -= 50;
                i++;
            }
        }

        informationDrawer.drawText("Press ENTER to go back to menu", posX - 250, posY - 100, 4);
    }

    /**
     * Moves the pusher object according to the user input.
     * @param pusher The pusher object
     * @param pusherId The id of the Pusher (either Pusher 1 - Left, or Pusher 2 - Right)
     */
    public void updatePusher(Pusher pusher, boolean pusherId) {
        int[] keyCodes;
        if (pusherId) {
            // USE WASD
            keyCodes = new int[]{51, 47, 29, 32};
        } else {
            // USE IJKL
            keyCodes = new int[]{37, 39, 38, 40};
        }
        boolean[] restricts = new boolean[4];

        pusher.restrictMovementOnWall(pusherId, restricts,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (Gdx.input.isKeyPressed(keyCodes[0]) && !restricts[0]) {
            pusher.setPosY(pusher.getPosY() + 6);
        }
        if (Gdx.input.isKeyPressed(keyCodes[1]) && !restricts[2]) {
            pusher.setPosY(pusher.getPosY() - 6);
        }
        if (Gdx.input.isKeyPressed(keyCodes[2]) && !restricts[1]) {
            pusher.setPosX(pusher.getPosX() - 6);
        }
        if (Gdx.input.isKeyPressed(keyCodes[3]) && !restricts[3]) {
            pusher.setPosX(pusher.getPosX() + 6);
        }
    }

    /**
     * Waits for Enter to be pressed to go back to the menu.
     */
    public void waitForEnter() {
        if (Gdx.input.isKeyJustPressed(ENTER)) {
            Render.changeGameStrategy(Render.ApplicationStrategy.MENU);
            backSound.dispose();
        }
    }





    /**
     * This method changes the puck position according to the rules of Air Hockey.
     */
    public void updatePuck() {
        // Collision between Pusher 1 and the puck
        pusher1.checkAndExecuteCollision(puck);

        // Check and execute collision between Pusher 2 and Puck
        pusher2.checkAndExecuteCollision(puck);

        puck.translate(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }





    /**
     * Dispose of all the initialized values.
     */
    public void dispose() {

    }
}
