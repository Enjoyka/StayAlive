package menu;

import game.Game;
import graphics.Screen;
import input.Keyboard;

public class HelpMenu extends Menu {
    public HelpMenu(Keyboard key) {
        super(key);
    }

    int timer = 18;
    int selected = 0;
    String options = " > Back < ";

    public void update() {
        if (timer > 0) timer--;

        if (selected == 0 && key.select && timer == 0) {
            key .releaseAll();
            Game.menu = new MainMenu(key);
        }
        if (key.exit) {
            Game.menu = new MainMenu(key);
        }
    }

    public void render(Screen screen) {
        String[] text = { "Hey what is up guys!My name is Lorex and Welcome to a game \"Stay Alive\"!" +
                            "The game has quite a simple concept. " +
                            "Use the arrow keys or WASD keys to move you character."};
        for (int i = 0; i < text.length; i++) {
            screen.renderText(text[i], 10 + 2, 30 + i * 30 + 2, 24, 0, 0);
            screen.renderText(text[i], 10, 30 + i * 30, 24, 0, 0xffffff);
        }
        screen.renderText(options, 350 + 3, 515 + 3, 50, 1, 0);
        screen.renderText(options, 350, 515, 50, 1, 0xffffff);
    }
}
