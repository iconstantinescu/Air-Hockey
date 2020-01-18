package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import objects.GameObject;
import objects.Wall;

/**
 * The class containing the main which is calling the rendering methods.
 */
public class Main {

    /**
     * Main function of the game, where the window is being created,
     * and the method rendering the game is invoked..
     *
     * @param args Args that can be passed from the console
     */
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1280;
        config.height = 720;
        config.title = "Air Hockey";

        new LwjglApplication(new Render(), config);



    }


}