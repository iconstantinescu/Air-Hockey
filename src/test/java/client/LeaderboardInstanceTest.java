package client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeaderboardInstanceTest {

    private transient LeaderboardInstance leaderboardInstance;
    private transient String nickname;
    private transient long points;

    @BeforeEach
    void setUp() {

        nickname = "john";
        points = 100;
        leaderboardInstance = new LeaderboardInstance(nickname, points);
    }

    @Test
    void getNickname() {
        assertEquals(nickname, leaderboardInstance.getNickname());
    }

    @Test
    void setNickname() {
        leaderboardInstance.setNickname("Robert");
        assertEquals("Robert", leaderboardInstance.getNickname());
    }

    @Test
    void getPoints() {
        assertEquals(points, leaderboardInstance.getPoints());
    }

    @Test
    void setPoints() {
        leaderboardInstance.setPoints(200);
        assertEquals(200, leaderboardInstance.getPoints());
    }


}
