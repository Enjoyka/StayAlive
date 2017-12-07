package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class Tile {
    public Sprite sprite;

    public static Tile grass                 = new GrassTile(Sprite.grass);
    public static Tile green_hedge           = new GreenHedgeTile(Sprite.green_hedge);
    public static Tile yellow_hedge          = new YellowHedgeTile(Sprite.yellow_hedge);
    public static Tile ground                = new GroundTile(Sprite.ground);
    public static Tile owergrown_ground      = new OwergrownGroundTile(Sprite.owergrown_ground);
    public static Tile lighter_broken_ground = new LighterBrokenTile(Sprite.lighter_broken_ground);
    public static Tile darker_broken_ground  = new DarkerBrokenTile(Sprite.darker_broken_ground);
    public static Tile floor                 = new FloorTile(Sprite.floor);
    public static Tile wooden_floor          = new WoodenFloorTile(Sprite.wooden_floor);
    public static Tile white_floor           = new WhiteFloorTile(Sprite.white_floor);
    public static Tile red_floor             = new RedFloorTile(Sprite.red_floor);
    public static Tile wall                  = new WallTile(Sprite.wall);
    public static Tile stone_wall            = new StoneWallTile(Sprite.stone_wall);
    public static Tile red_brick_wall        = new RedBrickWallTile(Sprite.red_brick_wall);
    public static Tile yellow_brick_wall     = new YellowBrickWallTile(Sprite.yellow_brick_wall);
    public static Tile white_brick_wall      = new WhiteBrickWallTile(Sprite.white_brick_wall);
    public static Tile brown_brick_wall      = new BrownBrickWallTile(Sprite.brown_brick_wall);
    public static Tile snow                  = new SnowTile(Sprite.snow);
    public static Tile water                 = new WaterTile(Sprite.water);
    public static Tile ice                   = new IceTile(Sprite.ice);
    public static Tile voidTile              = new VoidTile(Sprite.voidSprite);

    // remake colors and mb repaint level
    public static final int GRASS_COLOR                 = 0xFFFF0000;
    public static final int GROUND_COLOR                = 0xFF7F4000;//#2F2E22
    public static final int GREEN_HEDGE_COLOR           = 0xFF7F7F00;//#072F18 // brown +-
//    public static final int GREEN_HEDGE_COLOR           = 0xFFFFFF00;//#072F18 // brown +-
    public static final int RED_BRICK_WALL_COLOR        = 0xFFFF7F00;//#CE4C52
    public static final int FLOOR_COLOR                 = 0xFF00FFFF;//#4F2E13
    public static final int WALL_COLOR                  = 0xFFCACACA;//#262E31
    public static final int STONE_WALL_COLOR            = 0xFF7F7F7F;//#232B2D
    public static final int YELLOW_HEDGE_COLOR          = 0xFFFF00FF;//#C27D09
    public static final int WHITE_BRICK_WALL_COLOR      = 0xFFFF;//#E2DFC7

    public static final int OWERGROW_GROUND_COLOR       = 0xFFFF;//#544E42
    public static final int LIGHTER_BROKER_GROUND_COLOR = 0xFFFF;//#404040
    public static final int DARKER_BROKER_GROUND_COLOR  = 0xFFFF;//#24231F
    public static final int WOODEN_FLOOR_COLOR          = 0xFFFF;//#2F0D02
    public static final int WHITE_FLOOR_COLOR           = 0xFFFF;//#CFC9BE
    public static final int RED_FLOOR_COLOR             = 0xFFFF;//#6F0200
    public static final int YELLOW_BRICK_WALL_COLOR     = 0xFFFF;//#AF8820
    public static final int BROWN_BRICK_WALL_COLOR      = 0xFFFF;//#814C27
    public static final int SNOW_COLOR                  = 0xFFFF;//#9AAAE0
    public static final int WATER_COLOR                 = 0xFFFF;//#88A5FF
    public static final int ICE_COLOR                   = 0xFFFF;//#D2E3EE

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) { }

    public boolean solid() {
        return false;
    }
}
