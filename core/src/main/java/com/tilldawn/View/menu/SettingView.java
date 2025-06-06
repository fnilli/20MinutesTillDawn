package com.tilldawn.View.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tilldawn.Control.menu.MainMenuController;
import com.tilldawn.Control.menu.SettingController;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.TranslatableText;

public class SettingView implements Screen {
    private Stage stage;
    private final Skin skin = GameAssetManager.getGameAssetManager().getSkin();
    private final SettingController controller;


    private final TextButton backButton = new TextButton(TranslatableText.BackToMainMenu.getText(), skin);

    private CheckBox autoReloadCheckBox;
    private CheckBox darkThemeCheckBox;


    private SelectBox<String> selectLanguage;
    private final Label selectLanguageLabel = new Label(TranslatableText.SelectLanguage.getText(), skin);



    public SettingView(SettingController controller) {
        this.controller = controller;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //  Background
        Texture bgTexture = new Texture(Gdx.files.internal("blueBack.png"));
        Image background = new Image(bgTexture);
        if (App.getCurrentPlayer().isDarkTheme()) {
            background.setColor(Color.GRAY); // Or apply a shader for grayscale
        }
        background.setFillParent(true);
        stage.addActor(background);

        // Main table (two columns: left and right)
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top().padTop(30);
        mainTable.add(new Label(TranslatableText.SettingMenuTitle.getText(), skin, "title"))
            .padBottom(20).padLeft(300).row();

        // Left table: Music & SFX
        Table leftTable = new Table().top().left();
        Label volumeLabel = new Label(TranslatableText.MusicVolume.getText(), skin);
        Slider volumeSlider = new Slider(0f, 1f, 0.1f, false, skin);
        volumeSlider.setValue(GameAssetManager.getGameAssetManager().getVolume());
        volumeSlider.addListener(event -> {
            controller.setVolume(volumeSlider.getValue());
            return false;
        });

        Label musicLabel = new Label(TranslatableText.MusicTrack.getText(), skin);
        SelectBox<String> musicSelect = new SelectBox<>(skin);
        musicSelect.setItems("Default", "Theme1", "Theme2", "Theme3", "Theme4");
        musicSelect.setSelected("Default");
        musicSelect.addListener(event -> {
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            controller.changeMusic(musicSelect.getSelected());
            return false;
        });

        Label sfxLabel = new Label(TranslatableText.SoundEffects.getText(), skin);
        CheckBox sfxCheckBox = new CheckBox("", skin);
        sfxCheckBox.setChecked(GameAssetManager.getGameAssetManager().isSfxEnabled());
        sfxCheckBox.addListener(event -> {
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            controller.setSfxEnabled(sfxCheckBox.isChecked());
            return false;
        });

        // Auto-Reload
        Label autoReloadLabel = new Label(TranslatableText.AutoReload.getText(), skin);
        autoReloadCheckBox = new CheckBox("", skin);
        autoReloadCheckBox.setChecked(controller.isAutoReloadEnabled());
        autoReloadCheckBox.addListener(event -> {
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            controller.setAutoReloadEnabled(autoReloadCheckBox.isChecked());
            return false;
        });

// Dark Theme
        Label darkThemeLabel = new Label(TranslatableText.DarkTheme.getText(), skin);
        darkThemeCheckBox = new CheckBox("", skin);
        darkThemeCheckBox.setChecked(controller.isDarkThemeEnabled());
        darkThemeCheckBox.addListener(event -> {
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            controller.setDarkThemeEnabled(darkThemeCheckBox.isChecked());
            return false;
        });


        leftTable.add(volumeLabel).left();
        leftTable.row().pad(10, 0, 10, 0);
        leftTable.row().pad(10, 0, 10, 0);
        leftTable.add(volumeSlider).width(250).left();
        leftTable.row().pad(10, 0, 10, 0);

        leftTable.row().pad(20, 0, 10, 0);
        leftTable.add(musicLabel).left();
        leftTable.row().pad(10, 0, 10, 0);
        leftTable.add(musicSelect).width(250).left();
        leftTable.row().pad(20, 0, 10, 0);
        leftTable.add(sfxLabel).left();
        leftTable.row().pad(10, 0, 10, 0);
        leftTable.add(sfxCheckBox).left();

        leftTable.row().pad(20, 0, 10, 0);
        leftTable.add(autoReloadLabel).left();
        leftTable.row().pad(10, 0, 10, 0);
        leftTable.add(autoReloadCheckBox).left();

        leftTable.row().pad(20, 0, 10, 0);
        leftTable.add(darkThemeLabel).left();
        leftTable.row().pad(10, 0, 10, 0);
        leftTable.add(darkThemeCheckBox).left();

        // Right table: Controls
        Table rightTable = new Table().top().right();
        Label controlLabel = new Label(TranslatableText.Controls.getText(), skin);

        SelectBox<String> moveUpSelect = new SelectBox<>(skin);
        moveUpSelect.setItems("W", "Up Arrow");
        moveUpSelect.setSelected(controller.getMoveKey("up"));
        moveUpSelect.addListener(event -> {
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            controller.setMoveKey("up", moveUpSelect.getSelected());
            return false;
        });

        SelectBox<String> moveDownSelect = new SelectBox<>(skin);
        moveDownSelect.setItems("S", "Down Arrow");
        moveDownSelect.setSelected(controller.getMoveKey("down"));
        moveDownSelect.addListener(event -> {
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            controller.setMoveKey("down", moveDownSelect.getSelected());
            return false;
        });

        SelectBox<String> moveLeftSelect = new SelectBox<>(skin);
        moveLeftSelect.setItems("A", "Left Arrow");
        moveLeftSelect.setSelected(controller.getMoveKey("left"));
        moveLeftSelect.addListener(event -> {
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            controller.setMoveKey("left", moveLeftSelect.getSelected());
            return false;
        });

        SelectBox<String> moveRightSelect = new SelectBox<>(skin);
        moveRightSelect.setItems("D", "Right Arrow");
        moveRightSelect.setSelected(controller.getMoveKey("right"));
        moveRightSelect.addListener(event -> {
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            controller.setMoveKey("right", moveRightSelect.getSelected());
            return false;
        });

        selectLanguage = new SelectBox<>(skin); // Use the instance field
        selectLanguage.setItems("English", "French");
        selectLanguage.setSelected("English");
        selectLanguage.addListener(event -> {
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            controller.setLanguage(selectLanguage.getSelected());
            return false;
        });


        rightTable.add(controlLabel).left();
        rightTable.row().pad(10, 0, 0, 0);
//        rightTable.add(new Label("Move Up", skin)).left();
        rightTable.add(new Label(TranslatableText.MoveUp.getText(), skin)).left();
        rightTable.row();
        rightTable.add(moveUpSelect).width(250).left();
        rightTable.row().pad(10, 0, 0, 0);
//        rightTable.add(new Label("Move Down", skin)).left();
        rightTable.add(new Label(TranslatableText.MoveDown.getText(), skin)).left();

        rightTable.row();
        rightTable.add(moveDownSelect).width(250).left();
        rightTable.row().pad(10, 0, 0, 0);
//        rightTable.add(new Label("Move Left", skin)).left();
        rightTable.add(new Label(TranslatableText.MoveLeft.getText(), skin)).left();

        rightTable.row();
        rightTable.add(moveLeftSelect).width(250).left();
        rightTable.row().pad(10, 0, 0, 0);
        rightTable.add(new Label(TranslatableText.MoveRight.getText(), skin)).left();

        rightTable.row();
        rightTable.add(moveRightSelect).width(250).left();
        rightTable.row().pad(20, 0, 0, 0);
        rightTable.add(selectLanguageLabel).left();
        rightTable.row();
        rightTable.add(selectLanguage).width(250).left();



        TextButton backButton = new TextButton(TranslatableText.BackToMainMenu.getText(), skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });

        // Add left & right tables to main table
        mainTable.add(leftTable).left().padRight(100).padTop(20).top();
        mainTable.add(rightTable).right().padLeft(80).top();
        mainTable.row().padTop(50);

        backButton.setPosition((float) Gdx.graphics.getWidth() / 2 - 200, 20);
        backButton.setWidth(300);

        // Add to stage
        stage.addActor(mainTable);
        stage.addActor(backButton);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleSettingButtons();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public TextButton getBackButton() {
        return backButton;
    }

}
