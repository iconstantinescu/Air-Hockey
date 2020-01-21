package utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * The ObjectDrawer is a drawer whose main goal is drawing the objects and
 * the background of the game on the screen.
 */
public class ObjectDrawer {

    private transient ShapeRenderer shape;
    private transient SpriteBatch batch;
    private transient Sprite backgroundSprite;

    /**
     * A constructor for a simple ObjectDrawer, without any background image.
     */
    public ObjectDrawer() {
        shape = new ShapeRenderer();
        batch = new SpriteBatch();
    }

    /**
     * A constructor for an ObjectDrawer which can also draw a background image.
     * @param backgroundPath Path to the background image
     */
    public ObjectDrawer(String backgroundPath) {
        shape = new ShapeRenderer();
        batch = new SpriteBatch();
        backgroundSprite = new Sprite(new Texture(backgroundPath));
        backgroundSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    }



    /**
     * Draw the board (which is the background of the game).
     */
    public void drawBackground() {
        batch.begin();
        batch.draw(backgroundSprite, backgroundSprite.getX() - backgroundSprite.getWidth() / 2,
                backgroundSprite.getY() - backgroundSprite.getHeight() / 2, backgroundSprite.getWidth(),
                backgroundSprite.getHeight(), backgroundSprite.getWidth(), backgroundSprite.getHeight(), backgroundSprite.getScaleX(),
                backgroundSprite.getScaleY(), backgroundSprite.getRotation());
        batch.end();
    }


    /**
     * Method for drawing all the walls and gates.
     */
    public void drawWallsAndGates() {
        // DRAW UPPER WALL
        drawWall(0,0, Gdx.graphics.getWidth(), 5);
        // DRAW LOWER WALL
        drawWall(0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), -5);
        // DRAW THE LEFT GATE
        drawWall(0, Gdx.graphics.getHeight(), 5,- (Gdx.graphics.getHeight() / 3.0f));
        drawWall(0, 0, 5,Gdx.graphics.getHeight() / 3.0f);
        // DRAW THE RIGHT GATE
        drawWall(Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight(), -5,-(Gdx.graphics.getHeight() / 3.0f));
        drawWall(Gdx.graphics.getWidth(), 0, -5,Gdx.graphics.getHeight() / 3.0f);
    }

    /**
     * Draw a Wall for the game.
     */
    public void drawWall(float posX, float posY, float width, float height) {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.BLUE);
        shape.rect(posX, posY, width, height);
        shape.end();
    }


}
