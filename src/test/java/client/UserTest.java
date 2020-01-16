package client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private transient User testUser;
    private transient ArrayList<GameDetails> testGameList;
    private transient Timestamp timestamp;

    @BeforeEach
    void setUp() {
        testUser = new User(1, "john", 100, 1, 2);
        testGameList = new ArrayList<>();
        timestamp = new Timestamp(System.currentTimeMillis());
    }

    @Test
    void testEmptyConstructor() {
        User emptyUser = new User();
        assertEquals(0, emptyUser.getUserID());
        assertEquals(0, emptyUser.getPoints());
        assertEquals(0, emptyUser.getNumOfGamesPlayed());
        assertEquals(0, emptyUser.getNumOfLostGames());
        assertEquals(0, emptyUser.getNumOfWonGames());
        assertEquals("", emptyUser.getNickname());
        assertEquals(new ArrayList<>(), emptyUser.getGameHistory());

    }


    @Test
    void getUserID() {
        assertEquals(1,testUser.getUserID());
    }

    @Test
    void setUserID() {
        testUser.setUserId(5);
        assertEquals(5,testUser.getUserID());
    }

    @Test
    void getNickname() {
        assertEquals("john", testUser.getNickname());
    }

    @Test
    void setNickname() {
        testUser.setNickname("robert");
        assertEquals("robert", testUser.getNickname());
    }

    @Test
    void addPoints() {
        testUser.addPoints(100);
        assertEquals(200, testUser.getPoints());
    }

    @Test
    void getGameHistory() {

        assertEquals(testGameList, testUser.getGameHistory());
    }

    @Test
    void addGame() {
        GameDetails newGame = new GameDetails("john", "robert",
                5, 2, timestamp);

        testUser.addGame(newGame);
        testGameList.add(newGame);
        assertEquals(testGameList, testUser.getGameHistory());
    }

    @Test
    void getNumOfLostGames() {
        testUser.addLostGame();
        assertEquals(2, testUser.getNumOfLostGames());
    }

    @Test
    void setNumOfLostGames() {
        testUser.setNumOfLostGames(10);
        assertEquals(10, testUser.getNumOfLostGames());
    }

    @Test
    void getNumOfWonGames() {
        testUser.addWonGame();
        testUser.addWonGame();
        assertEquals(4, testUser.getNumOfWonGames());
    }

    @Test
    void setNumOfWonGames() {
        testUser.setNumOfWonGames(10);
        assertEquals(10, testUser.getNumOfWonGames());
    }


    @Test
    void getNumOfGamesPlayed() {
        assertEquals(3, testUser.getNumOfGamesPlayed());
    }
}