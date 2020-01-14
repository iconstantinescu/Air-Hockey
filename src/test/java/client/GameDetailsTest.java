package client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;

import com.badlogic.gdx.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameDetailsTest {

    private transient GameDetails testGame;
    private transient Timestamp timestamp;
    private transient String nickname1;
    private transient String nickname2;
    private transient int score1;
    private transient int score2;

    @BeforeEach
    void setUp() {

        nickname1 = "john";
        nickname2 = "robert";
        score1 = 5;
        score2 = 2;
        timestamp = new Timestamp(System.currentTimeMillis());
        testGame = new GameDetails(nickname1, nickname2,
                score1, score2, timestamp);
    }

    @Test
    void getNickname() {
        assertEquals(nickname1, testGame.getNickname1());
        assertEquals(nickname2, testGame.getNickname2());
    }

    @Test
    void getScoreUser1() {
        assertEquals(score1, testGame.getScoreUser1());
    }

    @Test
    void getScoreUser2() {
        assertEquals(score2, testGame.getScoreUser2());
    }

    @Test
    void getTimestamp() {
        assertEquals(timestamp, testGame.getTimestamp());
    }


    @Test
    void setNickname1() {
        testGame.setNickname1("newName");
        assertEquals("newName", testGame.getNickname1());
    }

    @Test
    void setUsername2() {
        testGame.setNickname2("newName");
        assertEquals("newName", testGame.getNickname2());
    }

    @Test
    void setScoreUser1() {
        testGame.setScoreUser1(3);
        assertEquals(3, testGame.getScoreUser1());
    }

    @Test
    void setScoreUser2() {
        testGame.setScoreUser2(3);
        assertEquals(3, testGame.getScoreUser2());
    }

    @Test
    void setTimestamp() {
        Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());
        testGame.setTimestamp(newTimestamp);
        assertEquals(newTimestamp, testGame.getTimestamp());
    }

    @Test
    void testEmptyConstructor() {
        GameDetails emptyGame = new GameDetails();
        assertEquals("", emptyGame.getNickname1());
        assertEquals("", emptyGame.getNickname2());
        assertEquals(0, emptyGame.getScoreUser1());
        assertEquals(0, emptyGame.getScoreUser2());
        assertEquals(new Timestamp(0), emptyGame.getTimestamp());
    }
}