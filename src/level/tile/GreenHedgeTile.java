package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class GreenHedgeTile extends Tile {
    public GreenHedgeTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }
}
