package com.tilldawn.Control.monster;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;
import com.tilldawn.Main;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.monster.Tree;

import java.util.ArrayList;

public class TreeController {
    private ArrayList<Tree> trees;
    private final Animation<Texture> animation;

    public TreeController(Animation<Texture> animation) {
        this.animation = animation;

        this.trees = new ArrayList<>();

        float mapWidth = 3776f;
        float mapHeight = 2688f;
        float treeWidth = 128f;  // Adjust based on your tree sprite size
        float treeHeight = 128f;

        //draw 40 trees
        for (int i = 0; i < 40; i++) {
            float x = MathUtils.random(0, mapWidth - treeWidth);
            float y = MathUtils.random(0, mapHeight - treeHeight);
            trees.add(new Tree(x, y, animation));
        }

    }

//    public void update(float delta) {
//        Player player = App.getCurrentPlayer();
//        float currentTime = System.nanoTime() / 1_000_000_000f;
//
//        for (Tree tree : trees) {
//
//            // 1. Update animation state time
//            tree.setStateTime(tree.getStateTime() + Gdx.graphics.getDeltaTime());
//
//            // 2. Get current animation frame
//            Texture currentFrame = tree.getAnimation().getKeyFrame(tree.getStateTime(), true);
//
//            // 3. Draw the tree
//            float scale = 2.0f;
//            float width = currentFrame.getWidth() * scale;
//            float height = currentFrame.getHeight() * scale;
//            Main.getBatch().draw(currentFrame, tree.getX(), tree.getY(), width, height);
//
//            // 4. Check collision with player
//            if (tree.getRect().collidesWith(player.getRect())) {
//                if (currentTime - player.getLastHitTime() > player.getHitCooldown()) {
//                    //decrease player heath
//                    player.setHealth(Math.max(0, player.getHealth() - 1));
//
//                    player.setLastHitTime(currentTime);
//                    System.out.println("Player hit! Health: " + player.getHealth());
//                }
//            }else {
//                Texture frame = tree.getCurrentFrame();
//                Main.getBatch().draw(frame, tree.getX(), tree.getY(), width, height);
//            }
//
//
//
//
//            tree.update(delta);
//            tree.draw(Main.getBatch());
//        }
//    }

    public void update(Player player, float delta) {
        float currentTime = System.nanoTime() / 1_000_000_000f;

        for (Tree tree : trees) {
            tree.update(delta); // Update animation time

            if (tree.getRect().collidesWith(player.getRect())) {
                if (currentTime - player.getLastHitTime() > player.getHitCooldown()) {
//                    player.setHealth(Math.max(0, player.getHealth() - 1));
                    player.setHealth(player.getHealth() - 1);
                    player.setLastHitTime(currentTime);
                    System.out.println("Player hit TREE! Health: " + player.getHealth());
                }
            }
            Texture currentFrame = tree.getAnimation().getKeyFrame(tree.getStateTime(), true);

            float scale = 2.0f;
            float width = currentFrame.getWidth() * scale;
            float height = currentFrame.getHeight() * scale;
            Main.getBatch().draw(currentFrame, tree.getX(), tree.getY(), width, height);
        }
    }



    public ArrayList<Tree> getTrees() {
        return trees;
    }

}
