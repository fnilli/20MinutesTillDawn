package com.tilldawn.Model.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.GameAssetManager;

public class Bullet {
    private Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private Sprite sprite = new Sprite(texture);
//    private int damage = 5;
    private Vector2 direction;
    private CollisionRect rect;


    public Bullet(float startX, float startY, Vector2 direction){
        this.direction = direction.nor(); // Normalize to ensure consistent speed

        sprite.setSize(30, 30);
        sprite.setPosition(startX, startY);

        rect = new CollisionRect(startX, startY, sprite.getWidth(), sprite.getHeight());
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2 getDirection() {
        return direction;
    }

//    public int getDamage() {
//        return damage;
//    }

    public CollisionRect getRect() {
        return rect;
    }

    public void updatePosition() {
        float newX = sprite.getX() + direction.x * 10;
        float newY = sprite.getY() + direction.y * 10;
        sprite.setPosition(newX, newY);
        rect.move(newX, newY);
    }
}
