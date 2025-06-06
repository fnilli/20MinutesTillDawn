package com.tilldawn.Control.monster;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.badlogic.gdx.graphics.Texture;
import com.tilldawn.Model.monster.TreeMonster;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreeMonsterController {
    private final List<TreeMonster> trees = new ArrayList<>();
    private final Animation<Texture> animation;

    public TreeMonsterController(Animation<Texture> animation) {
        this.animation = animation;
    }

    public void spawnMonster(float x, float y) {
        trees.add(new TreeMonster(x, y, animation));
    }

    public void update(Player player, float delta) {
        float currentTime = System.nanoTime() / 1_000_000_000f;

        Iterator<TreeMonster> it = trees.iterator();
        while (it.hasNext()) {
            TreeMonster m = it.next();
            m.update(delta);

                // 1. Update animation state time
                m.setStateTime(m.getStateTime() + Gdx.graphics.getDeltaTime());

                // 2. Get current animation frame
                Texture currentFrame = m.getAnimation().getKeyFrame(m.getStateTime(), true);

                // 3. Draw the TreeMonster
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
//                      player.setHealth(Math.max(0, player.getHealth() - 1));
                        player.setHealth(player.getHealth() - 1);
                        player.setLastHitTime(currentTime);
                        player.setTakingDamage(true);
                        System.out.println("Player hit TreeMonster! Health: " + player.getHealth());
                    }
                }

            Texture frame = m.getCurrentFrame();
            Main.getBatch().draw(frame, m.getX(), m.getY(), width, height);

        }
    }



    public List<TreeMonster> getTrees() {
        return trees;
    }

    public Animation<Texture> getAnimation() {
        return animation;
    }
}

