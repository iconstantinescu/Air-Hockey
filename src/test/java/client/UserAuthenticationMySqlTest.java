package client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

class UserAuthenticationMySqlTest {

    @InjectMocks
    private transient UserAuthenticationMySql authenticationController;
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
    public void testAuthenticateOk() throws SQLException {

        Mockito.when(mockResultSet.getInt("user_id")).thenReturn(1);
        Mockito.when(mockResultSet.getString("nickname")).thenReturn("name");
        Mockito.when(mockResultSet.getLong("points")).thenReturn(100L);
        Mockito.when(mockResultSet.getInt("games_won")).thenReturn(2);
        Mockito.when(mockResultSet.getInt("games_lost")).thenReturn(3);
        User authenticatedUser = authenticationController.authenticate(username, pwd);
        assertNotEquals(null, authenticatedUser);
    }

    @Test
    public void testAuthenticateFailed() throws SQLException {

        Mockito.when(mockResultSet.getInt(1)).thenReturn(0);
        User authenticatedUser = authenticationController
                .authenticate(username, pwd);
        assertEquals(null, authenticatedUser);
    }

}