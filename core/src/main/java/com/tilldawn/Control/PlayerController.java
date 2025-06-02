package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerController {
    private Player player;
    private final Map<String, Integer> keyBindings = new HashMap<>();
    private Texture lightTexture = new Texture(Gdx.files.internal("assets/hallow3.png"));

    public PlayerController(Player player){
        this.player = player;
        keyBindings.put("up", Input.Keys.W);
        keyBindings.put("down", Input.Keys.S);
        keyBindings.put("left", Input.Keys.A);
        keyBindings.put("right", Input.Keys.D);
    }


    public void update(float delta){

        if (player.isSpeedyActive()) {
            player.decreaseSpeedyTimer(delta);
        }


        if (player.isPlayerIdle()) {
            idleAnimation();
        }


        handlePlayerInput();

        player.getPlayerSprite().setX(player.getPosX());
        player.getPlayerSprite().setY(player.getPosY());



        // Center the light texture on the player
        float lightSize = 500f; // or adjust as needed
        float lightX = player.getPosX() + player.getPlayerSprite().getWidth() / 2 - lightSize / 2;
        float lightY = player.getPosY() + player.getPlayerSprite().getHeight() / 2 - lightSize / 2;

        Main.getBatch().setColor(1, 1, 1, 0.4f); // optional: control light transparency
        Main.getBatch().draw(lightTexture, lightX, lightY, lightSize, lightSize);
        Main.getBatch().setColor(1, 1, 1, 1); // reset color to default

        // Now draw the sprite at the correct world position
        player.getPlayerSprite().draw(Main.getBatch());
    }



public void handlePlayerInput() {
    float newX = player.getPosX();
    float newY = player.getPosY();
    boolean isMoving = false;


    if (Gdx.input.isKeyPressed(keyBindings.get("up"))) {
        newY += player.getSpeed();
        isMoving = true;
    }
    if (Gdx.input.isKeyPressed(keyBindings.get("down"))) {
        newY -= player.getSpeed();
        isMoving = true;
    }
    if (Gdx.input.isKeyPressed(keyBindings.get("left"))) {
        newX -= player.getSpeed();
        isMoving = true;
        player.getPlayerSprite().setFlip(true, false);
    }
    if (Gdx.input.isKeyPressed(keyBindings.get("right"))) {
        newX += player.getSpeed();
        isMoving = true;
        player.getPlayerSprite().setFlip(false, false);
    }


//    if (isMoving) {
//        player.setCurrentAnimation(player.getRunAnimation());
//    } else {
//        player.setCurrentAnimation(player.getIdleAnimation());
//    }

    // Update player's logical position
    player.setPosX(newX);
    player.setPosY(newY);

    // Update player's visual sprite position
    player.getPlayerSprite().setX(newX);
    player.getPlayerSprite().setY(newY);


    player.getRect().move(newX, newY);
}


    public void idleAnimation(){
        Animation<Texture> animation = player.getHero().getWalkAnimation();
//        System.out.println(player.getHero().toString());

        player.getPlayerSprite().setRegion(animation.getKeyFrame(player.getTime()));

        if (!animation.isAnimationFinished(player.getTime())) {
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            player.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }



    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getMoveKey(String direction) {
        int key = keyBindings.getOrDefault(direction, -1);
        return key == Input.Keys.W || key == Input.Keys.S || key == Input.Keys.A || key == Input.Keys.D
            ? Input.Keys.toString(key)
            : getArrowKeyName(key); // handle arrow keys
    }

    private String getArrowKeyName(int key) {
        switch (key) {
            case Input.Keys.UP: return "Up Arrow";
            case Input.Keys.DOWN: return "Down Arrow";
            case Input.Keys.LEFT: return "Left Arrow";
            case Input.Keys.RIGHT: return "Right Arrow";
            default: return "Unknown";
        }
    }

    public void setMoveKey(String direction, String keyName) {
        switch (keyName) {
            case "W": keyBindings.put(direction, Input.Keys.W); break;
            case "S": keyBindings.put(direction, Input.Keys.S); break;
            case "A": keyBindings.put(direction, Input.Keys.A); break;
            case "D": keyBindings.put(direction, Input.Keys.D); break;
            case "Up Arrow": keyBindings.put(direction, Input.Keys.UP); break;
            case "Down Arrow": keyBindings.put(direction, Input.Keys.DOWN); break;
            case "Left Arrow": keyBindings.put(direction, Input.Keys.LEFT); break;
            case "Right Arrow": keyBindings.put(direction, Input.Keys.RIGHT); break;
        }
    }

    public void loadKeyBindingsFromPreferences() {
        Preferences prefs = Gdx.app.getPreferences("PlayerSettings");

        setMoveKey("up", prefs.getString("moveUp", "W"));
        setMoveKey("down", prefs.getString("moveDown", "S"));
        setMoveKey("left", prefs.getString("moveLeft", "A"));
        setMoveKey("right", prefs.getString("moveRight", "D"));
    }

}
