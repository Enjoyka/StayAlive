package entity.mob;

import graphics.AnimatedSprites;
import graphics.Screen;
import graphics.Sprite;
import graphics.SpriteSheet;
import level.Node;
import util.Vector2i;

import java.util.List;

public class Star extends Mob {
    private AnimatedSprites up = new AnimatedSprites(SpriteSheet.dummy_up, 16, 16, 4);
    private AnimatedSprites right = new AnimatedSprites(SpriteSheet.dummy_right, 16, 16, 4);
    private AnimatedSprites down = new AnimatedSprites(SpriteSheet.dummy_down, 16, 16, 4);
    private AnimatedSprites left = new AnimatedSprites(SpriteSheet.dummy_left, 16, 16, 4);

    private AnimatedSprites animatedSprite = down;

    private List<Node> path = null;

    private double xa = 0;
    private double ya = 0;
    private int time = 0;

    public Star(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.dummy;
    }

    private void move() {
        xa = 0;
        ya = 0;

        int px = (int) level.getPlayerAt(0).getX();
        int py = (int) level.getPlayerAt(0).getY();

        Vector2i start = new Vector2i((int) getX() >> 4, (int) getY() >> 4);
        Vector2i destination = new Vector2i(px >> 4, py >> 4);

        if (time % 60 == 0) path = level.findPath(start, destination);
        if (path != null) {
            Vector2i vec = path.get(path.size() - 1).tile;
            if (x < vec.getX() << 4) xa++;
            if (x > vec.getX() << 4) xa--;
            if (y < vec.getY() << 4) ya++;
            if (y > vec.getY() << 4) ya--;
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
    }

    public void update() {
        time++;
        move();
        if (walking)
            animatedSprite.update();
        else
            animatedSprite.setFrame(0);

        if (ya < 0) {
            animatedSprite = up;
            dir = Mob.Direction.UP;
        } else if (ya > 0) {
            animatedSprite = down;
            dir = Mob.Direction.DOWN;
        }

        if (xa < 0) {
            animatedSprite = left;
            dir = Mob.Direction.LEFT;
        } else if (xa > 0) {
            animatedSprite = right;
            dir = Mob.Direction.RIGHT;
        }
    }


    public void render(Screen screen) {
        sprite = animatedSprite.getSprite();
        screen.renderMob((int) x, (int) y, sprite);
    }
}
