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
}
