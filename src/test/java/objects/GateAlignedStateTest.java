package objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import game.RenderGame;
import game.RenderStrategy;
import org.junit.jupiter.api.Test;

class GateAlignedStateTest {


    @Test
    void gateBehaviourUnderZero() {
        ScoreBoard scoreBoard = new ScoreBoard(0,0);
        Puck puck = new Puck(-20, 5, 5, 2, 2, scoreBoard);
        PuckState state = new GateAlignedState();

        state.executeBehavior(puck, 1280, 720);

        assertEquals(1, scoreBoard.getPlayer2Score());
        assertEquals(puck.getposX(), 540);
        assertEquals(puck.getposY(), 368);
    }

    @Test
    void gateBehaviourOverWidth() {
        ScoreBoard scoreBoard = new ScoreBoard(0,0);
        Puck puck = new Puck(1300, 360, 5, 2, 2, scoreBoard);

        PuckState state = new GateAlignedState();

        state.executeBehavior(puck, 1280, 720);

        assertEquals(1, scoreBoard.getPlayer1Score());
        assertEquals(puck.getposX(), 740);
        assertEquals(puck.getposY(), 368);
    }

}