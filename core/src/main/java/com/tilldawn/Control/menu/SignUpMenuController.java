package com.tilldawn.Control.menu;

import com.badlogic.gdx.Gdx;
import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.View.menu.MainMenuView;
import com.tilldawn.View.menu.SignUpMenuView;
import com.tilldawn.View.menu.StartMenuView;

public class SignUpMenuController {
    private SignUpMenuView view;
    private final UserDatabase userDatabase = new UserDatabase();

    public void setView(SignUpMenuView view) {
        this.view = view;
    }



    public void handleLoginButtons() {
        if(view.getBackButton().isPressed()){
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new StartMenuView(new StartMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
        if (view.getRegisterButton().isPressed()) {
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            String username = view.getUsernameField().getText();
            String password = view.getPasswordField().getText();
            String securityAnswer = view.getSecurityQuestionField().getText();

            if (!isRegistrationValid(password)) {
                view.setMessage("Weak password.");
                return;
            }

            if (securityAnswer == null || securityAnswer.trim().isEmpty()) {
                view.setMessage("Please answer the security question.");
                return;
            }

            // Save user with security answer
            if (userDatabase.register(username, password, securityAnswer)) {
                view.setMessage("User registered successfully!");
                Gdx.app.log("Register", "Saved at: " + Gdx.files.local("users.json").file().getAbsolutePath());

                User newUser = new User(username, password, securityAnswer);
                Player newPlayer = new Player(newUser);

                App.setCurrentUser(newUser);
                App.setCurrentPlayer(newPlayer);

                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else {
                view.setMessage("Username already taken.");
            }
        }
    }

    private boolean isRegistrationValid( String password) {
        if ( password.length() < 8) return false;

        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        String specialChars = "!@_()*&%$#";

        for (char c : password.toCharArray()) {
            if (java.lang.Character.isUpperCase(c)) hasUppercase = true;
            else if (java.lang.Character.isDigit(c)) hasDigit = true;
            else if (specialChars.indexOf(c) != -1) hasSpecialChar = true;
        }

        return hasUppercase && hasDigit && hasSpecialChar;
    }


    public com.badlogic.gdx.scenes.scene2d.ui.Skin getSkin() {
        return GameAssetManager.getGameAssetManager().getSkin();
    }
}






