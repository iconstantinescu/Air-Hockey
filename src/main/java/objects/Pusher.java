package objects;

import utilities.MathUtils;

public class Pusher {

    private transient float posX;

    private transient float posY;

    private float radius;

    /**
     * Constructor for the pusher object.
     * @param posX x position
     * @param posY y position
     * @param radius radius of puck
     */
    public Pusher(float posX, float posY, float radius) {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
    }

    public float getposX() {
        return posX;
    }

    public void setposX(float posX) {
        this.posX = posX;
    }

    public float getposY() {
        return posY;
    }

    public void setposY(float posY) {
        this.posY = posY;
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
        if (MathUtils.euclideanDistance(getposX(),
                getposY(), puck.getposX(),
                puck.getposY()) <= puck.getRadius() + getRadius()) {
            double[] deltas = MathUtils.reflect(getposX(),
                    getposY(), puck.getposX(), puck.getposY());
            //puck.setDeltaX(-puck.getDeltaX());
            //puck.setDeltaY(-puck.getDeltaY());
            puck.setDeltaX((float) deltas[0] * 6f);
            puck.setDeltaY((float) deltas[1] * 6f);
            return true;
        }
        return false;
    }
}
