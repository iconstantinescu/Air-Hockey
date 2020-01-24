package client;

/**
 * Interface for user registration related methods.
 */
public interface UserRegistration {

    boolean createNewUser(String username, String password, String nickname);
}
