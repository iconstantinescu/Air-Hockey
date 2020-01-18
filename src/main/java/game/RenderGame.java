package game;

import static com.badlogic.gdx.Input.Keys.ENTER;

import client.Leaderboard;
import client.LeaderboardEntry;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import objects.GateAlignedState;
import objects.Puck;
import objects.Pusher;
import objects.ScoreBoard;

/**
 * The specific Main renderer inheriting from the general Renderer.
 */
public class RenderGame implements RenderStrategy {
    private transient ShapeRenderer shape;
    private transient Pusher pusher1;
    private transient Pusher pusher2;
    private transient Puck puck;
    private transient ScoreBoard scoreBoard;
    private transient Texture img;
    private transient Sprite sprite;
    private transient SpriteBatch batch;
    private static final Music backSound =
            Gdx.audio.newMusic(Gdx.files.internal("media/song.wav"));
    private transient boolean matchUploaded;
    private transient Leaderboard leaderboard;
    private static final Music hitSound =
            Gdx.audio.newMusic(Gdx.files.internal("media/hit.wav"));
    private static final Music goalSound =
            Gdx.audio.newMusic(Gdx.files.internal("media/airhorn.wav"));

    /**
     * Constructor for the Renderer.
     */
    public RenderGame() {
        // Set pusher 1 and 2 positions
        pusher1 = new Pusher(300, 100, 40);
        pusher2 = new Pusher(800, 360, 40);

        // Set the field sprite
        img = new Texture("media/field.png");
        sprite = new Sprite(img);
        sprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        // Set the objects sprites
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        scoreBoard = new ScoreBoard();
        puck = new Puck(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 15, 0, 0,
                scoreBoard);

        // Initiate the Background Sound
        backSound.setLooping(true);
        backSound.play();

        matchUploaded = false;

    }

    /**
     * Method that runs the rendering of the game.
     */
    public void run() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        drawBoard();

        // DRAW THE PUCK OR GAME OVER
        if (scoreBoard.isGameOver()) {
            drawText("Player " + winnerNumber() + " Won", (Gdx.graphics.getWidth() / 2) - 150,
                    Gdx.graphics.getHeight() - 100);
            // DRAW TOP SCORES
            uploadMatch();
            drawTopScores(Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() - 150);
            // GO BACK TO MENU IF ENTER IS PRESSED
            waitForEnter();
        } else {
            // CALCULATE THE POSITIONS OF THE PUCK
            updatePuck();
            drawGameObject(-1, puck.getposX(), puck.getposY(), puck.getRadius());
        }

        // CHANGE PUSHER1 POSITION ACCORDING TO KEYBOARD INPUT
        updatePusher(pusher1, true);
        // CHANGE PUSHER2 POSITION ACCORDING TO KEYBOARD INPUT
        updatePusher(pusher2, false);

        // DRAW PUSHER 1
        drawGameObject(0, pusher1.getposX(), pusher1.getposY(), pusher1.getRadius());
        // DRAW PUSHER 2
        drawGameObject(0, pusher2.getposX(), pusher2.getposY(), pusher2.getRadius());

        // PLAY SOUNDS
        if (GateAlignedState.playGoalSound) {
            goalSound.play();
            GateAlignedState.playGoalSound = false;
        }
        if (Pusher.playHitSound) {
            hitSound.play();
            Pusher.playHitSound = false;
        }

        // RENDER THE SCORE
        drawText(scoreBoard.getPlayer1Score() + " : "
                        + scoreBoard.getPlayer2Score(), (Gdx.graphics.getWidth() / 2) - 50,
                Gdx.graphics.getHeight() - 20);

        drawWallsAndGates();
    }

    /**
     * Method that uploads the match into the match history of the database,
     * and adds points to the winner of the game.
     */
    public void uploadMatch() {
        if (matchUploaded == false) {
            int addPoints = 10 * Math.abs(scoreBoard.getPlayer1Score()
                    - scoreBoard.getPlayer2Score());

            // ADD POINTS TO WINNER
            if (scoreBoard.getWinner()) {
                Render.user1.addPoints(addPoints);
                Render.userDao.updateUser(Render.user1);
            } else {
                Render.user2.addPoints(addPoints);
                Render.userDao.updateUser(Render.user2);
            }

            // SAVE GAME INTO HISTORY
            matchUploaded = Render.userDao.saveGame(
                    Render.user1.getUserID(),
                    Render.user2.getUserID(),
                    scoreBoard.getPlayer1Score(),
                    scoreBoard.getPlayer2Score());

        }
    }

    /**
     * Get the winner number (either 1 or 2).
     */
    public int winnerNumber() {
        if (scoreBoard.getWinner()) {
            return 1;
        }
        return 2;
    }

    /**
     * Method for Drawing the Top 5 Scores.
     * @param posX The x coordinate of the first score
     * @param posY The y coordinate of the first score
     */
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void drawTopScores(float posX, float posY) {
        if (leaderboard == null) {
            leaderboard = Render.leaderboardDao.getLeaderboard(5);
        }

        if (leaderboard != null) {

            int i = 1;
            for (LeaderboardEntry entry : leaderboard.getLeaderboardList()) {

                drawText(i + ". " + entry.getNickname() + " " + entry.getPoints(),
                        posX, posY);

                posY -= 50;
                i++;
            }
        }


        drawText("Press ENTER to go back to menu", posX - 250, posY - 100);
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
     * Moves the pusher object according to the user input.
     * @param pusher The pusher object
     * @param pusherId The id of the Pusher (either Pusher 1 - Left, or Pusher 2 - Right)
     */
    public void updatePusher(Pusher pusher, boolean pusherId) {
        int[] keyCodes;
        if (pusherId) {
            // USE WASD
            keyCodes = new int[]{51, 47, 29, 32};
        } else {
            // USE IJKL
            keyCodes = new int[]{37, 39, 38, 40};
        }
        boolean[] restricts = new boolean[4];

        pusher.restrictMovementOnWall(pusherId, restricts,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (Gdx.input.isKeyPressed(keyCodes[0]) && !restricts[0]) {
            pusher.setposY(pusher.getposY() + 6);
        }
        if (Gdx.input.isKeyPressed(keyCodes[1]) && !restricts[2]) {
            pusher.setposY(pusher.getposY() - 6);
        }
        if (Gdx.input.isKeyPressed(keyCodes[2]) && !restricts[1]) {
            pusher.setposX(pusher.getposX() - 6);
        }
        if (Gdx.input.isKeyPressed(keyCodes[3]) && !restricts[3]) {
            pusher.setposX(pusher.getposX() + 6);
        }
    }

    /**
     * Waits for Enter to be pressed to go back to the menu.
     */
    public void waitForEnter() {
        if (Gdx.input.isKeyJustPressed(ENTER)) {
            Render.changeGameStrategy(Render.ApplicationStrategy.MENU);
            backSound.dispose();
        }
    }

    /**
     * Function that renders a gameObject (which can be either the puck or the pusher.
     * @param objectType The type of object (-1 for puck, 0 for pusher)
     * @param posX The x coordinate of the object
     * @param posY The y coordinate of the object
     * @param radius The radius of the object
     */
    public void drawGameObject(int objectType, float posX, float posY, float radius) {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        if (objectType == -1) {
            shape.setColor(Color.RED);
        } else if (objectType == 0) {
            shape.setColor(Color.FIREBRICK);
        }
        shape.circle(posX, posY, radius);
        shape.end();
    }

    /**
     * Draw the board (which is the background of the game).
     */
    public void drawBoard() {
        batch.begin();
        batch.draw(sprite, sprite.getX() - sprite.getWidth() / 2,
                sprite.getY() - sprite.getHeight() / 2, sprite.getWidth(),
                sprite.getHeight(), sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(),
                sprite.getScaleY(), sprite.getRotation());
        batch.end();
    }

    /**
     * This method changes the puck position according to the rules of Air Hockey.
     */
    public void updatePuck() {
        // Collision between Pusher 1 and the puck
        pusher1.checkAndExecuteCollision(puck);

        // Check and execute collision between Pusher 2 and Puck
        pusher2.checkAndExecuteCollision(puck);

        puck.translate(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
     * Draw text on the screen.
     * @param str Text to display on screen
     * @param posX The x coordinate of the text
     * @param posY The y coordinate of the text
     */
    public void drawText(String str, float posX, float posY) {
        BitmapFont font = new BitmapFont();
        batch.begin();
        font.setColor(0,0,0,1);
        font.getData().setScale(4);
        font.draw(batch, str, posX,
                posY);
        batch.end();
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
