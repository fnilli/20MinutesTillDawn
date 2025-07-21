package com.tilldawn.Model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    private static final String FILE_PATH = "users.json";
    private ArrayList<User> users;
    private final Json json = new Json();

    public UserDatabase() {
        load();
    }

    public void load() {
        FileHandle file = Gdx.files.local(FILE_PATH);
        if (file.exists()) {
            users = json.fromJson(ArrayList.class, User.class, file.readString());
        } else {
            users = new ArrayList<>();
        }
    }

    public void save() {
        FileHandle file = Gdx.files.local(FILE_PATH);
        file.writeString(json.prettyPrint(users), false);
    }


    public User getUser(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }


    public boolean register(String username, String password, String securityAnswer) {
        if (getUser(username) != null) return false; // user exists
        User newUser = new User(username, password, securityAnswer);
        users.add(newUser);
        save();
        return true;
    }

    public void deleteUser(String username) {
        users.removeIf(u -> u.getUsername().equals(username));
    }

    public boolean updateUserStats(String username, int newScore, int newKills, float survivedTime) {
        User user = getUser(username);
        if (user == null) return false;

        // Update fields
        user.setScore(newScore);
        user.setKills(newKills);

        // Only update mostSurvivedTime if it’s greater than the current value
        if (survivedTime > user.getMostSurvivedTime()) {
            user.setMostSurvivedTime(survivedTime);
        }

        save(); // Save the updated list
        return true;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
