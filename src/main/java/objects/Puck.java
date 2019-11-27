package objects;

public class Puck {

    private transient float posX;

    private transient float posY;

    private float radius;

    private float deltaX;

    private float deltaY;

    /**
     * Constructor for the puck object.
     * @param posX x position
     * @param posY y position
     * @param radius radius of puck
     * @param deltaX x movement
     * @param deltaY y movement
     */
    public Puck(float posX, float posY, float radius, float deltaX, float deltaY) {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
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

    public float getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }

    public float getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(float deltaY) {
        this.deltaY = deltaY;
    }

    public void translate() {
        setposX(deltaX + getposX());
        setposY(deltaY + getposY());
    }

}
