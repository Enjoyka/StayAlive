package entity.mob;

import game.Game;
import graphics.Screen;
import graphics.Sprite;
import input.Keyboard;
import input.Mouse;

public class Player extends Mob {
    private Keyboard input;
    private Sprite sprite;
    private int animate = 0;
    private boolean walking = false;

    public Player(Keyboard input) {
        this.input = input;
        sprite = Sprite.player_forward;
    }

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
        sprite = Sprite.player_forward;
    }

    public void update() {
        int xa = 0, ya = 0;

        if (animate < 7500)
            animate++;
        else
            animate = 0;

        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;
        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }

        updateShooting();
    }

    private void updateShooting() {
        if (Mouse.getButton() == 1) {
            double dx = Mouse.getX() - Game.getWindowWidth() / 2;
            double dy = Mouse.getY() - Game.getWindowHeight() / 2;
            double dir = Math.atan2(dx, dy);
            shoot(x, y, dir);
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

        screen.renderPlayer(x, y, sprite);
    }
}
