package com.tilldawn.Control.menu;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.TranslatableText;
import com.tilldawn.View.menu.MainMenuView;
import com.tilldawn.View.menu.TalentMenuView;

public class TalentMenuController {

    private TalentMenuView view;

    public void setView(TalentMenuView view) {
        this.view = view;
    }

    public void back() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
    }


    public String getHeroHints() {
        return TranslatableText.HeroHintsDescription.getText();
    }

    public String getKeyBindings() {
        return TranslatableText.KeyBindingsDescription.getText();
    }

    public String getCheatCodes() {
        return TranslatableText.CheatCodeInstructions.getText(); // already exists
    }

    public String getAbilitiesDescription() {
        return TranslatableText.AbilitiesDescription.getText();
    }

}
