package client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LeaderboardTest {

    private transient Leaderboard testLeaderboard;
    private transient List<LeaderboardEntry> leaderboardList;
    private transient int sizeLimit;

    @BeforeEach
    void setUp() {

        leaderboardList = new ArrayList<>();
        leaderboardList.add(new LeaderboardEntry("john", 500));
        leaderboardList.add(new LeaderboardEntry("robert", 200));
        sizeLimit = 10;
        testLeaderboard = new Leaderboard(leaderboardList, sizeLimit);
    }

    @Test
    void testJustSizeConstructor() {
        Leaderboard emptyLeaderboard = new Leaderboard(sizeLimit);
        assertEquals(new ArrayList<>(), emptyLeaderboard.getLeaderboardList());
        assertEquals(sizeLimit, emptyLeaderboard.getSizeLimit());
    }

    @Test
    void getLeaderboardList() {
        assertEquals(leaderboardList, testLeaderboard.getLeaderboardList());
    }

    @Test
    void setLeaderboardList() {
        List<LeaderboardEntry> newList = new ArrayList<>();
        newList.add(new LeaderboardEntry("darwin", 100));
        testLeaderboard.setLeaderboardList(newList);
        assertEquals(newList, testLeaderboard.getLeaderboardList());
    }

    @Test
    void addEntry() {

        LeaderboardEntry newEntry = new LeaderboardEntry("jaron", 100);
        testLeaderboard.addEntry(newEntry);
        leaderboardList.add(newEntry);
        assertEquals(leaderboardList, testLeaderboard.getLeaderboardList());
    }

    @Test
    void getSizeLimit() {
        assertEquals(sizeLimit, testLeaderboard.getSizeLimit());
    }

    @Test
    void setSizeLimit() {
        testLeaderboard.setSizeLimit(2);
        assertEquals(2, testLeaderboard.getSizeLimit());
    }

    @Test
    void getCurrentSize() {
        assertEquals(leaderboardList.size(), testLeaderboard.getCurrentSize());
    }

    @Test
    void changeSizeLower() {
        testLeaderboard.setSizeLimit(1);


        //Should keep just the first entry of the list
        leaderboardList.remove(1);
        assertEquals(leaderboardList, testLeaderboard.getLeaderboardList());
        assertEquals(1, testLeaderboard.getCurrentSize());

    }

    @Test
    void sizeLimitExceeded() {
        testLeaderboard.setSizeLimit(2);

        assertFalse(testLeaderboard.addEntry(new LeaderboardEntry()));
        assertEquals(leaderboardList, testLeaderboard.getLeaderboardList());
        assertEquals(leaderboardList.size(), testLeaderboard.getCurrentSize());

    }

    @Test
    void setListExceedLimit() {
        testLeaderboard.setSizeLimit(0);

        testLeaderboard.setLeaderboardList(leaderboardList);
        assertEquals(new ArrayList<>(), testLeaderboard.getLeaderboardList());
        assertEquals(0, testLeaderboard.getCurrentSize());
    }

    @Test
    void initializeListExceedLimit() {
        Leaderboard newLeaderboard = new Leaderboard(leaderboardList, 0);

        assertEquals(new ArrayList<>(), newLeaderboard.getLeaderboardList());
        assertEquals(0, newLeaderboard.getCurrentSize());
    }
}
