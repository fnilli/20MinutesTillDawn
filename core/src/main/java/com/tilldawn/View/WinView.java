package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Control.GameController;
import com.tilldawn.Control.menu.MainMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Player;
import com.tilldawn.View.menu.MainMenuView;

import static com.tilldawn.Control.GameController.timeRemaining;

public class WinView implements Screen {
    private Stage stage;
    private final Table table;
    private final Label titleLabel;
    private Label username;
    private Label score;
    private Label kills;
    private Label timeLeft;
    private  Label won;
    private TextButton retryButton;
    private TextButton backButton;
    private float totalTime;

    public WinView(float totalTime, Skin skin) {
        this.titleLabel = new Label("Y O U    W O N !", skin.get("title", Label.LabelStyle.class));
        this.username = new Label("", skin);;
        this.score = new Label("", skin);;
        this.kills = new Label("", skin);;
        this.timeLeft = new Label("", skin);;
        this.table = new Table();


        this.won = new Label("Congratulations!", skin);
        this.retryButton = new TextButton("Retry", skin);
        this.backButton = new TextButton("Back to Menu", skin);
        this.totalTime = totalTime;


        GameAssetManager.getGameAssetManager().getWonSound().play();


    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //  Background
        Texture bgTexture = new Texture(Gdx.files.internal("blueBack.png"));
        Image background = new Image(bgTexture);
        background.setFillParent(true);
        stage.addActor(background);


        Player player = App.getCurrentPlayer();
        if (player != null) {
            username.setText("username: " + player.getUser().getUsername());
        } else username.setText("name: guest");

        assert player != null;
        int scoreNumber = (int) (player.getKills() * totalTime);

        score.setText("Score: " + scoreNumber);
        int killsCountNumber = player.getKills();
        kills.setText("Kill count: " +  killsCountNumber);
        int timeSurvived = (int) (totalTime - timeRemaining);
        timeLeft.setText("Time you survived: " + timeSurvived + " seconds");


        retryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });




        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });




        table.setFillParent(true);
//        table.top().padTop(20); // Move entire table content toward the top

// No need to add titleLabel twice
        table.add(titleLabel).padBottom(100).row();
        table.add(won).padBottom(20).row();
        table.row().pad(10, 0, 10, 0);

        table.add(username).padBottom(20).row();
        table.add(score).padBottom(20).row();
        table.add(kills).padBottom(20).row();
        table.add(timeLeft).padBottom(20).row();
        table.row().pad(10, 0, 10, 0);
        table.row().pad(10, 0, 10, 0);

        table.add(retryButton).width(400).padBottom(15).row();
        table.add(backButton).width(400).row();

        stage.addActor(table);

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

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
}
