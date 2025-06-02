package com.tilldawn.Control.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.View.menu.MainMenuView;
import com.tilldawn.View.menu.ProfileMenuView;
import com.tilldawn.View.menu.StartMenuView;

public class ProfileMenuController {
    private ProfileMenuView view;
    private final UserDatabase userDatabase;
    private final User currentUser;

    public ProfileMenuController() {
        this.userDatabase =  new UserDatabase();
        this.currentUser = App.getCurrentUser();
    }

    public void setView(ProfileMenuView view) {
        this.view = view;
    }


    public void changeUsername(String newUsername) {
        if (newUsername.isEmpty()) {
            view.setMessage("Username cannot be empty.");
            return;
        }
        if (newUsername.equals(currentUser.getUsername())) {
            view.setMessage("That's already your username.");
            return;
        }

        if (userDatabase.getUser(newUsername) != null) {
            view.setMessage("Username already taken.");
            return;
        }

        // Update the user and save
        currentUser.setUsername(newUsername);
        userDatabase.save();
        view.setMessage("Username changed to: " + newUsername);
    }

    public void changeAvatar() {
        // Run file chooser in a separate thread
        new Thread(() -> {
            javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
            fileChooser.setDialogTitle("Select Avatar Image");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PNG Images", "png"));

            int result = fileChooser.showOpenDialog(null);
            if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
                java.io.File selectedFile = fileChooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();

                // Run texture loading on LibGDX main thread
                Gdx.app.postRunnable(() -> {
                    try {
                        App.getCurrentPlayer().setAvatarPath(path); // Updates texture and sprite
                        view.setMessage("Avatar updated successfully.");
                    } catch (Exception e) {
                        view.setMessage("Failed to load image.");
                        e.printStackTrace();
                    }
                });
            }
        }).start();
    }



    public void changePassword(String newPassword) {
        if (newPassword.isEmpty()) {
            view.setMessage("Password cannot be empty.");
            return;
        }

        if (!isRegistrationValid(newPassword)) {
            view.setMessage("Weak password.");
            return;
        }

        currentUser.setPassword(newPassword);
        userDatabase.save();
        view.setMessage("Password changed successfully.");
    }


    public void deleteAccount() {
        userDatabase.deleteUser(currentUser.getUsername());
        userDatabase.save();
        view.setMessage("Account deleted. Logging out...");

        // Add a 1-second delay before switching screens
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new StartMenuView(new StartMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }, 1); // 1 seconds delay
    }




    private boolean isRegistrationValid( String password) {
        if ( password.length() < 8) return false;

        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        String specialChars = "!@_()*&%$#";

        for (char c : password.toCharArray()) {
            if (java.lang.Character.isUpperCase(c)) hasUppercase = true;
            else if (java.lang.Character.isDigit(c)) hasDigit = true;
            else if (specialChars.indexOf(c) != -1) hasSpecialChar = true;
        }

        return hasUppercase && hasDigit && hasSpecialChar;
    }

    public void back(){
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
    }



    public void setAvatar(String fileName) {
        Player player = App.getCurrentPlayer();


        switch (fileName) {
            case "Idle_0.png": player.setHero(Heros.Shana); break;
            case "Idle_1.png": player.setHero(Heros.Diamond); break;
            case "Idle_2.png": player.setHero(Heros.Scarlet); break;
            case "Idle_3.png": player.setHero(Heros.Lilith); break;
            case "Idle_4.png": player.setHero(Heros.Dasher); break;
        }

        player.setAvatarPath("assets/Images/Sprite/Idle/" + fileName);
        userDatabase.save();
        view.setMessage("Avatar set to " + fileName);
    }


}



