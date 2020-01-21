package utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game.Render;
import objects.ScoreBoard;

import static com.badlogic.gdx.Input.Keys.ENTER;

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

    /**
     * Waits for Enter to be pressed to go back to the menu.
     */
    public void waitForEnter() {
        if (Gdx.input.isKeyJustPressed(ENTER)) {
            Render.changeGameStrategy(Render.ApplicationStrategy.MENU);
//            backSound.dispose();
        }
    }

    public void drawInformation(int score1, int score2) {
        // RENDER THE SCORE
        drawText(score1 + " : "
                        + score2, (Gdx.graphics.getWidth() / 2) - 50,
                Gdx.graphics.getHeight() - 20, 4);
        drawText("Player 1", 80, Gdx.graphics.getHeight() - 20, 2);
        drawText("Player 2", Gdx.graphics.getWidth() - 180, Gdx.graphics.getHeight() - 20, 2);
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
