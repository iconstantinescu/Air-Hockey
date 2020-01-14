package client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void sizeExceededTest() {
        testLeaderboard.setSizeLimit(1);


        //Should keep just the first entry of the list
        leaderboardList.remove(1);
        assertEquals(leaderboardList, testLeaderboard.getLeaderboardList());
        assertEquals(1, testLeaderboard.getCurrentSize());

    }
}
