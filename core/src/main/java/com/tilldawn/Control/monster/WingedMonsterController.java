package com.tilldawn.Control.monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.badlogic.gdx.graphics.Texture;
import com.tilldawn.Model.monster.WingedMonster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WingedMonsterController {
    private final List<WingedMonster> monsters = new ArrayList<>();
    private final Animation<Texture> animation;
    private List<Seed> seeds = new ArrayList<>();

    public WingedMonsterController(Animation<Texture> animation) {
        this.animation = animation;
    }

    public void spawnMonster(float x, float y) {
        monsters.add(new WingedMonster(x, y, animation));
    }

//    public void update(Player player, float delta) {
//        float currentTime = System.nanoTime() / 1_000_000_000f;
//
//        Iterator<WingedMonster> it = monsters.iterator();
//        while (it.hasNext()) {
//            WingedMonster m = it.next();
//
//            m.update(delta, currentTime, player.getPosX(), player.getPosY());
//
//
//            if (!m.isDead()) {
////                m.moveTowards(player.getPosX(), player.getPosY());
//
//                // 1. Update animation state time
//                m.setStateTime(m.getStateTime() + Gdx.graphics.getDeltaTime());
//
//                // 2. Get current animation frame
//                Texture currentFrame = m.getAnimation().getKeyFrame(m.getStateTime(), true);
//
//                // 3. Draw the monster
//                float scale = 2.0f;
//                float width = currentFrame.getWidth() * scale;
//                float height = currentFrame.getHeight() * scale;
//                Main.getBatch().draw(currentFrame, m.getX(), m.getY(), width, height);
//
//                // 4. Check collision with player
//                if (m.getRect().collidesWith(player.getRect())) {
//                    if (currentTime - player.getLastHitTime() > player.getHitCooldown()) {
////                        player.setHealth(Math.max(0, player.getHealth() - 1));
////                        player.setHealth(player.getHealth() - 1);
//                        player.decreaseHealth();
//                        player.setLastHitTime(currentTime);
//                        System.out.println("Player hit MONSTER! Health: " + player.getHealth());
//                    }
//                }
//
//
//
//            }
//
//            if (m.isDeathAnimationFinished()) {
//                if (!m.getSeedDropped()) {
//                    seeds.add(new Seed(m.getX(), m.getY()));
//                    m.setSeedDropped(true);
//                }
//                seeds.add(new Seed(m.getX(), m.getY()));
//
//                App.getCurrentPlayer().increaseKill();
//                it.remove(); // finally remove monster
//            }
//
//
//            // Death animation and cleanup
//            if (m.isDying()) {
//                Texture frame = m.getDeathAnimation().getKeyFrame(m.getStateTime(), false);
//                Main.getBatch().draw(frame, m.getX(), m.getY());
//            }
//
//            if (m.isDeathAnimationFinished()) {
//                if (!m.getSeedDropped()) {
//                    seeds.add(new Seed(m.getX(), m.getY()));
//                    m.setSeedDropped(true);
//                }
//                App.getCurrentPlayer().increaseKill();
////                it.remove(); // remove monster
//            }
//
//
//
//        }
//    }

public void update(Player player, float delta) {
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
                if (currentTime - player.getLastHitTime() > player.getHitCooldown()) {
                    player.decreaseHealth();
                    player.setLastHitTime(currentTime);
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
            if (!m.getSeedDropped()) {
                seeds.add(new Seed(m.getX(), m.getY()));
                m.setSeedDropped(true);
            }
            App.getCurrentPlayer().increaseKill();
            shouldRemove = true;
        }

        if (shouldRemove) {
            it.remove(); // ✅ safe removal
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


    public List<WingedMonster> getMonsters() {
        return monsters;
    }

    public Animation<Texture> getAnimation() {
        return animation;
    }
}

