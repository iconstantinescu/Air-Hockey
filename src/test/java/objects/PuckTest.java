package objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;



class PuckTest {

    @Test
    void getposX() {
        Puck puck = new Puck(10, 10, 10, 0, 0, new ScoreBoard());
        assertEquals(10, puck.getposX());
    }

    @Test
    void setposX() {
        Puck puck = new Puck(10, 10, 10, 0, 0, new ScoreBoard());
        puck.setposX(15);
        assertEquals(15, puck.getposX());
    }

    @Test
    void getposY() {
        Puck puck = new Puck(10, 10, 10, 0, 0, new ScoreBoard());
        assertEquals(10, puck.getposY());
    }

    @Test
    void setposY() {
        Puck puck = new Puck(10, 10, 10, 0, 0, new ScoreBoard());
        puck.setposY(15);
        assertEquals(15, puck.getposY());
    }

    @Test
    void getRadius() {
        Puck puck = new Puck(10, 10, 10, 0, 0, new ScoreBoard());
        assertEquals(10, puck.getRadius());
    }

    @Test
    void setRadius() {
        Puck puck = new Puck(10, 10, 10, 0, 0, new ScoreBoard());
        puck.setRadius(15);
        assertEquals(15, puck.getRadius());
    }

    @Test
    void getDeltaX() {
        Puck puck = new Puck(10, 10, 10, 0, 0, new ScoreBoard());
        assertEquals(0, puck.getDeltaX());
    }

    @Test
    void setDeltaX() {
        Puck puck = new Puck(10, 10, 10, 0, 0, new ScoreBoard());
        puck.setDeltaX(15);
        assertEquals(15, puck.getDeltaX());
    }

    @Test
    void getDeltaY() {
        Puck puck = new Puck(10, 10, 10, 0, 0, new ScoreBoard());
        assertEquals(0, puck.getDeltaY());
    }

    @Test
    void setDeltaY() {
        Puck puck = new Puck(10, 10, 10, 0, 0, new ScoreBoard());
        puck.setDeltaY(15);
        assertEquals(15, puck.getDeltaY());
    }

    @Test
    void translate() {
        Puck puck = new Puck(640, 360, 5, 2, 2, new ScoreBoard());
        puck.translate(1280, 720);
        assertEquals(puck.getposX(), 642);
        assertEquals(puck.getposY(), 362);

    }

    @Test
    void translateOutOfGateRange() {
        Puck puck = new Puck(1200, 600, 5, 2, 2, new ScoreBoard());
        puck.translate(1280, 720);
        assertEquals(puck.getposX(), 1202);
        assertEquals(puck.getposY(), 602);

    }

    @Test
    void checkInGateRangeTrue() {
        Puck puck = new Puck(640, 360, 5, 2, 2, new ScoreBoard());
        puck.checkCurrentState(720);

        assertTrue(puck.getPuckState() instanceof GateAlignedState);
    }

    @Test
    void checkInGateRangeFalse() {
        Puck puck = new Puck(5, 5, 5, 2, 2, new ScoreBoard());
        puck.checkCurrentState(720);

        assertTrue(puck.getPuckState() instanceof OutOfGatesState);
    }

    @Test
    void checkInGateRangeFalseAbove() {
        Puck puck = new Puck(1200, 600, 5, 2, 2, new ScoreBoard());
        puck.checkCurrentState(720);

        assertTrue(puck.getPuckState() instanceof OutOfGatesState);
    }


    @Test
    void translateMenu() {
        Puck puck = new Puck(1200, 600, 5, 2, 2, new ScoreBoard());

        puck.translateMenu(1920, 1080);

        assertEquals(1202, puck.getposX());
        assertEquals(602, puck.getposY());
    }

    @Test
    void setPuckState() {
        Puck puck = new Puck(1200, 600, 5, 2, 2, new ScoreBoard());

        puck.setPuckState(new OutOfGatesState());

        assertTrue(puck.getPuckState() instanceof OutOfGatesState);
    }

    @Test
    void outToOutState() {
        Puck puck = new Puck(1200, 600, 5, 2, 2, new ScoreBoard());

        puck.setPuckState(new OutOfGatesState());

        puck.checkCurrentState(700);

        assertTrue(puck.getPuckState() instanceof OutOfGatesState);
    }

    @Test
    void gateToGateState() {
        Puck puck = new Puck(1200, 600, 5, 2, 2, new ScoreBoard());

        puck.setPuckState(new GateAlignedState());

        puck.checkCurrentState(1200);

        assertTrue(puck.getPuckState() instanceof GateAlignedState);
    }

    @Test
    void checkCurrentStateOutState() {
        Puck puck = new Puck(1200, 600, 5, 2, 2, new ScoreBoard());

        puck.checkCurrentState(700);

        assertTrue(puck.getPuckState() instanceof OutOfGatesState);
    }

    @Test
    void checkCurrentStateGateState() {
        Puck puck = new Puck(1200, 600, 5, 2, 2, new ScoreBoard());

        puck.checkCurrentState(1200);

        assertTrue(puck.getPuckState() instanceof GateAlignedState);
    }
}