package graphics;

public class AnimatedSprites extends Sprite {
    private Sprite sprite;

    private int frame = 0;
    private int rate = 0;
    private int lenght = -1;
    private int time = 0;

    public AnimatedSprites(SpriteSheet sheet, int width, int height, int lenght) {
        super(sheet, width, height);
        this.lenght = lenght;
        sprite = sheet.getSprites()[0];
        if (lenght > sheet.getSprites().length) System.err.println("Lenght of animation is too long");
    }

    public void update() {
        time++;
        if (time % lenght == 0) {
            if (frame >= lenght - 1)
                frame = 0;
            else
                frame++;
            sprite = sheet.getSprites()[frame];
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setFrameRate(int frames) {
        rate = frames;
    }

    public void setFrame(int index) {
        if (index > sheet.getSprites().length - 1) return;
        sprite = sheet.getSprites()[index];
    }
}