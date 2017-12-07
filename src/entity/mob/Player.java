package entity.mob;

import entity.projectile.ArrowProjectile;
import entity.projectile.Projectile;
import entity.projectile.FireballProjectile;
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

    protected static int kills = 0;
    protected static int expirience = 0;
    protected static int gold = 0;

    private int time = 0;
    private int lvl = 1;
    private int animate = 0;
    private int fireRate = 0;
    private int spellRate = 0;
    private int playerHealth = 100;
    private int playerMana = 1000;

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
        sprite = Sprite.player;
        animatedSprite = down;
    }

    public Player(String name, int x, int y, Keyboard input) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.input = input;

        sprite = Sprite.player;
        fireRate = ArrowProjectile.FIRE_RATE;

        // User Interface
        ui = Game.getManagerUI();
        UIPanel panel = (UIPanel) new UIPanel(new Vector2i((300 - 80) * 3, 0), new Vector2i(85 * 3, 168 * 3)).setColor(0x4A4A4A);
        ui.addPanel(panel);

        // Player name
        UILabel nameLabel = new UILabel(new Vector2i(10, 40), name);
        nameLabel.setColor(0xFFFFFF);
        nameLabel.setFont(new Font("Helvetica", Font.BOLD, 32));
        nameLabel.dropShadow = true;
        panel.addComponent(nameLabel);

        // Level
        uiLevelBar = new UIProgressBar(new Vector2i(10, 55), new Vector2i(85 * 3 - 25, 20));
        uiLevelBar.setColor(0x24a024);
        uiLevelBar.setForegroundColor(0x38ff38);
        panel.addComponent(uiLevelBar);

        UILabel lvlLabel = new UILabel(new Vector2i(uiLevelBar.position).add(new Vector2i(80, 17)), "LEVEL: " + lvl);
        lvlLabel.setColor(0xFFFFFF);
        lvlLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lvlLabel.dropShadow = true;
        panel.addComponent(lvlLabel);

        // Health
        uiHealthBar = new UIProgressBar(new Vector2i(10, 85), new Vector2i(85 * 3 - 25, 20));
        uiHealthBar.setColor(0xc11f1f);
        uiHealthBar.setForegroundColor(0xf21f1f);
        panel.addComponent(uiHealthBar);

        UILabel hpLabel = new UILabel(new Vector2i(uiHealthBar.position).add(new Vector2i(80, 17)), "HEALTH");
        hpLabel.setColor(0xFFFFFF);
        hpLabel.setFont(new Font("Arial", Font.BOLD, 20));
        hpLabel.dropShadow = true;
        panel.addComponent(hpLabel);

        // Mana
        uiManaBar = new UIProgressBar(new Vector2i(10, 115), new Vector2i(85 * 3 - 25, 20));
        uiManaBar.setColor(0x2f2fbf); //
        uiManaBar.setForegroundColor(0x4242ff); // change to dark blue or light blue, idk
        panel.addComponent(uiManaBar);

        UILabel mpLabel = new UILabel(new Vector2i(uiManaBar.position).add(new Vector2i(80, 17)), "MANA");
        mpLabel.setColor(0xFFFFFF);
        mpLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mpLabel.dropShadow = true;
        panel.addComponent(mpLabel);
    }

    public String getName() {
        return name;
    }

    public void update() {
        time++;

        if (walking)
            animatedSprite.update();
        else
            animatedSprite.setFrame(0);

        if (fireRate > 0) fireRate--;
        if (spellRate > 0) spellRate--;

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

        if (time % 30 == 1)
            if (playerMana < 1000) playerMana++;

        if (time % 60 == 1)
            if (playerHealth < 100) playerHealth++;

        updateShooting();
        if (playerMana > 50) updateSpell();

        clear();

        if (time % 360 == 1) {
            System.out.println("ENEMIES LEFT:      " + level.mobs.size());
            System.out.println("ENEMIES DESTROYED: " + kills);
            System.out.println("EXPIRIENCE EARNED: " + expirience);
            System.out.println("GOLD EARNED:       " + gold);
        }
        uiLevelBar.setProgress(lvl / 10.0);
        uiHealthBar.setProgress(playerHealth / 100.0);
        uiManaBar.setProgress(playerMana / 1000.0);
    }

    private void clear() {
        for (int i = 0; i < level.getProjectiles().size(); i++) {
            Projectile p = level.getProjectiles().get(i);
            if (p.isRemoved()) level.getProjectiles().remove(i);
        }
    }

    public void damagePlayer(int damage) {
        if (playerHealth > 0)
            playerHealth -= damage;
        else if (playerHealth < 0)
            System.out.println("GAME OVER");
    }

    protected void shoot(double x, double y, double dir) {
        Projectile p = new ArrowProjectile(x, y, dir);
        level.add(p);
    }

    protected void spell(double x, double y, double dir) {
        Projectile p = new FireballProjectile(x, y, dir);
        level.add(p);
    }

    private void updateShooting() {
        if (Mouse.getX() > 660) return;
        if (Mouse.getButton() == 1 && fireRate <= 0) {
            double dx = Mouse.getX() - Game.getWindowWidth() / 2;
            double dy = Mouse.getY() - Game.getWindowHeight() / 2;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);
            fireRate = ArrowProjectile.FIRE_RATE;
        }
    }
    // todo: remake players and mobs projectiles usage - give mobs spell or smthng else, and give player bullets or other
    private void updateSpell() {
        if (Mouse.getX() > 660) return;
        if (Mouse.getButton() == 3 && spellRate <= 0) {
            double dx = Mouse.getX() - Game.getWindowWidth() / 2;
            double dy = Mouse.getY() - Game.getWindowHeight() / 2;
            double dir = Math.atan2(dy, dx);
            playerMana -= 50;
            spell(x, y, dir);
            spellRate = FireballProjectile.FIRE_RATE;
        }
    }

    public void render(Screen screen) {
        sprite = animatedSprite.getSprite();
        screen.renderMob((int) x, (int) y, sprite);
    }
}