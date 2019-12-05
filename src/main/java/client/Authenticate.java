package client;

import game.Render;

public class Authenticate {

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


}
