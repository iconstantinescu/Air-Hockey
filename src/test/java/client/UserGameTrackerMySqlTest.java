package client;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UserGameTrackerMySqlTest {
    @InjectMocks
    private transient UserGameTrackerMySql userGameTrackerMySql;
    @Mock
    private transient Connection mockConnection;
    @Mock
    private transient PreparedStatement mockPS;
    @Mock
    private transient ResultSet mockResultSet;
    @Mock
    private transient ConnectionFactory connectionFactory;

    User testUser;

    /**
     * Setup Method.
     * @throws SQLException Exception for SQL errors
     * @throws ClassNotFoundException Exception in case the class is not found
     */
    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);

        testUser = new User(1, "john",  100, 2, 3);

        Mockito.when(connectionFactory.createConnection(anyString())).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(anyString())).thenReturn(mockPS);
        Mockito.when(mockPS.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    }

    @Test
    public void testSaveGame() {

        assertTrue(userGameTrackerMySql.saveGame(1,2,5,4));

    }

    @Test
    public void testSqlExceptions() throws SQLException {

        Mockito.when(connectionFactory.createConnection(anyString()))
                .thenThrow(new SQLException());

        assertFalse(userGameTrackerMySql.saveGame(1,2,5,4));
        assertFalse(userGameTrackerMySql.updateUserStats(testUser));
        assertEquals(new ArrayList<>(), userGameTrackerMySql.getGameHistory(1));

    }

    @Test
    void updateUserStats() {

        assertTrue(userGameTrackerMySql.updateUserStats(testUser));
    }

    @Test
    void getGameHistory() throws SQLException {

        Long time = System.currentTimeMillis();
        List<GameDetails> gameList = new ArrayList<>();
        gameList.add(new GameDetails("john", "robert", 5, 3,
                new Timestamp(time)));

        Mockito.when(mockResultSet.getInt("user_id_1")).thenReturn(1);
        Mockito.when(mockResultSet.getInt("user_id_2")).thenReturn(2);
        Mockito.when(mockResultSet.getInt("score_user_1")).thenReturn(5);
        Mockito.when(mockResultSet.getInt("score_user_2")).thenReturn(3);
        Mockito.when(mockResultSet.getLong("game_timestamp")).thenReturn(time);

        ResultSet otherRs = Mockito.mock(ResultSet.class);

        Mockito.when(mockPS.executeQuery()).thenReturn(mockResultSet).thenReturn(otherRs);
        Mockito.when(otherRs.next()).thenReturn(true).thenReturn(true);
        Mockito.when(otherRs.getString("nickname"))
                .thenReturn("john").thenReturn("robert");


        List<GameDetails> resultList = userGameTrackerMySql.getGameHistory(1);
        assertEquals(gameList, resultList);
    }
}
