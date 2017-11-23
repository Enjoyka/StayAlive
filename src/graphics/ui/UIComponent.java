package graphics.ui;

import graphics.Screen;
import util.Vector2i;

import java.awt.*;

public class UIComponent {
    public int backgroundColor;
    public Vector2i position, offset;
    public Color color;

    public UIComponent(Vector2i position) {
        this.position = position;
        offset = new Vector2i();
    }

    public UIComponent setColor(int color) {
        this.color = new Color(color);
        return this;
    }

    public void update() {

    }

    public void render(Graphics g) {

    }

    void setOffset(Vector2i offset) {
        this.offset = offset;
    }
}
