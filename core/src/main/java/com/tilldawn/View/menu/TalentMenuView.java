package com.tilldawn.View.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Control.menu.TalentMenuController;

public class TalentMenuView implements Screen {

    private Stage stage;
    private final TalentMenuController controller;
    private final Skin skin;
    private final Table table;
    private final TextButton backButton;
    private final Label heroHintsLabel, keyBindingsLabel, cheatCodesLabel, abilitiesLabel;

    public TalentMenuView(TalentMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.table = new Table();
        this.backButton = new TextButton("Back", skin);

        this.heroHintsLabel = new Label(controller.getHeroHints(), skin);
        this.keyBindingsLabel = new Label(controller.getKeyBindings(), skin);
        this.cheatCodesLabel = new Label(controller.getCheatCodes(), skin);
        this.abilitiesLabel = new Label(controller.getAbilitiesDescription(), skin);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Image bg = new Image(new Texture("blueBack.png"));
        bg.setFillParent(true);
        stage.addActor(bg);

        ScrollPane.ScrollPaneStyle scrollStyle = new ScrollPane.ScrollPaneStyle();
        ScrollPane scrollPane;

        Table contentTable = new Table(skin);
        contentTable.defaults().left().pad(10).width(800);

        // Section: Hero Hints
        contentTable.add(new Label("Hero Hints", skin, "title")).row();
        heroHintsLabel.setWrap(true);
        contentTable.add(heroHintsLabel).row();

        // Section: Key Bindings
        contentTable.add(new Label("Key Bindings", skin, "title")).row();
        keyBindingsLabel.setWrap(true);
        contentTable.add(keyBindingsLabel).row();

        // Section: Cheat Codes
        contentTable.add(new Label("Cheat Codes", skin, "title")).row();
        cheatCodesLabel.setWrap(true);
        contentTable.add(cheatCodesLabel).row();

        // Section: Abilities
        contentTable.add(new Label("Abilities", skin, "title")).row();
        abilitiesLabel.setWrap(true);
        contentTable.add(abilitiesLabel).row();

        scrollPane = new ScrollPane(contentTable, skin);
        scrollPane.setScrollingDisabled(true, false); // Only vertical scroll
        scrollPane.setFadeScrollBars(false);

        table.setFillParent(true);
        table.top().padTop(30);
        table.add(new Label("Talent (Hint) Menu", skin, "title")).padBottom(20).row();
        table.add(scrollPane).expandY().fill().padBottom(20).row();
        table.add(backButton).width(200);

        backButton.addListener(e -> {
            if (!backButton.isPressed()) return false;
            controller.back();
            return false;
        });

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}

}
