package client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LeaderboardEntryTest {

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

    @Test
    void equalsSame() {
        assertTrue(testLeaderboardEntry.equals(testLeaderboardEntry));
    }

    @Test
    void equalsOther() {
        LeaderboardEntry otherEntry = new LeaderboardEntry(nickname, points);
        assertTrue(testLeaderboardEntry.equals(otherEntry));
    }

    @Test
    void notEqualsOther() {
        LeaderboardEntry otherEntry = new LeaderboardEntry();
        assertFalse(testLeaderboardEntry.equals(otherEntry));
    }

    @Test
    @SuppressWarnings("PMD.EqualsNull") // we need explicitly to call equals null for the test
    void notEqualsNull() {
        assertFalse(testLeaderboardEntry.equals(null));
    }

    @Test
    void notEqualsString() {
        assertFalse(testLeaderboardEntry.equals("String"));
    }

    @Test
    void notEqualsNickname() {
        LeaderboardEntry otherEntry = new LeaderboardEntry("otherName", points);
        assertFalse(testLeaderboardEntry.equals(otherEntry));
    }
}
