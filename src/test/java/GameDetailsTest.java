import static org.junit.jupiter.api.Assertions.assertEquals;

import client.GameDetails;
import java.sql.Timestamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameDetailsTest {

    private transient GameDetails testGame;
    private transient Timestamp timestamp;

    @BeforeEach
    void setUp() {

        timestamp = new Timestamp(System.currentTimeMillis());
        testGame = new GameDetails("john", "robert",
                5, 2, timestamp);
    }

    @Test
    void getUsername() {
        assertEquals("john", testGame.getUsername1());
        assertEquals("robert", testGame.getUsername2());
    }

    @Test
    void getScoreUser1() {
        assertEquals(5, testGame.getScoreUser1());
    }

    @Test
    void getScoreUser2() {
        assertEquals(2, testGame.getScoreUser2());
    }

    @Test
    void getTimestamp() {
        assertEquals(timestamp, testGame.getTimestamp());
    }
}