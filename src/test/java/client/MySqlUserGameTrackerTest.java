package client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;





public class MySqlUserGameTrackerTest {
    @InjectMocks
    private transient MySqlUserGameTracker mySqlUserGameTracker;
    @Mock
    private transient Connection mockConnection;
    @Mock
    private transient PreparedStatement mockPS;
    @Mock
    private transient ResultSet mockResultSet;
    @Mock
    private transient ConnectionFactory connectionFactory;

    /**
     * Setup Method.
     * @throws SQLException Exception for SQL errors
     * @throws ClassNotFoundException Exception in case the class is not found
     */
    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        MockitoAnnotations.initMocks(this);

        Mockito.when(connectionFactory.createConnection(anyString())).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(anyString())).thenReturn(mockPS);
        Mockito.when(mockPS.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    }

    @Test
    public void testGetPoints() throws SQLException {

        Mockito.when(mockResultSet.getInt(1)).thenReturn(1000);
        assertEquals(1000, mySqlUserGameTracker.getPoints(1));
    }

    @Test
    public void testUpdatePoints() throws SQLException {

        assertEquals(true, mySqlUserGameTracker.updatePoints(1,100));

    }

    @Test
    public void testSaveGame() {

        assertEquals(true, mySqlUserGameTracker.saveGame(1,2,5,4));

    }

    @Test
    public void testGetGamesPlayed() throws SQLException {

        Mockito.when(mockResultSet.getInt(1)).thenReturn(5);
        assertEquals(5, mySqlUserGameTracker.getGamesPlayed(1));
    }

    @Test
    public void testSqlException() throws SQLException, ClassNotFoundException {

        Mockito.when(connectionFactory.createConnection(anyString()))
                .thenThrow(new SQLException());

        assertEquals(false, mySqlUserGameTracker.saveGame(1,2,5,4));
        assertEquals(false, mySqlUserGameTracker.updatePoints(1,100));
        assertEquals(0, mySqlUserGameTracker.getGamesPlayed(1));
        assertEquals(0, mySqlUserGameTracker.getPoints(1));

    }



}
