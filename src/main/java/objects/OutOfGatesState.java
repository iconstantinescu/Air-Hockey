package objects;

/**
 * This class implements the behaviour of the Puck when it is
 * out of the Gate Range, when the puck has to be restricted from going
 * past the edges of the screen by inverting its movement speed variable.
 */
public class OutOfGatesState implements PuckState {

    /**
     * Execute the specific behaviour of the Puck when it is out of
     * the Gate Range.
     * @param puck The specific Puck object
     * @param screenWidth Width of the game screen
     * @param screenHeight Height of the game screen
     */
    @Override
    public void executeBehavior(Puck puck, float screenWidth, float screenHeight) {
        preventOut(puck, puck.getDeltaX() + puck.getPosX(), puck.getDeltaY() + puck.getPosY(),
                screenWidth, screenHeight);
    }


    /**
     * This method prevents the Puck from going out of bounds.
     * @param futureX The future X position
     * @param futureY The future Y position
     */
    public void preventOut(Puck puck, float futureX, float futureY,
                           float screenWidth, float screenHeight) {
        if (futureX + puck.getRadius() > screenWidth || futureX - puck.getRadius() < 0) {
            puck.setDeltaX(-puck.getDeltaX());
            Pusher.playHitSound = true;
        }

        if (futureY + puck.getRadius() > screenHeight || futureY - puck.getRadius() < 0) {
            puck.setDeltaY(-puck.getDeltaY());
            Pusher.playHitSound = true;
        }
    }
}
