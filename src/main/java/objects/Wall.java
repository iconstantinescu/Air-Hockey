package objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;

public class Wall extends GameObject {

    private transient ShapeRenderer shape;

    public Wall(float x, float y, int width, int height) {
        super(x, y, width, height);
        this.shape = new ShapeRenderer();
    }


    /**
     * Draw a Wall for the game.
     */
    public void drawGameObject() {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.BLUE);
        shape.rect(getPosX(), getPosY(), getWidth(), getHeight());
        shape.end();
    }
}
