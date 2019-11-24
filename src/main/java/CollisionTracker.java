public class CollisionTracker {


    /**
     * Checking for wall collision.
     * @param screenWidth width of the screen
     * @param screenHeight height of the scree
     * @param puck puck object
     */
    public static void checkWallCollision(float screenWidth, float screenHeight, Puck puck) {
        if (puck.getposX() + puck.getRadius() >= screenWidth) {
            puck.setDeltaX(-puck.getDeltaX());
        }

        if (puck.getposY() + puck.getRadius() >= screenHeight) {
            puck.setDeltaY(-puck.getDeltaY());
        }

    }

}
