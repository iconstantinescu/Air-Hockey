import com.badlogic.gdx.Gdx;

public class SoundEffects {
    // make the collision between a puck and a wall and a puck and a pusher sound hit.wav
    //    public static void collision(Sound sound) {
    //        if (wallCollision() || pusherCollision()) {
    //            sound = Gdx.audio.newSound(Gdx.files.internal("song.wav"));
    //            sound.play();
    //        }
    //    }

    public static void backgroundSound(com.badlogic.gdx.audio.Sound sound) {
        sound = Gdx.audio.newSound(Gdx.files.internal("song.wav"));
        sound.loop();
    }
}
