package entity.projectile;

import graphics.Screen;
import graphics.Sprite;

public class ArrowProjectile extends Projectile {
    public final static int FIRE_RATE = 15; // higher is slower

    public ArrowProjectile(double x, double y, double dir) {
        super(x, y, dir);
        range = 100;
        speed = 5;
        damage = 15;
        sprite = Sprite.rotate(Sprite.projectile_arrow, angle);
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }

    public void update() {
        mobHit();
        move();
    }

    protected void move() {
        x += nx;
        y += ny;
        if (distance() > range) remove();
    }

    private double distance() {
        double dist = 0;
        dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
        return dist;
    }

    public void render(Screen screen) {
        screen.renderProjectile((int) x, (int) y - 8, this);
    }
}
