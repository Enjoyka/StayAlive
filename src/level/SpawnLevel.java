package level;

import entity.mob.Chaser;
import entity.mob.Dummy;
import entity.mob.Shooter;
import entity.mob.Star;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class SpawnLevel extends Level {
    private final int DUMMY_NUMBER = 50;
    private final int SHOOTER_NUMBER = 50;
    private final int CHASER_NUMBER = 1;
    private final int STAR_NUMBER = 1;
    private Random random;

    public SpawnLevel(String path) {
        super(path);
    }

    protected void loadLevel(String path) {
        random = new Random();

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
        for (int i = 0; i < DUMMY_NUMBER; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            while (this.getTile(x, y).solid()) {
                x = random.nextInt(width);
                y = random.nextInt(height);
            }
            add(new Dummy(x, y));
        }

        for (int i = 0; i < SHOOTER_NUMBER; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            while (this.getTile(x, y).solid()) {
                x = random.nextInt(width);
                y = random.nextInt(height);
            }
            add(new Shooter(x, y));
        }
    }

    protected void generateLevel() {
    }
}
