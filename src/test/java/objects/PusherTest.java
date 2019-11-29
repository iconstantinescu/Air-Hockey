package objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


    @Test
    void restrictMovementOnWallCornerLeft() {
        Pusher pusher = new Pusher(-1, -1, 0);

        boolean[] some = new boolean[4];
        pusher.restrictMovementOnWall(true, some, 1280, 720);

        assertTrue(some[2]);
        assertTrue(some[1]);
    }

    @Test
    void restrictMovementOnWallCornerRight() {
        Pusher pusher = new Pusher(1281, 721, 0);

        boolean[] some = new boolean[4];
        pusher.restrictMovementOnWall(true, some, 1280, 720);

        assertTrue(some[0]);
        assertTrue(some[3]);
    }

    @Test
    void restrictMovementOnWall() {
        Pusher pusher = new Pusher(-1, -1, 0);

        boolean[] some = new boolean[4];
        pusher.restrictMovementOnWall(true, some, 1280, 720);

        assertTrue(some[2]);
        assertTrue(some[1]);
    }
}