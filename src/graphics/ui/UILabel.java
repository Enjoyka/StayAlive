package graphics.ui;

import java.awt.Font;
import util.Vector2i;

import java.awt.*;

public class UILabel extends UIComponent {
    private final int TEXT_SHADOW_SIZE = 2;
    public boolean dropShadow = false;
    public String text;
    private Font font;

    public UILabel(Vector2i position, String text) {
        super(position);
        font = new Font("Arial", Font.BOLD, 24);
        this.text = text;
        color = new Color(0xFFFFFF);
    }

    public UILabel setFont(Font font) {
        this.font = font;
        return this;
    }

    public void render(Graphics g) {
        if (dropShadow) {
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString(text, position.x + offset.x + TEXT_SHADOW_SIZE, position.y + offset.y + TEXT_SHADOW_SIZE);
        }
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, position.x + offset.x, position.y + offset.y);
    }
}
