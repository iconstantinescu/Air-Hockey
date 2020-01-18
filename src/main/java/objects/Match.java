package objects;

import com.badlogic.gdx.Gdx;
import org.lwjgl.util.vector.Vector2f;

public class Match {
    private transient Pusher pusher1;
    private transient Pusher pusher2;
    private transient Vector2f pusher1Reset;
    private transient Vector2f pusher2Reset;
    private transient Puck puck;
    private transient ScoreBoard scoreBoard;

    public Match() {
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
    }

    public void updateMatch() {
        updatePuck();

        checkPushersReset();

        // CHANGE PUSHER1 POSITION ACCORDING TO KEYBOARD INPUT
        updatePusher(pusher1, true);
        // CHANGE PUSHER2 POSITION ACCORDING TO KEYBOARD INPUT
        updatePusher(pusher2, false);


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
     * This method changes the puck position according to the rules of Air Hockey.
     */
    public void updatePuck() {
        // Collision between Pusher 1 and the puck
        pusher1.checkAndExecuteCollision(puck);

        // Check and execute collision between Pusher 2 and Puck
        pusher2.checkAndExecuteCollision(puck);

        puck.translate(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public Pusher getPusher1() {
        return pusher1;
    }

    public Pusher getPusher2() {
        return pusher2;
    }

    public Vector2f getPusher1Reset() {
        return pusher1Reset;
    }

    public Vector2f getPusher2Reset() {
        return pusher2Reset;
    }

    public Puck getPuck() {
        return puck;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
}
