package level;

import java.util.Random;

public class RandomLevel extends Level {
    private static final Random random = new Random();

    public RandomLevel(int width, int height) {
        super(width, height);
    }

    protected void generateLevel() { }
}
