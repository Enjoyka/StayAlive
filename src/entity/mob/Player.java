package entity.mob;

import entity.projectile.Projectile;
import entity.projectile.WizardProjectile;
import game.Game;
import graphics.AnimatedSprites;
import graphics.Screen;
import graphics.Sprite;
import graphics.SpriteSheet;
import graphics.ui.*;
import input.Keyboard;
import input.Mouse;
import util.Vector2i;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Mob {
    private BufferedImage home;
    private Keyboard input;
    private Sprite sprite;
    private String name;

    private int animate = 0;
    private int fireRate = 0;

    private boolean walking = false;

    private AnimatedSprites up = new AnimatedSprites(SpriteSheet.player_up, 16, 16, 4);
    private AnimatedSprites right = new AnimatedSprites(SpriteSheet.player_right, 16, 16, 4);
    private AnimatedSprites down = new AnimatedSprites(SpriteSheet.player_down, 16, 16, 4);
    private AnimatedSprites left = new AnimatedSprites(SpriteSheet.player_left, 16, 16, 4);

    private AnimatedSprites animatedSprite = down;

    private ManagerUI ui;
    private UIProgressBar uiHealthBar;
    private UIProgressBar uiManaBar;
    private UIProgressBar uiLevelBar;

    @Deprecated
    public Player(String name, Keyboard input) {
        this.name = name;
        this.input = input;
        sprite = Sprite.player_forward;
        animatedSprite = down;
    }

    public Player(String name, int x, int y, Keyboard input) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.input = input;

        sprite = Sprite.player_forward;
        fireRate = WizardProjectile.FIRE_RATE;

        //player default attributes
        health = 100;
        mana = 1000;

        // User Interface
        ui = Game.getManagerUI();
        UIPanel panel = (UIPanel) new UIPanel(new Vector2i((300 - 80) * 3, 0), new Vector2i(85 * 3, 168 * 3)).setColor(0x4A4A4A);
        ui.addPanel(panel);

        // Player name
        UILabel nameLabel = new UILabel(new Vector2i(10, 240), name);
        nameLabel.setColor(0xFFFFFF);
        nameLabel.setFont(new Font("Helvetica", Font.BOLD, 32));
        nameLabel.dropShadow = true;
        panel.addComponent(nameLabel);

        // Level
        uiLevelBar = new UIProgressBar(new Vector2i(10, 255), new Vector2i(85 * 3 - 25, 20));
        uiLevelBar.setColor(0x4AFF4A);
        panel.addComponent(uiLevelBar);

        UILabel lvlLabel = new UILabel(new Vector2i(uiLevelBar.position).add(new Vector2i(80, 17)), "LVL 1");
        lvlLabel.setColor(0xFFFFFF);
        lvlLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.addComponent(lvlLabel);

        // Health
        uiHealthBar = new UIProgressBar(new Vector2i(10, 285), new Vector2i(85 * 3 - 25, 20));
        uiHealthBar.setColor(0xFF2B2B);
        panel.addComponent(uiHealthBar);

        UILabel hpLabel = new UILabel(new Vector2i(uiHealthBar.position).add(new Vector2i(80, 17)), "HP: " + health);
        hpLabel.setColor(0xFFFFFF);
        hpLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.addComponent(hpLabel);

        // Mana
        uiManaBar = new UIProgressBar(new Vector2i(10, 315), new Vector2i(85 * 3 - 25, 20));
        uiManaBar.setColor(0x4B4BFF);
        panel.addComponent(uiManaBar);

        UILabel mpLabel = new UILabel(new Vector2i(uiManaBar.position).add(new Vector2i(80, 17)), "MP: " + mana);
        mpLabel.setColor(0xFFFFFF);
        mpLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.addComponent(mpLabel);
    }

    public String getName() {
        return name;
    }

    public void update() {
        if (walking)
            animatedSprite.update();
        else
            animatedSprite.setFrame(0);

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

        uiHealthBar.setProgress(health / 100.0);
        uiManaBar.setProgress(mana / 1000.0);
    }

    private void clear() {
        for (int i = 0; i < level.getProjectiles().size(); i++) {
            Projectile p = level.getProjectiles().get(i);
            if (p.isRemoved()) level.getProjectiles().remove(i);
        }
    }

    private void updateShooting() {
        if (Mouse.getX() > 660) return;
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
