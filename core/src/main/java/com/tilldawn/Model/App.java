package com.tilldawn.Model;

import com.badlogic.gdx.Screen;
import com.tilldawn.View.GameView;
import com.tilldawn.View.menu.ProfileMenuView;

import java.util.ArrayList;

public class App {
    private static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;
    private static Player currentPlayer;
    private static Screen currentScreen;


    private static GameView gameView;

    public void setScreen(Screen screen) {
        currentScreen = screen;
    }

    public static Screen getCurrentScreen() {
        return currentScreen;
    }

    public static boolean isInProfileMenu() {
        return currentScreen instanceof ProfileMenuView;
    }

    public App() {
    }

    public static GameView getGameView() {
        return gameView;
    }

    public static void setGameView(GameView gameView) {
        App.gameView = gameView;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    private User getUserByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }

    public static void logout() {
        currentUser = null;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        App.currentPlayer = currentPlayer;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }


}
