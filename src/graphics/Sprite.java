package graphics;

public class Sprite {
    protected SpriteSheet sheet;

    private int width, height;
    private int x, y;

    public final int SIZE;
    public int[] pixels;

    public static Sprite voidSprite             = new Sprite(16, 0);

    //Spawn level here:
    public static Sprite grass                  = new Sprite(16, 0, 2, SpriteSheet.tiles);

    public static Sprite green_hedge            = new Sprite(16, 3, 0, SpriteSheet.tiles);
    public static Sprite yellow_hedge           = new Sprite(16, 3, 0, SpriteSheet.tiles);

    public static Sprite ground                 = new Sprite(16, 1, 2, SpriteSheet.tiles);
    public static Sprite owergrown_ground       = new Sprite(16, 1, 1, SpriteSheet.tiles);
    public static Sprite lighter_broken_ground  = new Sprite(16, 1, 0, SpriteSheet.tiles);
    public static Sprite darker_broken_ground   = new Sprite(16, 1, 3, SpriteSheet.tiles);

    public static Sprite floor                  = new Sprite(16, 2, 2, SpriteSheet.tiles);
    public static Sprite wooden_floor           = new Sprite(16, 2, 3, SpriteSheet.tiles);
    public static Sprite white_floor            = new Sprite(16, 2, 0, SpriteSheet.tiles);
    public static Sprite red_floor              = new Sprite(16, 2, 1, SpriteSheet.tiles);

    public static Sprite wall                   = new Sprite(16, 2, 3, SpriteSheet.tiles);
    public static Sprite stone_wall             = new Sprite(16, 2, 2, SpriteSheet.tiles);
    public static Sprite red_brick_wall         = new Sprite(16, 2, 0, SpriteSheet.tiles);
    public static Sprite yellow_brick_wall      = new Sprite(16, 2, 1, SpriteSheet.tiles);
    public static Sprite white_brick_wall       = new Sprite(16, 2, 5, SpriteSheet.tiles);
    public static Sprite brown_brick_wall       = new Sprite(16, 2, 4, SpriteSheet.tiles);

    public static Sprite snow                   = new Sprite(16, 5, 0, SpriteSheet.tiles);
    public static Sprite water                  = new Sprite(16, 5, 1, SpriteSheet.tiles);
    public static Sprite ice                    = new Sprite(16, 5, 2, SpriteSheet.tiles);

    //Player sprites here:
    public static Sprite player                 = new Sprite(16, 0, 12, SpriteSheet.tiles);

    // Mob sprites here:
    public static Sprite dummy                  = new Sprite(16, 0, 0, SpriteSheet.dummy_down);

    //Projectile sprites here:
    public static Sprite projectile_fireball    = new Sprite(16, 0, 0, SpriteSheet.projectile_wizard);
    public static Sprite projectile_arctic_burn = new Sprite(16, 1, 0, SpriteSheet.projectile_wizard);
    public static Sprite projectile_arrow       = new Sprite(16, 2, 0, SpriteSheet.projectile_wizard);

    //Particle sprites here:
    public static Sprite particle_normal        = new Sprite(3, 0xC3C3C3);
    //for fireball - FF0000 or FF7F27; arctic burn - 3F48CC or 00A2E8; arrow - C3C3C3 or 7F7F7F

    protected Sprite(SpriteSheet sheet, int width, int height) {
        SIZE = (width == height) ? width : -1;
        this.width = width;
        this.height = height;
        this.sheet = sheet;
    }

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }

    public Sprite(int width, int height, int colour) {
        SIZE = -1;
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        setColour(colour);
    }

    public Sprite(int size, int colour) {
        SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int[SIZE * SIZE];
        setColour(colour);
    }

    public Sprite (int[] pixels, int width, int height) {
        SIZE = (width == height) ? width : -1;
        this.width = width;
        this.height = height;
        this.pixels = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++)
            this.pixels[i] = pixels[i];
    }

    public static Sprite rotate(Sprite sprite, double angle) {
        return new Sprite(rotate(sprite.pixels, sprite.width, sprite.height, angle), sprite.width, sprite.height);
    }

    private static int[] rotate(int[] pixels, int width, int height, double angle) {
        int[] result = new int[width * height];

        double nx_x = rotationX(-angle, 1.0, 0.0);
        double nx_y = rotationY(-angle, 1.0, 0.0);
        double ny_x = rotationX(-angle, 0.0, 1.0);
        double ny_y = rotationY(-angle, 0.0, 1.0);

        double x0 = rotationX(-angle, -width / 2.0, -height / 2.0) + width / 2.0;
        double y0 = rotationY(-angle, -width / 2.0, -height / 2.0) + height / 2.0;

        for (int y = 0; y < height; y++) {
            double x1 = x0;
            double y1 = y0;
            for (int x = 0; x < width; x++) {
                int xx = (int) x1;
                int yy = (int) y1;
                int color = 0;
                if (xx < 0 || xx >= width || yy < 0 || yy >= height)
                    color = 0xFFFFFFFF;
                else
                    color = pixels[xx + yy * width];
                result[x + y * width] = color;
                x1 += nx_x;
                y1 += nx_y;
            }
            x0 += ny_x;
            y0 += ny_y;
        }
        return result;
    }

    private static double rotationX(double angle, double x, double y) {
        double cos = Math.cos(angle - Math.PI / 2);
        double sin = Math.sin(angle - Math.PI / 2);
        return x * cos + y * -sin;
    }

    private static double rotationY(double angle, double x, double y) {
        double cos = Math.cos(angle - Math.PI / 2);
        double sin = Math.sin(angle - Math.PI / 2);
        return  x * sin + y * cos;
    }

    public static Sprite[] split(SpriteSheet sheet) {
        int amount = (sheet.getWidth() * sheet.getHeight()) / (sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT);
        Sprite[] sprites = new Sprite[amount];
        int current = 0;
        int[] pixels = new int[sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT];
        for (int yp = 0; yp < sheet.getHeight() / sheet.SPRITE_HEIGHT; yp++) {
            for (int xp = 0; xp < sheet.getWidth() / sheet.SPRITE_WIDTH; xp++) {
                for (int y = 0; y < sheet.SPRITE_HEIGHT; y++) {
                    for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {
                        int xo = x + xp * sheet.SPRITE_WIDTH; // xo - xoffset
                        int yo = y + yp * sheet.SPRITE_HEIGHT;
                        pixels[x + y * sheet.SPRITE_WIDTH] = sheet.getPixels()[xo + yo * sheet.getWidth()];
                    }
                }
                sprites[current++] = new Sprite(pixels, sheet.SPRITE_WIDTH, sheet.SPRITE_HEIGHT);
            }
        }
        return sprites;
    }

    public Sprite (int[] pixels, int size) {
        SIZE = width = height = size;
        this.pixels = pixels;
    }

    private void setColour(int colour) {
        for (int i = 0; i < width * height; i++)
            pixels[i] = colour;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void load() {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SPRITE_WIDTH];
    }
}