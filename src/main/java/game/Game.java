package game;

import client.*;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import login.RenderLogin;


public class Game {

    /**
     * Main function of the game, where the window is being created.
     *
     * @param args Args that can be passed from the console
     */
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1280;
        config.height = 720;
        config.title = "Air Hockey";

        new LwjglApplication(new RenderLogin(), config);

        //RegistrationController registrationController = new RegistrationController(new ConnectionFactory());
        //registrationController.createNewUser("test", "test", "john");

        AuthenticationController authenticationController = new AuthenticationController(new ConnectionFactory());
        String salt = authenticationController.getSalt("test");
        System.out.println(authenticationController.authenticate("test", "test", salt));


    }


}