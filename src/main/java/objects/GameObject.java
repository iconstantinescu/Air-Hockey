package objects;

/**
 * This GameObject function acts as the parent of the Puck and Pusher
 * classes, whose parameters, getters and setters are very similar,
 * both of them being objects of the game.
 */
public abstract class GameObject {

    private float posX;

    private float posY;

    private float radius;

    /**
     * The main constructor of a Game object, setting its main information.
     * @param posX The x coordinate on the screen.
     * @param posY The y coordinate on the screen.
     * @param radius The radius of the object.
     */
    public GameObject(float posX, float posY, float radius) {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
