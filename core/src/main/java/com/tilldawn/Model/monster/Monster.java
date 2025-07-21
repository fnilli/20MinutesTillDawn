package com.tilldawn.Model.monster;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.GameAssetManager;

public class Monster {
    private float x, y;
    private int health = 25;
    private Animation<Texture> animation;
    private float stateTime = 0f;
    private CollisionRect rect;
    private float speed = 1f;


    private boolean dying = false;
    private float deathTime = 0f;
    private static final float DEATH_ANIMATION_DURATION = 0.5f; // adjust as needed
    private Animation<Texture> deathAnimation;
    private boolean seedDropped = false;


    public Monster(float x, float y, Animation<Texture> animation) {
        this.x = x;
        this.y = y;
        this.animation = animation;



        Texture firstFrame = animation.getKeyFrame(0);
        float scale = 2.0f;
        float width = firstFrame.getWidth() * scale;
        float height = firstFrame.getHeight() * scale;

        this.rect = new CollisionRect(x, y, width, height);
        this.deathAnimation = GameAssetManager.getGameAssetManager().getMonsterDeathAnimation();


    }

    public void update(float deltaTime) {
        stateTime += deltaTime;
        if (dying) {
            deathTime += deltaTime;
        }
    }


    public Texture getCurrentFrame() {
        return animation.getKeyFrame(stateTime, true); // true for looping
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public void moveTowards(float targetX, float targetY) {
        float dx = targetX - x;
        float dy = targetY - y;
        float length = (float) Math.sqrt(dx * dx + dy * dy);
        if (length > 0) {
            x += speed * dx / length;
            y += speed * dy / length;
        }
        rect.move(x, y);
    }


    public void takeDamage(float dmg) {
        if (!dying) {
            health -= dmg;
            if (health <= 0) {
                dying = true;
                stateTime = 0f; // reset for death animation
            }
        }
    }


    public boolean isDead() {
        return dying;
    }

    public boolean isDying() {
        return dying;
    }

    public boolean isDeathAnimationFinished() {
        return dying && deathTime >= DEATH_ANIMATION_DURATION;
    }

    public float getStateTime() {
        return stateTime;
    }

    public Animation<Texture> getAnimation() {
        return animation;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public boolean getSeedDropped() {
        return seedDropped;
    }

    public void setSeedDropped(boolean seedDropped) {
        this.seedDropped = seedDropped;
    }


    public int getHealth() {
        return health;
    }

    public float getDeathTime() {
        return deathTime;
    }

    public Animation<Texture> getDeathAnimation() {
        return deathAnimation;
    }

    public boolean isSeedDropped() {
        return seedDropped;
    }
}
