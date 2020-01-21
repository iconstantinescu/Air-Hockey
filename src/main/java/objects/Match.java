package objects;

import com.badlogic.gdx.Gdx;
import utilities.InformationDrawer;

public class Match {
    private transient Pusher pusher1;
    private transient Pusher pusher2;
    private transient Puck puck;
    public transient ScoreBoard scoreBoard;
    private transient InformationDrawer informationDrawer;


    public Match() {
        // Set pusher 1 and 2 positions
        pusher1 = new Pusher(Gdx.graphics.getWidth() / 4f,
                Gdx.graphics.getHeight() / 2f, 40);
        pusher2 = new Pusher(Gdx.graphics.getWidth() * (3f / 4f),
                Gdx.graphics.getHeight() / 2f, 40);

        scoreBoard = new ScoreBoard();
        float rand = (Math.random() < 0.5) ? 1f : -1f;
        puck = new Puck(Gdx.graphics.getWidth() / 2
                + 100 * rand, Gdx.graphics.getHeight() / 2, 15, 0, 0,
                scoreBoard);

        informationDrawer = new InformationDrawer();

    }

    public void updateMatch() {
        // DRAW THE PUCK OR GAME OVER
        if (scoreBoard.isGameOver()) {
            // DRAW TOP SCORES
            scoreBoard.drawTopScores(5,
                    Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() - 150);
            // GO BACK TO MENU IF ENTER IS PRESSED
            informationDrawer.waitForEnter();
        } else {
            // CALCULATE THE POSITIONS OF THE PUCK
            updatePuck();

            checkPushersReset();

            // CHANGE PUSHER1 POSITION ACCORDING TO KEYBOARD INPUT
            updatePusher(pusher1, true);
            // CHANGE PUSHER2 POSITION ACCORDING TO KEYBOARD INPUT
            updatePusher(pusher2, false);
        }
    }

    /**
     * Method that checks if the pusher positions need to be reset.
     */
    public void checkPushersReset() {
        if (Pusher.resetPusher) {
            pusher1.setPosX(Gdx.graphics.getWidth() / 4f);
            pusher1.setPosY(Gdx.graphics.getHeight() / 2f);
            pusher2.setPosX(Gdx.graphics.getWidth() * (3f / 4f));
            pusher2.setPosY(Gdx.graphics.getHeight() / 2f);
            Pusher.resetPusher = !Pusher.resetPusher;
        }
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

        pusher.drawGameObject();
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
        puck.drawGameObject();
    }




}
