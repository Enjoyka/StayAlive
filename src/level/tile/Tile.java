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

    public static final int GRASS_COLOR                 = 0xFF00FF00;
    public static final int GREEN_HEDGE_COLOR           = 0xFF072F18;//#072F18
    public static final int YELLOW_HEDGE_COLOR          = 0xFFFFFF00;//#C27D09
    public static final int GROUND_COLOR                = 0xFF2F2E22;//#2F2E22
    public static final int OWERGROW_GROUND_COLOR       = 0xFF544E42;//#544E42
    public static final int LIGHTER_BROKER_GROUND_COLOR = 0xFF404040;//#404040
    public static final int DARKER_BROKER_GROUND_COLOR  = 0xFF24231F;//#24231F
    public static final int FLOOR_COLOR                 = 0xFF4F2E13;//#4F2E13
    public static final int WOODEN_FLOOR_COLOR          = 0xFF2F0D02;//#2F0D02
    public static final int WHITE_FLOOR_COLOR           = 0xFFCFC9BE;//#CFC9BE
    public static final int RED_FLOOR_COLOR             = 0xFF6F0200;//#6F0200
    public static final int WALL_COLOR                  = 0xFF7F7F7F;//#262E31
    public static final int STONE_WALL_COLOR            = 0xFF232B2D;//#232B2D
    public static final int RED_BRICK_WALL_COLOR        = 0xFFCE4C52;//#CE4C52
    public static final int YELLOW_BRICK_WALL_COLOR     = 0xFFAF8820;//#AF8820
    public static final int WHITE_BRICK_WALL_COLOR      = 0xFFE2DFC7;//#E2DFC7
    public static final int BROWN_BRICK_WALL_COLOR      = 0xFF814C27;//#814C27
    public static final int SNOW_COLOR                  = 0xFF9AAAE0;//#9AAAE0
    public static final int WATER_COLOR                 = 0xFF88A5FF;//#88A5FF
    public static final int ICE_COLOR                   = 0xFFD2E3EE;//#D2E3EE

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) { }

    public boolean solid() {
        return false;
    }
}
