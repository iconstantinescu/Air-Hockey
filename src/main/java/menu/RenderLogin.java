package menu;

import client.User;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import game.Render;
import game.RenderStrategy;

/**
 * The specific renderer of the Login, during this loop the login can
 * and sign up can be done.
 */
public class RenderLogin implements RenderStrategy {
    transient TextField username;
    transient TextField password;
    transient Stage stage;
    transient TextButton loginButton;
    transient TextButton registerButton;
    transient Label passwordText;
    transient Label usernameText;
    transient Label playerLoginText;
    transient Label error;
    transient Skin skin;
    private transient Texture background;

    transient String nameInput;
    transient String passInput;

    /**
     * Creates the items to be rendered on stage.
     */
    public RenderLogin() {

        background = new Texture("media/background.jpg");

        skin = new Skin(Gdx.files.internal("assets/ui/skin/uiskin.json"));
        stage = new Stage(new ScreenViewport());

        // check if player 1 or 2 is logging in
        if (Render.user1.getUserID() == 0) {
            playerLoginText = new Label("Login Player 1", skin);
        } else {
            playerLoginText = new Label("Login Player 2", skin);
        }

        float centerW = Gdx.graphics.getWidth() / 2;
        float centerH = Gdx.graphics.getHeight() / 2;

        username = new TextField("", skin);
        username.setSize(400, 40);

        // set username field to center
        float usernameX = centerW - username.getWidth() / 2;
        float usernameY = centerH + username.getHeight();
        username.setPosition(usernameX,usernameY);

        playerLoginText.setSize(300, 50);
        playerLoginText.setPosition(usernameX, usernameY + 60);


        password = new TextField("", skin);
        password.setSize(400,40);

        // set password field to directly under username
        password.setPosition(usernameX, usernameY - username.getHeight() - 5);
        password.setPasswordMode(true);
        password.setPasswordCharacter('*');

        loginButton = new TextButton("login",skin,"default");
        loginButton.setWidth(200);
        loginButton.setHeight(40);

        // login button position under password and username fields on left side
        loginButton.setPosition(usernameX,usernameY - username.getHeight() * 2 - 10);

        registerButton = new TextButton("register", skin, "default");
        registerButton.setWidth(200);
        registerButton.setHeight(40);

        // register button position under password and username fields on right side
        registerButton.setPosition(usernameX + 200, usernameY - username.getHeight() * 2 - 10);

        usernameText = new Label("username:", skin);

        // to left of username
        usernameText.setSize(100, 40);
        usernameText.setPosition(usernameX - 110, usernameY);

        // to left of password
        passwordText = new Label("password:", skin);
        passwordText.setSize(100, 40);
        passwordText.setPosition(usernameX - 110, usernameY - username.getHeight() - 5);

        error = new Label("", skin);
        error.setSize(300, 50);
        error.setPosition(usernameX, usernameY - username.getHeight() * 2 - 70);
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                loginClicked();
                error.setText("Login failed, try again");
                error.setColor(100, 0,0,1);
            }
        });

        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean registered = registerClicked();
                if (registered) {
                    error.setText("Your account has been registered, please login");
                    error.setColor(0, 100,0,1);
                } else {
                    error.setText("There has been a problem, try another username,"
                            + "\n also length has to be bigger than 3 characters");
                    error.setColor(100, 0,0,1);
                }

            }
        });

        stage.addActor(loginButton);
        stage.addActor(registerButton);
        stage.addActor(username);
        stage.addActor(password);
        stage.addActor(usernameText);
        stage.addActor(passwordText);
        stage.addActor(playerLoginText);
        stage.addActor(error);
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Renders the scene.
     */
    public void run() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    /**
     * Disposes of render items.
     */
    public void dispose() {
        stage.dispose();
        skin.dispose();

    }

    /**
     * Sets the variables of password and username
     * when login button clicked and prints to console.
     * Logs in if authentication success.
     */
    public void loginClicked() {
        passInput = password.getText();
        nameInput = username.getText();
        if (passInput.equals("") || nameInput.equals("")) {
            return;
        }


        User resultUser = Render.userDao.authenticate(nameInput, passInput);
        if (resultUser != null) {
            System.out.println("user " + nameInput + " authenticated");
            if (Render.user1.getUserID() == 0) {
                Render.user1 = resultUser;
                Render.changeGameStrategy(Render.ApplicationStrategy.MENU);
            } else if (Render.user2.getUserID() == 0
                    && !(Render.user1.getUserID() == resultUser.getUserID())) {
                Render.user2 = resultUser;
                Render.secondAuthentication = true;
                Render.changeGameStrategy(Render.ApplicationStrategy.GAME);
            }
        }
    }


    /**
     * calls to register user.
     */
    public boolean registerClicked() {
        passInput = password.getText();
        nameInput = username.getText();

        if (passInput.length() < 3 || nameInput.length() < 3) {
            return false;
        }
        return Render.userDao.createNewUser(nameInput, passInput, nameInput);
    }


}


