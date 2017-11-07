package level;

import entity.Entity;
import entity.projectile.Projectile;
import graphics.Screen;
import level.tile.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level {
    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;
    protected int tile_size;

    private List<Entity> entities = new ArrayList<Entity>();
    private List<Projectile> projectiles = new ArrayList<Projectile>();

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
        generateLevel();
    }

    protected void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                getTile(x, y);
            }
        }
        tile_size = 16;
    }

    protected void loadLevel(String path) { }

    public void update() {
        for (int i = 0; i < entities.size(); i++ )
            entities.get(i).update();

        for (int i = 0; i < projectiles.size(); i++ )
            projectiles.get(i).update();
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    private void time() {
    }

    public boolean tileCollision(double x, double y, double xa, double ya, int size) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = (((int) x + (int) xa) + c % 2 * size / 8 + 8) / 16;
            int yt = (((int) y + (int) ya) + c / 2 * size / 8 + 8) / 16;
            System.out.println("( " + xt + ", " + yt + " )");
            if (getTile(xt, yt).solid()) solid = true;
        }
        return solid;
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
        for (int i = 0; i < entities.size(); i++ )
            entities.get(i).render(screen);

        for (int i = 0; i < projectiles.size(); i++ )
            projectiles.get(i).render(screen);
    }

    public void add(Entity e) {
        entities.add(e);
    }

    public void addProjectile(Projectile p) {
        p.init(this);
        projectiles.add(p);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        if (tiles[x + y * width] == Tile.GRASS_COLOR) return Tile.grass;
        if (tiles[x + y * width] == Tile.FLOWER_COLOR) return Tile.flower;
        if (tiles[x + y * width] == Tile.ROCK_COLOR) return Tile.rock;
        return Tile.voidTile;
    }
}
