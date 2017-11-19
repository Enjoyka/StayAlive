package level;

import entity.mob.Chaser;
import entity.mob.Dummy;
import entity.mob.Star;
import level.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class SpawnLevel extends Level {
    public SpawnLevel(String path) {
        super(path);
    }

    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(path));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            tiles = new int[w * h];
            image.getRGB(0, 0, w, h, tiles, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception! Could not load Level file!");
        }

        add(new Dummy(3, 3));
        add(new Chaser(4, 4));
        add(new Star(5,5));
    }

    protected void generateLevel() {
    }
}
