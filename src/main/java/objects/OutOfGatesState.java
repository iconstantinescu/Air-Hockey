package objects;

public class OutOfGatesState implements PuckState {

    @Override
    public void executeBehavior(Puck puck, float screenWidth, float screenHeight) {
        preventOut(puck, puck.getDeltaX() + puck.getposX(), puck.getDeltaY() + puck.getposY(),
                screenWidth, screenHeight);
    }


    /**
     * This method prevents the Puck from going out of bounds.
     * @param futureX The future X position
     * @param futureY The future Y position
     */
    public void preventOut(Puck puck, float futureX, float futureY, float screenWidth, float screenHeight) {
        if (futureX + puck.getRadius() > screenWidth || futureX - puck.getRadius() < 0) {
            puck.setDeltaX(-puck.getDeltaX());
        }

        if (futureY + puck.getRadius() > screenHeight || futureY - puck.getRadius() < 0) {
            puck.setDeltaY(-puck.getDeltaY());
        }
    }
}
