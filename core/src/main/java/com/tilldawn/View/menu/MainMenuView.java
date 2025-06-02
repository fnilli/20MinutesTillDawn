package com.tilldawn.View.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.menu.MainMenuController;
import com.tilldawn.Model.App;

public class MainMenuView implements Screen {
    private Stage stage;

    private final TextButton continueButton;
    private final TextButton settingsButton;
    private final TextButton profileButton;
    private final TextButton preGameButton;
    private final TextButton scoreboardButton;
    private final TextButton talentButton;
    private final TextButton logoutButton;

    private final Label usernameLabel;
    private final Label scoreLabel;
    private final Image avatarImage;

    private final Label gameTitle;
    private final Table table;
    private final MainMenuController controller;

    public MainMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;

        this.continueButton = new TextButton("Load Game", skin);
        this.settingsButton = new TextButton("Settings", skin);
        this.profileButton = new TextButton("Profile", skin);
        this.preGameButton = new TextButton("Pre-Game", skin);
        this.scoreboardButton = new TextButton("Scoreboard", skin);
        this.talentButton = new TextButton("Talent", skin);
        this.logoutButton = new TextButton("Logout", skin);

        this.gameTitle = new Label("M a i n    M e n u", skin.get("title", Label.LabelStyle.class));

        // Player info labels
        this.usernameLabel = new Label("Username: Guest", skin);
        this.scoreLabel = new Label("Score: 0", skin);

        // Placeholder avatar image
        Texture avatarTexture = new Texture(App.getCurrentPlayer().getAvatarPath());
        this.avatarImage = new Image(avatarTexture);
        this.avatarImage.setSize(64, 64);

        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //add background
        Texture bgTexture = new Texture(Gdx.files.internal("blueBack.png"));
        Image background = new Image(bgTexture);
        background.setFillParent(true);
        stage.addActor(background);



        // Title row
        table.setFillParent(true);
        table.add(gameTitle).colspan(2).padBottom(780);
        table.row();

        Table rootTable = new Table();
        rootTable.setFillParent(true);
        stage.addActor(rootTable);

// === LEFT SIDE: Player Info & Account Options ===
        Table leftColumn = new Table();
        leftColumn.top().left();

        leftColumn.add(avatarImage).size(128).padBottom(30);
        leftColumn.row().padBottom(250);
        leftColumn.add(usernameLabel).left().padBottom(50);
        leftColumn.row();
        leftColumn.add(scoreLabel).left().padBottom(20);
        leftColumn.row();
        leftColumn.add(continueButton).width(320).padBottom(10);
        leftColumn.row();
        leftColumn.add(logoutButton).width(320);

// === RIGHT SIDE: Menu Options ===
        Table rightColumn = new Table();
        rightColumn.top().left();

        rightColumn.add(settingsButton).width(320).padBottom(10);
        rightColumn.row();
        rightColumn.add(profileButton).width(320).padBottom(10);
        rightColumn.row();
        rightColumn.add(preGameButton).width(320).padBottom(10);
        rightColumn.row();
        rightColumn.add(scoreboardButton).width(320).padBottom(10);
        rightColumn.row();
        rightColumn.add(talentButton).width(320);

// === Add Both Columns to Root Table ===
        rootTable.add(leftColumn).pad(80,0,0,300).top().left();
        rootTable.add(rightColumn).pad(0,50,0,100).top().left();

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleMainMenuButtons();
    }

    @Override
    public void resize(int width, int height) { }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void hide() { }
    @Override
    public void dispose() {
//        stage.dispose();
//        avatarImage.getDrawable().getTexture().dispose();
    }

    // Getters for controller access
    public TextButton getContinueButton() { return continueButton; }
    public TextButton getSettingsButton() { return settingsButton; }
    public TextButton getProfileButton() { return profileButton; }
    public TextButton getPreGameButton() { return preGameButton; }
    public TextButton getScoreboardButton() { return scoreboardButton; }
    public TextButton getTalentButton() { return talentButton; }
    public TextButton getLogoutButton() { return logoutButton; }

    public Label getUsernameLabel() { return usernameLabel; }
    public Label getScoreLabel() { return scoreLabel; }

    public void setUsername(String username) {
        usernameLabel.setText("Username: " + username);
    }

    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
}
