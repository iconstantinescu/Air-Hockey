import org.junit.Assert;
import org.junit.Before;


import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.mockito.Matchers.anyString;

public class DatabaseControllerTest {
    @InjectMocks
    private DatabaseController dbController;
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPS;
    @Mock
    private ResultSet mockResultSet;

    @Before
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
