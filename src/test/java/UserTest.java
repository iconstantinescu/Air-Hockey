import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User testUser;
    private ArrayList<GameDetails> testGameList;
    private Timestamp timestamp;

    @BeforeEach
    void setUp() {
        testUser = new User(1, "john", 100, 1, 2);
        testGameList = new ArrayList<>();
        timestamp = new Timestamp(System.currentTimeMillis());
    }

    @Test
    void getUserID() {
        Assert.assertEquals(1,testUser.getUserID());
    }

    @Test
    void getUsername() {
        Assert.assertEquals("john", testUser.getUsername());
    }

    @Test
    void setUsername() {
        testUser.setUsername("robert");
        Assert.assertEquals("robert", testUser.getUsername());
    }

    @Test
    void addPoints() {
        testUser.addPoints(100);
        Assert.assertEquals(200, testUser.getPoints());
    }

    @Test
    void getGameHistory() {

        Assert.assertEquals(testGameList, testUser.getGameHistory());
    }

    @Test
    void addGame() {
        GameDetails newGame = new GameDetails("john", "robert",
                5, 2, timestamp);

        testUser.addGame(newGame);
        testGameList.add(newGame);
        Assert.assertEquals(testGameList, testUser.getGameHistory());
    }

    @Test
    void getLostGames() {
        testUser.addLostGame();
        Assert.assertEquals(2, testUser.getNumOfLostGames());
    }

    @Test
    void getWonGames() {
        testUser.addWonGame();
        testUser.addWonGame();
        Assert.assertEquals(4, testUser.getNumOfWonGames());
    }

    @Test
    void getNumOfGamesPlayed() {
        Assert.assertEquals(3, testUser.getNumOfGamesPlayed());
    }
}