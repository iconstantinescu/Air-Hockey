package game;

import static com.badlogic.gdx.Input.Keys.ENTER;

import client.Leaderboard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

import objects.*;
import utilities.DatabaseInteraction;
import utilities.InformationDrawer;

import java.util.ArrayList;

/**
 * The specific Main renderer inheriting from the general Renderer.
 */
public class RenderGame implements RenderStrategy {
    private transient ObjectDrawer objectDrawer;
    private transient InformationDrawer informationDrawer;
    private transient DatabaseInteraction databaseInteraction;

    private transient Match match;
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
        match = new Match();

        // Initiate the Background Sound
        backSound.setLooping(true);
        backSound.play();

        matchUploaded = false;

        objectDrawer = new ObjectDrawer("media/field.png");
        informationDrawer = new InformationDrawer();
        databaseInteraction = new DatabaseInteraction();

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
        if (match.getScoreBoard().isGameOver()) {
            informationDrawer.drawText("Player " + match.winnerNumber() + " Won", (Gdx.graphics.getWidth() / 2) - 150,
                    Gdx.graphics.getHeight() - 100, 4);
            // DRAW TOP SCORES
            matchUploaded = databaseInteraction.uploadMatch(matchUploaded, match.getScoreBoard());
            databaseInteraction.drawTopScores(leaderboard, informationDrawer, 5,
                    Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() - 150);
            // GO BACK TO MENU IF ENTER IS PRESSED
            waitForEnter();
        } else {
            // CALCULATE THE POSITIONS OF THE PUCK
            match.updateMatch();
            objectDrawer.drawGameObject(match.getPuck());
            // DRAW PUSHER 1
            objectDrawer.drawGameObject(match.getPusher1());
            // DRAW PUSHER 2
            objectDrawer.drawGameObject(match.getPusher2());

            // PLAY SOUNDS
            if (GateAlignedState.playGoalSound) {
                goalSound.play();
                GateAlignedState.playGoalSound = false;
            }
            if (Pusher.playHitSound) {
                hitSound.play();
                Pusher.playHitSound = false;
            }
        }

        // RENDER THE SCORE
        informationDrawer.drawText(match.getScoreBoard().getPlayer1Score() + " : "
                        + match.getScoreBoard().getPlayer2Score(), (Gdx.graphics.getWidth() / 2) - 50,
                Gdx.graphics.getHeight() - 20, 4);

        objectDrawer.drawWallsAndGates(new ArrayList<Wall>());
        informationDrawer.drawText("Player 1", 80, Gdx.graphics.getHeight() - 20,2);
        informationDrawer.drawText("Player 2", Gdx.graphics.getWidth() - 180, Gdx.graphics.getHeight() - 20, 2);
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
     * Dispose of all the initialized values.
     */
    public void dispose() {

    }
}
