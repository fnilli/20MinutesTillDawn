package com.tilldawn.Model.monster;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShrinkingShield {
    private float duration = 20f;             // Total duration of shield effect
    private float elapsedTime = 0f;     // How much time has passed
    private float initialRadius;        // Starting radius (e.g., screen size)
    private float minRadius = 100f;            // Minimum radius before disappearing
    private Texture texture;            // Shield texture
    private boolean active = true;
    private float centerX, centerY;

    public ShrinkingShield(float centerX, float centerY, float initialRadius,  Texture texture) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.initialRadius = initialRadius;
        this.texture = texture;
    }

    public void update(float delta) {
        if (!active) return;

        elapsedTime += delta;
        if (elapsedTime >= duration) {
            active = false;
        }
    }

    public void render(SpriteBatch batch, float playerX, float playerY) {
        if (!active) return;

        float progress = elapsedTime / duration;
        float currentRadius = Math.max(minRadius, initialRadius * (1 - progress));

        batch.draw(texture,
            centerX - currentRadius,
            centerY - currentRadius,
            currentRadius * 2,
            currentRadius * 2
        );

    }

    public boolean isActive() {
        return active;
    }

    public float getCurrentRadius() {
        float progress = elapsedTime / duration;
        return Math.max(minRadius, initialRadius * (1 - progress));
    }

    public float getCenterX() { return centerX; }
    public float getCenterY() { return centerY; }

}
