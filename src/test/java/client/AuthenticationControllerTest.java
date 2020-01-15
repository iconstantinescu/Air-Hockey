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

class AuthenticationControllerTest {

    @InjectMocks
    private transient AuthenticationController authenticationController;
    @Mock
    private transient Connection mockConnection;
    @Mock
    private transient PreparedStatement mockPS;
    @Mock
    private transient ResultSet mockResultSet;
    @Mock
    private transient ConnectionFactory connectionFactory;

    private transient String username;
    private transient String pwd;
    private transient String salt;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        MockitoAnnotations.initMocks(this);

        Mockito.when(connectionFactory.createConnection(anyString())).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(anyString())).thenReturn(mockPS);
        Mockito.when(mockConnection.isClosed()).thenReturn(true);
        Mockito.when(mockPS.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);

        pwd = BcryptHashing.hashPassword("pwd");
        salt = BcryptHashing.getSalt();
        username = "user";
    }

    @Test
    public void testGetSalt() throws SQLException {

        Mockito.when(mockResultSet.getString(1)).thenReturn("salt");
        assertEquals("salt", authenticationController.getSalt(username));
    }

    @Test
    public void testAuthenticateOk() throws SQLException {

        Mockito.when(mockResultSet.getInt(1)).thenReturn(1);
        boolean authenticated = authenticationController
                .authenticate(username, pwd, salt);
        assertEquals(true, authenticated);
    }

    @Test
    public void testAuthenticateFailed() throws SQLException {

        Mockito.when(mockResultSet.getInt(1)).thenReturn(0);
        boolean authenticated = authenticationController
                .authenticate(username, pwd, salt);
        assertEquals(false, authenticated);
    }

    @Test
    public void testGetUserId() throws SQLException {

        int id = 10;
        Mockito.when(mockResultSet.getInt(1)).thenReturn(id);
        assertEquals(10, authenticationController.getUserId("john"));

    }

    @Test
    public void testSqlExceptions() throws SQLException {

        Mockito.when(connectionFactory.createConnection(anyString()))
                .thenThrow(new SQLException());

        authenticationController.authenticate(username, pwd, salt);
        assertEquals("", authenticationController.getSalt(username));
        assertEquals(-1, authenticationController.getUserId(username));

    }

}