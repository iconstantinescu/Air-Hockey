package client;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptHashing {

    private static String salt;

    /**
     * Hash a password.
     * @param passwordPlaintext password in plain text
     * @return hashed password as a string
     */
    public static String hashPassword(String passwordPlaintext) {
        int workload = 12;
        salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(passwordPlaintext, salt);
    }

    public static String getSalt() {
        return salt;
    }


}
