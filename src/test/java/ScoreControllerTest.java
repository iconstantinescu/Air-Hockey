import static org.mockito.Matchers.anyString;

import client.ScoreController;
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
    private ScoreController dbController;
    @Mock
    private transient Connection mockConnection;
    @Mock
    private transient PreparedStatement mockPS;
    @Mock
    private transient ResultSet mockResultSet;

    /**
     * Setup Method.
     * @throws SQLException Exception for SQL errors
     * @throws ClassNotFoundException Exception in case the class is not found
     */
    @BeforeEach
    public void setUp() throws SQLException, ClassNotFoundException {
        MockitoAnnotations.initMocks(this);

        //Mockito.when(DriverManager.getConnection(anyString())).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(anyString())).thenReturn(mockPS);
        Mockito.when(mockPS.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    }

    @Test
    public void testGetPoints() throws SQLException {

        /*
        Mockito.when(mockResultSet.getInt(1)).thenReturn(1000);

        Assert.assertEquals(1000, dbController.getPoints(1));
        Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
        */
    }
}
