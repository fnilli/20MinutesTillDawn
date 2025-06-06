package com.tilldawn.Control.monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.badlogic.gdx.graphics.Texture;
import com.tilldawn.Model.monster.Seed;
import com.tilldawn.Model.monster.ShrinkingShield;
import com.tilldawn.Model.monster.WingedMonster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WingedMonsterController {
    private final List<WingedMonster> monsters = new ArrayList<>();
    private final Animation<Texture> animation;
    private List<Seed> seeds = new ArrayList<>();
    private ShrinkingShield shield = null;
    private boolean shieldActive = false;

    public WingedMonsterController(Animation<Texture> animation) {
        this.animation = animation;
    }

    public void spawnMonster(float x, float y) {
        monsters.add(new WingedMonster(x, y, animation));

        if (!shieldActive) {
            float screenSize = Math.max(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            Texture shieldTexture = GameAssetManager.getGameAssetManager().getShieldTexture();

            float centerX = Gdx.graphics.getWidth() / 2f;
            float centerY = Gdx.graphics.getHeight() / 2f;

            shield = new ShrinkingShield(centerX, centerY, screenSize,  shieldTexture);
            shieldActive = true;
        }
    }



public void uppdate(Player player, float delta) {
    float currentTime = System.nanoTime() / 1_000_000_000f;


    Iterator<WingedMonster> it = monsters.iterator();
    while (it.hasNext()) {
        WingedMonster m = it.next();
        boolean shouldRemove = false;

        m.update(delta, currentTime, player.getPosX(), player.getPosY());

        if (!m.isDead()) {
            // 1. Update animation state time
            m.setStateTime(m.getStateTime() + Gdx.graphics.getDeltaTime());

            // 2. Get current animation frame
            Texture currentFrame = m.getAnimation().getKeyFrame(m.getStateTime(), true);

            // 3. Draw the monster
            float scale = 2.0f;
            float width = currentFrame.getWidth() * scale;
            float height = currentFrame.getHeight() * scale;
            Main.getBatch().draw(currentFrame, m.getX(), m.getY(), width, height);

            // 4. Check collision with player
            if (m.getRect().collidesWith(player.getRect())) {
                if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                    GameAssetManager.getGameAssetManager().getDamageSound().play();
                }
                if (currentTime - player.getLastHitTime() > player.getHitCooldown()) {
                    player.decreaseHealth();
                    player.setLastHitTime(currentTime);
                    player.setTakingDamage(true);
                    System.out.println("Player hit MONSTER! Health: " + player.getHealth());
                }
            }
        }

        // Death animation and cleanup
        if (m.isDying()) {
            Texture frame = m.getDeathAnimation().getKeyFrame(m.getStateTime(), false);
            Main.getBatch().draw(frame, m.getX(), m.getY());
        }

        // Handle death animation finished
        if (m.isDeathAnimationFinished()) {
            if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                GameAssetManager.getGameAssetManager().getMonsterDeathSound().play();
            }
            if (!m.getSeedDropped()) {
                seeds.add(new Seed(m.getX(), m.getY()));
                m.setSeedDropped(true);
            }
            App.getCurrentPlayer().increaseKill();
            shouldRemove = true;
        }

        if (shouldRemove) {
            it.remove();
        }

    }


    //  Seed rendering & player collision check
    for (Seed seed : seeds) {
        if (!seed.isCollected()) {
            Main.getBatch().draw(seed.getSprite(), seed.getSprite().getX(), seed.getSprite().getY());

            if (seed.collidesWithPlayer(player.getRect())) {
                if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                    GameAssetManager.getGameAssetManager().getGetCoinSound().play();
                }
                seed.collect();
                player.increaseXp(3);
                System.out.println("Seed collected! Player health: " + player.getHealth());
            }
        }
    }

}


    public void update(Player player, float delta) {
        float currentTime = System.nanoTime() / 1_000_000_000f;

        Iterator<WingedMonster> it = monsters.iterator();
        while (it.hasNext()) {
            WingedMonster m = it.next();
            boolean shouldRemove = false;

            m.update(delta, currentTime, player.getPosX(), player.getPosY());

            if (!m.isDead()) {
                // Animate monster
                m.setStateTime(m.getStateTime() + Gdx.graphics.getDeltaTime());
                Texture currentFrame = m.getAnimation().getKeyFrame(m.getStateTime(), true);
                float scale = 2.0f;
                float width = currentFrame.getWidth() * scale;
                float height = currentFrame.getHeight() * scale;
                Main.getBatch().draw(currentFrame, m.getX(), m.getY(), width, height);

                // Player collision
                if (m.getRect().collidesWith(player.getRect())) {
                    if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                        GameAssetManager.getGameAssetManager().getDamageSound().play();
                    }
                    if (currentTime - player.getLastHitTime() > player.getHitCooldown()) {
                        player.decreaseHealth();
                        player.setLastHitTime(currentTime);
                        player.setTakingDamage(true);
                        System.out.println("Player hit MONSTER! Health: " + player.getHealth());
                    }
                }
            }

            // Draw death animation
            if (m.isDying()) {
                Texture frame = m.getDeathAnimation().getKeyFrame(m.getStateTime(), false);
                Main.getBatch().draw(frame, m.getX(), m.getY());
            }

            // Handle death cleanup
            if (m.isDeathAnimationFinished()) {
                if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                    GameAssetManager.getGameAssetManager().getMonsterDeathSound().play();
                }
                if (!m.getSeedDropped()) {
                    seeds.add(new Seed(m.getX(), m.getY()));
                    m.setSeedDropped(true);
                }
                App.getCurrentPlayer().increaseKill();
                shouldRemove = true;
            }

            if (shouldRemove) {
                it.remove();
            }
        }

        // Remove shield if all monsters are gone
        if (monsters.isEmpty()) {
            shield = null;
            shieldActive = false;
        }

        // Shrinking shield logic
        if (shieldActive && shield != null && shield.isActive()) {
            shield.update(delta);
            shield.render(Main.getBatch(), player.getCenterX(), player.getCenterY());

            float dx = player.getCenterX() - shield.getCenterX();
            float dy = player.getCenterY() - shield.getCenterY();
            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            if (distance > shield.getCurrentRadius()) {
                if (!player.isTakingDamage()) {
                    player.decreaseHealth();
                    player.setTakingDamage(true);
                }
            }

        }

        // Render and check for seed pickups
        for (Seed seed : seeds) {
            if (!seed.isCollected()) {
                Main.getBatch().draw(seed.getSprite(), seed.getSprite().getX(), seed.getSprite().getY());

                if (seed.collidesWithPlayer(player.getRect())) {
                    if (App.getCurrentPlayer() == null || App.getCurrentPlayer().isSfx()) {
                        GameAssetManager.getGameAssetManager().getGetCoinSound().play();
                    }
                    seed.collect();
                    player.increaseXp(3);
                    System.out.println("Seed collected! Player XP: " + player.getXp());
                }
            }
        }
    }

    public List<Seed> getSeeds() {
        return seeds;
    }


    public List<WingedMonster> getMonsters() {
        return monsters;
    }

    public Animation<Texture> getAnimation() {
        return animation;
    }
}

