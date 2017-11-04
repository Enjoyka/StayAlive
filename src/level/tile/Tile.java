package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class Tile {
    public int x, y;
    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile flower = new FlowerTile(Sprite.flower);
    public static Tile rock = new RockTile(Sprite.rock);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public static final int GRASS_COLOR  = 0xFF00FF00;
    public static final int FLOWER_COLOR = 0xFFFFFF00;
    public static final int ROCK_COLOR   = 0xFF7F7F7F;
    public static final int WATER_COLOR  = 0xFF7F7F7F; //unused
    //make more stuff like this

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) { }

    public boolean solid() {
        return false;
    }
}
