package entity.mob;

import entity.Entity;
import entity.projectile.Projectile;
import entity.projectile.WizardProjectile;
import game.Game;
import graphics.AnimatedSprites;
import graphics.Screen;
import graphics.Sprite;
import graphics.SpriteSheet;
import graphics.ui.ManagerUI;
import graphics.ui.UILabel;
import graphics.ui.UIPanel;
import input.Keyboard;
import input.Mouse;
import util.Vector2i;

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

    private ManagerUI ui;

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
        ui = Game.getManagerUI();
        UIPanel panel = new UIPanel(new Vector2i((300 - 80) * 3, 0), new Vector2i(80 * 3, 168 * 3));
        ui.addPanel(panel);
        panel.addComponent(new UILabel(new Vector2i(15, 15), "Leave me here").setColor(0xFF0000));
    }

    public void update() {
        if (walking) animatedSprite.update();
        else animatedSprite.setFrame(0);
        if (fireRate > 0) fireRate--;
        double xa = 0, ya = 0;
        double speed = 1;

        if (animate < 7500)
            animate++;
        else
            animate = 0;

        if (input.up) {
            animatedSprite = up;
            ya -= speed;
        }
        if (input.down) {
            animatedSprite = down;
            ya += speed;
        }
        if (input.left) {
            animatedSprite = left;
            xa -= speed;
        }
        if (input.right) {
            animatedSprite = right;
            xa += speed;
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
        sprite = animatedSprite.getSprite();
        screen.renderMob((int) x, (int) y, sprite);
    }
}
