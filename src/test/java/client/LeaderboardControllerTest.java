package client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class LeaderboardControllerTest {

    @InjectMocks
    private transient LeaderboardController leaderboardController;
    @Mock
    private transient Connection mockConnection;
    @Mock
    private transient PreparedStatement mockPS;
    @Mock
    private transient ResultSet mockResultSet;
    @Mock
    private transient ConnectionFactory connectionFactory;


    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);

        Mockito.when(connectionFactory.createConnection(anyString())).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(anyString())).thenReturn(mockPS);
        Mockito.when(mockPS.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    }

    @Test
    void getTopN() throws SQLException {

        String nickname = "john";
        int points = 100;
        Mockito.when(mockResultSet.getString(1)).thenReturn(nickname);
        Mockito.when(mockResultSet.getInt(2)).thenReturn(points);

        List<LeaderboardInstance> resultList = leaderboardController.getTopN(1);
        assertEquals(nickname, resultList.get(0).getNickname());
        assertEquals(points, resultList.get(0).getPoints());
    }

    @Test
    public void testSqlException() throws SQLException {

        Mockito.when(connectionFactory.createConnection(anyString()))
                .thenThrow(new SQLException());

        assertEquals(new ArrayList<>(), leaderboardController.getTopN(0));
    }
}