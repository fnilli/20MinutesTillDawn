package com.tilldawn.Model.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon {

    private Weapons type ;
    private final Texture weaponTexture ;
    private Sprite sprite ;
    private int ammo;

    public Weapon(Weapons type) {
        this.type = type;
        this.weaponTexture  = new Texture(type.getSprite());
        this.sprite = new Sprite(weaponTexture);
        this.ammo = type.getMaxAmmo();
        sprite.setX((float) Gdx.graphics.getWidth() / 2 );
        sprite.setY((float) Gdx.graphics.getHeight() / 2);
        sprite.setSize(50,50);

    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo){
        this.ammo = ammo;
    }

    public Weapons getType() {
        return type;
    }


}

