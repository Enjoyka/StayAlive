package entity.projectile;

import entity.Entity;
import graphics.Sprite;

import java.util.Random;

public abstract class Projectile extends Entity {
    protected final int xOrigin, yOrigin;
    protected double speed, range, damage;
    protected double x, y;
    protected double nx, ny; //new x, new y
    protected double angle;
    protected double distance;
    protected Sprite sprite;

    protected final Random random = new Random();

    public Projectile(int x, int y, double dir) {
        xOrigin = x;
        yOrigin = y;
        angle = dir;
        this.x = x;
        this.y = y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getSpriteSize() {
        return sprite.SIZE;
    }

    protected void move() {

    }
}
