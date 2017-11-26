package graphics.ui;

import input.Mouse;
import util.Vector2i;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class UIButton extends UIComponent {
    public UILabel label;
    private UIButtonListener buttonListener;
    private UIActionListener actionListener;

    private Image image;

    private boolean inside = false;
    private boolean pressed = false;
    private boolean ignorePressed = false;
    private boolean ignoreAction = false;

    public UIButton(Vector2i position, Vector2i size, UIActionListener actionListener) {
        super(position, size);
        this.actionListener = actionListener;
        Vector2i labelPosition = new Vector2i(position);
        labelPosition.x += 23;
        labelPosition.y += size.y - 5;
        label = new UILabel(labelPosition, "");
        label.setColor(0xFFFFFF);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.active = false;

        init();
    }

    public UIButton(Vector2i position, BufferedImage image, UIActionListener actionListener) {
        super(position, new Vector2i(image.getWidth(), image.getHeight()));
        this.actionListener = actionListener;
        setImage(image);
        init();
    }


    private void init() {
        setColor(0xbbbbbb);
        buttonListener = new UIButtonListener();
    }

    void init(UIPanel panel) {
        super.init(panel);
        if (label != null)
            panel.addComponent(label);
    }

    public void setButtonListener(UIButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setText(String text) {
        if (text == "")
            label.active = false;
        else
            label.text = text;
    }

    public void performAction() {
        actionListener.perform();
    }

    public void ignoreNextPress() {
        ignoreAction = true;
    }

    public void update() {
        Rectangle rect = new Rectangle(getAbsolutePosition().x, getAbsolutePosition().y, size.x, size.y);
        boolean leftMouseButtonDown = Mouse.getButton() == MouseEvent.BUTTON1;
        if (rect.contains(new Point(Mouse.getX(), Mouse.getY()))) {
            if (!inside) {
                if (leftMouseButtonDown) {
                    ignorePressed = true;
                } else {
                    ignorePressed = false;
                }
                buttonListener.entered(this);
            }
            inside = true;

            if (!pressed && !ignorePressed && leftMouseButtonDown) {
                buttonListener.pressed(this);
                pressed = true;
            } else if (Mouse.getButton() == MouseEvent.NOBUTTON) {
                if (pressed) {
                    buttonListener.released(this);
                    if (!ignoreAction)
                        actionListener.perform();
                    else
                        ignoreAction = false;
                    pressed = false;
                }
                ignorePressed = false;
            }
        } else {
            if (inside) {
                buttonListener.exited(this);
                pressed = false;
            }
            inside = false;
        }
    }

    public void render(Graphics g) {
        int x = position.x + offset.x;
        int y = position.y + offset.y;
        if (image != null) {
            g.drawImage(image, x, y, null);
        } else {
            g.setColor(color);
            g.fillRect(x, y, size.x, size.y);

            if (label != null)
                label.render(g);
        }
    }
}