package com.tilldawn.View.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Control.menu.TalentMenuController;
import com.tilldawn.Control.menu.PauseMenuController;
import com.tilldawn.Model.*;

public class PauseMenuView implements Screen {

    private Stage stage;
    private final PauseMenuController controller;
    private final Player player;
    private final Skin skin;
    private final Table table;
    private final TextButton continueButton;
    private final TextButton giveUpButton;
    private final Label cheatCodesLabel;
    private final Label abilitiesLabel;

    public PauseMenuView(PauseMenuController controller,  Skin skin, Player player) {
        this.controller = controller;
        this.skin = skin;
        this.player = player;

        this.table = new Table();
        this.continueButton = new TextButton(TranslatableText.ContinueButton.getText(), skin);
        this.giveUpButton = new TextButton(TranslatableText.GiveUpButton.getText(), skin);

        this.cheatCodesLabel = new Label(TranslatableText.CheatCodeInstructions.getText(), skin);
        this.abilitiesLabel = new Label(getAcquiredAbilitiesDescription(player), skin);

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

        // Section: Cheat Codes
        contentTable.add(new Label(TranslatableText.CheatCodesTitle.getText(), skin, "title")).row();
        cheatCodesLabel.setWrap(true);
        contentTable.add(cheatCodesLabel).row();

        // Section: Achieved Abilities
        contentTable.add(new Label(TranslatableText.AchievedAbilitiesTitle.getText(), skin, "title")).row();
        abilitiesLabel.setText(getAcquiredAbilitiesDescription(player));
        abilitiesLabel.setWrap(true);
        contentTable.add(abilitiesLabel).row();


        scrollPane = new ScrollPane(contentTable, skin);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setFadeScrollBars(false);

        table.setFillParent(true);
        table.top().padTop(30);
        table.add(new Label(TranslatableText.PauseMenuTitle.getText(), skin, "title")).padBottom(20).row();
        table.add(scrollPane).expandY().fill().padBottom(20).row();

        Table buttonRow = new Table();
        buttonRow.add(continueButton).width(250).padRight(20);
        buttonRow.add(giveUpButton).width(250);
        table.add(buttonRow).padBottom(20).row();

        continueButton.addListener(e -> {
            if (!continueButton.isPressed()) return false;
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            controller.resumeGame();
            return false;
        });

        giveUpButton.addListener(e -> {
            if (!giveUpButton.isPressed()) return false;
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            controller.goToGameOver();
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

    public String getAcquiredAbilitiesDescription(Player player) {
        StringBuilder sb = new StringBuilder();
        for (Ability ability : player.getAcquiredAbilities()) {
            sb.append("• ").append(ability.name()).append("\n");
        }
        return sb.length() > 0 ? sb.toString() : TranslatableText.NoAbilitiesMessage.getText();
    }
    public String getCheatCodes() {
        return "Cheats:\n- M key: -60 seconds\n- N key: Level up\n- H key: Increase heath\n- J key: Invite winged monster\n- K key: +10 ammos";
    }


}
