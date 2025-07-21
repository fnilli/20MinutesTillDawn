package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.HashMap;
import java.util.Map;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("assets/skin/pixthulhu-ui.json"));
    private final Skin darkSkin = new Skin(Gdx.files.internal("assets/skin/pixthulhu-ui-dark.json"));


    private final String shana0 = "Images/Sprite/Walk/shana/Walk_1 #8630.png";
    private final String shana1 = "Images/Sprite/Walk/shana/Walk_3 #8767.png";
    private final String shana2 = "Images/Sprite/Walk/shana/Walk_2 #8709.png";
    private final String shana3 = "Images/Sprite/Walk/shana/Walk_4.png";
    private final String shana4 = "Images/Sprite/Walk/shana/Walk_5 #8686.png";
    private final String shana5 = "Images/Sprite/Walk/shana/Walk_5 #8686.png";
    private final String shana6 = "Images/Sprite/Walk/shana/Walk_6 #8440.png";
    private final String shana7 = "Images/Sprite/Walk/shana/Walk_7.png";
    private final Texture shana0_tex = new Texture(shana0);
    private final Texture shana1_tex = new Texture(shana1);
    private final Texture shana2_tex = new Texture(shana2);
    private final Texture shana3_tex = new Texture(shana3);
    private final Texture shana4_tex = new Texture(shana4);
    private final Texture shana5_tex = new Texture(shana5);
    private final Texture shana6_tex = new Texture(shana6);
    private final Texture shana7_tex = new Texture(shana7);
    private final Animation<Texture> shanaAnimation = new Animation<>(0.1f, shana0_tex, shana1_tex, shana2_tex, shana3_tex, shana4_tex, shana5_tex, shana6_tex, shana7_tex);


    private final String character1_idle0 = "Images/Sprite/Walk/diamond/Walk_0 #8679.png";
    private final String character1_idle1 = "Images/Sprite/Walk/diamond/Walk_1.png";
    private final String character1_idle2 = "Images/Sprite/Walk/diamond/Walk_2.png";
    private final String character1_idle3 = "Images/Sprite/Walk/diamond/Walk_3.png";
    private final String character1_idle4 = "Images/Sprite/Walk/diamond/Walk_5.png";
    private final String character1_idle5 = "Images/Sprite/Walk/diamond/Walk_6.png";
    private final Texture character1_idle0_tex = new Texture(character1_idle0);
    private final Texture character1_idle1_tex = new Texture(character1_idle1);
    private final Texture character1_idle2_tex = new Texture(character1_idle2);
    private final Texture character1_idle3_tex = new Texture(character1_idle3);
    private final Texture character1_idle4_tex = new Texture(character1_idle4);
    private final Texture character1_idle5_tex = new Texture(character1_idle5);
    private final Animation<Texture> diamondAnimation = new Animation<>(0.1f, character1_idle0_tex, character1_idle1_tex, character1_idle2_tex, character1_idle3_tex, character1_idle4_tex, character1_idle5_tex);


    private final String scarlet0 = "Images/Sprite/Walk/scarlet/Walk_0 #8278.png";
    private final String scarlet1 = "Images/Sprite/Walk/scarlet/Walk_1 #8632.png";
    private final String scarlet2 = "Images/Sprite/Walk/scarlet/Walk_2 #8711.png";
    private final String scarlet3 = "Images/Sprite/Walk/scarlet/Walk_3 #8769.png";
    private final String scarlet4 = "Images/Sprite/Walk/scarlet/Walk_5 #8688.png";
    private final String scarlet5 = "Images/Sprite/Walk/scarlet/Walk_6 #8442.png";
    private final String scarlet6 = "Images/Sprite/Walk/scarlet/Walk_7 #8214.png";
    private final Texture scarlet0_tex = new Texture(scarlet0);
    private final Texture scarlet1_tex = new Texture(scarlet1);
    private final Texture scarlet2_tex = new Texture(scarlet2);
    private final Texture scarlet3_tex = new Texture(scarlet3);
    private final Texture scarlet4_tex = new Texture(scarlet4);
    private final Texture scarlet5_tex = new Texture(scarlet5);
    private final Texture scarlet6_tex = new Texture(scarlet6);
    private  final  Animation<Texture> scarletAnimation = new Animation<>(0.1f, scarlet0_tex, scarlet1_tex, scarlet2_tex, scarlet3_tex, scarlet4_tex, scarlet5_tex, scarlet6_tex);


    private final String lilith0 = "Images/Sprite/Walk/lilith/Walk_0 #8279.png";
    private final String lilith1 = "Images/Sprite/Walk/lilith/Walk_1 #8633.png";
    private final String lilith2 = "Images/Sprite/Walk/lilith/Walk_2 #8712.png";
    private final String lilith3 = "Images/Sprite/Walk/lilith/Walk_3 #8770.png";
    private final String lilith4 = "Images/Sprite/Walk/lilith/Walk_4 #8465.png";
    private final String lilith5 = "Images/Sprite/Walk/lilith/Walk_5 #8689.png";
    private final String lilith6 = "Images/Sprite/Walk/lilith/Walk_6 #8443.png";
    private final String lilith7 = "Images/Sprite/Walk/lilith/Walk_7 #8215.png";
    private final Texture lilith0_tex = new Texture(lilith0);
    private final Texture lilith1_tex = new Texture(lilith1);
    private final Texture lilith2_tex = new Texture(lilith2);
    private final Texture lilith3_tex = new Texture(lilith3);
    private final Texture lilith4_tex = new Texture(lilith4);
    private final Texture lilith5_tex = new Texture(lilith5);
    private final Texture lilith6_tex = new Texture(lilith6);
    private final Texture lilith7_tex = new Texture(lilith7);
    private final Animation<Texture> lilithAnimation = new Animation<>(0.1f, lilith0_tex, lilith1_tex, lilith2_tex, lilith3_tex, lilith4_tex,
        lilith5_tex, lilith6_tex, lilith7_tex);


    private final String dasher0 = "Images/Sprite/Walk/dasher/Walk_0 #8277.png";
    private final String dasher1 = "Images/Sprite/Walk/dasher/Walk_1 #8631.png";
    private final String dasher2 = "Images/Sprite/Walk/dasher/Walk_2 #8710.png";
    private final String dasher3 = "Images/Sprite/Walk/dasher/Walk_3 #8768.png";
    private final String dasher4 = "Images/Sprite/Walk/dasher/Walk_4 #8464.png";
    private final String dasher5 = "Images/Sprite/Walk/dasher/Walk_5 #8687.png";
    private final String dasher6 = "Images/Sprite/Walk/dasher/Walk_6 #8441.png";
    private final String dasher7 = "Images/Sprite/Walk/dasher/Walk_7 #8213.png";
    private final Texture dasher0_tex = new Texture(dasher0);
    private final Texture dasher1_tex = new Texture(dasher1);
    private final Texture dasher2_tex = new Texture(dasher2);
    private final Texture dasher3_tex = new Texture(dasher3);
    private final Texture dasher4_tex = new Texture(dasher4);
    private final Texture dasher5_tex = new Texture(dasher5);
    private final Texture dasher6_tex = new Texture(dasher6);
    private final Texture dasher7_tex = new Texture(dasher7);
    private final Animation<Texture> dasherAnimation = new Animation<>(0.1f, dasher0_tex, dasher1_tex, dasher2_tex, dasher3_tex, dasher4_tex,
        dasher5_tex, dasher6_tex, dasher7_tex);

    private final String smg = "Images/Sprite/SMGStill/SMGStill.png";
    private final Texture smgTexture = new Texture(smg);
    private final String revolver = "Images/Sprite/RevolverStill/RevolverStill.png";
    private final Texture revolverTexture = new Texture(revolver);
    private final String shotgun = "Images/Sprite/T/T_Shotgun_SS_0.png";
    private final Texture shotgunTexture = new Texture(shotgun);

    private final String mosterBullet = "Images/Sprite/EyeMonsterProjecitle/EyeMonsterProjecitle.png";
    private Music backgroundMusic;
    private float musicVolume = 3.0f; // default volume

    private final String defaultMusicPath = "audio/default_theme.mp3";

    private boolean sfxEnabled = true;

    private final String brainMonster_idle0 = "Images/Sprite/BrainMonster/BrainMonster_0.png";
    private final String brainMonster_idle1 = "Images/Sprite/BrainMonster/BrainMonster_1.png";
    private final String brainMonster_idle2 = "Images/Sprite/BrainMonster/BrainMonster_2.png";
    private final String brainMonster_idle3 = "Images/Sprite/BrainMonster/BrainMonster_3.png";
    private final Texture brainMonster_idle0_tex = new Texture(brainMonster_idle0);
    private final Texture brainMonster_idle1_tex = new Texture(brainMonster_idle1);
    private final Texture brainMonster_idle2_tex = new Texture(brainMonster_idle2);
    private final Texture brainMonster_idle3_tex = new Texture(brainMonster_idle3);
    private final Animation<Texture> brainMonsterAnimation = new Animation<>(0.2f, brainMonster_idle0_tex, brainMonster_idle1_tex, brainMonster_idle2_tex, brainMonster_idle3_tex);

    private final String eyeMonster0 = "Images/Sprite/EyeMonster/EyeMonster_0.png";
    private final String eyeMonster1 = "Images/Sprite/EyeMonster/EyeMonster_1.png";
    private final String eyeMonster2 = "Images/Sprite/EyeMonster/EyeMonster_2.png";
    private final Texture eyeMonster_tex0 = new Texture(eyeMonster0);
    private final Texture eyeMonster_tex1 = new Texture(eyeMonster1);
    private final Texture eyeMonster_tex2 = new Texture(eyeMonster2);
    private final  Animation<Texture> eyeMonsterAnimation = new Animation<>(0.2f, eyeMonster_tex0, eyeMonster_tex1, eyeMonster_tex2);

    private final String winged0 = "Images/Sprite/WingedMonster/WingedMonster_0.png";
    private final String winged1 = "Images/Sprite/WingedMonster/WingedMonster_1.png" ;
    private final String winged2 = "Images/Sprite/WingedMonster/WingedMonster_2.png";
    private final String winged3 = "Images/Sprite/WingedMonster/WingedMonster_3.png";
    private final String winged4 = "Images/Sprite/WingedMonster/WingedMonster_4.png";
    private final Texture winged0tex = new Texture(winged0);
    private final Texture winged1tex = new Texture(winged1);
    private final Texture winged2tex = new Texture(winged2);
    private final Texture winged3tex = new Texture(winged3);
    private final Texture winged4tex = new Texture(winged4);
    private final Animation<Texture> wingedMonsterAnimation = new Animation<>(0.2f, winged0tex, winged1tex, winged2tex, winged3tex, winged4tex);


    private final String monsterDeath0 = "Images/Sprite/FireballExplosion/FireballExplosion_0.png";
    private final String monsterDeath1 = "Images/Sprite/FireballExplosion/FireballExplosion_1.png";
    private final String monsterDeath2 = "Images/Sprite/FireballExplosion/FireballExplosion_2.png";
    private final String monsterDeath3 = "Images/Sprite/FireballExplosion/FireballExplosion_3.png";
    private final String monsterDeath4 = "Images/Sprite/FireballExplosion/FireballExplosion_4.png";
    private final String monsterDeath5 = "Images/Sprite/FireballExplosion/FireballExplosion_5.png";
    private final Texture monsterDeath_tex0 = new Texture(monsterDeath0);
    private final Texture monsterDeath_tex1 = new Texture(monsterDeath1);
    private final Texture monsterDeath_tex2 = new Texture(monsterDeath2);
    private final Texture monsterDeath_tex3 = new Texture(monsterDeath3);
    private final Texture monsterDeath_tex4 = new Texture(monsterDeath4);
    private final Texture monsterDeath_tex5 = new Texture(monsterDeath5);

    private final Animation<Texture> monsterDeathAnimation = new Animation<>(0.1f, monsterDeath_tex0, monsterDeath_tex1 , monsterDeath_tex2 , monsterDeath_tex3, monsterDeath_tex4, monsterDeath_tex5);


    private final String tree0 = "Images/Sprite/T/T_TreeMonster_0.png";
    private final String tree1 = "Images/Sprite/T/T_TreeMonster_1.png";
    private final String tree2 = "Images/Sprite/T/T_TreeMonster_2.png";
    private final Texture treeTex0 = new Texture(tree0);
    private final Texture treeTex1 = new Texture(tree1);
    private final Texture treeTex2 = new Texture(tree2);
    private final Animation<Texture> treeAnimation = new Animation<>(1f, treeTex0, treeTex1, treeTex2);


    private final String shield0 = "Images/Sprite/HolyShield/HolyShield_0.png";
    private final String shield1 = "Images/Sprite/HolyShield/HolyShield_1.png";
    private final String shield2 = "Images/Sprite/HolyShield/HolyShield_2.png";
    private final String shield3 = "Images/Sprite/HolyShield/HolyShield_3.png";
    private final String shield4 = "Images/Sprite/HolyShield/HolyShield_4.png";
    private final String shield5 = "Images/Sprite/HolyShield/HolyShield_5.png";
    private final String shield6 = "Images/Sprite/HolyShield/HolyShield_6.png";
    private final Texture shield_tex0 = new Texture(shield0);
    private final Texture shield_tex1 = new Texture(shield1);
    private final Texture shield_tex2 = new Texture(shield2);
    private final Texture shield_tex3 = new Texture(shield3);
    private final Texture shield_tex4 = new Texture(shield4);
    private final Texture shield_tex5 = new Texture(shield5);
    private final Texture shield_tex6 = new Texture(shield6);
    private final Animation<Texture> shieldAnimation = new Animation<>(0.1f, shield_tex0, shield_tex1, shield_tex2, dasher3_tex, shield_tex4, shield_tex5, shield_tex6);


    public Texture getShieldTexture(){
        return new Texture("Images/Sprite/PowerupFrame/PowerupFrame.png");
    }

    private final String heroDamamge0 = "Images/Sprite/DeathFX/DeathFX_0.png";
    private final String heroDamamge1 = "Images/Sprite/DeathFX/DeathFX_1.png";
    private final String heroDamamge2 = "Images/Sprite/DeathFX/DeathFX_2.png";
    private final String heroDamamge3 = "Images/Sprite/DeathFX/DeathFX_3.png";
    private final Texture damage_tex0 = new Texture(heroDamamge0);
    private final Texture damage_tex1 = new Texture(heroDamamge1);
    private final Texture damage_tex2 = new Texture(heroDamamge2);
    private final Texture damage_tex3 = new Texture(heroDamamge3);
    private final Animation<Texture> heroDamageAnimation = new Animation<>(0.08f, damage_tex0, damage_tex1, damage_tex2, damage_tex3);

    private final Sound wonSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/goodresult.mp3"));
    private final Sound loseSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/death-scream.mp3"));
    private final Sound click2ButtonSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/UI Click 36.wav"));
    private final Sound clickButtonSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Pop.wav"));
    private final Sound checkBoxSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Item Lock.wav"));
    private final Sound getCoinSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Crystal Reward Tick.wav"));
    private final Sound monsterDeathSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Explosion_Blood_01.wav"));

    private final Sound shootSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/single_shot.wav"));
    private final Sound powerUpSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Special & Powerup (8).wav"));
    private final Sound damageSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/sfx_sounds_impact1.wav"));
    private final Sound switchSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Switch.wav"));
    private final Sound weaponReloadSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Weapon_Shotgun_Reload.wav"));
    private final Sound bloodSplashSound = Gdx.audio.newSound(Gdx.files.internal("SFX/AudioClip/Blood_Splash_Quick_01.wav"));




    public Sound getLoseSound() {
        return loseSound;
    }

    public Sound getWonSound() {    return wonSound;}

    public Sound getGetCoinSound() {return getCoinSound;}

    public Sound getCheckBoxSound() {
        return checkBoxSound;
    }

    public Sound getClick2ButtonSound() {
        return click2ButtonSound;
    }

    public Sound getPowerUpSound() {
        return powerUpSound;
    }

    public Sound getDamageSound() {
        return damageSound;
    }

    public Sound getSwitchSound() {
        return switchSound;
    }

    public Sound getWeaponReloadSound() {
        return weaponReloadSound;
    }

    public Sound getBloodSplashSound() {
        return bloodSplashSound;
    }

    public Sound getMonsterDeathSound() {
        return monsterDeathSound;
    }

    public Sound getClickButtonSound() { return clickButtonSound;}

    public Sound getShootSound() {
        return shootSound;
    }

    public Animation<Texture> getTreeAnimation() {
        return treeAnimation;
    }

    public Animation<Texture> getMonsterDeathAnimation() {
        return monsterDeathAnimation;
    }

    public Animation<Texture> getBrainMonsterAnimation() {
        return brainMonsterAnimation;
    }

    public Animation<Texture> getEyeMonsterAnimation() {
        return eyeMonsterAnimation;
    }

    public Animation<Texture> getShieldAnimation() {
        return shieldAnimation;
    }

    public Animation<Texture> getWingedMonsterAnimation() {
        return wingedMonsterAnimation;
    }

    public Animation<Texture> getShanaAnimation() {
        return shanaAnimation;
    }


    private GameAssetManager(){
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(defaultMusicPath));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(musicVolume);
        backgroundMusic.play();
    }

    public static GameAssetManager getGameAssetManager(){
        if (gameAssetManager == null){
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    public Animation<Texture> getHeroDamageAnimation() {
        return heroDamageAnimation;
    }

    public Skin getSkin() {
        if (App.getCurrentPlayer() != null && App.getCurrentPlayer().isDarkTheme() ) {
            return darkSkin;
        }
        return skin;
    }

    public Animation<Texture> getCharacter1_idle_animation() {
        return diamondAnimation;
    }

    public String getCharacter1_idle0(){
        return character1_idle0;
    }

    public Texture getSmgTexture(){
        return smgTexture;
    }

    public String getSmg(){
        return smg;
    }
    public String getRevolver() {
        return revolver;
    }
    public String getShotgun() {
        return shotgun;
    }

    public String getBullet(){
        return "bullet.png";
    }

    public void setMusic(String path) {
        if (backgroundMusic != null) backgroundMusic.stop();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(path));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(musicVolume);
        backgroundMusic.play();
    }

    public void setVolume(float volume) {
        this.musicVolume = volume;
        if (backgroundMusic != null) {
            backgroundMusic.setVolume(volume);
        }
    }

    public String getMonsterBullet() {
        return mosterBullet;
    }

    public float getVolume() {
        return musicVolume;
    }

    public void stopMusic() {
        if (backgroundMusic != null) backgroundMusic.stop();
    }

    public void playMusic() {
        if (backgroundMusic != null) backgroundMusic.play();
    }

    public String getDefaultMusicPath() {
        return defaultMusicPath;
    }

    public String getMusic1Path() {
        return "audio/theme1.mp3";
    }

    public String getMusic4Path() {
        return "audio/theme4.mp3";
    }

    public String getMusic3Path() {
        return "audio/theme3.mp3";
    }

    public String getMusic2Path() {
        return "audio/theme2.mp3";
    }

    public boolean isSfxEnabled() {
        return sfxEnabled;
    }

    public void setSfxEnabled(boolean sfxEnabled) {
        this.sfxEnabled = sfxEnabled;
    }

    public Animation<Texture> getDiamondAnimation() {
        return diamondAnimation;
    }

    public Animation<Texture> getScarletAnimation() {
        return scarletAnimation;
    }

    public Animation<Texture> getLilithAnimation() {
        return lilithAnimation;
    }
    public Animation<Texture> getDasherAnimation() {
        return dasherAnimation;
    }

}
