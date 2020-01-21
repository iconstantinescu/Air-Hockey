package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

import objects.*;
import utilities.InformationDrawer;
import utilities.ObjectDrawer;

/**
 * The specific Main renderer inheriting from the general Renderer.
 */
public class RenderGame implements RenderStrategy {
    private transient ObjectDrawer objectDrawer;
    private transient InformationDrawer informationDrawer;


    private transient Match match;
    private static final Music backSound =
            Gdx.audio.newMusic(Gdx.files.internal("media/song.wav"));
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
        match.updateMatch();

        // PLAY SOUNDS
        if (GateAlignedState.playGoalSound) {
            goalSound.play();
            GateAlignedState.playGoalSound = false;
        }
        if (Pusher.playHitSound) {
            hitSound.play();
            Pusher.playHitSound = false;
        }

        objectDrawer.drawWallsAndGates();

        informationDrawer.drawInformation(match.scoreBoard.getPlayer1Score(), match.scoreBoard.getPlayer2Score());
    }


    /**
     * Dispose of all the initialized values.
     */
    public void dispose() {

    }
}
