package entity;

import graphics.Screen;
import graphics.Sprite;
import level.Level;

import java.util.Random;

public class Entity {
    protected Sprite sprite;
    protected Level level;
    protected Random random = new Random();

    private boolean removed = false;

    protected double x, y;

    public void update() {
    }

    public void render(Screen screen) {
        if (sprite != null) screen.renderSprite((int) x, (int) y, sprite, true);
    }

    public void remove() {
        //remove from level
        removed = true;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void init(Level level) {
        this.level = level;
    }
}