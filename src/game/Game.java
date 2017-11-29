package game;

import entity.mob.Player;
import graphics.Screen;
import graphics.ui.ManagerUI;
import input.Keyboard;
import input.Mouse;
import level.Level;
import level.SpawnLevel;
import level.TileCoordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    private static int width = 300;
    private static int height = width / 16 * 9;
    private static int scale = 3;
    private static String TITLE = "Stay Alive";
    private static ImageIcon icon;
    private static ManagerUI managerUI;

    private Thread thread;
    private JFrame frame;
    private Keyboard key;
    private Level level;
    private Player player;
    private Screen screen;

    private boolean running = false;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        icon = new ImageIcon("D:\\Work\\IdeaProjects\\StayAlive\\res\\icons\\icon.png");
        screen = new Screen(width, height);
        managerUI = new ManagerUI();
        frame = new JFrame();
        key = new Keyboard();
        level = new SpawnLevel("D:\\Work\\IdeaProjects\\StayAlive\\res\\levels\\level.png");
        TileCoordinate playerSpawn = new TileCoordinate(10, 10);
        player = new Player("Player", playerSpawn.x(), playerSpawn.y(), key);
        level.add(player);

        Mouse mouse = new Mouse();
        addKeyListener(key);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public static int getWindowWidth() {
        return width * scale;
    }

    public static int getWindowHeight() {
        return height * scale;
    }

    public static ManagerUI getManagerUI() {
        return managerUI;
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1_000_000_000.0 / 60;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle(TITLE + " | " + updates + " updates, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    public void update() {
        key.update();
        level.update();
        managerUI.update();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        double xScroll = player.getX() - screen.width / 2;
        double yScroll = player.getY() - screen.height / 2;
        level.render((int) xScroll, (int) yScroll, screen);

        for (int i = 0; i < pixels.length; i++)
            pixels[i] = screen.pixels[i];

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        managerUI.render(g);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setIconImage(icon.getImage());
        game.frame.setTitle(Game.TITLE);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setResizable(false);
        game.frame.setVisible(true);

        game.start();
    }
}