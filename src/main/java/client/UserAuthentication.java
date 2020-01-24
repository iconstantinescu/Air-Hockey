package client;

/**
 * Interface for user authentication via a database/system.
 */
public interface UserAuthentication {

    User authenticate(String username, String password);
}
