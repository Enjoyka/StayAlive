package entity.mob;

import entity.Entity;
import entity.projectile.ArcticBurnProjectile;
import entity.projectile.ArrowProjectile;
import entity.projectile.Projectile;
import graphics.Screen;
import graphics.Sprite;

import static entity.mob.Player.expirience;
import static entity.mob.Player.gold;
import static entity.mob.Player.kills;

public abstract class Mob extends Entity {
    protected Sprite sprite;

    protected boolean moving = false;
    protected boolean walking = false;
    protected int mobHealth = 100;
    protected int destroyed = 0;

    protected enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    protected Direction dir;

    public void move(double xa, double ya) {
        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            return;
        }

        if (xa > 0) dir = Direction.RIGHT;
        if (xa < 0) dir = Direction.LEFT;
        if (ya > 0) dir = Direction.DOWN;
        if (ya < 0) dir = Direction.UP;

        while (xa != 0) {
            if (Math.abs(xa) > 1) {
                if (!collision(abs(xa), ya)) {
                    this.x += abs(xa);
                }
                xa -= abs(xa);
            } else {
                if (!collision(abs(xa), ya)) {
                    this.x += xa;
                }
                xa = 0;
            }
        }

        while (ya != 0) {
            if (Math.abs(ya) > 1) {
                if (!collision(xa, abs(ya))) {
                    this.y += abs(ya);
                }
                ya -= abs(ya);
            } else {
                if (!collision(xa, abs(ya))) {
                    this.y += ya;
                }
                ya = 0;
            }
        }
    }

    private int abs(double value) {
        if (value < 0) return -1;
        return 1;
    }

    public void damageMob(int damage) {
        if (mobHealth > 0) {
            mobHealth -= damage;
        } else if (mobHealth <= 0) {
            kills++;
            expirience += 100;
            gold += 50;
            remove();
        }
    }

    public abstract void update();

    public abstract void render(Screen screen);

    protected void shoot(double x, double y, double dir) {
        Projectile p = new ArcticBurnProjectile(x, y, dir);
        level.add(p);
    }

    private boolean collision(double xa, double ya) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            double xt = ((x + xa) - c % 2 * 10 + 5) / 16;
            double yt = ((y + ya) - c / 2 * 10 + 5) / 16;
            int ix = (int) Math.ceil(xt);
            int iy = (int) Math.ceil(yt);
            if (c % 2 == 0) ix = (int) Math.floor(xt);
            if (c / 2 == 0) iy = (int) Math.floor(yt);
            if (level.getTile(ix, iy).solid()) solid = true;
        }
        return solid;
    }
}