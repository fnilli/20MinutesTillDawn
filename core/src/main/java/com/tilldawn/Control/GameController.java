package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tilldawn.Control.menu.PauseMenuController;
import com.tilldawn.Control.menu.TalentMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.Model.weapons.Weapon;
import com.tilldawn.View.GameOverView;
import com.tilldawn.View.GameView;
import com.tilldawn.View.WinView;
import com.tilldawn.View.menu.PauseMenuView;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;
//    private SmgWeaponController smgWeaponController;
//    private ShotgunController shotgunController;
//    private RevolverController revolverController;
    public static float timeRemaining =  App.getCurrentPlayer().getTotalGameTime();

    public void setView(GameView view) {
    this.view = view;

    Player sharedPlayer = App.getCurrentPlayer();
    PlayerController controller = new PlayerController(sharedPlayer);
    controller.loadKeyBindingsFromPreferences();

    playerController = controller;
    worldController = new WorldController(playerController);
    weaponController = new WeaponController(new Weapon(sharedPlayer.getWeaponType()), sharedPlayer, worldController);

    playerController.loadKeyBindingsFromPreferences();


    }

    public void updateGame(float delta) {
        if (view != null) {
            // Decrease the timer based on delta time
            timeRemaining -= com.badlogic.gdx.Gdx.graphics.getDeltaTime();
            if (timeRemaining < 0) {
                timeRemaining = 0;
                //WON
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new WinView( App.getCurrentPlayer().getTotalGameTime(), GameAssetManager.getGameAssetManager().getSkin()));
            }

            if(playerController.getPlayer().getHealth() < 0){
                //LOST
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameOverView( App.getCurrentPlayer().getTotalGameTime(), GameAssetManager.getGameAssetManager().getSkin()));
            }



            worldController.update(delta);
            playerController.update(delta);
            weaponController.update();
            handleCheats();


        }
    }

    public void handleCheats() {
        Player player = playerController.getPlayer();

        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            GameController.timeRemaining -= 60; // Decrease time by 60 seconds
            view.setMessage("Cheated -60 seconds");
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
            player.increaseXp(player.getXpToNextLevel() - player.getXp());
            view.setMessage("Cheated Level +1");
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.H)) {
            player.increaseHealth();
            view.setMessage("Cheated Restored health");

//            if (player.getHealth() < player.getHero().getHp()) {
//                player.increaseHealth();
//                view.setMessage("Cheated Restored health");
//            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.J)) {
            worldController.getWingedMonsterController().spawnMonster(2450, 700);
            view.setMessage("Winged Monster Is Coming...");
        }

            if (Gdx.input.isKeyJustPressed(Input.Keys.K)) {
            // Add 10 ammo
                weaponController.getWeapon().setAmmo(weaponController.getWeapon().getAmmo() + 10);
            view.setMessage("Cheated +10 Ammo");
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            PauseMenuController pauseController = new PauseMenuController(this.view); // use the existing game view
            TalentMenuController talentController = new TalentMenuController(); // create or retrieve this as needed
            Skin skin = GameAssetManager.getGameAssetManager().getSkin();

            Main.getMain().setScreen(new PauseMenuView(pauseController,  skin, App.getCurrentPlayer()));
            System.out.println("Abilities: " + player.getAcquiredAbilities());

        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            weaponController.handleReloadKeyPress();
            view.setMessage("weapon reloaded");
        }

        }

    public float getTimeRemaining() {
        return timeRemaining;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }

//        public SmgWeaponController getSmgWeaponController() {
//        return smgWeaponController;
//    }
//
//
//    public ShotgunController getShotgunController() {
//        return shotgunController;
//    }
//
//    public RevolverController getRevolverController() {
//        return revolverController;
//    }
}
