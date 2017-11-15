package entity.mob;

import entity.projectile.Projectile;
import entity.projectile.WizardProjectile;
import game.Game;
import graphics.AnimatedSprites;
import graphics.Screen;
import graphics.Sprite;
import graphics.SpriteSheet;
import input.Keyboard;
import input.Mouse;

public class Player extends Mob {
    private Keyboard input;
    private Sprite sprite;
    private int animate = 0;
    private int fireRate = 0;
    private boolean walking = false;

    private AnimatedSprites up = new AnimatedSprites(SpriteSheet.player_up, 16, 16, 4);
    private AnimatedSprites right = new AnimatedSprites(SpriteSheet.player_right, 16, 16, 4);
    private AnimatedSprites down = new AnimatedSprites(SpriteSheet.player_down, 16, 16, 4);
    private AnimatedSprites left = new AnimatedSprites(SpriteSheet.player_left, 16, 16, 4);

    private AnimatedSprites animatedSprite = down;

    public Player(Keyboard input) {
        this.input = input;
        sprite = Sprite.player_forward;
        animatedSprite = down;
    }

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
        sprite = Sprite.player_forward;
        fireRate = WizardProjectile.FIRE_RATE;
    }

    public void update() {
        if (walking) animatedSprite.update();
        else animatedSprite.setFrame(0);
        if (fireRate > 0) fireRate--;
        int xa = 0, ya = 0;

        if (animate < 7500)
            animate++;
        else
            animate = 0;

        if (input.up) {
            animatedSprite = up;
            ya--;
        }
        if (input.down) {
            animatedSprite = down;
            ya++;
        }
        if (input.left) {
            animatedSprite = left;
            xa--;
        }
        if (input.right) {
            animatedSprite = right;
            xa++;
        }
        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
        clear();
        updateShooting();
    }

    private void clear() {
        for (int i = 0; i < level.getProjectiles().size(); i++) {
            Projectile p = level.getProjectiles().get(i);
            if (p.isRemoved()) level.getProjectiles().remove(i);
        }
    }

    private void updateShooting() {
        if (Mouse.getButton() == 1 && fireRate <= 0) {
            double dx = Mouse.getX() - Game.getWindowWidth() / 2;
            double dy = Mouse.getY() - Game.getWindowHeight() / 2;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);
            fireRate = WizardProjectile.FIRE_RATE;
        }
    }

    public void render(Screen screen) {
        if (dir == 0) {
            sprite = Sprite.player_forward;
            if (walking) {
                if (animate % 20 > 10) {
                    sprite = Sprite.player_forward1;
                } else {
                    sprite = Sprite.player_forward2;
                }
            }
        }

        if (dir == 1) {
            sprite = Sprite.player_right;
            if (walking) {
                if (animate % 20 > 10) {
                    sprite = Sprite.player_right1;
                } else {
                    sprite = Sprite.player_right2;
                }
            }
        }

        if (dir == 2) {
            sprite = Sprite.player_back;
            if (walking) {
                if (animate % 20 > 10) {
                    sprite = Sprite.player_back1;
                } else {
                    sprite = Sprite.player_back2;
                }
            }
        }

        if (dir == 3) {
            sprite = Sprite.player_left;
            if (walking) {
                if (animate % 20 > 10) {
                    sprite = Sprite.player_left1;
                } else {
                    sprite = Sprite.player_left2;
                }
            }
        }
        sprite = animatedSprite.getSprite();
        screen.renderPlayer(x, y, sprite);
    }
}
