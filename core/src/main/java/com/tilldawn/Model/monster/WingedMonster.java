package com.tilldawn.Model.monster;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.GameAssetManager;

public class WingedMonster {
    private float x, y;
    private int health = 400 ;
    private Animation<Texture> animation;
    private float stateTime = 0f;
    private CollisionRect rect;
    private float speed = 1f;


    private boolean dying = false;
    private float deathTime = 0f;
    private static final float DEATH_ANIMATION_DURATION = 0.5f; // adjust as needed
    private Animation<Texture> deathAnimation;
    private boolean seedDropped = false;

//dash features
private float lastDashTime = 0f;
    private boolean isDashing = false;
    private float dashDuration = 0.5f; // how long the dash lasts
    private float dashSpeed = 5f;
    private float dashStartTime = 0f;




    public WingedMonster(float x, float y, Animation<Texture> animation) {
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

    public void update(float deltaTime, float currentTime, float playerX, float playerY) {
        stateTime += deltaTime;
        if (dying) {
            deathTime += deltaTime;
            return;
        }

        // Dash every 5 seconds
        if (!isDashing && currentTime - lastDashTime > 5f) {
            isDashing = true;
            dashStartTime = currentTime;
            lastDashTime = currentTime;
        }

        if (isDashing) {
            if (currentTime - dashStartTime < dashDuration) {
                moveTowards(playerX, playerY, dashSpeed); // fast movement
            } else {
                isDashing = false;
            }
        } else {
            moveTowards(playerX, playerY, speed); // normal movement
        }
    }



    public Texture getCurrentFrame() {
        return animation.getKeyFrame(stateTime, true); // true for looping
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public void moveTowards(float targetX, float targetY, float customSpeed) {
        float dx = targetX - x;
        float dy = targetY - y;
        float length = (float) Math.sqrt(dx * dx + dy * dy);
        if (length > 0) {
            x += customSpeed * dx / length;
            y += customSpeed * dy / length;
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
