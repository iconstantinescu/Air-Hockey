package objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class OutOfGatesStateTest {

    @Test
    void preventOutLeftCorner() {
        Puck puck = new Puck(2,2,10,-1, 1, new ScoreBoard());

        PuckState state = new OutOfGatesState();

        state.executeBehavior(puck, 1280, 720);


        assertEquals(1, puck.getDeltaX());
        assertEquals(-1, puck.getDeltaY());

    }

    @Test
    void preventOutRightTopCorner() {
        Puck puck = new Puck(1280,720,10,1, 1, new ScoreBoard());

        PuckState state = new OutOfGatesState();

        state.executeBehavior(puck, 1280, 720);

        assertEquals(-1, puck.getDeltaX());
        assertEquals(-1, puck.getDeltaY());

    }


}