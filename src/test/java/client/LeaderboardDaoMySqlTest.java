package client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

public class LeaderboardDaoMySqlTest {

    @InjectMocks
    transient LeaderboardDaoMySql leaderboardDaoMySql;

    @Mock
    transient Connection mockConnection;
    @Mock
    transient PreparedStatement mockPS;
    @Mock
    transient ResultSet mockResultSet;
    @Mock
    transient ConnectionFactory connectionFactory;


    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);

        Mockito.when(connectionFactory.createConnection(anyString())).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(anyString())).thenReturn(mockPS);
        Mockito.when(mockPS.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    }

    @Test
    void getLeaderboard() throws SQLException {

        Leaderboard leaderboard = new Leaderboard(1);
        LeaderboardEntry leaderboardEntry = new LeaderboardEntry("john", 100);
        leaderboard.addEntry(leaderboardEntry);

        Mockito.when(mockResultSet.getString(1)).thenReturn("john");
        Mockito.when(mockResultSet.getInt(2)).thenReturn(100);

        assertEquals(leaderboard, leaderboardDaoMySql.getLeaderboard(1));
    }

    @Test
    void getLeaderboardPosition() throws SQLException {

        int position = 10;
        Mockito.when(mockResultSet.getInt(anyInt())).thenReturn(position);
        assertEquals(position, leaderboardDaoMySql.getLeaderboardPosition(1));

    }

    @Test
    void testExceptions() throws SQLException {

        Mockito.when(connectionFactory.createConnection(anyString()))
                .thenThrow(new SQLException());
        assertEquals(-1, leaderboardDaoMySql.getLeaderboardPosition(1));
        assertEquals(new Leaderboard(10), leaderboardDaoMySql.getLeaderboard(10));
    }
}