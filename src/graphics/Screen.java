package graphics;


import entity.mob.Chaser;
import entity.mob.Mob;
import entity.projectile.Projectile;
import level.tile.Tile;

import java.util.Random;

public class Screen {
    public int width;
    public int height;
    public int xOffset, yOffset;
    public final int MAP_SIZE = 8;
    public final int MAP_SIZE_MASK = MAP_SIZE - 1;
    public int[] pixels;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
            tiles[i] = random.nextInt(0xffffff);
            tiles[0] = 0;
        }
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
        if (fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }

        for (int y = 0; y < sheet.HEIGHT; y++) {
            int ya = y + yp;
            for (int x = 0; x < sheet.WIDTH; x++) {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                pixels[xa + ya * width] = sheet.pixels[x + y * sheet.WIDTH];
            }
        }
    }

    public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
        if (fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }

        for (int y = 0; y < sprite.getHeight(); y++) {
            int ya = y + yp;
            for (int x = 0; x < sprite.getHeight(); x++) {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
            }
        }
    }

    public void renderTile(int xp, int yp, Tile tile) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int ya = y + yp;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = x + xp;
                if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }

    public void renderProjectile(int xp, int yp, Projectile p) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < p.getSpriteSize(); y++) {
            int ya = y + yp;
            for (int x = 0; x < p.getSpriteSize(); x++) {
                int xa = x + xp;
                if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int color = p.getSprite().pixels[x + y * p.getSprite().SIZE];
                if (color != 0xFFFFFFFF) pixels[xa + ya * width] = color;
            }
        }
    }

//    public void renderMob(int xp, int yp, Mob mob) {
//        xp -= xOffset;
//        yp -= yOffset;
//        for (int y = 0; y < 16; y++) {
//            int ya = y + yp;
//            for (int x = 0; x < 16; x++) {
//                int xa = x + xp;
//                if (xa < -16 || xa >= width || ya < 0 || ya >= height) break;
//                if (xa < 0) xa = 0;
//                int color = mob.getSprite().pixels[x + y * 16];
//                if ((mob instanceof Chaser) && color == 0xff5E0013) color = 0xffFF8000;
//                if (color != 0xFFFFFFFF) pixels[xa + ya * width] = color;
//            }
//        }
//    }

    public void renderMob(int xp, int yp, Sprite sprite) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < 16; y++) {
            int ya = y + yp;
            for (int x = 0; x < 16; x++) {
                int xa = x + xp;
                if (xa < -16 || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int color = sprite.pixels[x + y * 16];
                if (color != 0xFFFFFFFF) pixels[xa + ya * width] = color;
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
