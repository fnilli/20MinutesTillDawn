package com.tilldawn.Model.monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Model.CollisionRect;

public class Seed {
    private Sprite sprite;
    private boolean collected = false;
    private CollisionRect rect;

    public Seed(float x, float y) {
        Texture texture = new Texture(Gdx.files.internal("Images/Sprite/EyeMonster/EyeMonster_Em.png"));
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
        sprite.setSize(64, 64); // adjust size

        this.rect = new CollisionRect(x, y, sprite.getWidth(), sprite.getHeight());
    }


    public Sprite getSprite() {
        return sprite;
    }

    public boolean isCollected() {
        return collected;
    }

    public void collect() {
        collected = true;
    }

    public boolean collidesWithPlayer(CollisionRect playerRect) {
        return !collected && playerRect.collidesWith(new CollisionRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight()));
    }
}
