package objects;

import com.badlogic.gdx.Gdx;

/**
 * Class implementing the behaviour of the Puck.
 */
public class Puck {

    private transient PuckState puckState;

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
    public Puck(float posX, float posY, float radius, float deltaX, float deltaY, ScoreBoard scoreBoard) {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.scoreBoard = scoreBoard;
        changeStateTo(new GateAlignedState());
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
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
        checkCurrentState(new ScoreBoard(0, 0), screenWidth, screenHeight);

        puckState.executeBehavior(this, screenWidth, screenHeight);

        setposX(deltaX + getposX());
        setposY(deltaY + getposY());
    }

    /**
     * Change the state of the puck.s
     * @param newPuckState
     */
    private void changeStateTo(PuckState newPuckState) {
        this.puckState = newPuckState;
    }



    public void checkCurrentState(ScoreBoard scoreBoard, int screenWidth, int screenHeight) {
        if(getposY() >= screenHeight / 3
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
