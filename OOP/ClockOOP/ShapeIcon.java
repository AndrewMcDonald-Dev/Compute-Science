import java.awt.*;
import javax.swing.*;

class ShapeIcon implements Icon {
    private int width;
    private int height;
    private Moveable shape;

    public ShapeIcon(Moveable s, int w, int h) {
        shape = s;
        width = w;
        height = h;
    }

    @Override
    public int getIconHeight() {
        return height;
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        shape.draw(g2);

    }
}