package objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game.RenderGame;
import game.RenderStrategy;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import utilities.InformationDrawer;

class GateAlignedStateTest {

    @InjectMocks
    private transient ScoreBoard scoreBoard;
    @InjectMocks
    private transient Puck puck;
    @Mock
    private transient InformationDrawer informationDrawer;
    @Mock
    private transient ShapeRenderer shapeRenderer;
    @Mock
    private transient ScoreBoard mockScoreBoard;

    @Test
    void gateBehaviourUnderZero() {
        scoreBoard = new ScoreBoard(0,0, informationDrawer);
        Puck puck = new Puck(-20, 5, 5, 2, 2, scoreBoard, shapeRenderer);
        PuckState state = new GateAlignedState();

        state.executeBehavior(puck, 1280, 720);

        assertEquals(1, scoreBoard.getPlayer2Score());
        assertEquals(puck.getPosX(), 540);
        assertEquals(puck.getPosY(), 368);
    }

    @Test
    void gateBehaviourOverWidth() {
        scoreBoard = new ScoreBoard(0,0, informationDrawer);
        Puck puck = new Puck(1300, 360, 5, 2, 2, scoreBoard, shapeRenderer);
        puck.setShape(shapeRenderer);

        PuckState state = new GateAlignedState();

        state.executeBehavior(puck, 1280, 720);

        assertEquals(1, scoreBoard.getPlayer1Score());
        assertEquals(puck.getPosX(), 740);
        assertEquals(puck.getPosY(), 368);
    }

}