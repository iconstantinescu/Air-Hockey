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
    public void testSaveGame() {

        assertEquals(true, userGameTrackerMySql.saveGame(1,2,5,4));

    }

    @Test
    public void testSqlException() throws SQLException, ClassNotFoundException {

        Mockito.when(connectionFactory.createConnection(anyString()))
                .thenThrow(new SQLException());

        assertEquals(false, userGameTrackerMySql.saveGame(1,2,5,4));

    }

}
