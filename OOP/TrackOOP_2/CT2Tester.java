import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CT2Tester {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.getContentPane().setBackground(Color.ORANGE);
    final Moveable shape = new CT2Shape(0, 0, 600);

    ShapeIcon icon = new ShapeIcon(shape, 600, 600);

    final JLabel label = new JLabel(icon);

    frame.setLayout(new FlowLayout());
    frame.add(label);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);

    final int DELAY = 50;
    Timer t = new Timer(DELAY, new ActionListener() {

      public void actionPerformed(ActionEvent event) {
        label.repaint();
        shape.translate(1, 1);

      }
    });
    t.start();
  }

}