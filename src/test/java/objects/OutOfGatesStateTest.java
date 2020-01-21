package objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class OutOfGatesStateTest {

    @InjectMocks
    private transient Puck puck;
    @Mock
    private transient ShapeRenderer shapeRenderer;
    @Mock
    private transient ScoreBoard scoreBoard;

    @Test
    void preventOutLeftCorner() {
        puck = new Puck(2,2,10,-1, 1, scoreBoard, shapeRenderer);

        PuckState state = new OutOfGatesState();

        state.executeBehavior(puck, 1280, 720);


        assertEquals(1, puck.getDeltaX());
        assertEquals(-1, puck.getDeltaY());

    }

    @Test
    void preventOutRightTopCorner() {
        puck = new Puck(1280,720,10,1, 1, scoreBoard, shapeRenderer);

        PuckState state = new OutOfGatesState();

        state.executeBehavior(puck, 1280, 720);

        assertEquals(-1, puck.getDeltaX());
        assertEquals(-1, puck.getDeltaY());

    }


}