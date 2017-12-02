package menu;

import game.Game;
import graphics.Screen;
import input.Keyboard;

public class PlayMenu extends Menu {
    public PlayMenu(Keyboard key) {
        super(key);
    }

    int timer = 10;
    int selected = 0;
    String[] options = { "First", "Second", "Third", "Fourth", "Random" };
    public static int biome = -1; // mb change name later

    public void update() {
        if (timer > 0) timer--;

        if (key.down && selected < options.length - 1 && timer == 0) {
            selected++;
            timer = 10;
        }

        if (key.up && selected > 0 && timer == 0) {
            selected--;
            timer = 10;
        }

        if (selected < 0) selected = 0;
        if (selected > options.length - 1) selected = options.length - 1;

        if (selected == 0) {
            options[selected] = " > " + "First" + " < ";
            if (key.select) {
                biome = 0;
            }
        } else {
            options[0] = "First";
        }

        if (selected == 1) {
            options[selected] = " > " + "Second" + " < ";
            if (key.select) {
                biome = 1;
            }
        } else {
            options[1] = "Second";
        }

        if (selected == 2) {
            options[selected] = " > " + "Third" + " < ";
            if (key.select) {
                biome = 2;
            }
        } else {
            options[2] = "Third";
        }

        if (selected == 3) {
            options[selected] = " > " + "Fourth" + " < ";
            if (key.select) {
                biome = 3;
            }
        } else {
            options[3] = "Fourth";
        }

        if (selected == 4) {
            options[selected] = " > " + "Random" + " < ";
            if (key.select) {
                biome = random.nextInt(3);
            }
        } else {
            options[4] = "Random";
        }

        if (key.exit) Game.menu = new MainMenu(key);
        if (key.select && timer == 0) Game.menu = null;
    }

    public void render(Screen screen) {
        screen.renderText("Select level map", 280 + 1, 80 + 1, 50, 1, 0xffffff);
        screen.renderText("Select level map", 280, 80, 50, 1, 0);
        for (int i = 0; i < options.length; i++) {
            screen.renderText(options[i], 350 + 3, 250 + i * 60 + 3, 50, 1, 0);
            screen.renderText(options[i], 350, 250 + i * 60, 50, 1, 0xffffff);
        }
    }
}