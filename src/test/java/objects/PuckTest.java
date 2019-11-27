package objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PuckTest {

    @Test
    void getposX() {
        Puck puck = new Puck(10, 10, 10, 0, 0);
        assertEquals(10, puck.getposX());
    }

    @Test
    void setposX() {
        Puck puck = new Puck(10, 10, 10, 0, 0);
        puck.setposX(15);
        assertEquals(15, puck.getposX());
    }

    @Test
    void getposY() {
        Puck puck = new Puck(10, 10, 10, 0, 0);
        assertEquals(10, puck.getposY());
    }

    @Test
    void setposY() {
        Puck puck = new Puck(10, 10, 10, 0, 0);
        puck.setposY(15);
        assertEquals(15, puck.getposY());
    }

    @Test
    void getRadius() {
        Puck puck = new Puck(10, 10, 10, 0, 0);
        assertEquals(10, puck.getRadius());
    }

    @Test
    void getDeltaX() {
        Puck puck = new Puck(10, 10, 10, 0, 0);
        assertEquals(0, puck.getDeltaX());
    }

    @Test
    void setDeltaX() {
        Puck puck = new Puck(10, 10, 10, 0, 0);
        puck.setDeltaX(15);
        assertEquals(15, puck.getDeltaX());
    }

    @Test
    void getDeltaY() {
        Puck puck = new Puck(10, 10, 10, 0, 0);
        assertEquals(0, puck.getDeltaY());
    }

    @Test
    void setDeltaY() {
        Puck puck = new Puck(10, 10, 10, 0, 0);
        puck.setDeltaY(15);
        assertEquals(15, puck.getDeltaY());
    }

    //TODO Remove static calls first before testing these 3 methods !!!

    @Test
    void translate() {
        //Puck puck = new Puck(10, 10, 5, 2, 2);
        //puck.translate();
        //assertEquals(puck.getposX(), 12);
        //assertEquals(puck.getposY(), 12);

    }



    @Test
    void checkInGateRange() {

    }

    @Test
    void gateBehaviour() {
    }
}