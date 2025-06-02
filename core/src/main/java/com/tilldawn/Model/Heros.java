package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public enum Heros {
    Shana(4, 4, "assets/Images/Sprite/Idle/Idle_0.png", "Images/Sprite/T/T_Shana_Portrait.png",
        GameAssetManager.getGameAssetManager().getShanaAnimation()),
    Diamond(2, 7, "assets/Images/Sprite/Idle/Idle_1.png", "Images/Sprite/T/T_Diamond_Portrait.png",
        GameAssetManager.getGameAssetManager().getDiamondAnimation()),
    Scarlet(5,3, "assets/Images/Sprite/Idle/Idle_2.png", "Images/Sprite/T/T_Scarlett_Portrait.png",
        GameAssetManager.getGameAssetManager().getScarletAnimation()),
    Lilith(3, 5, "assets/Images/Sprite/Idle/Idle_3.png", "Images/Sprite/T/T_Lilith_Portrait.png",
        GameAssetManager.getGameAssetManager().getLilithAnimation()),
    Dasher(10,2, "assets/Images/Sprite/Idle/Idle_4.png", "Images/Sprite/T/T_Dasher_Portrait.png",
        GameAssetManager.getGameAssetManager().getDasherAnimation());

    private int speed;
    private int hp;
    private String heroPath;
    private String portraitPath;
    private Animation<Texture> walkAnimation;

    Heros(int speed, int hp, String heroPath , String portraitPath, Animation<Texture> walkAnimation) {
        this.speed = speed;
        this.hp = hp;
        this.heroPath = heroPath;
        this.portraitPath = portraitPath;
        this.walkAnimation = walkAnimation;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHp() {
        return hp;
    }

    public String getHeroPath() {
        return heroPath;
    }

    public String getPortraitPath() {
        return portraitPath;
    }

    public Animation<Texture> getWalkAnimation() {
        return walkAnimation;
    }
}
