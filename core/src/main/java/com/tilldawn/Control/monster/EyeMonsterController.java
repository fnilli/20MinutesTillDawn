package com.tilldawn.Control.monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.badlogic.gdx.graphics.Texture;
import com.tilldawn.Model.monster.EyeMonster;
import com.tilldawn.Model.monster.MonsterBullet;

import java.util.*;

public class EyeMonsterController {
    private final List<EyeMonster> monsters = new ArrayList<>();
    private final Animation<Texture> animation;
    private List<Seed> seeds = new ArrayList<>();
    private final List<MonsterBullet> monsterBullets = new ArrayList<>();
    private final Map<EyeMonster, Float> lastShotTimes = new HashMap<>();

    public EyeMonsterController(Animation<Texture> animation) {
        this.animation = animation;
    }

    public void spawnMonster(float x, float y) {
        EyeMonster monster = new EyeMonster(x, y, animation);
        monsters.add(monster);
        lastShotTimes.put(monster, 0f); // last shot = 0
    }


public void update(Player player, float delta) {
    float currentTime = System.nanoTime() / 1_000_000_000f;

    Iterator<EyeMonster> it = monsters.iterator();
    while (it.hasNext()) {
        EyeMonster m = it.next();
        boolean shouldRemove = false;

        m.update(delta);

        if (!m.isDead()) {
            m.moveTowards(player.getPosX(), player.getPosY());

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
                if (currentTime - player.getLastHitTime() > player.getHitCooldown()) {
                    player.setHealth(player.getHealth() - 1);
                    player.setLastHitTime(currentTime);
                    player.setTakingDamage(true);
                    System.out.println("Player hit MONSTER! Health: " + player.getHealth());
                }
            }

            // 5. Shooting logic every 3 seconds
            float lastShot = lastShotTimes.getOrDefault(m, 0f);
            if (currentTime - lastShot >= 3f) {
                float startX = m.getX() + m.getRect().getWidth() / 2f;
                float startY = m.getY() + m.getRect().getHeight() / 2f;
                float targetX = player.getPosX();
                float targetY = player.getPosY();
                Vector2 direction = new Vector2(targetX - startX, targetY - startY);

                monsterBullets.add(new MonsterBullet(startX, startY, direction));
                lastShotTimes.put(m, currentTime);
            }
        }

        // Death animation and cleanup
        if (m.isDying()) {
            Texture frame = m.getDeathAnimation().getKeyFrame(m.getStateTime(), false);
            Main.getBatch().draw(frame, m.getX(), m.getY());
        }

        if (m.isDeathAnimationFinished()) {
            if (!m.getSeedDropped()) {
                seeds.add(new Seed(m.getX(), m.getY()));
                m.setSeedDropped(true);
            }
            App.getCurrentPlayer().increaseKill();
            lastShotTimes.remove(m);
            shouldRemove = true;
        }

        if (shouldRemove) {
            it.remove();
        }
    }

    // Bullet update + drawing
    Iterator<MonsterBullet> bulletIterator = monsterBullets.iterator();
    while (bulletIterator.hasNext()) {
        MonsterBullet bullet = bulletIterator.next();
        bullet.updatePosition();
        Sprite sprite = bullet.getSprite();
        Main.getBatch().draw(sprite, sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());

        // Bullet collision with player
        if (bullet.getRect().collidesWith(player.getRect())) {
            player.setHealth(player.getHealth() - bullet.getDamage());
            bulletIterator.remove();
            continue;
        }

        // Remove off-screen bullets
        if (sprite.getX() < 0 || sprite.getX() > Gdx.graphics.getWidth()
            || sprite.getY() < 0 || sprite.getY() > Gdx.graphics.getHeight()) {
            bulletIterator.remove();
        }
    }

    //  Seed rendering & player collision check
    for (Seed seed : seeds) {
        if (!seed.isCollected()) {
            Main.getBatch().draw(seed.getSprite(), seed.getSprite().getX(), seed.getSprite().getY());

            if (seed.collidesWithPlayer(player.getRect())) {
                seed.collect();
                player.increaseXp(3);
                System.out.println("Seed collected! Player health: " + player.getHealth());
            }
        }
    }
}


    public List<Seed> getSeeds() {
        return seeds;
    }


    public List<EyeMonster> getMonsters() {
        return monsters;
    }

    public List<MonsterBullet> getMonsterBullets() {
        return monsterBullets;
    }

    public Animation<Texture> getAnimation() {
        return animation;
    }
}

