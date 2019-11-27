package objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PusherTest {

    @Test
    void getposX() {
        Pusher pusher = new Pusher(10, 10, 10);
        assertEquals(10, pusher.getposX());
    }

    @Test
    void setposX() {
        Pusher pusher = new Pusher(10, 10, 10);
        pusher.setposX(15);
        assertEquals(15, pusher.getposX());
    }

    @Test
    void getposY() {
        Pusher pusher = new Pusher(10, 10, 10);
        assertEquals(10, pusher.getposY());
    }

    @Test
    void setposY() {
        Pusher pusher = new Pusher(10, 10, 10);
        pusher.setposY(15);
        assertEquals(15, pusher.getposY());
    }

    @Test
    void getRadius() {
        Pusher pusher = new Pusher(10, 10, 10);
        assertEquals(10, pusher.getRadius());
    }

    @Test
    void setRadius() {
        Pusher pusher = new Pusher(10, 10, 10);
        pusher.setRadius(15);
        assertEquals(15, pusher.getRadius());
    }

    @Test
    void checkAndExecuteCollision() {
        Pusher pusher = new Pusher(10, 10, 10);
        Puck puck = new Puck(10, 12, 2, 0, 0);

        assertTrue(pusher.checkAndExecuteCollision(puck));
        assertEquals(0, puck.getDeltaX());
        assertEquals(6, puck.getDeltaY());
    }


}