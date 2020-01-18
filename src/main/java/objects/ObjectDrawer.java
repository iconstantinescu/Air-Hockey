package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class ObjectDrawer {

    ShapeRenderer shape;
    SpriteBatch batch;
    Sprite backgroundSprite;

    /**
     * A constructor for a simple ObjectDrawer, without any background image.
     */
    public ObjectDrawer() {
        shape = new ShapeRenderer();
        batch = new SpriteBatch();
    }

    /**
     * A constructor for an ObjectDrawer which can also draw a background image.
     */
    public ObjectDrawer(String backgroundPath) {
        shape = new ShapeRenderer();
        batch = new SpriteBatch();
        backgroundSprite = new Sprite(new Texture(backgroundPath));
        backgroundSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    }

    /**
     * Method for drawing all the walls and gates.
     */
    public void drawWallsAndGates(ArrayList<Wall> walls) {
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
     * Draw a Wall for the game.
     * @param posX The x coordinate of the wall
     * @param posY The y coordinate of the wall
     * @param width The width of the wall
     * @param height The height of the wall
     */
    public void drawWall(float posX, float posY, float width, float height) {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.BLUE);
        shape.rect(posX, posY, width, height);
        shape.end();
    }

    /**
     * Function that renders a gameObject (which can be either the puck or the pusher.
     * @param object The type of object (Puck or Pusher)
     */
    public void drawGameObject(GameObject object) {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        if (object instanceof Puck) {
            shape.setColor(Color.RED);
        }
        if (object instanceof Pusher) {
            shape.setColor(Color.FIREBRICK);
        }
        shape.circle(object.getPosX(), object.getPosY(), object.getRadius());
        shape.end();
    }


}
