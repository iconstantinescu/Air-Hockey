package client;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Class that is used to hash the plain text password using a salt.
 * The class uses BcryptHashing method for hashing the password.
 */
public class BcryptHashing {

    private static String salt;

    /**
     * Hash a password with a generated salt given it's plain text.
     * This method will be used when a new user creates an account.
     * The generated salt will be stored in the database and for later use.
     * @param passwordPlaintext password in plain text
     * @return hashed password as a String
     */
    public static String hashPassword(String passwordPlaintext) {
        int workload = 12;
        salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(passwordPlaintext, salt);
    }

    /**
     * Hash a password with a given salt (as a parameter).
     * @param passwordPlaintext the password in plain text
     * @param salt the salt that will be used for hashing
     * @return hashed password as a String
     */
    public static String hashPasswordWithSalt(String passwordPlaintext, String salt) {
        return BCrypt.hashpw(passwordPlaintext, salt);
    }

    /**
     * Getter for the salt field.
     * @return the salt that was used for hashing the password within the same object instance
     */
    public static String getSalt() {
        return salt;
    }


}
