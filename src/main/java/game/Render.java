package game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import objects.Puck;
import objects.Pusher;
import objects.ScoreBoard;
import utilities.CollisionTracker;

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
    private static ScoreBoard scoreBoard;

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
        scoreBoard = new ScoreBoard(0,0);
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


        // Check if Puck can enter gate, if yes then act
        if (puck.checkInGateRange(scoreBoard, Gdx.graphics.getWidth(), Gdx.graphics.getHeight())) {
            puck.gateBehaviour(scoreBoard, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        } else {
            puck.checkWallCollision(Gdx.graphics.getWidth(),
                    Gdx.graphics.getHeight());
        }

        // Collision between Pusher 1 and the puck
        pusher1.checkAndExecuteCollision(puck);

        // Check and execute collision between Pusher 2 and Puck
        pusher2.checkAndExecuteCollision(puck);

        puck.translate(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.RED);
        shape.circle(puck.getposX(), puck.getposY(), puck.getRadius());
        shape.end();

        // Pusher position update and rendering
        boolean[] restricts = new boolean[4];

        pusher1.restrictMovementOnWall(true, restricts,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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



        pusher2.restrictMovementOnWall(false, restricts,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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