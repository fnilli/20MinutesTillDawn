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

    public LevelUpController(GameView gameView) {
        this.gameView = gameView;
        this.randomAbilities = new ArrayList<>();
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
        player.applyAbility(ability);
        resumeGame();
    }



}
