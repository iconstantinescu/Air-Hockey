package menu;

import com.badlogic.gdx.Gdx;
import objects.Puck;
import objects.Pusher;
import utilities.MathUtils;

/**
 * This is the class that sets the sound effects.
 * Here you will find the sounds that are made and when they will be made.
 */
public class SoundEffects {

    /**
     * Whens starting the game, you will hear a background sound.
     * The background sound will be played as long as you are on the game screen.
     *
     * @param sound The sound that will be played
     */
    public static void backgroundSound(com.badlogic.gdx.audio.Music sound) {
        sound = Gdx.audio.newMusic(Gdx.files.internal("media/song.wav"));
        sound.setLooping(true);
        sound.play();
    }

    /**
     * Make a hit sound when a pusher hits the puck or the puck hits the wall.
     * You will hear a single hit.
     *
     * @param sound  The sound to be played
     * @param puck   The puck that hits
     * @param pusher The pusher to hit the puck
     */
    public static void hitSound(com.badlogic.gdx.audio.Sound sound, Puck puck, Pusher pusher,
                                int screenWidth, int screenHeigth) {
        if (MathUtils.checkRadius(pusher.getposX(), pusher.getposY(), pusher.getRadius(),
                puck.getposX(), puck.getposY(), puck.getRadius())
                || hitWall(screenWidth, screenHeigth, puck)) {
            sound = Gdx.audio.newSound(Gdx.files.internal("media/hit.wav"));
            sound.play();
        }
    }

    /**
     * Check if the puck hits a wall to know when to play a hit sound.
     * You will hear a single hit.
     *
     * @param screenWidth  The width of the screen
     * @param screenHeight The height of the screen
     * @param puck         The puck
     * @return true if you hit a wall
     */
    public static boolean hitWall(int screenWidth, int screenHeight, Puck puck) {
        if (puck.getposY() - puck.getRadius() <= 0) {
            return true;
        }

        if (puck.getposY() + puck.getRadius() >= screenHeight) {
            return true;
        }

        if (puck.getposX() - puck.getRadius() <= 0) {
            return true;
        }
        if (puck.getposX() + puck.getRadius() >= screenWidth) {
            return true;
        }

        return false;
    }
}
