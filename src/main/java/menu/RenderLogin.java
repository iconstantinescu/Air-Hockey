package menu;

import client.Client;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import game.Render;
import game.Renderer;

/**
 * The specific renderer of the Login.
 */
public class RenderLogin implements Renderer {
    transient TextField username;
    transient TextField password;
    transient Stage stage;
    transient TextButton loginButton;
    transient TextButton registerButton;
    transient TextField passwordText;
    transient TextField usernameText;
    transient Skin skin;

    transient String nameInput;
    transient String passInput;

    /**
     * Creates the items to be rendered on stage.
     */
    public RenderLogin() {

        skin = new Skin(Gdx.files.internal("assets/ui/skin/uiskin.json"));
        stage = new Stage(new ScreenViewport());

        loginButton = new TextButton("login",skin,"default");
        loginButton.setWidth(200);
        loginButton.setHeight(40);
        loginButton.setPosition(300,150);

        registerButton = new TextButton("register", skin, "default");
        registerButton.setWidth(200);
        registerButton.setHeight(40);
        registerButton.setPosition(510, 150);

        username = new TextField("", skin);
        username.setPosition(300,250);
        username.setSize(300, 40);

        password = new TextField("", skin);
        password.setPosition(300, 200);
        password.setSize(300,40);
        password.setPasswordMode(true);
        password.setPasswordCharacter('*');

        usernameText = new TextField("username:", skin);
        usernameText.setSize(100, 40);
        usernameText.setPosition(190, 250);


        passwordText = new TextField("password:", skin);
        passwordText.setSize(100, 40);
        passwordText.setPosition(190, 200);

        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                loginClicked();


            }
        });

        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                registerClicked();


            }
        });

        stage.addActor(loginButton);
        stage.addActor(registerButton);
        stage.addActor(username);
        stage.addActor(password);
        stage.addActor(usernameText);
        stage.addActor(passwordText);

        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Renders the scene.
     */
    public void run() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
        Client auth = new Client();
        System.out.println("username: " + nameInput);
        System.out.println("password: " + passInput);
        if (auth.authenticate(passInput, nameInput)) {
            Render.changeGameState(Render.GameState.MENU);
            System.out.println("user " + nameInput + " authenticated");
        }
    }


    /**
     * calls to register user.
     */
    public void registerClicked() {
        passInput = password.getText();
        nameInput = username.getText();
        Client auth = new Client();
        auth.register(nameInput, passInput, nameInput);
    }

    /**
     * Getter for password.
     * @return inputted password.
     */
    public String getPassword() {
        return passInput;
    }

    /**
     * Getter for username.
     * @return inputted username.
     */
    public String getUsername() {
        return nameInput;
    }


}


