package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tilldawn.Control.LevelUpController;

import com.tilldawn.Main;
import com.tilldawn.Model.weapons.Weapons;
import com.tilldawn.View.LevelUpView;

import java.util.*;

public class Player {
    private User user;
    private Texture playerTexture = new Texture(GameAssetManager.getGameAssetManager().getCharacter1_idle0());
    private Sprite playerSprite = new Sprite(playerTexture);
    private Heros hero = Heros.Shana; //default
    private float Health = hero.getHp();
    private CollisionRect rect ;
    private float time = 0;
    private float totalGameTime = 120;
    private float speed = hero.getSpeed();
    private int score = 0;
    private int level = 1;
    private int xp = 0;
    private float stateTime = 0f; // in Player class
    private int kills = 0;
    private int ammo = 0;
    private boolean autoReload = true;
    private boolean darkTheme = false;
    private boolean sfx = true;
    private String avatarPath = "assets/Images/Sprite/Idle/Idle_0.png"; // default

    private Weapons weaponType = Weapons.Smg;
    private float lastHitTime = 0f;
    private final float hitCooldown = 1.0f;

    //ability stuff
    private Set<Ability> acquiredAbilities = new HashSet<>();
    private boolean speedyActive = false;
    private float speedyTimer = 0f;
    private boolean damagerActive = false;
    private float damagerTimer = 0f;

    private boolean isPlayerIdle = true;
    private String language = "English";


    private boolean takingDamage = false;
    private float damageTime = 0f;
    private final float DAMAGE_DURATION = 0.3f; // seconds


    public Player(User user){
        this.user = user;

        // Assign a random hero from the enum
        Heros[] allHeroes = Heros.values();
        int index = (int)(Math.random() * allHeroes.length);
        this.hero = allHeroes[index];

        // Set initial health and speed from selected hero
        this.Health = hero.getHp();
        this.speed = hero.getSpeed();

        // Set default avatar path from the selected hero
        this.avatarPath = hero.getHeroPath();
        this.playerTexture = new Texture(Gdx.files.absolute(avatarPath));
        this.playerSprite = new Sprite(playerTexture);
        this.playerSprite.setSize(playerTexture.getWidth() * 4, playerTexture.getHeight() * 4);

        // Position player in the center of the screen
        playerSprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);

        // Initialize collision rect
        rect = new CollisionRect(playerSprite.getX(), playerSprite.getY(), playerSprite.getWidth(), playerSprite.getHeight());

    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
        this.playerTexture = new Texture(Gdx.files.absolute(avatarPath)); // Note: absolute!
        this.playerSprite = new Sprite(this.playerTexture);
        this.playerSprite.setSize(playerTexture.getWidth() * 4, playerTexture.getHeight() * 4);
    }

    public Weapons getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(Weapons weapons) {
        this.weaponType = weapons;
    }



    public boolean getAutoReload() {
        return autoReload;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }

    public boolean isDarkTheme() {
        return darkTheme;
    }

    public void setDarkTheme(boolean darkTheme) {
        this.darkTheme = darkTheme;
    }

    public boolean isSfx() {
        return sfx;
    }

    public void setSfx(boolean sfx) {
        this.sfx = sfx;
    }

    public float getSpeed() {
        return speed;
    }

    public int getKills() {
        return kills;
    }

    public void increaseKill(){
        kills++;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }


    public float getPosX() {
        return playerSprite.getX();
    }
    public float getPosY() {
        return playerSprite.getY();
    }
    public void setPosX(float posX) {
        playerSprite.setX(posX);
    }
    public void setPosY(float posY) {
        playerSprite.setY(posY);
    }


    public float getCenterX() {
        return playerSprite.getX() + playerSprite.getWidth() / 2f;
    }

    public float getCenterY() {
        return playerSprite.getY() + playerSprite.getHeight() / 2f;
    }


    public float getHealth() {
        return Health;
    }

    public void setHealth(float health) {
        this.Health = health;
    }
    public void increaseHealth(){
        Health++;
    }

    public void decreaseHealth(){
        Health--;
    }
    public CollisionRect getRect() {
        return rect;
    }


    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }


    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getTotalGameTime() {
        return totalGameTime;
    }

    public void setTotalGameTime(float totalGameTime) {
        this.totalGameTime = totalGameTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int amount){
        score += amount;
    }

    public float getLastHitTime() { return lastHitTime; }
    public void setLastHitTime(float time) { this.lastHitTime = time; }

    public float getHitCooldown() { return hitCooldown; }

    public int getLevel() {
        return level;
    }

    public Heros getHero() {
        return hero;
    }

    public void setHero(Heros hero) {
        this.hero = hero;
        this.speed = hero.getSpeed();
        this.Health = hero.getHp();
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }



    public void increaseXp(int amount) {
        int oldLevel = level;
        xp += amount;
        updateLevel();

        if (level > oldLevel) {
            onLevelUp();  // Trigger ability selection or reward
        }
    }
    private void onLevelUp() {
        if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
            GameAssetManager.getGameAssetManager().getPowerUpSound().play();
        }
        List<Ability> options = getRandomAbilities(3);
        // This will pause the game and let the player choose

        LevelUpController levelUpController = new LevelUpController(App.getGameView()); // use the existing game view
        Skin skin = GameAssetManager.getGameAssetManager().getSkin();

        Main.getMain().setScreen(new LevelUpView(levelUpController, skin));
    }

    public Set<Ability> getAcquiredAbilities() {
        return acquiredAbilities;
    }


    private void updateLevel() {
        if (xp >= 3 * 25) {
            level = 5;
        } else if (xp >= 3 * 9) {
            level = 4;
        } else if (xp >= 3 * 8) {
            level = 3;
        } else if (xp >= 3 * 3) {
            level = 2;
        } else {
            level = 1;
        }
    }

    public int getXpToNextLevel() {
        if (xp < 9) return 9;
        if (xp < 24) return 24;
        if (xp < 45) return 45;
        if (xp < 75) return 75;
        return 100; // Max or dummy value
    }

    public List<Ability> getRandomAbilities(int count) {
        List<Ability> all = new ArrayList<>(Arrays.asList(Ability.values()));
        all.removeAll(acquiredAbilities); // optionally prevent repeats
        Collections.shuffle(all);
        return all.subList(0, Math.min(count, all.size()));
    }




    public boolean isSpeedyActive() {
        return speedyActive;
    }

    public void decreaseSpeedyTimer(float delta) {
        if (!speedyActive) return;
        speedyTimer -= delta;
        if (speedyTimer <= 0f) {
            speedyActive = false;
            speed /= 2; // revert speed
            speedyTimer = 0f;
        }
    }


    public boolean isDamagerActive() {
        return damagerActive;
    }

    public void decreaseDamagerTimer(float delta) {
        if (!damagerActive) return;
        damagerTimer -= delta;
        if (damagerTimer <= 0f) {
            damagerActive = false;
            damagerTimer = 0f;
        }
    }



    public void setSpeedyActive(boolean speedyActive) {
        this.speedyActive = speedyActive;
    }

    public void setDamagerActive(boolean damagerActive) {
        this.damagerActive = damagerActive;
    }

    public void setDamagerTimer(float damagerTimer) {
        this.damagerTimer = damagerTimer;
    }

    public void setSpeedyTimer(float speedyTimer) {
        this.speedyTimer = speedyTimer;
    }

    public boolean isTakingDamage() {
        return takingDamage;
    }

    public void setTakingDamage(boolean takingDamage) {
        this.takingDamage = takingDamage;
        if (takingDamage) this.damageTime = 0f;
    }

    public float getDamageTime() {
        return damageTime;
    }

    public void increaseDamageTime(float delta) {
        this.damageTime += delta;
        if (damageTime >= DAMAGE_DURATION) {
            takingDamage = false;
            damageTime = 0f;
        }
    }


}
