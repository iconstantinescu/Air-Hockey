package objects;

import utilities.MathUtils;

/**
 * The class containing the behaviour of the pusher.
 */
public class Pusher extends GameObject {

    public static boolean resetPusher;

    private float radius;

    public static boolean playHitSound = false;

    /**
     * Constructor for the pusher object.
     *
     * @param posX   x position
     * @param posY   y position
     * @param radius radius of puck
     */
    public Pusher(float posX, float posY, float radius) {
        super(posX, posY);
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * Function first checks if a collision occurs between the Puck and the Pusher,
     * and if it does then it simulates the collision between the objects.
     * @param puck The Puck to be checked
     * @return Boolean showing whether a collision happened or not
     */
    public boolean checkAndExecuteCollision(Puck puck) {
        if (MathUtils.checkRadius(this.getPosX(), this.getPosY(), this.getRadius(),
                puck.getPosX(), puck.getPosY(), puck.getRadius())) {
            double[] deltas = MathUtils.reflect(getPosX(),
                    getPosY(), puck.getPosX(), puck.getPosY());
            puck.setDeltaX((float) deltas[0] * 12f);
            puck.setDeltaY((float) deltas[1] * 12f);
            return true;
        }
        return false;
    }

    /**
     * Method that restricts the movement of the Pusher when touching a Wall.
     * @param playerId True for Player1, False for Player2
     * @param restricts Array of resticts according UP, LEFT, DOWN and RIGHT
     * @param screenWidth Width of the screen
     * @param screenHeigth Height of the screen
     * @return
     */
    public boolean[] restrictMovementOnWall(boolean playerId,
                                            boolean[] restricts,
                                            int screenWidth, int screenHeigth) {
        // Initialize the restrict Array.
        for (int i = 0; i < 4; i++) {
            restricts[i] = false;
        }

        // Restrict Down if necessary
        if (getPosX() - radius < 0) {
            restricts[2] = true;
        }

        // Restrict Up if necessary
        if (getPosY() + radius >= screenHeigth) {
            restricts[0] = true;
        }

        // Check for which player you should restrict the movement
        if (playerId) {
            // Restrict Left if necessary
            if (getPosX() - radius <= 0) {
                restricts[1] = true;
            }
            // Restrict Right if necessary
            if (getPosY() + radius >= screenWidth / 2) {
                restricts[3] = true;
            }
        } else {
            // Restrict Left if necessary
            if (getPosX() - radius <= screenWidth / 2) {
                restricts[1] = true;
            }
            // Restrict Right if necessary
            if (getPosX() + radius >= screenWidth) {
                restricts[3] = true;
            }
        }

        return restricts;
    }
}
