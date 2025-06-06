package com.tilldawn.Control.menu;

import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.menu.MainMenuView;
import com.tilldawn.View.menu.SettingView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import javax.swing.*;

public class SettingController {
    private SettingView view;

    public void setLanguage(String language) {
        switch (language){
            case "English": App.getCurrentPlayer().setLanguage("English"); break;
            case "French": App.getCurrentPlayer().setLanguage("French"); break;
            default: App.getCurrentPlayer().setLanguage("English"); break;
        }

        System.out.println(App.getCurrentPlayer().getLanguage());
    }

    public void setVolume(float value) {
        GameAssetManager.getGameAssetManager().setVolume(value);
    }

    public void changeMusic(String selected) {
        switch (selected) {
            case "Default":
                GameAssetManager.getGameAssetManager().setMusic(GameAssetManager.getGameAssetManager().getDefaultMusicPath());
                break;
            case "Theme1":
                GameAssetManager.getGameAssetManager().setMusic(GameAssetManager.getGameAssetManager().getMusic1Path());
                break;
            case "Theme2":
                GameAssetManager.getGameAssetManager().setMusic(GameAssetManager.getGameAssetManager().getMusic2Path());
                break;
            case "Theme3":
                GameAssetManager.getGameAssetManager().setMusic(GameAssetManager.getGameAssetManager().getMusic3Path());
                break;
            default:
                GameAssetManager.getGameAssetManager().setMusic(GameAssetManager.getGameAssetManager().getMusic4Path());
                break;
        }
    }

    public void handleSettingButtons() {
        if (view == null) return;

        if (view.getBackButton().isPressed()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }

    public void setSfxEnabled(boolean enabled) {
        GameAssetManager.getGameAssetManager().setSfxEnabled(enabled);
    }
    public void setMoveeKey(String direction, String key) {
        // Save key setting to player preferences or memory //TODO
    }

    public String getMoveKey(String direction) {
        Preferences prefs = Gdx.app.getPreferences("PlayerSettings");
        switch (direction) {
            case "up": return prefs.getString("moveUp", "W");
            case "down": return prefs.getString("moveDown", "S");
            case "left": return prefs.getString("moveLeft", "A");
            case "right": return prefs.getString("moveRight", "D");
            default: return "W";
        }
    }




    public void setMoveKey(String direction, String key) {
        Preferences prefs = Gdx.app.getPreferences("PlayerSettings");
        switch (direction) {
            case "up": prefs.putString("moveUp", key); break;
            case "down": prefs.putString("moveDown", key); break;
            case "left": prefs.putString("moveLeft", key); break;
            case "right": prefs.putString("moveRight", key); break;
        }
        prefs.flush(); // Save the preferences
    }


    public boolean isAutoReloadEnabled() {
        return App.getCurrentPlayer().getAutoReload();
    }

    public void setAutoReloadEnabled(boolean enabled) {
        App.getCurrentPlayer().setAutoReload(enabled);
    }

    public boolean isDarkThemeEnabled() {
        return App.getCurrentPlayer().isDarkTheme();
    }

    public void setDarkThemeEnabled(boolean enabled) {
        App.getCurrentPlayer().setDarkTheme(enabled);
    }

    public boolean isSfxEnabled() {
        return App.getCurrentPlayer().isSfx();
    }

    public void setSfx(boolean sfx) {
        App.getCurrentPlayer().setSfx(sfx);
    }


}

