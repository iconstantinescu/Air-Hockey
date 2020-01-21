package utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BcryptHashingTest {


    @Test
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    void testHashPassword() {

        BcryptHashing bcryptHashing = new BcryptHashing(); //required for test coverage
        String hashedPassword = BcryptHashing.hashPasswordWithGeneratedSalt("pwd");
        String salt = BcryptHashing.getSalt();
        assertEquals(hashedPassword, BcryptHashing.hashPasswordWithGivenSalt("pwd", salt));
    }
}
