package entity.mob;

import graphics.AnimatedSprites;
import graphics.Screen;
import graphics.SpriteSheet;

public class Dummy extends Mob {
    private AnimatedSprites up = new AnimatedSprites(SpriteSheet.dummy_up, 16, 16, 4);
    private AnimatedSprites right = new AnimatedSprites(SpriteSheet.dummy_right, 16, 16, 4);
    private AnimatedSprites down = new AnimatedSprites(SpriteSheet.dummy_down, 16, 16, 4);
    private AnimatedSprites left = new AnimatedSprites(SpriteSheet.dummy_left, 16, 16, 4);

    private AnimatedSprites animatedSprite = down;

    private int time = 0;

    int xa = 0;
    int ya = 0;

    public Dummy(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = down;

        leftEdge = 4;
        rightEdge = 4;
        topEdge = spriteSize / 2;
        bottomEdge = 0;
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
    }

    public void render(Screen screen) {
        sprite = animatedSprite.getSprite();
        screen.renderMob((int) x, (int) y, sprite);
    }
}