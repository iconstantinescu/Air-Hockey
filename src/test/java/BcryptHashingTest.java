import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import utilities.BcryptHashing;


class BcryptHashingTest {


    @Test
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    void testHashPassword() {

        BcryptHashing bcryptHashing = new BcryptHashing();
        String hashedPassword = bcryptHashing.hashPasswordWithGeneratedSalt("pwd");
        String salt = bcryptHashing.getSalt();
        assertEquals(hashedPassword, BcryptHashing.hashPasswordWithGivenSalt("pwd", salt));
    }
}
