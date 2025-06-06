//package com.tilldawn.View;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.*;
//import com.badlogic.gdx.utils.ScreenUtils;
//import com.tilldawn.Control.LevelUpController;
//import com.tilldawn.Control.menu.TalentMenuController;
//import com.tilldawn.Control.menu.PauseMenuController;
//import com.tilldawn.Model.Ability;
//
//public class LevelUpView implements Screen {
//
//    private Stage stage;
//    private final LevelUpController controller;
//    private final Skin skin;
//    private final Table table;
//    private final TextButton continueButton;
//    private final TextButton giveUpButton;
//
//    public LevelUpView(LevelUpController controller,  Skin skin) {
//        this.controller = controller;
//        this.skin = skin;
//
//        this.table = new Table();
//        this.continueButton = new TextButton("Continue", skin);
//        this.giveUpButton = new TextButton("Give Up", skin);
//
//
//        controller.setView(this);
//    }
//
//    @Override
//    public void show() {
//        stage = new Stage();
//        Gdx.input.setInputProcessor(stage);
//
//        Image bg = new Image(new Texture("blueBack.png"));
//        bg.setFillParent(true);
//        stage.addActor(bg);
//
//        ScrollPane.ScrollPaneStyle scrollStyle = new ScrollPane.ScrollPaneStyle();
//        ScrollPane scrollPane;
//
//        Table contentTable = new Table(skin);
//        contentTable.defaults().left().pad(10).width(800);
//
//
//        scrollPane = new ScrollPane(contentTable, skin);
//        scrollPane.setScrollingDisabled(true, false);
//        scrollPane.setFadeScrollBars(false);
//
//        table.setFillParent(true);
//        table.top().padTop(30);
//        table.add(new Label("Choose One Ability", skin, "title")).padBottom(20).row();
//        table.add(scrollPane).expandY().fill().padBottom(20).row();
//
//        Table buttonRow = new Table();
//        buttonRow.add(continueButton).width(250).padRight(20);
//        buttonRow.add(giveUpButton).width(250);
//        table.add(buttonRow).padBottom(20).row();
//
//        continueButton.addListener(e -> {
//            if (!continueButton.isPressed()) return false;
//            controller.resumeGame();
//            return false;
//        });
//
//        giveUpButton.addListener(e -> {
//            if (!giveUpButton.isPressed()) return false;
//            controller.goToGameOver();
//            return false;
//        });
//
//        stage.addActor(table);
//    }
//
//    @Override
//    public void render(float delta) {
//        ScreenUtils.clear(0, 0, 0, 1);
//        stage.act(Math.min(delta, 1 / 30f));
//        stage.draw();
//    }
//
//    @Override public void resize(int width, int height) {}
//    @Override public void pause() {}
//    @Override public void resume() {}
//    @Override public void hide() {}
//    @Override public void dispose() {}
//
//    public void displayAbilities(List<Ability> abilities) {
//        Table abilityTable = new Table(skin);
//
//        for (Ability ability : abilities) {
//            TextButton abilityButton = new TextButton(ability.name(), skin);
//            abilityButton.addListener(e -> {
//                if (!abilityButton.isPressed()) return false;
//                controller.abilityChosen(ability);
//                return false;
//            });
//            abilityTable.add(abilityButton).pad(10).row();
//        }
//
//        table.add(abilityTable).row();
//    }
//
//
//
//}
package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Control.LevelUpController;
import com.tilldawn.Model.Ability;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;

import java.util.List;

public class LevelUpView implements Screen {

    private Stage stage;
    private final LevelUpController controller;
    private final Skin skin;
    private final Table table;
    private final TextButton continueButton;
    private final TextButton giveUpButton;
    private Table abilityTable;

    public LevelUpView(LevelUpController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.table = new Table();
        this.continueButton = new TextButton("Continue", skin);
        this.giveUpButton = new TextButton("Give Up", skin);
        this.abilityTable = new Table(skin);

        controller.setView(this); // triggers ability generation and display
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Image bg = new Image(new Texture("blueBack.png"));
        bg.setFillParent(true);
        stage.addActor(bg);

        table.setFillParent(true);
        table.top().padTop(30);

        table.add(new Label("Level Up", skin, "title")).padBottom(20).row();
        table.add(abilityTable).padBottom(20).row(); // abilities get filled in later

        Table buttonRow = new Table();
        buttonRow.add(continueButton).width(250).padRight(20);
        buttonRow.add(giveUpButton).width(250);
        table.add(buttonRow).padBottom(20).row();

        continueButton.addListener(e -> {
            if (!continueButton.isPressed()) return false;
            controller.resumeGame();
            return false;
        });

        giveUpButton.addListener(e -> {
            if (!giveUpButton.isPressed()) return false;
            controller.goToGameOver();
            return false;
        });

        stage.addActor(table);
    }

    /**
     * Called by the controller to populate ability buttons.
     */
    public void displayAbilities(List<Ability> abilities) {
        abilityTable.clear();

        for (Ability ability : abilities) {
            TextButton abilityButton = new TextButton(ability.name(), skin);
            abilityButton.addListener(e -> {
                if (!abilityButton.isPressed()) return false;
                if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                    GameAssetManager.getGameAssetManager().getClickButtonSound().play();
                }
                controller.abilityChosen(ability);
                return false;
            });
            abilityTable.add(abilityButton).pad(10).row();
        }
    }

    @Override public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
    }
}
