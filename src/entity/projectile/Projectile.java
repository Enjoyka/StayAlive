package entity.projectile;

import entity.Entity;
import entity.mob.Dummy;
import entity.spawner.ParticleSpawner;
import graphics.Sprite;

import java.util.Random;

public abstract class Projectile extends Entity {
    protected final double xOrigin, yOrigin;
    protected double speed, range, damage;
    protected double x, y;
    protected double nx, ny;
    protected double angle;
    protected double distance;
    protected Sprite sprite;

    protected final Random random = new Random();

    public Projectile(double x, double y, double dir) {
        xOrigin = x;
        yOrigin = y;
        angle = dir;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getSpriteSize() {
        return sprite.SIZE;
    }

    protected void move() {
    }

    protected void mobHit() {
        if (level.tileCollision((int) (x + nx), (int) (y + ny), 7, 5, 5)) {
            level.add(new ParticleSpawner((int) x, (int) y, 50, 20, level));
            remove();
        }
        for (int i = 0; i < level.mobs.size(); i++) {
            if (x < level.mobs.get(i).getX() + 20 && x > level.mobs.get(i).getX() - 20 &&
                y < level.mobs.get(i).getY() + 20 && y > level.mobs.get(i).getY() - 20) {
                level.mobs.get(i).damageMob((int) damage);
                level.add(new ParticleSpawner((int) x, (int) y, 10, 10, level));
                remove();
                return;
            }
        }
        move();
    }
    // make better projectile collision
    protected void playerHit() {
        if (level.tileCollision((int) (x + nx), (int) (y + ny), 7, 5, 5)) {
            level.add(new ParticleSpawner((int) x, (int) y, 10, 10, level));
            remove();
        }
        for (int i = 0; i < level.players.size(); i++) {
            if (x < level.players.get(i).getX() + 10 && x > level.players.get(i).getX() - 10 &&
                y < level.players.get(i).getY() + 10 && y > level.players.get(i).getY() - 10) {
                level.players.get(i).damagePlayer((int) damage);
                level.add(new ParticleSpawner((int) x, (int) y, 10, 10, level));
                remove();
                return;
            }
        }
    }
}