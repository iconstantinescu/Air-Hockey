package utilities;

import objects.Puck;

public class CollisionTracker {

    private int screenWidth;
    private int screenHeigth;

    public CollisionTracker(int screenWidth, int screenHeigth) {
        this.screenHeigth = screenHeigth;
        this.screenWidth = screenWidth;
    }

    /**
     * Checking for wall collision.
     * @param screenWidth width of the screen
     * @param screenHeight height of the scree
     * @param puck puck object
     */
    public static void checkWallCollision(float screenWidth, float screenHeight, Puck puck) {
        if (puck.getposX() + puck.getRadius()
                >= screenWidth || puck.getposX() - puck.getRadius() <= 0) {
            puck.setDeltaX(-puck.getDeltaX());
        }

        if (puck.getposY() + puck.getRadius()
                >= screenHeight || puck.getposY() - puck.getRadius() <= 0) {
            puck.setDeltaY(-puck.getDeltaY());
        }

    }

    /**
     * Method that restricts the movement of the Pusher when touching a Wall.
     * @param xPos xPos of the Pusher
     * @param yPos yPos of the Pusher
     * @param radius Radius of the Pusher
     * @param playerId The player controlling the Pusher
     * @return
     */
    public boolean[] restrictMovementOnWall(float xPos, float yPos, float radius, int playerId) {
        boolean[] restricts = new boolean[4];

        for (int i = 0; i < 4; i++) {
            restricts[i] = false;
        }

        if (yPos - radius < 0) {
            restricts[2] = true;
        }

        if (yPos + radius >= screenHeigth) {
            restricts[0] = true;
        }

        if (playerId == 1) {
            if (xPos - radius <= 0) {
                restricts[1] = true;
            }
            if (xPos + radius >= screenWidth / 2) {
                restricts[3] = true;
            }
        } else {
            if (xPos - radius <= screenWidth / 2) {
                restricts[1] = true;
            }
            if (xPos + radius >= screenWidth) {
                restricts[3] = true;
            }
        }


        return restricts;
    }

}
