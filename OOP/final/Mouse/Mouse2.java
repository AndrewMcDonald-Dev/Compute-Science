import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import java.util.*;

class Mouse2 extends JFrame implements MouseListener, MouseMotionListener {

	private static final int W = 600;
	private static final int H = 600;
	private static final int x0 = 150;
	private static final int y0 = 250;
	private static final int doubleClick = 2;

	private int last_x;// previous mouse coordinates
	private int last_y;
	private double rho = 0;// angle of rotation of rose about center

	public static void main(String[] args) {

		Mouse2 frame = new Mouse2();
		frame.setVisible(true);
	}

	public Mouse2() {
		// establish the frame
		setTitle("TrackMouse");
		setSize(W, H);
		setResizable(false);
		setLocation(x0, y0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		last_x = 0;
		last_y = 0;
		// mouse event handlers for mouse events on this frame
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	// required MouseListener methods
	public void mouseClicked(MouseEvent event) {
			int x, y;
			x = event.getX();
			y = event.getY();
			System.out.println("(" + x + "," + y + ")");

			Graphics g = getGraphics();
			Graphics2D g2 = (Graphics2D) g;
			Rectangle2D.Double rect1 = new Rectangle2D.Double(0, 0, 300, 300);
			Rectangle2D.Double rect2= new Rectangle2D.Double(0, 300, 300, 600);
			Rectangle2D.Double rect3 = new Rectangle2D.Double(300, 0, 600, 300);
			Rectangle2D.Double rect4 = new Rectangle2D.Double(300, 300, 600, 600);

			if(x < 300){
				if(y < 300){
					setAllColors(rect1, rect2, rect3, rect4, g2);
				}else{
					setAllColors(rect2, rect1, rect3, rect4, g2);
				}
			}else{
				if(y < 300){
					setAllColors(rect3, rect1, rect2, rect4, g2);
				}else{
					setAllColors(rect4, rect1, rect2, rect3, g2);
				}
			}
	}

	public void setAllColors(Shape re1, Shape re2, Shape re3, Shape re4, Graphics2D g2){
		g2.setColor(Color.blue);
		g2.fill(re1);
		g2.setColor(Color.red);
		g2.fill(re2);
		g2.fill(re3);
		g2.fill(re4);
	}

	// methods implemented with empty bodies
	public void mouseEntered(MouseEvent event) {
	}

	public void mouseExited(MouseEvent event) {
	}

	public void mousePressed(MouseEvent event) {
	}

	public void mouseReleased(MouseEvent event) {
	}

	// required MouseMotionListener methods
	public void mouseDragged(MouseEvent event) { // display the path the mouse is dragged along
		int xx = event.getX();
		int yy = event.getY();
		int diameter = 0;
		if (!event.isMetaDown()) { // don't process the right button drag
			Graphics g = getGraphics();
			Graphics2D g2 = (Graphics2D) g;
			Rectangle2D.Double rect1 = new Rectangle2D.Double(0, 0, 600, 600);
			diameter = (int)(Math.sqrt(Math.pow(xx - 300, 2) + Math.pow(yy - 300, 2)));
			int radius = diameter/2;
			Ellipse2D.Double ball2 = new Ellipse2D.Double(300 - radius, 300 - radius, diameter, diameter);


			g2.setColor(Color.yellow);
			g2.fill(rect1);
			g2.setColor(Color.black);
			g2.fill(ball2);
			// g2.draw(ball2);

		} // end draw

	}

	public void mouseMoved(MouseEvent event) {
	}

}
