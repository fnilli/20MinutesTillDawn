package com.tilldawn.Control.menu;

import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.View.menu.LoginMenuView;
import com.tilldawn.View.menu.MainMenuView;
import com.tilldawn.View.menu.StartMenuView;

public class LoginMenuController {
    private LoginMenuView view;
    private final UserDatabase userDatabase = new UserDatabase();
    private boolean answerVerified = false;

    public void setView(LoginMenuView view) {
        this.view = view;
    }



    public void handleLoginButtons() {
        if (view.getLoginButton().isPressed()) {
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            String username = view.getUsernameField().getText();
            String password = view.getPasswordField().getText();

            User user = userDatabase.getUser(username);

            if (user == null) {
                view.setMessage("Username Not Found.");
            } else if (!user.getPassword().equals(password)) {
                view.setMessage("Incorrect Password.");
            } else {
                App.setCurrentUser(user);
                App.setCurrentPlayer(new Player(user));
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }

        if (view.getForgotPasswordButton().isPressed()) {
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            view.toggleSecurityUI(true);
            view.setMessage("Answer your security question:");
        }

        if(view.getBackButton().isPressed()){
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new StartMenuView(new StartMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }

        if (view.isSecurityUIVisible() && view.getSubmitSecurityButton().isPressed()) {
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getClickButtonSound().play();
            }
            String username = view.getUsernameField().getText();
            User user = userDatabase.getUser(username);

            if (user == null) {
                view.setMessage("Username Not Found.");
                return;
            }

            if (!answerVerified) {
                String answer = view.getSecurityAnswerField().getText();
                if (user.getSecurityAnswer() != null && user.getSecurityAnswer().equalsIgnoreCase(answer.trim())) {
                    view.setMessage("Correct! Enter your new password:");
                    view.getSecurityAnswerField().setText(""); // clear field
                    view.getSecurityAnswerField().setPasswordMode(true);
                    view.getSecurityAnswerField().setPasswordCharacter('*');
                    view.getSubmitSecurityButton().setText("Set Password");
                    answerVerified = true;
                } else {
                    view.setMessage("Incorrect answer.");
                }
            } else {
                String newPassword = view.getSecurityAnswerField().getText();
                if (!isRegistrationValid(newPassword)) {
                    view.setMessage("Weak Password.");
                    return;
                }


                user.setPassword(newPassword);
                userDatabase.save();
                view.setMessage("Password updated successfully.");
                answerVerified = false;
                view.toggleSecurityUI(false);
            }
        }
    }



    private boolean isRegistrationValid( String password) {
        if ( password.length() < 8) return false;

        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        String specialChars = "@_()*&%$#";

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




