package client;

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


class UserRegistrationMySqlTest {


    @InjectMocks
    private transient UserDaoMySql userDaoMySql;
    @Mock
    private transient Connection mockConnection;
    @Mock
    private transient PreparedStatement mockPS;
    @Mock
    private transient ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);

        Mockito.when(mockConnection.prepareStatement(anyString())).thenReturn(mockPS);
        Mockito.when(mockPS.execute()).thenReturn(true);
        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    }

    @Test
    void testCreateNewUser() {

        boolean created = userDaoMySql
                .createNewUser("user", "pwd", "john");
        assertTrue(created);
    }

    @Test
    void testSqlException() throws SQLException {

        Mockito.when(mockConnection.prepareStatement(anyString()))
                .thenThrow(new SQLException());

        userDaoMySql.createNewUser("user", "pwd", "john");
    }


}