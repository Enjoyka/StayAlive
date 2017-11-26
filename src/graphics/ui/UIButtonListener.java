package graphics.ui;

public class UIButtonListener {
    //pick better colors for all UI
    public void entered(UIButton button) {
        button.setColor(0x2A2A2A);
    }

    public void exited(UIButton button) {
        button.setColor(0xCACACA);
    }

    public void pressed(UIButton button) {
        button.setColor(0x4B4B4B);
    }

    public void released(UIButton button) {
        button.setColor(0xCACACA);
    }
}
