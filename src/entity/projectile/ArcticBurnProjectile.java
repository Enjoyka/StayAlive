package entity.projectile;

import graphics.Screen;
import graphics.Sprite;

public class ArcticBurnProjectile extends Projectile {
    public final static int FIRE_RATE = 10; // higher is slower

    public ArcticBurnProjectile(double x, double y, double dir) {
        super(x, y, dir);
        range = 70;
        speed = 4;
        damage = 10;
        sprite = Sprite.rotate(Sprite.projectile_arctic_burn, angle);
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }

    public void update() {
        playerHit();
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
        screen.renderProjectile((int) x, (int) y, this);
    }
}
