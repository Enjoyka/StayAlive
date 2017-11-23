package graphics.ui;

import java.awt.Font;
import util.Vector2i;

import java.awt.*;

public class UILabel extends UIComponent {
    public String text;
    private Font font;

    public UILabel(Vector2i position, String text) {
        super(position);
        font = new Font("Consolas", Font.PLAIN, 16);
        this.text = text;
        color = new Color(0x00FF00);
    }

    public UILabel setFont(Font font) {
        this.font = font;
        return this;
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, position.x + offset.x, position.y);
    }
}
