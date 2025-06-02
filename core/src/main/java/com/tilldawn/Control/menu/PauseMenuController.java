package com.tilldawn.Control.menu;

import com.tilldawn.Control.GameController;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.GameOverView;
import com.tilldawn.View.GameView;
import com.tilldawn.View.menu.PauseMenuView;

public class PauseMenuController {
    private PauseMenuView view;
    private GameView gameView;

    public PauseMenuController(GameView gameView) {
        this.gameView = gameView;
    }
        public void setView(PauseMenuView view) {
            this.view = view;
        }

    public void resumeGame() {
        Main.getMain().setScreen(gameView); // resumes existing game
    }

    public void goToGameOver() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new GameOverView(App.getCurrentPlayer().getTotalGameTime(),  GameAssetManager.getGameAssetManager().getSkin()));
    }

}
