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
    private static Pusher pusher1;
    private static Pusher pusher2;
    private static Puck puck;
    private static CollisionTracker collisionTracker;
    private static boolean[] restricts1;

    @Override
    public void create() {

        pusher1 = new Pusher(300, 100, 40);
        pusher2 = new Pusher(1000, 100, 40);

        batch = new SpriteBatch();
        img = new Texture("field.png");

        sprite = new Sprite(img);

        sprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        shape = new ShapeRenderer();

        puck = new Puck(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 15, 0, 0);

        collisionTracker = new CollisionTracker(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        //        if (puck.getposX() + puck.getRadius()
        //                 >= Gdx.graphics.getWidth() || puck.getposX() - puck.getRadius() <= 0) {
        //            puck.setDeltaX(-puck.getDeltaX());
        //        }
        //
        //        if (puck.getposY() + puck.getRadius() >= Gdx.graphics.getHeight()
        //                || puck.getposY() - puck.getRadius() <= 0) {
        //            puck.setDeltaY(-puck.getDeltaY());
        //        }


        // Check if Puck can enter gate, if yes then act


        CollisionTracker.checkWallCollision(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), puck);

        // Collision between Pusher 1 and the puck
        if (MathUtils.euclideanDistance(pusher1.getposX(),
                pusher1.getposY(), puck.getposX(),
                puck.getposY()) <= puck.getRadius() + pusher1.getRadius()) {
            double[] deltas = MathUtils.reflect(pusher1.getposX(),
                    pusher1.getposY(), puck.getposX(), puck.getposY());
            //puck.setDeltaX(-puck.getDeltaX());
            //puck.setDeltaY(-puck.getDeltaY());
            puck.setDeltaX((float) deltas[0] * 6f);
            puck.setDeltaY((float) deltas[1] * 6f);
        }

        if (MathUtils.euclideanDistance(pusher2.getposX(),
                pusher2.getposY(), puck.getposX(),
                puck.getposY()) <= puck.getRadius() + pusher2.getRadius()) {
            double[] deltas = MathUtils.reflect(pusher2.getposX(),
                    pusher2.getposY(), puck.getposX(), puck.getposY());
            //puck.setDeltaX(-puck.getDeltaX());
            //puck.setDeltaY(-puck.getDeltaY());
            puck.setDeltaX((float) deltas[0] * 6f);
            puck.setDeltaY((float) deltas[1] * 6f);
        }

        //System.out.println(puck.getposX() + puck.getRadius());

        puck.translate();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.RED);
        shape.circle(puck.getposX(), puck.getposY(), puck.getRadius());
        shape.end();

        // Pusher position update and rendering

         boolean restricts[] = collisionTracker.restrictMovementOnWall(pusher1.getposX(), pusher1.getposY(), pusher1.getRadius(), 1);



//        System.out.println(pusher1.getposX() + " " +  pusher1.getposY());
//
//        System.out.println(restricts[2]);

        if (Gdx.input.isKeyPressed(51) && !restricts[0]) {
                pusher1.setposY(pusher1.getposY() + 4);
        }
        if (Gdx.input.isKeyPressed(47) && !restricts[2]) {
            pusher1.setposY(pusher1.getposY() - 4);
        }
        if (Gdx.input.isKeyPressed(29) && !restricts[1]) {
                pusher1.setposX(pusher1.getposX() - 4);
        }
        if (Gdx.input.isKeyPressed(32) && !restricts[3]) {
            pusher1.setposX(pusher1.getposX() + 4);
        }

        restricts = collisionTracker.restrictMovementOnWall(pusher2.getposX(), pusher2.getposY(), pusher2.getRadius(), 2);

        if (Gdx.input.isKeyPressed(37)  && !restricts[0]) {
            pusher2.setposY(pusher2.getposY() + 4);
        }
        if (Gdx.input.isKeyPressed(39)  && !restricts[2]) {
            pusher2.setposY(pusher2.getposY() - 4);
        }
        if (Gdx.input.isKeyPressed(38)  && !restricts[1]) {
            pusher2.setposX(pusher2.getposX() - 4);
        }
        if (Gdx.input.isKeyPressed(40)  && !restricts[3]) {
            pusher2.setposX(pusher2.getposX() + 4);
        }

        // Render Pusher 1
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.FIREBRICK);
        shape.circle(pusher1.getposX(), pusher1.getposY(), pusher1.getRadius());
        shape.end();

        // Render Pusher 2
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.FIREBRICK);
        shape.circle(pusher2.getposX(), pusher2.getposY(), pusher2.getRadius());
        shape.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        shape.dispose();

    }
}