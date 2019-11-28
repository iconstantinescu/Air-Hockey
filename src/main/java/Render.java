import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Render extends ApplicationAdapter {
    private static SpriteBatch batch;
    private static SpriteBatch playBatch;
    private static SpriteBatch scoresBatch;
    private static SpriteBatch quitBatch;
    private static Texture img;
    private static Texture home;
    private static Texture play;
    private static Texture scores;
    private static Texture quit;
    private static Sprite sprite;
    private static Sprite homeSprite;
    private static Sprite playSprite;
    private static Sprite scoresSprite;
    private static Sprite quitSprite;
    private static ShapeRenderer shape;
    private static Pusher pusher;
    private static Puck puck;
    private static Sound backSound;
    private static Sound hitSound;

    private static RenderMenu renderMenu = new RenderMenu();
    private static SoundEffects soundEffects = new SoundEffects();

    @Override
    public void create() {
        pusher = new Pusher(300, 100, 40);

        batch = new SpriteBatch();
        playBatch = new SpriteBatch();
        scoresBatch = new SpriteBatch();
        quitBatch = new SpriteBatch();

        img = new Texture("field.png");

        home = new Texture("home.png");
        homeSprite = new Sprite(home);
        homeSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        play = new Texture("play.png");
        playSprite = new Sprite(play);
        playSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        playSprite.setPosition(playSprite.getX(),
                playSprite.getY() + playSprite.getHeight() / 2);

        scores = new Texture("scores.png");
        scoresSprite = new Sprite(scores);
        scoresSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        quit = new Texture("quit.png");
        quitSprite = new Sprite(quit);
        quitSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        quitSprite.setPosition(quitSprite.getX(),
                quitSprite.getY() - quitSprite.getHeight() / 2);

        sprite = new Sprite(img);

        sprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        shape = new ShapeRenderer();

        puck = new Puck(30, 30, 15, 3, 3);

        soundEffects.backgroundSound(backSound);

        //sprite.setScale(1,2);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderMenu.setHome(homeSprite, batch);

        //        batch.begin();
        //        batch.draw(sprite, sprite.getX() - sprite.getWidth() / 2,
        //                sprite.getY() - sprite.getHeight() / 2, sprite.getWidth(),
        //                sprite.getHeight(), sprite.getWidth(),
        //                sprite.getHeight(), sprite.getScaleX(),
        //                sprite.getScaleY(), sprite.getRotation());
        //        batch.end();

        // Puck position update and rendering
        if (puck.getposX() + puck.getRadius()
                >= Gdx.graphics.getWidth() || puck.getposX() - puck.getRadius() <= 0) {
            puck.setDeltaX(-puck.getDeltaX());
        }

        if (puck.getposY() + puck.getRadius() >= Gdx.graphics.getHeight()
                || puck.getposY() - puck.getRadius() <= 0) {
            puck.setDeltaY(-puck.getDeltaY());
        }

        if (MathUtils.euclideanDistance(pusher.getposX(),
                pusher.getposY(), puck.getposX(),
                puck.getposY()) <= puck.getRadius() + pusher.getRadius()) {
            double[] deltas = MathUtils.reflect(pusher.getposX(),
                    pusher.getposY(), puck.getposX(), puck.getposY());
            //puck.setDeltaX(-puck.getDeltaX());
            //puck.setDeltaY(-puck.getDeltaY());
            puck.setDeltaX((float) deltas[0] * 4.5f);
            puck.setDeltaY((float) deltas[1] * 4.5f);
        }

        //System.out.println(puck.getposX() + puck.getRadius());

        puck.translate();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.RED);
        shape.circle(puck.getposX(), puck.getposY(), puck.getRadius());
        shape.end();

        // Pusher position update and rendering

        if (Gdx.input.isKeyPressed(51)) {
            pusher.setposY(pusher.getposY() + 4);
        }
        if (Gdx.input.isKeyPressed(47)) {
            pusher.setposY(pusher.getposY() - 4);
        }
        if (Gdx.input.isKeyPressed(29)) {
            pusher.setposX(pusher.getposX() - 4);
        }
        if (Gdx.input.isKeyPressed(32)) {
            pusher.setposX(pusher.getposX() + 4);
        }

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.FIREBRICK);
        shape.circle(pusher.getposX(), pusher.getposY(), pusher.getRadius());
        shape.end();

        renderMenu.setPlay(playSprite, playBatch);
        renderMenu.setScores(scoresSprite, scoresBatch);
        renderMenu.setQuit(quitSprite, quitBatch);
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        shape.dispose();
        backSound.dispose();
    }
}