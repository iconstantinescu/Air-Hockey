package objects;

/**
 * This class implements the behaviour of the Puck when is is
 * inside the Gate Range.
 */
public class GateAlignedState implements PuckState {

    /**
     * Execute the specific behaviour of the Puck when it is inside the
     * Gate Range.
     * @param puck The specific Puck object
     * @param screenWidth Width of the game screen
     * @param screenHeight Height of the game screen
     */
    @Override
    public void executeBehavior(Puck puck, float screenWidth, float screenHeight) {
        gateBehaviour(puck, puck.getScoreBoard(), screenWidth, screenHeight);
    }

    /**
     * Update the Puck and the score according to the behaviour when in line with gate.
     * @param scoreBoard The scoreBoard to be updated
     */
    public void gateBehaviour(Puck puck, ScoreBoard scoreBoard,
                              float screenWidth, float screenHeight) {
        // Add point to second player if puck goes past the left gate
        if (puck.getposX() + puck.getRadius() < 0) {
            puck.resetPuck(screenWidth / 2 - 100, screenHeight / 2 + 8);
            scoreBoard.pointP2();
        }

        // Add point to first player if puck goes past the right gate
        if (puck.getposX() - puck.getRadius() >= screenWidth) {
            puck.resetPuck(screenWidth / 2 + 100, screenHeight / 2 + 8);
            scoreBoard.pointP1();
        }
    }
}
