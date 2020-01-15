package objects;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ScoreBoardTest {

    @Test
    void isGameOverWinner1() {
        ScoreBoard scoreBoard = new ScoreBoard(5, 4);

        assertTrue(scoreBoard.isGameOver());
    }

    @Test
    void isGameOverWinner2() {
        ScoreBoard scoreBoard = new ScoreBoard(4, 5);

        assertTrue(scoreBoard.isGameOver());
    }

    @Test
    void isGameOverNoWinner() {
        ScoreBoard scoreBoard = new ScoreBoard(4, 4);

        assertFalse(scoreBoard.isGameOver());
    }

    @Test
    void getWinnerP1() {
        ScoreBoard scoreBoard = new ScoreBoard(5, 4);

        assertTrue(scoreBoard.getWinner());
    }

    @Test
    void getWinnerP2() {
        ScoreBoard scoreBoard = new ScoreBoard(4, 5);

        assertFalse(scoreBoard.getWinner());
    }
}