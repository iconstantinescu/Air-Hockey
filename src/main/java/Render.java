import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Render extends ApplicationAdapter {
    private static SpriteBatch batch;
    private static Texture img;
    private static Sprite sprite;
    private static ShapeRenderer shape;
    private static Pusher pusher;
    private static Puck puck;

    @Override
    public void create() {
        pusher = new Pusher(300, 100, 40);

        batch = new SpriteBatch();
        img = new Texture("field.png");

        sprite = new Sprite(img);

        sprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        shape = new ShapeRenderer();

        puck = new Puck(30, 30, 15, 3, 3);

        //sprite.setScale(1,2);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(sprite, sprite.getX() - sprite.getWidth() / 2,
                sprite.getY() - sprite.getHeight() / 2, sprite.getWidth(),
                sprite.getHeight(), sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(),
                sprite.getScaleY(), sprite.getRotation());
        batch.end();


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

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        shape.dispose();

    }
}