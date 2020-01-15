package client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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





public class ScoreControllerTest {
    @InjectMocks
    private transient ScoreController scoreController;
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
        assertEquals(1000, scoreController.getPoints(1));
    }

    @Test
    public void testUpdatePoints() throws SQLException {

        assertTrue(scoreController.updatePoints(1,100));

    }

    @Test
    public void testSaveGame() {

        assertTrue(scoreController.saveGame(1,2,5,4));

    }

    @Test
    public void testGetGamesPlayed() throws SQLException {

        Mockito.when(mockResultSet.getInt(1)).thenReturn(5);
        assertEquals(5, scoreController.getGamesPlayed(1));
    }

    @Test
    public void testSqlException() throws SQLException, ClassNotFoundException {

        Mockito.when(connectionFactory.createConnection(anyString()))
                .thenThrow(new SQLException());

        assertFalse(scoreController.saveGame(1,2,5,4));
        assertFalse(scoreController.updatePoints(1,100));
        assertEquals(0, scoreController.getGamesPlayed(1));
        assertEquals(0, scoreController.getPoints(1));

    }



}
