package objects;

import com.badlogic.gdx.Gdx;

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
    public void translate() {
        if (!checkInGateRange(new ScoreBoard(0,0))) {
            preventOut(deltaX + getposX(), deltaY + getposY());
        }
        setposX(deltaX + getposX());
        setposY(deltaY + getposY());
    }

    /**
     * This method prevents the Puck from going out of bounds.
     * @param futureX The future X position
     * @param futureY The future Y position
     */
    public void preventOut(float futureX, float futureY) {
        if (futureX + radius > Gdx.graphics.getWidth() || futureX - radius < 0) {
            deltaX = -deltaX;
        }

        if (futureY + radius > Gdx.graphics.getHeight() || futureY - radius < 0) {
            deltaY = -deltaY;
        }
    }

    public boolean checkInGateRange(ScoreBoard scoreBoard) {
        return getposY() >= Gdx.graphics.getHeight() / 3
                && getposY() <= (Gdx.graphics.getHeight() / 3) * 2;
    }

    /**
     * Update the Puck and the score according to the behaviour when in line with gate.
     * @param scoreBoard The scoreBoard to be updated
     */
    public void gateBehaviour(ScoreBoard scoreBoard) {
        if (getposX() + getRadius() < 0) {
            setposX(Gdx.graphics.getWidth() / 2);
            setposY(Gdx.graphics.getHeight() / 2);
            setDeltaX(0);
            setDeltaY(0);
            scoreBoard.pointP2();
        }

        if (getposX() - getRadius() >= Gdx.graphics.getWidth()) {
            setposX(Gdx.graphics.getWidth() / 2);
            setposY(Gdx.graphics.getHeight() / 2);
            setDeltaX(0);
            setDeltaY(0);
            scoreBoard.pointP1();
        }
    }


}
