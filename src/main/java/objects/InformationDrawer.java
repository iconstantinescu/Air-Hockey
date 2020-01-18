package objects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class InformationDrawer {

    ShapeRenderer shape;
    SpriteBatch batch;

    /**
     * A constructor for a simple ObjectDrawer, without any background image.
     */
    public InformationDrawer() {
        shape = new ShapeRenderer();
        batch = new SpriteBatch();
    }

    public void drawListOfItems() {

    }

    /**
     * Draw text on the screen.
     * @param str Text to display on screen
     * @param posX The x coordinate of the text
     * @param posY The y coordinate of the text
     */
    public void drawText(String str, float posX, float posY, int fontScale) {
        BitmapFont font = new BitmapFont();
        batch.begin();
        font.setColor(0,0,0,1);
        font.getData().setScale(fontScale);
        font.draw(batch, str, posX,
                posY);
        batch.end();
    }
}
