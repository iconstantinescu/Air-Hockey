package menu;

import static java.lang.System.exit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderMenu {

    /**
     * Sets the home sprite and batch.
     * @param sprite the sprite to draw
     * @param batch the batch to render
     */
    public static void setHome(Sprite sprite, SpriteBatch batch) {
        batch.begin();
        batch.draw(sprite, sprite.getX() - sprite.getWidth() / 2,
                sprite.getY() - sprite.getHeight() / 3, sprite.getWidth(),
                sprite.getHeight(), sprite.getWidth(),
                sprite.getHeight(), sprite.getScaleX(),
                sprite.getScaleY(), sprite.getRotation() / 2);
        batch.end();
    }

    /**
     * Sets the play sprite and batch.
     * @param sprite the sprite to draw
     * @param batch the batch to render
     */
    public static void setPlay(Sprite sprite, SpriteBatch batch) {
        batch.begin();
        batch.draw(sprite, sprite.getX() - sprite.getWidth() / 2,
                sprite.getY() + sprite.getHeight() / 2);

        if (Gdx.input.getX() > (sprite.getX() - sprite.getWidth() / 2)
                && Gdx.input.getX() < (sprite.getX()
                + sprite.getWidth() / 2)
                && Gdx.graphics.getHeight() - Gdx.input.getY()
                > (sprite.getY() + sprite.getHeight() / 2)
                && Gdx.graphics.getHeight() - Gdx.input.getY()
                < (sprite.getY() + sprite.getHeight() * 3 / 2)
        ) {
            batch.setColor(Color.SKY);
        } else {
            batch.setColor(Color.WHITE);
        }
        batch.end();
    }

    /**
     * Sets the scores sprite and batch.
     * @param sprite the sprite to draw
     * @param batch the batch to render
     */
    public static void setScores(Sprite sprite, SpriteBatch batch) {
        batch.begin();
        batch.draw(sprite, sprite.getX() - sprite.getWidth() / 2,
                sprite.getY() - sprite.getHeight() / 2);

        if (Gdx.input.getX() > (sprite.getX() - sprite.getWidth() / 2)
                && Gdx.input.getX() < (sprite.getX() + sprite.getWidth() / 2)
                && Gdx.input.getY() > (sprite.getY() - sprite.getHeight() / 2)
                && Gdx.input.getY() < (sprite.getY() + sprite.getHeight() / 2)
        ) {
            batch.setColor(Color.SKY);
        } else {
            batch.setColor(Color.WHITE);
        }
        batch.end();
    }

    /**
     * Sets the quit sprite and batch.
     * @param sprite the sprite to draw
     * @param batch the batch to render
     */
    public static void setQuit(Sprite sprite, SpriteBatch batch) {
        batch.begin();
        batch.draw(sprite, sprite.getX() - sprite.getWidth() / 2,
                sprite.getY() - sprite.getHeight() * 2);

        if (Gdx.input.getX()
                > (sprite.getX() - sprite.getWidth() / 2)
                && Gdx.input.getX() < (sprite.getX() + sprite.getWidth() / 2)
                && Gdx.graphics.getHeight() - Gdx.input.getY()
                > (sprite.getY() - sprite.getHeight() * 3 / 2)
                && Gdx.graphics.getHeight() - Gdx.input.getY()
                < (sprite.getY() - sprite.getHeight() / 2)
        ) {
            batch.setColor(Color.SKY);
        } else {
            batch.setColor(Color.WHITE);
        }

        if (Gdx.input.justTouched() && Gdx.input.getX()
                > (sprite.getX() - sprite.getWidth() / 2)
                && Gdx.input.getX() < (sprite.getX() + sprite.getWidth() / 2)
                && Gdx.graphics.getHeight() - Gdx.input.getY()
                > (sprite.getY() - sprite.getHeight() * 3 / 2)
                && Gdx.graphics.getHeight() - Gdx.input.getY()
                < (sprite.getY() - sprite.getHeight() / 2)) {
            exit(0);
        }
        batch.end();
    }


}
