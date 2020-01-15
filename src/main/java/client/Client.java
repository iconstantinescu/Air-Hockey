package client;

/**
 * Class for authenticating and registering the user.
 */
public class Client {

    /**
     * Authenticates username and password.
     *
     * @param passInput inputted password.
     * @param nameInput inputted username.
     * @return if combination is found in the database.
     */
    public boolean authenticate(String passInput, String nameInput) {
        if (passInput.equals("") || nameInput.equals("")) {
            System.out.println("empty field");
            return false;
        }
        AuthenticationController authenticationController =
                new AuthenticationController(new ConnectionFactory());
        String salt = authenticationController.getSalt(nameInput);
        return authenticationController.authenticate(nameInput, passInput, salt);
    }

    /**
     * registers user in database.
     *
     * @param passInput inputted password.
     * @param nameInput inputted name.
     * @param nickname  inputted nickname.
     */
    public boolean register(String nameInput, String passInput, String nickname) {
        RegistrationController registrationController =
                new RegistrationController(new ConnectionFactory());
        return registrationController.createNewUser(nameInput, passInput, nameInput);

    }


}
