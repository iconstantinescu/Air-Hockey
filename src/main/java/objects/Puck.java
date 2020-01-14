package objects;

import com.badlogic.gdx.Gdx;

/**
 * Class implementing the behaviour of the Puck.
 */
public class Puck {

    private transient PuckState puckState;

    private transient PuckState menuState;

    private transient ScoreBoard scoreBoard;

    private transient float posX;

    private transient float posY;

    private float radius;

    private float deltaX;

    private float deltaY;

    /**
     * Constructor for the puck object.
     *
     * @param posX   x position
     * @param posY   y position
     * @param radius radius of puck
     * @param deltaX x movement
     * @param deltaY y movement
     */
    public Puck(float posX, float posY, float radius, float deltaX,
                float deltaY, ScoreBoard scoreBoard) {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.scoreBoard = scoreBoard;
        menuState = new OutOfGatesState();
        changeStateTo(new GateAlignedState());
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public PuckState getPuckState() {
        return puckState;
    }

    public void setRadius(float radius) {
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

    /**
     * Method used to move the Puck according to its speed.
     */
    public void translate(int screenWidth, int screenHeight) {
        checkCurrentState(screenHeight);

        puckState.executeBehavior(this, screenWidth, screenHeight);

        setposX(deltaX + getposX());
        setposY(deltaY + getposY());
    }

    /**
     * Specific Puck translation for the menu.
     * @param screenWidth Width of the screen
     * @param screenHeight Height of the screen
     */
    public void translateMenu(float screenWidth, float screenHeight) {

        menuState.executeBehavior(this, screenWidth, screenHeight);
        setposX(deltaX + getposX());
        setposY(deltaY + getposY());
    }

    /**
     * Method that resets the position of the Puck (used after a goal was scored).
     * @param newX The new X coordinate
     * @param newY The new Y coordinate
     */
    public void resetPuck(float newX, float newY) {
        this.deltaX = 0;
        this.deltaY = 0;
        this.posX = newX;
        this.posY = newY;
    }

    /**
     * Change the state of the puck.s
     * @param newPuckState The next State of the puck
     */
    private void changeStateTo(PuckState newPuckState) {
        this.puckState = newPuckState;
    }


    /**
     * This method checks the current position of the puck and decides whether a change
     * in its state is necessary.
     * @param screenHeight The height of the screen.
     */
    public void checkCurrentState(float screenHeight) {
        if (getposY() >= screenHeight / 3
                && getposY() <= (screenHeight / 3) * 2) {
            if (!(puckState instanceof GateAlignedState)) {
                changeStateTo(new GateAlignedState());
            }
        } else {
            if (!(puckState instanceof OutOfGatesState)) {
                changeStateTo(new OutOfGatesState());
            }
        }
    }






}
