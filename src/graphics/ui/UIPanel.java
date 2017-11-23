package graphics.ui;

import graphics.Screen;
import graphics.Sprite;
import util.Vector2i;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIPanel {
    private List<UIComponent> components = new ArrayList<UIComponent>();
    private Vector2i position, size;
    private Color color;

    public UIPanel(Vector2i position, Vector2i size) {
        this.position = position;
        this.size = size;
        color = new Color(0x7F7F7F);
    }

    public void addComponent(UIComponent component) {
        components.add(component);
    }

    public void update() {
        for (UIComponent component : components) {
            component.setOffset(position);
            component.update();
        }
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(position.x , position.y, size.x, size.y);
        for (UIComponent component : components)
            component.render(g);
    }
}
