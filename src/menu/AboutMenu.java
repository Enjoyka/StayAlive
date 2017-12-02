package menu;

import game.Game;
import graphics.Screen;
import input.Keyboard;

public class AboutMenu extends Menu {
    public AboutMenu(Keyboard key) {
        super(key);
    }

    int timer = 18;
    int selected = 0;
    String options = " > Back < ";

    public void update() {
        if (timer > 0) timer--;

        if (selected == 0 && key.select && timer == 0) {
            key.releaseAll();
            Game.menu = new MainMenu(key);
        }
        if (key.exit) {
            Game.menu = new MainMenu(key);
        }
    }

    public void render(Screen screen) {
        String[] text = { "Ok. if you here. its obvious that you want to know how something about this game" +
                            "Stay Alive is a free game made by Ruslan Krishtal." +
                            "This game is upcoming clone Realm of the mad God with few new features" +
                            "Thanks for playing!" };
        for (int i = 0; i < text.length; i++) {
            screen.renderText(text[i], 20 + 2, 50 + i * 30 + 2, 24, 0, 0);
            screen.renderText(text[i], 20, 50 + i * 30, 24, 0, 0xffffff);
        }
        screen.renderText(options, 350 + 3, 500 + 3, 50, 1, 0);
        screen.renderText(options, 350, 500, 50, 1, 0xffffff);
    }
}
