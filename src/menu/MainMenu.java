package menu;

import game.Game;
import graphics.Screen;
import input.Keyboard;

public class MainMenu extends Menu {
    public MainMenu(Keyboard key) {
        super(key);
    }

    int timer = 18;
    int selected = 0;
    String[] options = { "Play", "Options", "Help" };

    public void update() {
        if (timer > 0) timer--;
        if (key.down && selected < options.length && timer == 0) {
            selected++;
            timer = 10;
        }

        if (key.up && selected > 0 && timer == 0) {
            selected--;
            timer = 10;
        }

        if (selected < 0) selected = 0;
        if (selected > 2) selected = 2;

        if (selected == 0) {
            options[selected] = " > " + "Play" + " < ";
        } else {
            options[0] = "Play";
        }

        if (selected == 1) {
            options[selected] = " > " + "Help" + " < ";
            if (key.select) Game.menu = new HelpMenu(key);
        } else {
            options[1] = "Help";
        }

        if (selected == 2) {
            options[selected] = " > " + "About" + " < ";
            if (key.select) Game.menu = new AboutMenu(key);
        } else {
            options[2] = "About";
        }

        if (selected == 0 && key.select && timer == 0) {
            key.releaseAll();
            Game.menu = null;
            PlayMenu.biome = random.nextInt(3);
        }
    }

    public void render(Screen screen) {
        screen.renderText("Stay Alive", 170 + 4, 140 + 4, 120, 1, 0);
        screen.renderText("Stay Alive", 170, 140, 120, 1, 0xffffff);
        screen.renderText("A game by Ruslan Krishtal.", 250 + 2, 188 + 2, 30, 1, 0);
        screen.renderText("A game by Ruslan Krishtal.", 250, 188, 30, 1, 0xffffff);
        for (int i = 0; i < options.length; i++) {
            screen.renderText(options[i], 350 + 3, 350 + i * 60 + 3, 50, 1, 0);
            screen.renderText(options[i], 350, 350 + i * 60, 50, 1, 0xffffff);
        }
    }
}
