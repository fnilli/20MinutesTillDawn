package com.tilldawn.View.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.menu.StartMenuController;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;

public class StartMenuView implements Screen {
    private Stage stage;
    private final TextButton registerButton;
    private final TextButton loginButton;
    private final TextButton guestButton;
    private final Table table;
    private final TextButton exitButton;
    private final StartMenuController controller;

    private Texture bgTexture;
    private Image background;

    public StartMenuView(StartMenuController controller, Skin skin) {
        this.controller = controller;
        this.registerButton = new TextButton("Sign Up", skin);
        this.loginButton = new TextButton("Login", skin);
        this.guestButton = new TextButton("Guest Player", skin);
        this.exitButton = new TextButton("Exit", skin);
        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Pixmap pixmap = new Pixmap(Gdx.files.internal("Images/Sprite/T/T_CursorSprite.png")); // from assets folder
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pixmap, 0, 0)); // hotspot at (0,0)
        pixmap.dispose();


        bgTexture = new Texture(Gdx.files.internal("blueBack.png"));
        background = new Image(bgTexture);
        background.setFillParent(true);
        stage.addActor(background); // Background first

        Texture logoTexture = new Texture(Gdx.files.internal("Images/Sprite/T/T_20Logo.png"));
        Image logoImage = new Image(logoTexture);

        exitButton.addListener(e -> {
            if (!exitButton.isPressed()) return false;
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            Gdx.app.exit();
            return true;
        });

        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                    GameAssetManager.getGameAssetManager().getClickButtonSound().play();
                }
                controller.onRegisterClicked();
            }
        });

        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                    GameAssetManager.getGameAssetManager().getClickButtonSound().play();
                }
                controller.onLoginClicked();
            }
        });

        guestButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                    GameAssetManager.getGameAssetManager().getClickButtonSound().play();
                }
                controller.onGuestClicked();
            }
        });

//        exitButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                Gdx.app.exit();
//            }
//        });



        table.setFillParent(true);
        table.center();
        table.add(logoImage).size(700, 300).padBottom(70).row();  // Adds image with space below it

        table.add(registerButton).width(350).pad(10);
        table.row();
        table.add(loginButton).width(350).pad(10);
        table.row();
        table.add(guestButton).width(350).pad(10);
        table.row();
        table.add(exitButton).width(350).pad(10);

        stage.addActor(table); // Table drawn on top
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
//        controller.handleStartButtons();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        bgTexture.dispose(); // Important!
    }

    public TextButton getRegisterButton() { return registerButton; }
    public TextButton getLoginButton() { return loginButton; }
    public TextButton getGuestButton() { return guestButton; }
}
