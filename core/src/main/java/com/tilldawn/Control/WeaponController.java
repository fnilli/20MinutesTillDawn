package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.tilldawn.Main;
import com.tilldawn.Model.App;
import com.tilldawn.Model.weapons.Bullet;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.monster.EyeMonster;
import com.tilldawn.Model.monster.Monster;
import com.tilldawn.Model.monster.WingedMonster;
import com.tilldawn.Model.weapons.Weapon;

import java.util.ArrayList;
import java.util.Iterator;

public class WeaponController {

        private WorldController worldController; // reference to monsters

        private Weapon weapon;
        private Player player;
        private ArrayList<Bullet> bullets = new ArrayList<>();
    private boolean isReloading = false;
    private float reloadTimer = 0f;


    public WeaponController(Weapon weapon, Player player, WorldController worldController) {
            this.worldController = worldController;
            this.weapon = weapon;
            this.player = player;
            Sprite weaponSprite = weapon.getSprite();
            weaponSprite.setOriginCenter(); // Set pivot for rotation and positioning

        }


        public void update() {
            if (isReloading) {
                reloadTimer -= Gdx.graphics.getDeltaTime();
                if (reloadTimer <= 0) {
                    isReloading = false;
                    weapon.setAmmo(weapon.getType().getMaxAmmo());
                }
            }
            Sprite weaponSprite =  weapon.getSprite();

            weaponSprite.setOriginCenter();

            float weaponX = player.getPlayerSprite().getX() + player.getPlayerSprite().getWidth() / 2f - weaponSprite.getWidth() / 2f;
            float weaponY = player.getPlayerSprite().getY() + player.getPlayerSprite().getHeight() / 2f - weaponSprite.getHeight() / 2f;

            weaponSprite.setPosition(weaponX, weaponY);
            weaponSprite.draw(Main.getBatch());

            updateBullets();
        }

        public void handleWeaponRotation(int mouseX, int mouseY, OrthographicCamera camera) {
            Sprite weaponSprite =  weapon.getSprite();


            float playerX = player.getPlayerSprite().getX() + weaponSprite.getWidth() / 2;
            float playerY = player.getPlayerSprite().getY() + weaponSprite.getHeight() / 2;

            Vector3 mouseWorld3 = camera.unproject(new Vector3(mouseX, mouseY, 0));
            Vector2 mouseWorld = new Vector2(mouseWorld3.x, mouseWorld3.y);

            float angleRad = MathUtils.atan2(mouseWorld.y - playerY, mouseWorld.x - playerX);
            float angleDeg = angleRad * MathUtils.radiansToDegrees;

            weaponSprite.setRotation(angleDeg);
        }




    public void handleeWeaponShoot(int mouseX, int mouseY, OrthographicCamera camera) {
        if (isReloading) return;

        if (weapon.getAmmo() > 0) {
            Vector3 mouseWorld3 = camera.unproject(new Vector3(mouseX, mouseY, 0));
            Vector2 mouseWorld = new Vector2(mouseWorld3.x, mouseWorld3.y);

            float startX = player.getPlayerSprite().getX() + weapon.getSprite().getWidth() / 2;
            float startY = player.getPlayerSprite().getY() + weapon.getSprite().getHeight() / 2;

            Vector2 direction = new Vector2(mouseWorld.x - startX, mouseWorld.y - startY).nor();

            bullets.add(new Bullet(startX, startY, direction));
            weapon.setAmmo(weapon.getAmmo() - 1);

            if (weapon.getAmmo() == 0 && App.getCurrentPlayer().getAutoReload()) {
                startReloading();
            }
        }
    }

    public void handleWeaponShoot(int mouseX, int mouseY, OrthographicCamera camera) {
        if (isReloading) return;

        int projectileCount = weapon.getType().getProjectile();
        int currentAmmo = weapon.getAmmo();

        if (currentAmmo <= 0) return;

        Vector3 mouseWorld3 = camera.unproject(new Vector3(mouseX, mouseY, 0));
        Vector2 mouseWorld = new Vector2(mouseWorld3.x, mouseWorld3.y);

        float startX = player.getPlayerSprite().getX() + weapon.getSprite().getWidth() / 2;
        float startY = player.getPlayerSprite().getY() + weapon.getSprite().getHeight() / 2;

        Vector2 baseDirection = new Vector2(mouseWorld.x - startX, mouseWorld.y - startY).nor();

        // How wide the spread is, in degrees
        float spreadAngle = 15f; // shotgun-like spread

        // Angle offset: start from -spread/2 to +spread/2
        float startAngle = -spreadAngle * (projectileCount - 1) / 2f;

//        for (int i = 0; i < projectileCount && i < currentAmmo; i++) {
        for (int i = 0; i < projectileCount ; i++) {
            float angleOffset = startAngle + i * spreadAngle;
            Vector2 direction = baseDirection.cpy().rotateDeg(angleOffset);
            bullets.add(new Bullet(startX, startY, direction));
        }

        weapon.setAmmo(currentAmmo - projectileCount);

        if (weapon.getAmmo() <= 0 && App.getCurrentPlayer().getAutoReload()) {
            startReloading();
        }
    }


    private void startReloading() {
        isReloading = true;
        reloadTimer = weapon.getType().getReloadTime();
    }


    public void updateBullets() {
            Iterator<Bullet> bulletIterator = bullets.iterator();
            while (bulletIterator.hasNext()) {
                Bullet b = bulletIterator.next();

                b.updatePosition();
                b.getSprite().draw(Main.getBatch());

                boolean bulletHit = false;

                // Check collision with brain monsters
                for (Monster monster : worldController.getMonsterController().getMonsters()) {
                    if (!monster.isDead() && b.getRect().collidesWith(monster.getRect())) {
                        monster.takeDamage(weapon.getType().getDamage());

                        if(monster.isDead()) player.increaseKill();
                        bulletHit = true;
                        break;
                    }
                }

                // Check collision with eye monsters
                if (!bulletHit) {
                    for (EyeMonster monster : worldController.getEyeMonsterController().getMonsters()) {
                        if (!monster.isDead() && b.getRect().collidesWith(monster.getRect())) {
                            monster.takeDamage(weapon.getType().getDamage());
//                            System.out.println(weapon.getType().getDamage());
                            if(monster.isDead()) player.increaseKill();
                            bulletHit = true;
                            break;
                        }
                    }
                }

                // Check collision with winged monsters
                if (!bulletHit) {
                    for (WingedMonster monster : worldController.getWingedMonsterController().getMonsters()) {
                        if (!monster.isDead() && b.getRect().collidesWith(monster.getRect())) {
                            monster.takeDamage(weapon.getType().getDamage());
//                            System.out.println(weapon.getType().getDamage());
                            if(monster.isDead()) player.increaseKill();
                            bulletHit = true;
                            break;
                        }
                    }
                }

                if (bulletHit) {
                    bulletIterator.remove(); // ✅ safe now!
                }
            }
        }
    public void handleReloadKeyPress() {
        // Only reload if not already reloading and current ammo is less than max
        if (!isReloading && weapon.getAmmo() < weapon.getType().getMaxAmmo()) {
            startReloading();
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
