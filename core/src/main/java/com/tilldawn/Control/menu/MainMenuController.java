package com.tilldawn.Control.menu;

import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Player;
import com.tilldawn.View.menu.*;
// Import your other Views for Settings, Profile, Scoreboard, Talent, Login etc.

public class MainMenuController {
    private MainMenuView view;
    Player player = App.getCurrentPlayer();

    // Example user data; in your real app, load this from login or saved state
    private String currentUsername = App.getCurrentPlayer().getUser().getUsername();
    private int currentScore = App.getCurrentPlayer().getScore();

    public void setView(MainMenuView view) {
        this.view = view;
        updateUserInfoOnView();
    }

    private void updateUserInfoOnView() {
        if (view != null) {
            view.setUsername(currentUsername);
            view.setScore(currentScore);
            // For avatar, you can replace the image in the view or update its drawable
        }
    }

    public void handleMainMenuButtons() {
        if (view == null) return;

        if (view.getContinueButton().isPressed()) {
            System.out.println("Continue Last Game clicked");
            // Load last saved game or state here
        } else if (view.getSettingsButton().isPressed()) {
            System.out.println("Settings clicked");
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new SettingView(new SettingController()));
        }
        else if (view.getProfileButton().isPressed()) {
            System.out.println("Profile clicked");
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));

        } else if (view.getPreGameButton().isPressed()) {
            System.out.println("Pre-Game clicked");
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));

        } else if (view.getScoreboardButton().isPressed()) {
            System.out.println("Scoreboard clicked");
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ScoreboardMenuView(GameAssetManager.getGameAssetManager().getSkin()));

        } else if (view.getTalentButton().isPressed()) {
            System.out.println("Talent clicked");
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new TalentMenuView(new TalentMenuController(), GameAssetManager.getGameAssetManager().getSkin()));

        } else if (view.getLogoutButton().isPressed()) {
            System.out.println("Logout clicked");
            App.logout();
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(
                new StartMenuView(new StartMenuController(), GameAssetManager.getGameAssetManager().getSkin())
            );
        }
    }
}
