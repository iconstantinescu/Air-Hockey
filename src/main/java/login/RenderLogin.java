package login;

import com.badlogic.gdx.*;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class RenderLogin extends ApplicationAdapter {
    private TextField username;
    private TextField password;
    private Stage stage;
    private Table table;
    private TextButton loginButton;
    private Skin skin;

    private String nameInput;
    private String passInput;

    /**
     * creates the items to be rendered on stage
     */
    @Override
    public void create () {

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
        final Dialog dialog = new Dialog("Click Message",skin);

        loginButton.addListener(new ClickListener(){
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
     * renders the scene
     */
    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    /**
     * sets the variables of password and username
     * when login button clicked and prints to console
     */
    public void loginClicked() {
        passInput = password.getText();
        nameInput = username.getText();
        System.out.println("username: " + nameInput);
        System.out.println("password: " + passInput);
    }

    /**
     * @return inputted password
     */
    public String getPassword() {
        return passInput;
    }

    /**
     * r
     * @return inputted username
     */
    public String getUsername() {
        return nameInput;
    }

}


