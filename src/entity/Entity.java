package entity;

import graphics.Screen;
import level.Level;

import java.util.Random;

public class Entity {
    public int x, y;
    private boolean removed = false;
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

    public boolean isRemoved() {
        return removed;
    }

    public void init(Level level) {
        this.level = level;
    }
}
