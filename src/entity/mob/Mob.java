package entity.mob;

import entity.Entity;
import entity.particle.Particle;
import entity.projectile.Projectile;
import entity.projectile.WizardProjectile;
import graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class Mob extends Entity {
    protected Sprite sprite;
    protected int dir = 0;
    protected boolean moving = false;
    protected boolean walking = false;

    public void move(int xa, int ya) {
        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            return;
        }

        if (xa > 0) dir = 1; //right
        if (xa < 0) dir = 3; //left
        if (ya > 0) dir = 2; //down
        if (ya < 0) dir = 0; //up

        if (!collision(xa, ya)) {
            y += ya;
            x += xa;
        }
    }

    public void update() {
    }

    protected void shoot(int x, int y, double dir) {
        Projectile p = new WizardProjectile(x, y, dir);
        level.add(p);
    }

    private boolean collision(int xa, int ya) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = ((x + xa) + c % 2 * 10 + 5) / 16;
            int yt = ((y + ya) + c / 2 * 10 + 5) / 16;
            if (level.getTile(xt, yt).solid()) solid = true;
        }
        return solid;
    }

    public void render() {
    }
}
