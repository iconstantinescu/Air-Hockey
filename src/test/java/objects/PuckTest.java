package objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


class PuckTest {

    @InjectMocks
    private transient Puck puck;
    @Mock
    private transient ScoreBoard scoreBoard;
    @Mock
    private transient ShapeRenderer shapeRenderer;

    @Test
    void getposX() {
        puck = new Puck(10, 10, 10, 0, 0, scoreBoard, shapeRenderer);
        assertEquals(10, puck.getPosX());
    }

    @Test
    void setposX() {
        puck = new Puck(10, 10, 10, 0, 0, scoreBoard, shapeRenderer);
        puck.setPosX(15);
        assertEquals(15, puck.getPosX());
    }

    @Test
    void getposY() {
        puck = new Puck(10, 10, 10, 0, 0, scoreBoard, shapeRenderer);
        assertEquals(10, puck.getPosY());
    }

    @Test
    void setposY() {
        puck = new Puck(10, 10, 10, 0, 0, scoreBoard, shapeRenderer);
        puck.setPosY(15);
        assertEquals(15, puck.getPosY());
    }

    @Test
    void getRadius() {
        puck = new Puck(10, 10, 10, 0, 0, scoreBoard, shapeRenderer);
        assertEquals(10, puck.getRadius());
    }

    @Test
    void setRadius() {
        puck = new Puck(10, 10, 10, 0, 0, scoreBoard, shapeRenderer);
        puck.setRadius(15);
        assertEquals(15, puck.getRadius());
    }

    @Test
    void getDeltaX() {
        puck = new Puck(10, 10, 10, 0, 0, scoreBoard, shapeRenderer);
        assertEquals(0, puck.getDeltaX());
    }

    @Test
    void setDeltaX() {
        puck = new Puck(10, 10, 10, 0, 0, scoreBoard, shapeRenderer);
        puck.setDeltaX(15);
        assertEquals(15, puck.getDeltaX());
    }

    @Test
    void getDeltaY() {
        puck = new Puck(10, 10, 10, 0, 0, scoreBoard, shapeRenderer);
        assertEquals(0, puck.getDeltaY());
    }

    @Test
    void setDeltaY() {
        puck = new Puck(10, 10, 10, 0, 0, scoreBoard, shapeRenderer);
        puck.setDeltaY(15);
        assertEquals(15, puck.getDeltaY());
    }

    @Test
    void translate() {
        puck = new Puck(640, 360, 5, 2, 2, scoreBoard, shapeRenderer);
        puck.translate(1280, 720);
        assertEquals(puck.getPosX(), 642);
        assertEquals(puck.getPosY(), 362);

    }

    @Test
    void translateOutOfGateRange() {
        puck = new Puck(1200, 600, 5, 2, 2, scoreBoard, shapeRenderer);
        puck.translate(1280, 720);
        assertEquals(puck.getPosX(), 1202);
        assertEquals(puck.getPosY(), 602);

    }

    @Test
    void checkInGateRangeTrue() {
        puck = new Puck(640, 360, 5, 2, 2, scoreBoard, shapeRenderer);
        puck.checkCurrentState(720);

        assertTrue(puck.getPuckState() instanceof GateAlignedState);
    }

    @Test
    void checkInGateRangeFalse() {
        puck = new Puck(5, 5, 5, 2, 2, scoreBoard, shapeRenderer);
        puck.checkCurrentState(720);

        assertTrue(puck.getPuckState() instanceof OutOfGatesState);
    }

    @Test
    void checkInGateRangeFalseAbove() {
        puck = new Puck(1200, 600, 5, 2, 2, scoreBoard, shapeRenderer);
        puck.checkCurrentState(720);

        assertTrue(puck.getPuckState() instanceof OutOfGatesState);
    }


    @Test
    void translateMenu() {
        puck = new Puck(1200, 600, 5, 2, 2, scoreBoard, shapeRenderer);

        puck.translateMenu(1920, 1080);

        assertEquals(1202, puck.getPosX());
        assertEquals(602, puck.getPosY());
    }

    @Test
    void setPuckState() {
        puck = new Puck(1200, 600, 5, 2, 2, scoreBoard, shapeRenderer);

        puck.setPuckState(new OutOfGatesState());

        assertTrue(puck.getPuckState() instanceof OutOfGatesState);
    }

    @Test
    void outToOutState() {
        puck = new Puck(1200, 600, 5, 2, 2, scoreBoard, shapeRenderer);

        puck.setPuckState(new OutOfGatesState());

        puck.checkCurrentState(700);

        assertTrue(puck.getPuckState() instanceof OutOfGatesState);
    }

    @Test
    void gateToGateState() {
        puck = new Puck(1200, 600, 5, 2, 2, scoreBoard, shapeRenderer);

        puck.setPuckState(new GateAlignedState());

        puck.checkCurrentState(1200);

        assertTrue(puck.getPuckState() instanceof GateAlignedState);
    }

    @Test
    void checkCurrentStateOutState() {
        puck = new Puck(1200, 600, 5, 2, 2, scoreBoard, shapeRenderer);

        puck.checkCurrentState(700);

        assertTrue(puck.getPuckState() instanceof OutOfGatesState);
    }

    @Test
    void checkCurrentStateGateState() {
        puck = new Puck(1200, 600, 5, 2, 2, scoreBoard, shapeRenderer);

        puck.checkCurrentState(1200);

        assertTrue(puck.getPuckState() instanceof GateAlignedState);
    }
}