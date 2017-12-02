package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class Tile {
    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile flower = new FlowerTile(Sprite.flower);
    public static Tile rock = new RockTile(Sprite.rock);
    public static Tile hedge = new HedgeTile(Sprite.hedge);
    public static Tile sand = new SandTile(Sprite.sand);
    public static Tile floor = new FloorTile(Sprite.floor);
    public static Tile wall = new WallTile(Sprite.wall);
    public static Tile broken_ground = new BrokenGroundTile(Sprite.broken_ground);
    public static Tile lava = new LavaTile(Sprite.lava);
    public static Tile water = new WaterTile(Sprite.water);
    public static Tile ice = new IceTile(Sprite.ice);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public static final int GRASS_COLOR          = 0xFF00FF00;
    public static final int FLOWER_COLOR         = 0xFFFF00FF;
    public static final int ROCK_COLOR           = 0xFF7F7F7F;
    public static final int HEDGE_COLOR          = 0xFFFFC90E;
    public static final int SAND_COLOR           = 0xFFFFFF00;
    public static final int FLOOR_COLOR          = 0xFFB97A57;
    public static final int BROKEN_GROUND_COLOR  = 0xFF808040;
    public static final int WALL_COLOR           = 0xFFCACACA;
    public static final int LAVA_COLOR           = 0xFFFF0000;
    public static final int WATER_COLOR          = 0xFF0000FF;
    public static final int ICE_COLOR            = 0xFF99D9EA;
    //make more stuff like this

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) { }

    public boolean solid() {
        return false;
    }
}
