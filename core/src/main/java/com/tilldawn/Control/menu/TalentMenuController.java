package com.tilldawn.Control.menu;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
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
        return "Hero Tips:\n- Shanna: Beautiful balanced hero.\n- Diamond: God of health.\n- Dasher: The fastest hero.";
    }

    public String getKeyBindings() {
        return "Current Key Bindings:\n- Move: W/A/S/D\n- Attack: mouse click";
    }

    public String getCheatCodes() {
        return "Cheats:\n- M key: -60 seconds\n- N key: Level up\n- H key: Increase heath\n- J key: Invite winged monster\n- K key: +10 ammos";
    }

    public String getAbilitiesDescription() {
        return "Vitality,      +1 max HP\n" +
            "Damager,       +25% damage for 10s\n" +
            "Procrease,     +1 projectile count\n" +
            "Amocrease,     +5 max ammo\n" +
            "Speedy,         x2 movement speed for 10s";
//        return "Abilities:\n- Dash: Short burst of speed (Cooldown: 5s)\n- Heal: Restores health (Cooldown: 10s)";
    }

}
