package entity.projectile;

import entity.Entity;
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
}