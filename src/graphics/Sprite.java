package graphics;

public class Sprite {
    public final int SIZE;
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


    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }

    public Sprite(int size, int colour) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColour(colour);
    }

    private void setColour(int colour) {
        for (int i = 0; i < SIZE * SIZE; i++) {
            pixels[i] = colour;
        }
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }
}
