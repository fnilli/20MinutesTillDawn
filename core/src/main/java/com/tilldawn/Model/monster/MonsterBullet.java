package com.tilldawn.Model.monster;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.GameAssetManager;

public class MonsterBullet {
    private Texture texture = new Texture(GameAssetManager.getGameAssetManager().getMonsterBullet());
    private Sprite sprite = new Sprite(texture);
    private int damage = 1;
    private Vector2 direction;
    private CollisionRect rect;

    public MonsterBullet(float startX, float startY, Vector2 direction) {
        this.direction = direction.nor(); // Normalize for consistent speed

        sprite.setSize(20, 20);
        sprite.setPosition(startX, startY);

        rect = new CollisionRect(startX, startY, sprite.getWidth(), sprite.getHeight());
    }

    public void updatePosition() {
        float newX = sprite.getX() + direction.x * 6f; // Slower than player bullet
        float newY = sprite.getY() + direction.y * 6f;
        sprite.setPosition(newX, newY);
        rect.move(newX, newY);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getDamage() {
        return damage;
    }

    public CollisionRect getRect() {
        return rect;
    }
}
