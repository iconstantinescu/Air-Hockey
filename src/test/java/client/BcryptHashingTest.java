package client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BcryptHashingTest {


    @Test
    public void testHashPassword() {
        String hashedPassword = BcryptHashing.hashPassword("pwd");
        String salt = BcryptHashing.getSalt();
        assertEquals(hashedPassword, BcryptHashing.hashPasswordWithSalt("pwd", salt));
    }
}
