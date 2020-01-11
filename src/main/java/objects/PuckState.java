package objects;

/**
 * This interface is used by the puck to determine its next move,
 * depending on its state.
 */
public interface PuckState {

    /**
     * This method will have to calculate the next position of the Puck,
     * depending on its current state, as its state influences its behaviour.
     * @param puck
     * @param screenWidth
     * @param screenHeight
     */
    void executeBehavior(Puck puck, float screenWidth, float screenHeight);
}
