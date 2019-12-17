package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import menu.SoundEffects;
import objects.Puck;
import objects.Pusher;
import objects.ScoreBoard;

public class RenderGame implements Renderer {
    private transient ShapeRenderer shape;
    private transient Pusher pusher1;
    private transient Pusher pusher2;
    private transient Puck puck;
    private static boolean[] restricts1;
    private transient ScoreBoard scoreBoard;
    private transient Texture img;
    private transient Sprite sprite;
    private transient SpriteBatch batch;
    private static Sound backSound;
    private static Sound hitSound;
    private transient BitmapFont font;

    /**
     * Constructor for the Renderer.
     */
    public RenderGame() {
        pusher1 = new Pusher(300, 100, 40);
        pusher2 = new Pusher(800, 360, 40);

        img = new Texture("media/field.png");
        sprite = new Sprite(img);
        sprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        batch = new SpriteBatch();

        shape = new ShapeRenderer();
        puck = new Puck(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 15, 0, 0);
        scoreBoard = new ScoreBoard(0, 0);
        font = new BitmapFont();



        SoundEffects.backgroundSound(backSound);
    }

    /**
     * Method that runs the rendering of the game.
     */
    public void run() {
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

        if (Gdx.input.isKeyPressed(37) && !restricts[0]) {
            pusher2.setposY(pusher2.getposY() + 4);
        }
        if (Gdx.input.isKeyPressed(39) && !restricts[2]) {
            pusher2.setposY(pusher2.getposY() - 4);
        }
        if (Gdx.input.isKeyPressed(38) && !restricts[1]) {
            pusher2.setposX(pusher2.getposX() - 4);
        }
        if (Gdx.input.isKeyPressed(40) && !restricts[3]) {
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

        SoundEffects.hitSound(hitSound, puck, pusher1,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        SoundEffects.hitSound(hitSound, puck, pusher2,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // RENDER THE SCORE
        batch.begin();
        font.setColor(0,0,0,1);
        font.getData().setScale(4);
        font.draw(batch, scoreBoard.getPlayer1Score() + " : "
                + scoreBoard.getPlayer2Score(), (Gdx.graphics.getWidth() / 2) - 50,
                Gdx.graphics.getHeight() - 20);
        batch.end();


        // DRAW THE WALLS
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.BLUE);
        shape.rect(0,0, Gdx.graphics.getWidth(), 5);
        shape.rect(0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), -5);
        shape.end();

        // DRAW THE LEFT GATE
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.BLUE);
        shape.rect(0, Gdx.graphics.getHeight(), 5,- (Gdx.graphics.getHeight() / 3.0f));
        shape.rect(0, 0, 5,Gdx.graphics.getHeight() / 3.0f);
        shape.end();


        // DRAW THE RIGHT GATE
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.BLUE);
        shape.rect(Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight(), -5,-(Gdx.graphics.getHeight() / 3.0f));
        shape.rect(Gdx.graphics.getWidth(), 0, -5,Gdx.graphics.getHeight() / 3.0f);
        shape.end();

    }

    /**
     * Dispose of all the initialized values.
     */
    public void dispose() {
        batch.dispose();
        img.dispose();
        shape.dispose();
    }
}
