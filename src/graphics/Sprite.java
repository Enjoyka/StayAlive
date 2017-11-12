package graphics;

public class Sprite {
    public final int SIZE;
    private int width, height;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    public static Sprite voidSprite = new Sprite(16, 0);

    //Spawn level here:
    public static Sprite grass = new Sprite(16, 0, 1, SpriteSheet.tiles);
    public static Sprite flower = new Sprite(16, 1, 1, SpriteSheet.tiles);
    public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
    //mb make more sprites

    //Player sprites here:
    public static Sprite player_forward = new Sprite(16, 0, 12, SpriteSheet.tiles);
    public static Sprite player_right = new Sprite(16, 1, 12, SpriteSheet.tiles);
    public static Sprite player_back = new Sprite(16, 2, 12, SpriteSheet.tiles);
    public static Sprite player_left = new Sprite(16, 3, 12, SpriteSheet.tiles);

    public static Sprite player_forward1 = new Sprite(16, 0, 13, SpriteSheet.tiles);
    public static Sprite player_forward2 = new Sprite(16, 0, 15, SpriteSheet.tiles);

    public static Sprite player_right1 = new Sprite(16, 1, 13, SpriteSheet.tiles);
    public static Sprite player_right2 = new Sprite(16, 1, 15, SpriteSheet.tiles);

    public static Sprite player_back1 = new Sprite(16, 2, 13, SpriteSheet.tiles);
    public static Sprite player_back2 = new Sprite(16, 2, 15, SpriteSheet.tiles);

    public static Sprite player_left1 = new Sprite(16, 3, 13, SpriteSheet.tiles);
    public static Sprite player_left2 = new Sprite(16, 3, 15, SpriteSheet.tiles);

    //Projectile sprites here:
    public static Sprite projectile_wizard = new Sprite(16, 0, 0, SpriteSheet.projectile_wizard);

    //Particle sprites here:
    public static Sprite particle_normal = new Sprite(3, 0x00A2E8);

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

    private void setColour(int colour) {
        for (int i = 0; i < width * height; i++) {
            pixels[i] = colour;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }
}
