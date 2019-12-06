package client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;

class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;
    @Mock
    private transient Connection mockConnection;
    @Mock
    private transient PreparedStatement mockPS;
    @Mock
    private transient ResultSet mockResultSet;
    @Mock
    private transient ConnectionFactory connectionFactory;

    private transient String pwd;
    private transient String salt;
    @BeforeEach

    void setUp() throws SQLException, ClassNotFoundException {
        MockitoAnnotations.initMocks(this);

        Mockito.when(connectionFactory.createConnection(anyString())).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(anyString())).thenReturn(mockPS);
        Mockito.when(mockPS.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);

        pwd = BcryptHashing.hashPassword("pwd");
        salt = BcryptHashing.getSalt();
    }

    @Test
    public void testGetSalt() throws SQLException {

        Mockito.when(mockResultSet.getString(1)).thenReturn("salt");
        assertEquals("salt", authenticationController.getSalt("user"));
    }

    @Test
    public void testAuthenticateOk() throws SQLException {

        Mockito.when(mockResultSet.getInt(1)).thenReturn(1);
        boolean authenticated = authenticationController
                .authenticate("user", pwd, salt);
        assertEquals(true, authenticated);
    }

    @Test
    public void testAuthenticateFailed() throws SQLException {

        Mockito.when(mockResultSet.getInt(1)).thenReturn(0);
        boolean authenticated = authenticationController
                .authenticate("user", pwd, salt);
        assertEquals(false, authenticated);
    }

    @Test
    public void testSQLException() throws SQLException, ClassNotFoundException {

        Mockito.when(connectionFactory.createConnection(anyString()))
                .thenThrow(new SQLException());

        authenticationController.authenticate("user", pwd, salt);
        assertEquals("", authenticationController.getSalt("user"));

    }

}