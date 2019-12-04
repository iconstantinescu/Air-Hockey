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

    /**
     * Check if the hashed password is valid.
     * @param passwordPlaintext password in plain text
     * @param passwordHashed the same password hashed
     * @return true if it is a valid hash or false if not
     */
    public static boolean checkPassword(String passwordPlaintext, String passwordHashed) {
        boolean passwordVerified;

        if (passwordHashed == null || !passwordHashed.startsWith("$2a$")) {
            throw new IllegalArgumentException("Invalid hash!");
        }

        passwordVerified = BCrypt.checkpw(passwordPlaintext, passwordHashed);

        return passwordVerified;
    }

}
