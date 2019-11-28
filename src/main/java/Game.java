import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class Game {

    /**
     * Main function of the game, where the window is being created.
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