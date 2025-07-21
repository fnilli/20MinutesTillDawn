package com.tilldawn.Model.weapons;

import com.tilldawn.Model.GameAssetManager;

public enum Weapons {
    Revolver(20, 1 , 1f, 6, "assets/Images/Sprite/RevolverStill/RevolverStill.png",
        GameAssetManager.getGameAssetManager().getRevolver()),
    Shotgun(10, 4, 1f, 2, "assets/Images/Sprite/T/T_Shotgun_SS_0.png",
        GameAssetManager.getGameAssetManager().getShotgun()),
    Smg(8, 1, 2, 24, "assets/Images/Sprite/SMGStill/SMGStill.png",
        GameAssetManager.getGameAssetManager().getSmg()),;



    private int damage;
    private int projectile;
    private float reloadTime;
    private int maxAmmo;
    private String path;
    private String sprite;


    Weapons(int damage, int projectile, float reloadTime, int maxAmmo, String path, String sprite) {
        this.damage = damage;
        this.projectile = projectile;
        this.reloadTime = reloadTime;
        this.maxAmmo = maxAmmo;
        this.path = path;
        this.sprite = sprite;
    }

    public int getDamage() {
        return damage;
    }

    public int getProjectile() {
        return projectile;
    }

    public float getReloadTime() {
        return reloadTime;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public String getPath() {
        return path;
    }

    public String getSprite() {
        return sprite;
    }
}
