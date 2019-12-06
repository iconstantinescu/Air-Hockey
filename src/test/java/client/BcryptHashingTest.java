package client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class BcryptHashingTest {


    @Test
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void testHashPassword() {

        BcryptHashing bcryptHashing = new BcryptHashing();
        String hashedPassword = BcryptHashing.hashPassword("pwd");
        String salt = BcryptHashing.getSalt();
        assertEquals(hashedPassword, BcryptHashing.hashPasswordWithSalt("pwd", salt));
    }
}
