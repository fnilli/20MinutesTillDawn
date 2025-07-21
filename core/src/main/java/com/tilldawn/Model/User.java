package com.tilldawn.Model;

public class User {
    private String username;
    private String password;
    private String securityAnswer;
    private int score;
    private int kills;
    private float mostSurvivedTime; // in seconds

    public User() {
        // No-arg constructor needed for JSON serialization
    }

    public User(String username, String password, String securityAnswer) {
        this.username = username;
        this.password = password;
        this.securityAnswer = securityAnswer;
        this.score = 0;
    }

    // getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getSecurityAnswer() { return securityAnswer; }
    public void setSecurityAnswer(String securityAnswer) { this.securityAnswer = securityAnswer; }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getKills() {
        return kills;
    }
    public void setKills(int kills) {
        this.kills = kills;
    }

    public float getMostSurvivedTime() {
        return mostSurvivedTime;
    }
    public void setMostSurvivedTime(float mostSurvivedTime) {
        this.mostSurvivedTime = mostSurvivedTime;
    }

}
