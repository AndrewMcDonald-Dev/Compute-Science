import javax.swing.*;

class CirclesTester extends JFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		final Moveable shape = new CircleShape(0, 0, 200);

		ShapeIcon icon = new ShapeIcon(shape, 400, 400);

		final JLabel label = new JLabel(icon);

		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		label.repaint();
	}
}