package entity.mob;

import entity.Entity;
import graphics.AnimatedSprites;
import graphics.Screen;
import graphics.Sprite;
import graphics.SpriteSheet;
import util.Vector2i;

import java.util.List;

public class Shooter extends Mob {
    private AnimatedSprites up = new AnimatedSprites(SpriteSheet.shooter_up, 16, 16, 4);
    private AnimatedSprites right = new AnimatedSprites(SpriteSheet.shooter_right, 16, 16, 4);
    private AnimatedSprites down = new AnimatedSprites(SpriteSheet.shooter_down, 16, 16, 4);
    private AnimatedSprites left = new AnimatedSprites(SpriteSheet.shooter_left, 16, 16, 4);

    private AnimatedSprites animatedSprite = down;

    private int time = 0;
    private int xa = 0;
    private int ya = 0;

    private Entity rand = null;

    public Shooter(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.shooter;
        mobHealth = 150;
    }

    public void update() {
        time++;
        if (time % (random.nextInt(10) + 20) == 0) {
            xa = random.nextInt(3) - 1;
            ya = random.nextInt(3) - 1;
            if (random.nextInt(3) == 0) {
                xa = 0;
                ya = 0;
            }
        }

        if (walking)
            animatedSprite.update();
        else
            animatedSprite.setFrame(0);

        if (ya < 0) {
            animatedSprite = up;
            dir = Direction.UP;
        } else if (ya > 0) {
            animatedSprite = down;
            dir = Direction.DOWN;
        }

        if (xa < 0) {
            animatedSprite = left;
            dir = Direction.LEFT;
        } else if (xa > 0) {
            animatedSprite = right;
            dir = Direction.RIGHT;
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
        shootClosest();
    }

    private void shootRandom() {
        List<Entity> entities = level.getEntities(this, 100);
        entities.add(level.getClientPlayer());
        if (time % (30 + random.nextInt(90)) == 0) {
            int index = random.nextInt(entities.size());
            rand = entities.get(index);
        }

        if (rand != null) {
            double dx = rand.getX() - x;
            double dy = rand.getY() - y;
            double dir = Math.atan2(dy, dx);
            if (time % 30 == 1)
                shoot(x, y, dir);
        }
    }

    private void shootClosest() {
        List<Entity> entities = level.getEntities(this, 100);
        entities.add(level.getClientPlayer());

        double min = 0;
        Entity closest = null;
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            double distance = Vector2i.getDistance(new Vector2i((int) x, (int) y), new Vector2i((int) e.getX(), (int) e.getY()));
            if (i == 0 || distance < min) {
                min = distance;
                closest = e;
            }
        }
        if (closest != null) {
            double dx = closest.getX() - x;
            double dy = closest.getY() - y;
            double dir = Math.atan2(dy, dx);
            if (time % 30 == 1)
                shoot(x, y, dir);
        }
    }

    public void render(Screen screen) {
        sprite = animatedSprite.getSprite();
        screen.renderMob((int) x, (int) y, sprite);
    }
}