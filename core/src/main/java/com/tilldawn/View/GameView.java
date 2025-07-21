package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.GameController;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;

public class GameView implements Screen, InputProcessor {
    private Stage stage;
    private GameController controller;
    private OrthographicCamera camera;
    private Label healthLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label killLabel;
    private Label ammoLabel;
    private  Label messageLabel;
    private float messageTimer = 0;
    private ProgressBar xpBar;
    private Label xpLabel;

    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        this.healthLabel = new Label("Health:", skin);
        this.timeLabel = new Label("Time: 0", skin);
        this.levelLabel = new Label("Level: 1", skin);
        this.killLabel = new Label("Kills: ", skin);
        this.ammoLabel = new Label("Ammo: ", skin);
        this.messageLabel = new Label("", skin);
        messageLabel.setColor(Color.MAGENTA);

        xpBar = new ProgressBar(0, 100, 1, false, skin);
        xpBar.setWidth(200); // Width of the bar
        xpBar.setHeight(10); // Height of the bar
        xpBar.setAnimateDuration(0.2f);
        xpBar.setColor(Color.GREEN); // Optional: customize color
        this.xpLabel = new Label("XP:", skin);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(new com.badlogic.gdx.InputMultiplexer(stage, this));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Main.getBatch().setProjectionMatrix(camera.combined);

        Texture healthTex = new Texture(Gdx.files.internal("Images/Sprite/T/T_HeartPickup.png"));
        Image healthImg = new Image(healthTex);
        Texture timeTex = new Texture(Gdx.files.internal("Images/Sprite/rune/rune_dedication.png"));
        Image timeImg = new Image(timeTex);
        Texture levelTex = new Texture(Gdx.files.internal("Images/Sprite/Icon/Icon_Nimble.png"));
        Image levelImg = new Image(levelTex);
        Texture killTex = new Texture(Gdx.files.internal("Images/Sprite/Icon/Icon_DeathPlague.png"));
        Image killImg = new Image(killTex);
        Texture ammoTex = new Texture(Gdx.files.internal("Images/Sprite/T/T_AmmoIcon.png"));
        Image ammoImg = new Image(ammoTex);





        Table table = new Table();
        table.top().left();
        table.setFillParent(true); // Use entire stage
        table.pad(10);
        table.add(xpLabel).left().padLeft(20);  // Add this
        table.add(xpBar).left().padBottom(5).padLeft(10).row();  // Then add the XP bar
        table.add(healthImg).size(64, 64).padLeft(20);
        table.add(healthLabel).left().padBottom(10).padLeft(30).row();
        table.add(timeImg).size(64, 64).padLeft(20);
        table.add(timeLabel).left().padBottom(15).padLeft(30).row();
        table.add(levelImg).size(64, 64).padLeft(20);
        table.add(levelLabel).left().padLeft(20).row();
        table.add(killImg).size(64, 64).padLeft(20);
        table.add(killLabel).left().padLeft(20).row();
        table.add(ammoImg).size(64, 64).padLeft(20);
        table.add(ammoLabel).left().padLeft(20).row();

//        table.add(messageLabel).left().padLeft(20).row();
        messageLabel.setPosition(20, 20);

        stage.addActor(table);
        stage.addActor(messageLabel);


    }



    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        // Center camera on player
        float playerX = controller.getPlayerController().getPlayer().getPosX();
        float playerY = controller.getPlayerController().getPlayer().getPosY();
        camera.position.set(playerX, playerY, 0);
        camera.update();

        Main.getBatch().setProjectionMatrix(camera.combined);

        Main.getBatch().begin();
        controller.updateGame(delta);
        Main.getBatch().end();

        // Update labels
        healthLabel.setText("Health: " + (int) controller.getPlayerController().getPlayer().getHealth());
        timeLabel.setText("Time: " + (int) controller.getTimeRemaining()); // You need this logic
        levelLabel.setText("Level: " + controller.getPlayerController().getPlayer().getLevel());
        killLabel.setText("Kills: " + controller.getPlayerController().getPlayer().getKills());
        ammoLabel.setText("Ammo: " + controller.getWeaponController().getWeapon().getAmmo());

        //update xp process bar
        int xp = controller.getPlayerController().getPlayer().getXp();
        int nextXp = controller.getPlayerController().getPlayer().getXpToNextLevel();

        xpBar.setRange(0, nextXp);
        xpBar.setValue(xp);


        if (messageTimer > 0) {
            messageTimer -= delta;
            if (messageTimer <= 0) {
                messageLabel.setVisible(false);
            }
        }

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        controller.getWeaponController().handleWeaponShoot(screenX, screenY, camera);
        if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
            GameAssetManager.getGameAssetManager().getShootSound().play();
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
//        controller.getSmgWeaponController().handleWeaponRotation(screenX, screenY, camera);
        controller.getWeaponController().handleWeaponRotation(screenX, screenY, camera);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setMessage(String msg) {
        messageLabel.setText(msg);
        messageLabel.setVisible(true);
        messageTimer = 3f; // Show message for 3 seconds
    }

    public GameController getController() {
        return controller;
    }
}
