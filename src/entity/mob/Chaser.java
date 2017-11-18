package entity.mob;

import graphics.AnimatedSprites;
import graphics.Screen;
import graphics.Sprite;
import graphics.SpriteSheet;

import java.util.List;

public class Chaser extends Mob {
    private AnimatedSprites up = new AnimatedSprites(SpriteSheet.dummy_up, 16, 16, 4);
    private AnimatedSprites right = new AnimatedSprites(SpriteSheet.dummy_right, 16, 16, 4);
    private AnimatedSprites down = new AnimatedSprites(SpriteSheet.dummy_down, 16, 16, 4);
    private AnimatedSprites left = new AnimatedSprites(SpriteSheet.dummy_left, 16, 16, 4);

    private AnimatedSprites animatedSprite = down;

    private int xa = 0;
    private int ya = 0;

    public Chaser(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.dummy;
    }

    private void move() {
        xa = 0;
        ya = 0;

        List<Player> players = level.getPlayers(this, 50);
        if (players.size() > 0) {
            Player player = players.get(0);
            if (x < player.getX()) xa++;
            if (x > player.getX()) xa--;
            if (y < player.getY()) ya++;
            if (y > player.getY()) ya--;
        }
        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
    }

    public void update() {
        move();

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
    }


    public void render(Screen screen) {
        sprite = animatedSprite.getSprite();
        screen.renderMob(x, y, sprite);
    }
}
