package objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

import client.Leaderboard;
import client.LeaderboardDaoMySql;
import client.LeaderboardEntry;
import client.User;
import client.UserDaoMySql;
import game.Render;
import java.sql.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import utilities.InformationDrawer;


class ScoreBoardTest {

    @InjectMocks
    private transient ScoreBoard scoreBoard;
    @Mock
    private transient InformationDrawer informationDrawer;
    @Mock
    private transient UserDaoMySql userDaoMock;
    @Mock
    private transient Connection connectionMock;
    @Mock
    private transient User user1Mock;
    @Mock
    private transient User user2Mock;
    @Mock
    private transient LeaderboardDaoMySql leaderboardDaoMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void regularConstructor() {
        scoreBoard = new ScoreBoard(0, 1);
        assertEquals(0, scoreBoard.getPlayer1Score());
        assertEquals(1, scoreBoard.getPlayer2Score());
    }

    @Test
    void isGameOverWinner1() {
        scoreBoard = new ScoreBoard(5, 4, informationDrawer);

        assertTrue(scoreBoard.isGameOver());
    }

    @Test
    void isGameOverWinner2() {
        scoreBoard = new ScoreBoard(4, 5, informationDrawer);

        assertTrue(scoreBoard.isGameOver());
    }

    @Test
    void isGameOverNoWinner() {
        scoreBoard = new ScoreBoard(4, 4, informationDrawer);

        assertFalse(scoreBoard.isGameOver());
    }

    @Test
    void getWinnerP1() {
        scoreBoard = new ScoreBoard(5, 4, informationDrawer);

        assertTrue(scoreBoard.getWinner());
    }

    @Test
    void getWinnerP2() {
        scoreBoard = new ScoreBoard(4, 5, informationDrawer);

        assertFalse(scoreBoard.getWinner());
    }

    @Test
    void incrementScoreP1() {
        scoreBoard = new ScoreBoard(4, 3, informationDrawer);
        scoreBoard.matchUploaded = true;

        scoreBoard.pointP1();
        assertEquals(true, scoreBoard.matchUploaded);

    }

    @Test
    void incrementScoreP2() {
        Render.userDao = userDaoMock;
        Render.user1 = user1Mock;
        Render.user2 = user2Mock;
        scoreBoard = new ScoreBoard(4, 4, informationDrawer);

        Mockito.when(userDaoMock.saveGame(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(true);
        scoreBoard.pointP2();
        assertTrue(scoreBoard.matchUploaded);

    }

    @Test
    void uploadMatchP1() {
        Render.userDao = userDaoMock;
        Render.user1 = user1Mock;
        Render.user2 = user2Mock;
        scoreBoard = new ScoreBoard(5, 4, informationDrawer);

        Mockito.when(userDaoMock.saveGame(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(true);
        assertTrue(scoreBoard.uploadMatch(false));

    }

    @Test
    void drawTopScoresWithNull() {
        scoreBoard = new ScoreBoard(5, 4, informationDrawer);
        Render.leaderboardDao = leaderboardDaoMock;

        Leaderboard someLeaderBoard = new Leaderboard(5);

        someLeaderBoard.addEntry(new LeaderboardEntry());

        Mockito.when(leaderboardDaoMock.getLeaderboard(anyInt())).thenReturn(someLeaderBoard);

        scoreBoard.drawTopScores(5, 0, 0, 1000, 1000);



        Mockito.verify(informationDrawer,
                Mockito.atLeast(3)).drawText(anyString(),
                anyFloat(), anyFloat(),anyInt());
    }

    @Test
    void drawTopScores() {
        scoreBoard = new ScoreBoard(5, 4, informationDrawer);
        Render.leaderboardDao = leaderboardDaoMock;

        scoreBoard.leaderboard = new Leaderboard(5);

        scoreBoard.drawTopScores(5, 0, 0, 1000, 1000);



        Mockito.verify(informationDrawer,
                Mockito.atLeast(2)).drawText(anyString(),
                anyFloat(), anyFloat(),anyInt());
    }

    @Test
    void winnerNumberP2() {
        scoreBoard = new ScoreBoard(0, 5, informationDrawer);
        assertEquals(2,scoreBoard.winnerNumber());
    }

}