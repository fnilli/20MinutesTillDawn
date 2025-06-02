package com.tilldawn.Control.menu;


import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.User;
import com.tilldawn.View.menu.*;

public class StartMenuController {
    private StartMenuView view;

    public void setView(StartMenuView view) {
        this.view = view;
    }

    public void handleStartButtons() {
        if (view.getRegisterButton().isPressed()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SignUpMenuView(new SignUpMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        } else if (view.getLoginButton().isPressed()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        } else if (view.getGuestButton().isPressed()) {

            User newUser = new User("guest", "guest", "guest");
            App.setCurrentUser(newUser);
            App.setCurrentPlayer(new Player(newUser));


            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }
}
