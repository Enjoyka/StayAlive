package menu;

import game.Game;
import graphics.Screen;
import input.Keyboard;

import java.util.Random;

public class Menu {
    protected Keyboard key;
    protected Game game;
    protected Random random = new Random();

    public Menu(Keyboard key) {
        this.key = key;
    }

    public Menu(Game game) {
        this.game = game;
    }

    public void update() {
    }

    public void render(Screen screen) {
    }
}
