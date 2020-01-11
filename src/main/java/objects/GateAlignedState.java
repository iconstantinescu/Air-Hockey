package objects;

public class GateAlignedState implements PuckState{

    @Override
    public void executeBehavior(Puck puck, float screenWidth, float screenHeight) {
        gateBehaviour(puck, puck.getScoreBoard(), screenWidth, screenHeight);
    }

    /**
     * Update the Puck and the score according to the behaviour when in line with gate.
     * @param scoreBoard The scoreBoard to be updated
     */
    public void gateBehaviour(Puck puck, ScoreBoard scoreBoard, float screenWidth, float screenHeight) {
        // Add point to second player if puck goes past the left gate
        if (puck.getposX() + puck.getRadius() < 0) {
            puck.setposX(screenWidth / 2);
            puck.setposY(screenHeight / 2);
            puck.setDeltaX(0);
            puck.setDeltaY(0);
            scoreBoard.pointP2();
        }

        // Add point to first player if puck goes past the right gate
        if (puck.getposX() - puck.getRadius() >= screenWidth) {
            puck.setposX(screenWidth / 2);
            puck.setposY(screenHeight / 2);
            puck.setDeltaX(0);
            puck.setDeltaY(0);
            scoreBoard.pointP1();
        }
    }
}
