package graphics.ui;

import util.Vector2i;

import java.awt.*;

public class UIComponent {
    protected Vector2i offset, size;
    public Vector2i position;
    public Color color;
    public boolean active = true;
    protected UIPanel panel;

    public UIComponent(Vector2i position) {
        this.position = position;
        offset = new Vector2i();
    }

    public UIComponent(Vector2i position, Vector2i size) {
        this.position = position;
        this.size = size;
        offset = new Vector2i();
    }

    void init(UIPanel panel) {
        this.panel = panel;
    }

    public UIComponent setColor(int color) {
        this.color = new Color(color);
        return this;
    }

    public void update() {
    }

    public void render(Graphics g) {
    }

    public Vector2i getAbsolutePosition() {
        return new Vector2i(position).add(offset);
    }

    void setOffset(Vector2i offset) {
        this.offset = offset;
    }
}
