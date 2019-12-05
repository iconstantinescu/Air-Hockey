package menu;

import client.Authenticate;
import client.AuthenticationController;
import client.ConnectionFactory;
import com.badlogic.gdx.ApplicationAdapter;

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


public class RenderLogin implements Renderer {
    transient TextField username;
    transient TextField password;
    transient Stage stage;
    transient TextButton loginButton;
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

        username = new TextField("username", skin);
        username.setPosition(300,250);
        username.setSize(300, 40);

        password = new TextField("password", skin);
        password.setPosition(300, 200);
        password.setSize(300,40);


        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                loginClicked();


            }
        });

        stage.addActor(loginButton);
        stage.addActor(username);
        stage.addActor(password);

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
        Authenticate auth = new Authenticate();
        System.out.println("username: " + nameInput);
        System.out.println("password: " + passInput);
        if (auth.authenticate(passInput, nameInput)) {
            Render.changeGameState(Render.GameState.MENU);
            System.out.println("user " + nameInput + " authenticated");
        }
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


