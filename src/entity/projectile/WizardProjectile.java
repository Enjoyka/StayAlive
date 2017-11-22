package entity.projectile;

import entity.spawner.ParticleSpawner;
import entity.spawner.Spawner;
import graphics.Screen;
import graphics.Sprite;

public class WizardProjectile extends Projectile {
    public final static int FIRE_RATE = 15; // higher is slower

    public WizardProjectile(double x, double y, double dir) {
        super(x, y, dir);
        range = 100;
        speed = 4;
        damage = 20;
        sprite = Sprite.rotate(Sprite.projectile_arrow, angle);
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }

    public void update() {
        if (level.tileCollision((int) (x + nx), (int) (y + ny), 7, 5, 5)) {
            level.add(new ParticleSpawner((int) x, (int) y, 50, 20, level));
            remove();
        }
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
