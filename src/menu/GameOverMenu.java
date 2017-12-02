package menu;

import game.Game;
import graphics.Screen;
import input.Keyboard;

public class GameOverMenu extends Menu {
    public GameOverMenu(Keyboard key) {
        super(key);
    }

    int timer = 10;
    int selected = 0;
    String[] options = { "Play Again?", "Main Menu" };

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
        if (selected > 1) selected = 1;
        if (selected == 0) {
            options[selected] = " > " + "Play Again?" + " < ";
            if (key.select) {
                key.releaseAll();
                PlayMenu.biome = random.nextInt(3);
            } else {
                options[0] = "Play Again?";
            }

            if (selected == 1) {
                options[selected] = " > " + "Main Menu" + " < ";
                if (key.select) {
                    Game.menu = new MainMenu(key);
                }
            } else {
                options[1] = "Main Menu";
            }
        }
    }

    public void render(Screen screen) {

    }
}
