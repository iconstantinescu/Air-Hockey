package client;

import game.Render;

public class Client {

    /**
     * Authenticates username and password.
     * @param passInput inputted password.
     * @param nameInput inputted username.
     * @return if combination is found in the database.
     */
    public boolean authenticate(String passInput, String nameInput) {
        AuthenticationController authenticationController =
                new AuthenticationController(new ConnectionFactory());
        String salt = authenticationController.getSalt(passInput);
        return authenticationController.authenticate(nameInput, passInput, salt);
    }

    /**
     * registers user in database.
     * @param passInput inputted password.
     * @param nameInput inputted name.
     * @param nickname inputted nickname.
     */
    public void register(String nameInput, String passInput, String nickname) {
        RegistrationController registrationController =
                new RegistrationController(new ConnectionFactory());
        registrationController.createNewUser(nameInput, passInput, nameInput);
        System.out.println("registration called " + nameInput);
    }


}
