package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.Ability;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Player;
import com.tilldawn.View.GameOverView;
import com.tilldawn.View.GameView;
import com.tilldawn.View.LevelUpView;
import com.tilldawn.View.menu.PauseMenuView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LevelUpController {
    private LevelUpView view;
    private GameView gameView;
    private List<Ability> randomAbilities;
    private Player player;

    public LevelUpController(GameView gameView) {
        this.gameView = gameView;
        this.randomAbilities = new ArrayList<>();
        this.player = App.getCurrentPlayer();
    }



    public void resumeGame() {
        Main.getMain().setScreen(gameView); // resumes existing game
    }

    public void goToGameOver() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new GameOverView(App.getCurrentPlayer().getTotalGameTime(),  GameAssetManager.getGameAssetManager().getSkin()));
    }


    public void setView(LevelUpView view) {
        this.view = view;
        generateRandomAbilities();
        view.displayAbilities(randomAbilities); // You’ll implement this in View
    }

    public void generateRandomAbilities() {
        List<Ability> allAbilities = new ArrayList<>(Arrays.asList(Ability.values()));
        Collections.shuffle(allAbilities);
        randomAbilities = allAbilities.subList(0, 3);
    }

    public void abilityChosen(Ability ability) {
        Player player = App.getCurrentPlayer();
        activateAbility(    ability);
        resumeGame();
    }


    public void activateAbility(Ability ability) {
        player.getAcquiredAbilities().add(ability);
        switch (ability) {
            case VITALITY:     // +1 max HP
                player.increaseHealth();
                break;

            case AMOCREASE:  // +5 max ammo
                gameView.getController().getWeaponController().increaseMaxAmmo(5);
                break;

            case PROCREASE: // +1 projectile count
                gameView.getController().getWeaponController().increaseProjectileCount();
                break;

            case DAMAGER:     // +25% damage for 10s
                if (!player.isDamagerActive()){
                    gameView.getController().getWeaponController().increaseDamage();
                    player.setDamagerTimer(10f);
                    player.setDamagerActive(true);
                }
                break;

            case SPEEDY:   // x2 movement speed for 10s
                if (!player.isSpeedyActive()) {
                    player.setSpeed(player.getSpeed() * 2);
                    player.setSpeedyTimer( 10f);
                    player.setSpeedyActive(true);
                }
                break;
        }
    }
}
