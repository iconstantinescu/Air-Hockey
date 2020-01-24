package objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import client.User;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class PusherTest {

    @InjectMocks
    private transient Puck puck;
    @InjectMocks
    private transient Pusher pusher;
    @Mock
    private transient ScoreBoard scoreBoard;
    @Mock
    private transient ShapeRenderer shapeRenderer;


    @Test
    void getposX() {
        pusher = new Pusher(10, 10, 10, shapeRenderer);
        assertEquals(10, pusher.getPosX());
    }

    @Test
    void setposX() {
        pusher = new Pusher(10, 10, 10, shapeRenderer);
        pusher.setPosX(15);
        assertEquals(15, pusher.getPosX());
    }

    @Test
    void getposY() {
        pusher = new Pusher(10, 10, 10, shapeRenderer);
        assertEquals(10, pusher.getPosY());
    }

    @Test
    void setposY() {
        pusher = new Pusher(10, 10, 10, shapeRenderer);
        pusher.setPosY(15);
        assertEquals(15, pusher.getPosY());
    }

    @Test
    void getRadius() {
        pusher = new Pusher(10, 10, 10, shapeRenderer);
        assertEquals(10, pusher.getRadius());
    }

    @Test
    void setRadius() {
        pusher = new Pusher(10, 10, 10, shapeRenderer);
        pusher.setRadius(15);
        assertEquals(15, pusher.getRadius());
    }

    @Test
    void checkAndExecuteCollisionOverlap() {
        pusher = new Pusher(10, 10, 10, shapeRenderer);
        puck = new Puck(10, 12, 2, 0, 0, scoreBoard, shapeRenderer);

        assertTrue(pusher.checkAndExecuteCollision(puck));
        assertEquals(0, puck.getDeltaX());
        assertEquals(12, puck.getDeltaY());
    }

    @Test
    void checkAndExecuteCollisionNoOverlap() {
        pusher = new Pusher(300, 450, 10, shapeRenderer);
        puck = new Puck(10, 12, 2, 0, 0, scoreBoard, shapeRenderer);

        assertFalse(pusher.checkAndExecuteCollision(puck));
        assertEquals(0, puck.getDeltaX());
        assertEquals(0, puck.getDeltaY());
    }


    @Test
    void restrictMovementOnWallCornerLeft() {
        pusher = new Pusher(-1, -1, 0, shapeRenderer);

        boolean[] some = new boolean[4];
        pusher.restrictMovementOnWall(true, some, 1280, 720);

        assertTrue(some[2]);
        assertTrue(some[1]);
    }

    @Test
    void restrictMovementOnWallMiddleSecondPlayer() {
        pusher = new Pusher(641, 1, 1, shapeRenderer);

        boolean[] some = new boolean[4];
        pusher.restrictMovementOnWall(false, some, 1280, 720);

        assertTrue(some[1]);
    }

    @Test
    void restrictMovementOnWallRightSecondPlayer() {
        pusher = new Pusher(1279, 1, 1, shapeRenderer);

        boolean[] some = new boolean[4];
        pusher.restrictMovementOnWall(false, some, 1280, 720);

        assertTrue(some[3]);
    }

    @Test
    void restrictMovementOnWallCornerRight() {
        pusher = new Pusher(1281, 721, 0, shapeRenderer);

        boolean[] some = new boolean[4];
        pusher.restrictMovementOnWall(true, some, 1280, 720);

        assertTrue(some[0]);
        assertTrue(some[3]);
    }

    @Test
    void restrictMovementOnWall() {
        pusher = new Pusher(-1, -1, 0, shapeRenderer);

        boolean[] some = new boolean[4];
        pusher.restrictMovementOnWall(true, some, 1280, 720);

        assertTrue(some[2]);
        assertTrue(some[1]);
    }
}