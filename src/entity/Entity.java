package entity;

import graphics.Screen;
import graphics.Sprite;
import level.Level;

import java.util.Random;

public class Entity {
    private boolean removed = false;
    protected int x, y;
    protected Sprite sprite;
    protected Level level;
    protected Random random = new Random();

    public void update() {
    }

    public void render(Screen screen) {
    }

    public void remove() {
        //remove from level
        removed = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
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
