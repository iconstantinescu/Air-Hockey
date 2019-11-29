package utilities;

public class CollisionTracker {

    private transient int screenWidth;
    private transient int screenHeigth;

    public CollisionTracker(int screenWidth, int screenHeigth) {
        this.screenHeigth = screenHeigth;
        this.screenWidth = screenWidth;
    }

//    /**
//     * Checking for wall collision.
//     * @param screenWidth width of the screen
//     * @param screenHeight height of the scree
//     * @param puck puck object
//     */
//    public static void checkWallCollision(float screenWidth, float screenHeight, Puck puck) {
//        if (puck.getposX() + puck.getRadius()
//                >= screenWidth || puck.getposX() - puck.getRadius() <= 0) {
//            puck.setDeltaX(-puck.getDeltaX());
//        }
//
//        if (puck.getposY() + puck.getRadius()
//                >= screenHeight || puck.getposY() - puck.getRadius() <= 0) {
//            puck.setDeltaY(-puck.getDeltaY());
//        }
//
//    }

//    /**
//     * Method that restricts the movement of the Pusher when touching a Wall.
//     * @param posX xPos of the Pusher
//     * @param posY posY of the Pusher
//     * @param radius Radius of the Pusher
//     * @param playerId True for Player1, False for Player2
//     * @return
//     */
//    public boolean[] restrictMovementOnWall(float posX, float posY,
//                                            float radius, boolean playerId, boolean[] restricts) {
//        for (int i = 0; i < 4; i++) {
//            restricts[i] = false;
//        }
//
//        if (posY - radius < 0) {
//            restricts[2] = true;
//        }
//
//        if (posY + radius >= screenHeigth) {
//            restricts[0] = true;
//        }
//
//        if (playerId) {
//            if (posX - radius <= 0) {
//                restricts[1] = true;
//            }
//            if (posX + radius >= screenWidth / 2) {
//                restricts[3] = true;
//            }
//        } else {
//            if (posX - radius <= screenWidth / 2) {
//                restricts[1] = true;
//            }
//            if (posX + radius >= screenWidth) {
//                restricts[3] = true;
//            }
//        }
//
//
//        return restricts;
//    }

//    /**
//     * This method will return true when the puck goes out of the field.
//     *
//     * @param screenWidth  The width of the playing screen.
//     * @param screenHeight The height of the playing screen.
//     * @param puck         The Puck.
//     * @return true when the puck hits the wall, else returns false.
//     */
//    public static boolean goalCollision(float screenWidth, float screenHeight, Puck puck) {
//        if (puck.getposX() + puck.getRadius() >= screenWidth
//                || puck.getposY() + puck.getRadius() >= screenHeight) {
//            return true;
//        }
//        return false;
//    }
}
