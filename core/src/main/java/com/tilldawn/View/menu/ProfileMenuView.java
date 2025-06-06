package com.tilldawn.View.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Control.menu.ProfileMenuController;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.TranslatableText;

public class ProfileMenuView implements Screen {

    private Stage stage;
    private final Label titleLabel;
    private final TextButton changeUsernmeButton;
    private final TextButton changePasswordButton;
    private final TextField changingField;
    private final TextButton deleteAccountButton;
    private final TextButton changeAvatarButton;
    private final Table table;
    private final ProfileMenuController controller;
    private  Label messageLabel;
    private final TextButton backButton;
    private ImageButton avatarButton1, avatarButton2, avatarButton3, avatarButton4, avatarButton5;
    private final TextButton dragDropButton;




    public ProfileMenuView(ProfileMenuController controller, Skin skin) {
        this.controller = controller;
        this.titleLabel = new Label(TranslatableText.ProfileMenuTitle.getText(), skin.get("title", Label.LabelStyle.class));
        this.changingField = new TextField("", skin);
        this.changeUsernmeButton = new TextButton(TranslatableText.ChangeUsername.getText(), skin);
        this.changePasswordButton = new TextButton(TranslatableText.ChangePassword.getText(), skin);
        this.deleteAccountButton = new TextButton(TranslatableText.DeleteAccount.getText(), skin);
        this.changeAvatarButton = new TextButton(TranslatableText.ChooseAvatar.getText(), skin);
        this.table = new Table();
        this.messageLabel = new Label("", skin);
        messageLabel.setColor(Color.MAGENTA);
        this.backButton = new TextButton(TranslatableText.Back.getText(), skin);
        this.dragDropButton = new TextButton(TranslatableText.DragAndDrop.getText(), skin);

        // Load image button styles
        ImageButton.ImageButtonStyle style1 = new ImageButton.ImageButtonStyle();
        style1.imageUp = new Image(new Texture("assets/Images/Sprite/Idle/Idle_0.png")).getDrawable();

        ImageButton.ImageButtonStyle style2 = new ImageButton.ImageButtonStyle();
        style2.imageUp = new Image(new Texture("assets/Images/Sprite/Idle/Idle_1.png")).getDrawable();

        ImageButton.ImageButtonStyle style3 = new ImageButton.ImageButtonStyle();
        style3.imageUp = new Image(new Texture("assets/Images/Sprite/Idle/Idle_2.png")).getDrawable();

        ImageButton.ImageButtonStyle style4 = new ImageButton.ImageButtonStyle();
        style4.imageUp = new Image(new Texture("assets/Images/Sprite/Idle/Idle_3.png")).getDrawable();

        ImageButton.ImageButtonStyle style5 = new ImageButton.ImageButtonStyle();
        style4.imageUp = new Image(new Texture("assets/Images/Sprite/Idle/Idle_4.png")).getDrawable();

// Create buttons
        avatarButton1 = new ImageButton(style1);
        avatarButton2 = new ImageButton(style2);
        avatarButton3 = new ImageButton(style3);
        avatarButton4 = new ImageButton(style4);
        avatarButton5 = new ImageButton(style5);
        avatarButton1.getImageCell().size(128, 128);
        avatarButton2.getImageCell().size(128, 128);
        avatarButton3.getImageCell().size(128, 128);
        avatarButton4.getImageCell().size(128, 128);
        avatarButton5.getImageCell().size(128, 128);

// Set click listeners
        avatarButton1.addListener(event -> {
            if (!avatarButton1.isPressed()) return false;
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            controller.setAvatar("Idle_0.png");
            return false;
        });

        avatarButton2.addListener(event -> {
            if (!avatarButton2.isPressed()) return false;
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            controller.setAvatar("Idle_1.png");
            return false;
        });

        avatarButton3.addListener(event -> {
            if (!avatarButton3.isPressed()) return false;
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            controller.setAvatar("Idle_2.png");
            return false;
        });

        avatarButton4.addListener(event -> {
            if (!avatarButton4.isPressed()) return false;
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            controller.setAvatar("Idle_3.png");
            return false;
        });

        avatarButton5.addListener(event -> {
            if (!avatarButton5.isPressed()) return false;
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            controller.setAvatar("Idle_4.png");
            return false;
        });


        controller.setView(this);
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

        changeUsernmeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                    GameAssetManager.getGameAssetManager().getClickButtonSound().play();
                }
                String newUsername = changingField.getText().trim();
                if (newUsername.isEmpty()) {
                    messageLabel.setText(TranslatableText.EmptyUsernameWarning.getText());
                } else {
                    controller.changeUsername(newUsername);
                }
            }
        });

        changePasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                    GameAssetManager.getGameAssetManager().getClickButtonSound().play();
                }
                String newPassword = changingField.getText().trim();
                if (newPassword.isEmpty()) {
                    messageLabel.setText(TranslatableText.EmptyPasswordWarning.getText());
                } else {
                    controller.changePassword(newPassword);
                }
            }
        });


        deleteAccountButton.addListener(event -> {
            if (!deleteAccountButton.isPressed()) return false;
            controller.deleteAccount();
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            return false;
        });

        changeAvatarButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                    GameAssetManager.getGameAssetManager().getClickButtonSound().play();
                }
                controller.changeAvatar();
            }
        });

        backButton.addListener(event -> {
            if (!backButton.isPressed()) return false;
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            controller.back();
            return false;
        });


        table.setFillParent(true);
        table.add(titleLabel).colspan(5).padBottom(200);
        table.row().pad(10, 0, 10, 0);

        table.center();

        table.add(avatarButton1).size(64, 64);
        table.add(avatarButton2).size(64, 64);
        table.add(avatarButton3).size(64, 64) ;
        table.add(avatarButton4).size(64, 64);
        table.add(avatarButton5).size(64, 64);
        table.row().pad(10, 0, 10, 0);


        table.add(changeUsernmeButton).left().colspan(1).width(470);
        table.add(changePasswordButton).right().colspan(5).width(470);
        table.row().pad(10, 0, 10, 0);
        table.row().pad(10, 0, 10, 0);

        table.add(deleteAccountButton).left().colspan(1).width(470);
        table.add(changeAvatarButton).right().colspan(5).width(470);

        table.row().pad(10, 0, 10, 0);
        table.add(changingField).left().colspan(1).width(470);
        table.add(dragDropButton).right().colspan(5).width(470);
        table.row().pad(10, 0, 10, 0);
        table.add(messageLabel).colspan(3).width(470).padLeft(250);

        table.row().pad(0, 0, 0, 600);
        table.add(backButton).width(200);


        stage.addActor(table);


    }


    public void processDragDrop(String file) {
        App.getCurrentPlayer().setAvatarPath(file);
        setMessage(TranslatableText.AvatarUpdated.getText());
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


    public void setMessage(String msg) {
        messageLabel.setText(msg);
    }


    public TextButton getChangeUsernmeButton() {
        return changeUsernmeButton;
    }

    public TextButton getChangePasswordButton() {
        return changePasswordButton;
    }

    public TextField getChangingField() {
        return changingField;
    }

    public TextButton getDeleteAccountButton() {
        return deleteAccountButton;
    }

    public TextButton getChangeAvatarButton() {
        return changeAvatarButton;
    }

    public Label getMessageLabel() {
        return messageLabel;
    }

    public void setMessageLabel(Label messageLabel) {
        this.messageLabel = messageLabel;
    }

    public TextButton getBackButton() {
        return backButton;
    }
}

