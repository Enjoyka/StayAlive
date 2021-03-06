package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class SpriteSheet {
    private  String path;

    private int width, height;

    public final int SIZE;
    public final int SPRITE_WIDTH, SPRITE_HEIGHT;
    public int[] pixels;

    public static SpriteSheet tiles = new SpriteSheet("D:\\Work\\IdeaProjects\\StayAlive\\res\\textures\\sheets\\sprites\\spritesheet.png", 256);
    public static SpriteSheet projectile_wizard = new SpriteSheet("D:\\Work\\IdeaProjects\\StayAlive\\res\\textures\\sheets\\projectiles\\wizard.png", 48);

    public static SpriteSheet player = new SpriteSheet("D:\\Work\\IdeaProjects\\StayAlive\\res\\textures\\sheets\\mobs\\playersheet.png", 64, 64);
    public static SpriteSheet player_up = new SpriteSheet(player, 0, 0, 1, 4, 16);
    public static SpriteSheet player_right = new SpriteSheet(player, 1, 0, 1, 4, 16);
    public static SpriteSheet player_down = new SpriteSheet(player, 2, 0, 1, 4, 16);
    public static SpriteSheet player_left = new SpriteSheet(player, 3, 0, 1, 4, 16);

    public static SpriteSheet dummy = new SpriteSheet("D:\\Work\\IdeaProjects\\StayAlive\\res\\textures\\sheets\\mobs\\dummysheet.png", 64, 64);
    public static SpriteSheet dummy_up = new SpriteSheet(dummy, 0, 0, 1, 4, 16);
    public static SpriteSheet dummy_right = new SpriteSheet(dummy, 1, 0, 1, 4, 16);
    public static SpriteSheet dummy_down = new SpriteSheet(dummy, 2, 0, 1, 4, 16);
    public static SpriteSheet dummy_left = new SpriteSheet(dummy, 3, 0, 1, 4, 16);

    public static SpriteSheet shooter = new SpriteSheet("D:\\Work\\IdeaProjects\\StayAlive\\res\\textures\\sheets\\mobs\\ufo_sheet.png", 64, 64);
    public static SpriteSheet shooter_up = new SpriteSheet(shooter, 0, 0, 1, 4, 16);
    public static SpriteSheet shooter_right = new SpriteSheet(shooter, 1, 0, 1, 4, 16);
    public static SpriteSheet shooter_down = new SpriteSheet(shooter, 2, 0, 1, 4, 16);
    public static SpriteSheet shooter_left = new SpriteSheet(shooter, 3, 0, 1, 4, 16);

    public static SpriteSheet chaser = new SpriteSheet("D:\\Work\\IdeaProjects\\StayAlive\\res\\textures\\sheets\\mobs\\phantom_sheet.png", 64, 64);
    public static SpriteSheet chaser_up = new SpriteSheet(chaser, 0, 0, 1, 4, 16);
    public static SpriteSheet chaser_right = new SpriteSheet(chaser, 1, 0, 1, 4, 16);
    public static SpriteSheet chaser_down = new SpriteSheet(chaser, 2, 0, 1, 4, 16);
    public static SpriteSheet chaser_left = new SpriteSheet(chaser, 3, 0, 1, 4, 16);

    public static SpriteSheet star = new SpriteSheet("D:\\Work\\IdeaProjects\\StayAlive\\res\\textures\\sheets\\mobs\\gish_sheet.png", 64, 64);
    public static SpriteSheet star_up = new SpriteSheet(star, 0, 0, 1, 4, 16);
    public static SpriteSheet star_right = new SpriteSheet(star, 1, 0, 1, 4, 16);
    public static SpriteSheet star_down = new SpriteSheet(star, 2, 0, 1, 4, 16);
    public static SpriteSheet star_left = new SpriteSheet(star, 3, 0, 1, 4, 16);

    private Sprite[] sprites;

    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
        int xx = x * spriteSize;
        int yy = y * spriteSize;
        int w = width * spriteSize;
        int h = height * spriteSize;
        if (width == height)
            SIZE = width;
        else
            SIZE = -1;

        SPRITE_WIDTH = w;
        SPRITE_HEIGHT = h;
        pixels = new int[w * h];
        for (int y0 = 0; y0 < h; y0++) {
            int yp = yy + y0;
            for (int x0 = 0; x0 < w; x0++) {
                int xp = xx + x0;
                pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.SPRITE_WIDTH];
            }
        }
        int frame = 0;
        sprites = new Sprite[width * height];
        for (int ya = 0; ya < height; ya++) {
            for (int xa = 0; xa < width; xa++) {
                int[] spritePixels = new int[spriteSize * spriteSize];
                for (int y0 = 0; y0 < spriteSize; y0++) {
                    for (int x0 = 0; x0 < spriteSize; x0++) {
                        spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * SPRITE_WIDTH];
                    }
                }
                Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
                sprites[frame++] = sprite;
            }
        }
    }

    public SpriteSheet(String path, int size) {
        this.path = path;
        this.SIZE = size;
        SPRITE_WIDTH = size;
        SPRITE_HEIGHT = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    public SpriteSheet(String path, int width, int height) {
        this.path = path;
        SIZE = -1;
        SPRITE_WIDTH = width;
        SPRITE_HEIGHT = height;
        pixels = new int[SPRITE_WIDTH * SPRITE_HEIGHT];
        load();
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getPixels() {
        return pixels;
    }

    private void load() {
        try {
            System.out.print("Trying to load: " + path + "...");
            BufferedImage image = ImageIO.read(new FileInputStream(path));
            System.out.println(" Succeeded!");
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0 , width);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(" Failed!");
        }
    }
}
