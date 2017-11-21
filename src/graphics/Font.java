package graphics;

public class Font {
    private static SpriteSheet font = new SpriteSheet("D:\\Work\\IdeaProjects\\StayAlive\\res\\fonts\\font.png", 8); //default 16
    private static Sprite[] characters = Sprite.split(font);
    private static String charIndex = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!?";

    public Font() {

    }

    public void render(int x, int y, String text, Screen screen) {
        render(x, y, 0, 0, text, screen);
    }

    public void render(int x, int y, int col, String text, Screen screen) {
        render(x, y, 0, col, text, screen);
    }

    public void render(int x, int y, int spacing, int col, String text, Screen screen) {
        int xOffset = 0;
        int line = 0;
        for (int i = 0; i < text.toUpperCase().length(); i++) {
            xOffset += 8 + spacing;
            char currentChar = text.toUpperCase().charAt(i);
            int index = charIndex.indexOf(currentChar);
            if (currentChar == '\n') {
                xOffset = 0;
                line++;
            }
            if (index == -1) continue;
            screen.renderTextCharacter(x + xOffset, y + line * 16, characters[index], col, false);
        }
    }
}
