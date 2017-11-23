package graphics.ui;

import graphics.Screen;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerUI {
    private List<UIPanel> panels = new ArrayList<UIPanel>();

    public ManagerUI() {

    }

    public void addPanel(UIPanel panel) {
        panels.add(panel);
    }

    public void update() {
        for (UIPanel panel : panels)
            panel.update();
    }

    public void render(Graphics g) {
        for (UIPanel panel : panels)
            panel.render(g);
    }
}
