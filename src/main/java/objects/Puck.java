package objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Class implementing the behaviour of the Puck.
 */
public class Puck extends GameObject {

    private transient PuckState puckState;

    private transient ShapeRenderer shape;

    private transient PuckState menuState;

    private transient ScoreBoard scoreBoard;

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
        super(posX, posY, radius);
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.scoreBoard = scoreBoard;
        shape = new ShapeRenderer();
        menuState = new OutOfGatesState();
        changeStateTo(new GateAlignedState());
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public PuckState getPuckState() {
        return puckState;
    }

    public void setPuckState(PuckState puckState) {
        this.puckState = puckState;
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

        setPosX(deltaX + getPosX());
        setPosY(deltaY + getPosY());


    }

    /**
     * Specific Puck translation for the menu.
     * @param screenWidth Width of the screen
     * @param screenHeight Height of the screen
     */
    public void translateMenu(float screenWidth, float screenHeight) {

        menuState.executeBehavior(this, screenWidth, screenHeight);
        setPosX(deltaX + getPosX());
        setPosY(deltaY + getPosY());
    }

    /**
     * Method that resets the position of the Puck (used after a goal was scored).
     * @param newX The new X coordinate
     * @param newY The new Y coordinate
     */
    public void resetPuck(float newX, float newY) {
        this.deltaX = 0;
        this.deltaY = 0;
        setPosX(newX);
        setPosY(newY);
        Pusher.resetPusher = true;
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
        if (getPosY() >= screenHeight / 3
                && getPosY() <= (screenHeight / 3) * 2) {
            if (!(puckState instanceof GateAlignedState)) {
                changeStateTo(new GateAlignedState());
            }
        } else {
            if (!(puckState instanceof OutOfGatesState)) {
                changeStateTo(new OutOfGatesState());
            }
        }
    }

    /**
     * Function that renders a gameObject (which can be either the puck or the pusher.
     */
    public void drawGameObject() {
        shape.begin(ShapeRenderer.ShapeType.Filled);

        shape.setColor(Color.RED);

        shape.circle(getPosX(), getPosY(), getRadius());
        shape.end();
    }

}
