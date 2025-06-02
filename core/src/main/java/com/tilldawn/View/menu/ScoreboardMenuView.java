package com.tilldawn.View.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.menu.MainMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.User;
import com.tilldawn.Model.UserDatabase;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreboardMenuView implements Screen {
    private final Stage stage;
    private final Skin skin;
    private final Table table;
    private final SelectBox<String> sortBox;
    private final UserDatabase userDatabase = new UserDatabase();
    private final String currentUsername = App.getCurrentUser().getUsername();

    public ScoreboardMenuView(Skin skin) {
        this.skin = skin;
        stage = new Stage(new ScreenViewport());
        table = new Table();
        table.top();  // important: align contents to top inside ScrollPane

        sortBox = new SelectBox<>(skin);
        sortBox.setItems("Score", "Username", "Kills", "Survive Time");
        sortBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                updateTable(sortBox.getSelected());
            }
        });

        // Back button at bottom left
        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });

        // Layout top controls
        Table top = new Table();
        top.top().left();
        top.setFillParent(true);
        top.add(new Label("Sort by: ", skin)).padLeft(30);
        top.add(sortBox).pad(10);

        updateTable("Score");

        // Setup ScrollPane for table
        ScrollPane scrollPane = new ScrollPane(table, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(false, false);
        scrollPane.setForceScroll(false, true);
        float scrollWidth = Gdx.graphics.getWidth() - 600;  // new narrower width
        float scrollHeight = Gdx.graphics.getHeight() - 200;
        float scrollX = (Gdx.graphics.getWidth() - scrollWidth) / 2f;
        float scrollY = 100;

        scrollPane.setBounds(scrollX, scrollY, scrollWidth, scrollHeight);

        // Back button at bottom left
        backButton.setPosition(30, 20);
        backButton.setWidth(250);

        Gdx.input.setInputProcessor(stage);

        Texture bgTexture = new Texture(Gdx.files.internal("blueBack.png"));
        Image background = new Image(bgTexture);
        background.setFillParent(true);
        stage.addActor(background);

        stage.addActor(scrollPane);
        stage.addActor(top);
        stage.addActor(backButton);

    }

    private void updateTable(String sortType) {
        table.clear();

        ArrayList<User> users = new ArrayList<>(userDatabase.getUsers());

        switch (sortType) {
            case "Username":
                users.sort(Comparator.comparing(User::getUsername));
                break;
            case "Kills":
                users.sort(Comparator.comparingInt(User::getKills).reversed());
                break;
            case "Survive Time":
                users.sort(Comparator.comparingDouble(User::getMostSurvivedTime).reversed());
                break;
            default:
                users.sort(Comparator.comparingInt(User::getScore).reversed());
        }

        // Header row with bold style
        Label.LabelStyle headerStyle = new Label.LabelStyle(skin.getFont("font"), Color.WHITE);
        Table headerRow = new Table(skin);
        headerRow.setBackground(skin.newDrawable("white", Color.DARK_GRAY));
        headerRow.pad(5);

        Label usernameH = new Label("Username", headerStyle);
        Label scoreH = new Label("Score", headerStyle);
        Label killsH = new Label("Kills", headerStyle);
        Label surviveH = new Label("Survive Time", headerStyle);

        usernameH.setAlignment(Align.left);
        scoreH.setAlignment(Align.center);
        killsH.setAlignment(Align.center);
        surviveH.setAlignment(Align.center);

        usernameH.setFontScale(1.1f);
        scoreH.setFontScale(1.1f);
        killsH.setFontScale(1.1f);
        surviveH.setFontScale(1.1f);

        headerRow.add(usernameH).width(400).pad(5).left();
        headerRow.add(scoreH).width(100).pad(5);
        headerRow.add(killsH).width(100).pad(5);
        headerRow.add(surviveH).width(200).pad(5);

        table.add(headerRow).fillX().expandX().padBottom(15);
        table.row();

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);

            Label.LabelStyle userStyle = new Label.LabelStyle(skin.getFont("font"), Color.WHITE);
            Label username = new Label(user.getUsername(), userStyle);
            Label score = new Label(String.valueOf(user.getScore()), userStyle);
            Label kills = new Label(String.valueOf(user.getKills()), userStyle);
            Label surviveTime = new Label(String.format("%.2f", user.getMostSurvivedTime()), userStyle);

            username.setAlignment(Align.left);
            score.setAlignment(Align.center);
            kills.setAlignment(Align.center);
            surviveTime.setAlignment(Align.center);

            Table row = new Table(skin);
            row.pad(4);

            // Highlight top 3 rows
            if (i == 0) row.setBackground(skin.newDrawable("white", Color.GOLD));
            else if (i == 1) row.setBackground(skin.newDrawable("white", Color.GOLDENROD));
            else if (i == 2) row.setBackground(skin.newDrawable("white", Color.SKY));

            // Highlight current user row differently
            if (user.getUsername().equals(currentUsername)) {
                row.setBackground(skin.newDrawable("white", Color.CORAL));
                username.setColor(Color.BLACK);
                score.setColor(Color.BLACK);
                kills.setColor(Color.BLACK);
                surviveTime.setColor(Color.BLACK);
                username.setStyle(new Label.LabelStyle(skin.getFont("font"), Color.BLACK));
            }
            row.add(username).width(400).pad(5).left();
            row.add(score).width(100).pad(5).center();
            row.add(kills).width(100).pad(5).center();
            row.add(surviveTime).width(200).pad(5).center();

            table.add(row).fillX().expandX();
            table.row();
        }
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
//        bgTexture.dispose();
    }
}
