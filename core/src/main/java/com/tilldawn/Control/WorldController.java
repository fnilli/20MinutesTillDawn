package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.tilldawn.Control.monster.EyeMonsterController;
import com.tilldawn.Control.monster.MonsterController;
import com.tilldawn.Control.monster.TreeMonsterController;
import com.tilldawn.Control.monster.WingedMonsterController;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.monster.TreeMonster;

import java.util.List;

import static com.tilldawn.Control.GameController.timeRemaining;

public class WorldController {

    private final PlayerController playerController;
    private final TreeMonsterController treeMonsterController;
    private final MonsterController monsterController;
    private final EyeMonsterController eyeMonsterController;
    private final WingedMonsterController wingedMonsterController;
    private final Texture backgroundTexture;
    private float spawnTimer = 0;          // Time accumulator
    private float spawnInterval = 10f;     // Spawn interval in seconds (x)
    private boolean wingedSpawned = false;

    private float eyeSpawnTimer = 0f;
    private final float eyeSpawnInterval = 10f; // every 10 seconds


    public WorldController(PlayerController playerController) {
        this.backgroundTexture = new Texture("background.png");
        this.playerController = playerController;
        this.treeMonsterController = new TreeMonsterController(GameAssetManager.getGameAssetManager().getTreeAnimation());
        this.monsterController = new MonsterController(GameAssetManager.getGameAssetManager().getBrainMonsterAnimation());
        this.eyeMonsterController = new EyeMonsterController(GameAssetManager.getGameAssetManager().getEyeMonsterAnimation());
        this.wingedMonsterController = new WingedMonsterController(GameAssetManager.getGameAssetManager().getWingedMonsterAnimation());

        treeMonsterSpawner();
        monsterController.spawnMonster(200, 300);
        eyeMonsterController.spawnMonster(6000, 5000);
        monsterController.spawnMonster(700,1000);
//        eyeMonsterController.spawnMonster(1000, 500);

    }



    public void update(float delta) {
        float cameraX = playerController.getPlayer().getPosX() - Gdx.graphics.getWidth() / 2f;
        float cameraY = playerController.getPlayer().getPosY() - Gdx.graphics.getHeight() / 2f;
        Main.getBatch().draw(backgroundTexture, cameraX, cameraY);

        brainMonsterSpawner(delta);
        eyeMonsterSpawner(delta);
        wingedMonsterSpawner(delta);


//        treeController.update(playerController.getPlayer(), delta);
        treeMonsterController.update(playerController.getPlayer(), delta);
        monsterController.update(playerController.getPlayer(), delta);
        eyeMonsterController.update(playerController.getPlayer(), delta);
        wingedMonsterController.update(playerController.getPlayer(), delta);

    }

    public MonsterController getMonsterController() {
        return monsterController;
    }

    public EyeMonsterController getEyeMonsterController() {
        return eyeMonsterController;
    }

    public WingedMonsterController getWingedMonsterController() {
        return wingedMonsterController;
    }


    public TreeMonsterController getTreeMonsterController() {
        return treeMonsterController;
    }

    private void treeMonsterSpawner() {
        List<TreeMonster> trees = treeMonsterController.getTrees();
        float mapWidth = 3776f;
        float mapHeight = 2688f;
        float treeWidth = 128f;  // Adjust based on your tree sprite size
        float treeHeight = 128f;

        //draw 40 trees
        for (int i = 0; i < 40; i++) {
            float x = MathUtils.random(0, mapWidth - treeWidth);
            float y = MathUtils.random(0, mapHeight - treeHeight);
            trees.add(new TreeMonster(x, y, GameAssetManager.getGameAssetManager().getTreeAnimation()));
        }
    }

    private void brainMonsterSpawner(float delta) {
        spawnTimer += delta;

        if (spawnTimer >= spawnInterval) {
            spawnTimer = 0f;

            // Calculate timePassed
            float timePassed = App.getCurrentPlayer().getTotalGameTime() - timeRemaining;

            // n = timePassed / 30
            int numToSpawn = (int)(timePassed / 30f);

            for (int i = 0; i < numToSpawn; i++) {
                float x, y;
                int direction = MathUtils.random(0, 3);
                float margin = 200f;

                switch (direction) {
                    case 0: // top
                        x = playerController.getPlayer().getPosX() + MathUtils.random(-400, 400);
                        y = playerController.getPlayer().getPosY() + Gdx.graphics.getHeight() / 2f + margin;
                        break;
                    case 1: // right
                        x = playerController.getPlayer().getPosX() + Gdx.graphics.getWidth() / 2f + margin;
                        y = playerController.getPlayer().getPosY() + MathUtils.random(-300, 300);
                        break;
                    case 2: // bottom
                        x = playerController.getPlayer().getPosX() + MathUtils.random(-400, 400);
                        y = playerController.getPlayer().getPosY() - Gdx.graphics.getHeight() / 2f - margin;
                        break;
                    case 3: // left
                        x = playerController.getPlayer().getPosX() - Gdx.graphics.getWidth() / 2f - margin;
                        y = playerController.getPlayer().getPosY() + MathUtils.random(-300, 300);
                        break;
                    default:
                        x = y = 0;
                }

                monsterController.spawnMonster(x, y);
            }
        }

    }

    private void eyeMonsterSpawner(float delta) {
        eyeSpawnTimer += delta;

        float totalTime = App.getCurrentPlayer().getTotalGameTime();
        float timePassed = totalTime - timeRemaining;


        // Only start spawning after totalTime / 4 seconds
        if (timePassed >= totalTime / 4f) {
            if (eyeSpawnTimer >= eyeSpawnInterval) {
                eyeSpawnTimer = 0f;

                // Apply the given formula:
                int numToSpawn = (int)((4 * timePassed - totalTime + 30) / 30f);

                for (int i = 0; i < numToSpawn; i++) {
                    float x, y;
                    int direction = MathUtils.random(0, 3);
                    float margin = 200f;

                    switch (direction) {
                        case 0: // top
                            x = playerController.getPlayer().getPosX() + MathUtils.random(-400, 400);
                            y = playerController.getPlayer().getPosY() + Gdx.graphics.getHeight() / 2f + margin;
                            break;
                        case 1: // right
                            x = playerController.getPlayer().getPosX() + Gdx.graphics.getWidth() / 2f + margin;
                            y = playerController.getPlayer().getPosY() + MathUtils.random(-300, 300);
                            break;
                        case 2: // bottom
                            x = playerController.getPlayer().getPosX() + MathUtils.random(-400, 400);
                            y = playerController.getPlayer().getPosY() - Gdx.graphics.getHeight() / 2f - margin;
                            break;
                        case 3: // left
                            x = playerController.getPlayer().getPosX() - Gdx.graphics.getWidth() / 2f - margin;
                            y = playerController.getPlayer().getPosY() + MathUtils.random(-300, 300);
                            break;
                        default:
                            x = y = 0;
                    }

                    eyeMonsterController.spawnMonster(x, y);
                }
            }
        }

    }

    private void wingedMonsterSpawner(float delta) {
        float totalTime = App.getCurrentPlayer().getTotalGameTime();
        float timePassed = totalTime - timeRemaining;

        if (timePassed > totalTime / 2f && !wingedSpawned) {
            float x, y;
            int direction = MathUtils.random(0, 3);
            float margin = 250f;

            switch (direction) {
                case 0: // top
                    x = playerController.getPlayer().getPosX() + MathUtils.random(-400, 400);
                    y = playerController.getPlayer().getPosY() + Gdx.graphics.getHeight() / 2f + margin;
                    break;
                case 1: // right
                    x = playerController.getPlayer().getPosX() + Gdx.graphics.getWidth() / 2f + margin;
                    y = playerController.getPlayer().getPosY() + MathUtils.random(-300, 300);
                    break;
                case 2: // bottom
                    x = playerController.getPlayer().getPosX() + MathUtils.random(-400, 400);
                    y = playerController.getPlayer().getPosY() - Gdx.graphics.getHeight() / 2f - margin;
                    break;
                case 3: // left
                    x = playerController.getPlayer().getPosX() - Gdx.graphics.getWidth() / 2f - margin;
                    y = playerController.getPlayer().getPosY() + MathUtils.random(-300, 300);
                    break;
                default:
                    x = y = 0;
            }

            wingedMonsterController.spawnMonster(x, y);
            wingedSpawned = true;
        }
    }

}
