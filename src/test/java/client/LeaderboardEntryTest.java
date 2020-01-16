package client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LeaderboardEntryTest {

    private transient LeaderboardEntry testLeaderboardEntry;
    private transient String nickname;
    private transient long points;

    @BeforeEach
    void setUp() {

        nickname = "john";
        points = 100;
        testLeaderboardEntry = new LeaderboardEntry(nickname, points);
    }

    @Test
    void getNickname() {
        assertEquals(nickname, testLeaderboardEntry.getNickname());
    }

    @Test
    void setNickname() {
        testLeaderboardEntry.setNickname("Robert");
        assertEquals("Robert", testLeaderboardEntry.getNickname());
    }

    @Test
    void getPoints() {
        assertEquals(points, testLeaderboardEntry.getPoints());
    }

    @Test
    void setPoints() {
        testLeaderboardEntry.setPoints(200);
        assertEquals(200, testLeaderboardEntry.getPoints());
    }

    @Test
    void testEmptyConsructor() {
        LeaderboardEntry emptyEntry = new LeaderboardEntry();
        assertEquals("", emptyEntry.getNickname());
        assertEquals(0, emptyEntry.getPoints());
    }
}
