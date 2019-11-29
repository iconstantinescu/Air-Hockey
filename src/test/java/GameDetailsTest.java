import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class GameDetailsTest {

    private GameDetails testGame;
    private Timestamp timestamp;

    @BeforeEach
    void setUp() {

        timestamp = new Timestamp(System.currentTimeMillis());
        testGame = new GameDetails("john", "robert",
                5, 2, timestamp);
    }

    @Test
    void getUsername() {
        Assert.assertEquals("john", testGame.getUsername1());
        Assert.assertEquals("robert", testGame.getUsername2());
    }

    @Test
    void getScoreUser1() {
        Assert.assertEquals(5, testGame.getScoreUser1());
    }

    @Test
    void getScoreUser2() {
        Assert.assertEquals(2, testGame.getScoreUser2());
    }

    @Test
    void getTimestamp() {
        Assert.assertEquals(timestamp, testGame.getTimestamp());
    }
}