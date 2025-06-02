package com.tilldawn.Model.monster;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tilldawn.Model.CollisionRect;

public class Tree {
    private float x, y;
    private Animation<Texture> animation;
    private float stateTime = 0f;
    private CollisionRect rect;

    public Tree(float x, float y, Animation<Texture> animation) {
        this.x = x;
        this.y = y;
        this.animation = animation;

        Texture firstFrame = animation.getKeyFrame(0);
        float scale = 2.0f;
        float width = firstFrame.getWidth() * scale;
        float height = firstFrame.getHeight() * scale;
        this.rect = new CollisionRect(x, y, width, height);

    }

    public Texture getCurrentFrame() {
        return animation.getKeyFrame(stateTime, true); // true for looping
    }


    public void update(float delta) {
        stateTime += delta;
    }

    public void draw(SpriteBatch batch) {
        Texture currentFrame = animation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, x, y);
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public Animation<Texture> getAnimation() {
        return animation;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public CollisionRect getRect() {
        return rect;
    }
}
