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


class userRegistrationMySqlTest {

    @InjectMocks
    private transient UserRegistrationMySql registrationController;
    @Mock
    private transient Connection mockConnection;
    @Mock
    private transient PreparedStatement mockPS;
    @Mock
    private transient ResultSet mockResultSet;
    @Mock
    private transient ConnectionFactory connectionFactory;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        MockitoAnnotations.initMocks(this);

        Mockito.when(connectionFactory.createConnection(anyString())).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(anyString())).thenReturn(mockPS);
        Mockito.when(mockPS.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    }

    @Test
    public void testCreateNewUser() throws SQLException {

        boolean created = registrationController
                .createNewUser("user", "pwd", "john");
        assertEquals(true, created);
    }

    @Test
    public void testSqlException() throws SQLException, ClassNotFoundException {

        Mockito.when(connectionFactory.createConnection(anyString()))
                .thenThrow(new SQLException());

        registrationController.createNewUser("user", "pwd", "john");
    }


}