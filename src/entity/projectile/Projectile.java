package entity.projectile;

import entity.Entity;
import graphics.Sprite;

public abstract class Projectile extends Entity {
    protected final int xOrigin, yOrigin;
    protected double speed, rateOfFire, range, damage;
    protected double nx, ny; //new x, new y
    protected double angle;
    protected Sprite sprite;

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
