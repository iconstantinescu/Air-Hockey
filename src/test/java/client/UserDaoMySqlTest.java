package client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UserDaoMySqlTest {

    @InjectMocks
    transient UserDaoMySql userDao;

    @Mock
    transient UserGameTrackerMySql mockUserGameTracker;

    @Mock
    transient UserAuthenticationMySql mockUserAuthentication;

    @Mock
    transient UserRegistrationMySql mockUserRegistration;

    transient User testUser;
    transient String name;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        name = "john";
        testUser = new User(1, name,  100, 2, 3);

    }

    @Test
    void authenticate() {
        Mockito.when(mockUserAuthentication.authenticate(anyString(), anyString()))
               .thenReturn(testUser);
        assertEquals(testUser, userDao.authenticate(name, "pwd"));

    }

    @Test
    void createNewUser() {
        Mockito.when(mockUserRegistration.createNewUser(anyString(), anyString(), anyString()))
                .thenReturn(true);
        assertTrue(userDao.createNewUser(name, "pwd", name));
    }

    @Test
    void updateUser() {
        Mockito.when(mockUserGameTracker.updateUserStats(testUser))
                .thenReturn(true);
        assertTrue(userDao.updateUser(testUser));
    }

    @Test
    void saveGame() {
        Mockito.when(mockUserGameTracker.saveGame(anyInt(), anyInt(), anyInt(), anyInt()))
                .thenReturn(true);
        assertTrue(userDao.saveGame(1,2,5,3));
    }

    @Test
    void getGameHistory() {
        List<GameDetails> gameList = new ArrayList<>();
        gameList.add(new GameDetails(name, "robert", 5, 3,
                new Timestamp(System.currentTimeMillis())));

        Mockito.when(mockUserGameTracker.getGameHistory(anyInt())).thenReturn(gameList);
        assertEquals(gameList, userDao.getGameHistory(1));
    }
}
