import java.awt.*;
import java.util.*;
import java.awt.Color;
import java.awt.geom.*;
import javax.swing.*;

public class CircularTracksShape implements Moveable {
  private int xx;
  private int yy;
  private int width;

  int n;

  // variables for the position of each train
  private int[] x = new int[20]; // at most 20 trains
  private int[] y = new int[20];

  // variables for the track limits (the center of the track on the right, bottom,
  // left, and top, based on 40 pixel track width)
  int[] xmax = new int[20];
  int[] ymax = new int[20];
  int[] xmin = new int[20];
  int[] ymin = new int[20];
  int[] angles = new int[20];
  int[] radius = new int[20];

  // colors for tracks (hexadecimal format)
  Color[] cc = { Color.decode("#ADFFFA"), Color.decode("#FFADE1"), Color.decode("#F2FFAD"), Color.decode("#FFE0AD"),
      Color.decode("#ADB4FF"), Color.decode("#ADFFB0"), Color.decode("#FFA998"), Color.decode("#A9FA6D"),
      Color.decode("#ADFFFA"), Color.decode("#FFADE1"), Color.decode("#F2FFAD"), Color.decode("#FFE0AD"),
      Color.decode("#ADB4FF"), Color.decode("#ADFFB0"), Color.decode("#FFA998"), Color.decode("#E9FA6D") };

  // constructor
  public CircularTracksShape(int x0, int y0, int w) {
    xx = x0;
    yy = y0;
    width = w;
    n = width / 40; // number of tracks, each 40 pixels wide
    // trackLimits
    // for train on track i, give coordinates of the center of the track (and of the
    // train)
    // when train is on right, left, top, or bottom section of track i

    for (int i = 0; i < n; i++) {
      radius[i] = (width / 2) - 20 * i;
      // System.out.println(radius[i]);
      xmax[i] = width - 20 - 20 * i; // x-coordinate center of train on right
      xmin[i] = 20 * i; // x-coordinate of center on left
      ymax[i] = xmax[i]; // y-coordinate of center at bottom
      ymin[i] = xmin[i]; // y-coordinate of center at top
    }

    // each train starts from the lower left corner of its track
    for (int i = 0; i < n; i++) {
      angles[i] = (int) (Math.random() * 361);
    }

  }// end constructor

  // implementation of Moveable

  public void translate(int dx, int dy) {
    double angle = Math.toRadians(dx);
    for (int i = 0; i < n; i++) {
      angles[i] += dx;
    }
    // double pdelx = ; // horizontal increment or decrement (depending on current
    // position)
    double pdely = Math.sin(angle); // vertical displacement

    double ndelx = Math.cos(-angle); // horizontal increment or decrement (depending on current position)
    double ndely = Math.sin(-angle); // vertical displacement

    // position clockwise trains
    for (int i = 0; i <= n - 1; i = i + 2) {
      x[i] = (int) (300 + Math.cos(Math.toRadians(angles[i])) * radius[i]);
      y[i] = (int) (300 + Math.sin(Math.toRadians(angles[i])) * radius[i]);

      // // compute next position
      // if ((y[i] > ymin[i]) && (x[i] <= xmin[i])) { // train is on the left edge, so
      // move up (decrease y)
      // y[i] = y[i] - dely;
      // } else if ((y[i] <= ymin[i]) && (x[i] < xmax[i])) { // train is on top edge,
      // move to right (increase x)
      // x[i] = x[i] + delx;
      // } else if ((x[i] >= xmax[i]) && (y[i] < ymax[i])) { // train is on right, so
      // move down (increase y)
      // y[i] = y[i] + dely;
      // } else {
      // x[i] = x[i] - delx; // train is on bottom of track, move left
      // }
    } // for

    // position counter-clockwise trains
    for (int i = 1; i <= n - 1; i = i + 2) {
      x[i] = (int) (300 + Math.cos(Math.toRadians(-angles[i])) * radius[i]);
      y[i] = (int) (300 + Math.sin(Math.toRadians(-angles[i])) * radius[i]);
      // if ((y[i] >= ymax[i]) && (x[i] < xmax[i])) {
      // x[i] = x[i] + 1;
      // } else if ((x[i] >= xmax[i]) && (y[i] > ymin[i])) {
      // y[i] = y[i] - 1;
      // } else if ((y[i] <= ymin[i]) && (x[i] > xmin[i])) {
      // x[i] = x[i] - 1;
      // } else {
      // y[i] = y[i] + 1;
      // }
    } // for
  }

  public void draw(Graphics2D g2) {
    // paint tracks via superimposed shrinking rectangles
    for (int i = 0; i <= n - 1; i++) {
      Ellipse2D.Double circle = new Ellipse2D.Double(20 * i, 20 * i, width - (40 * i), width - (40 * i));
      g2.setColor(cc[i]);
      g2.fill(circle);
    }

    Random gen = new Random();

    // paint trains
    for (int i = 0; i <= n - 1; i = i + 1) {
      Color c = new Color(Math.abs(gen.nextInt()) % 255, Math.abs(gen.nextInt()) % 255, Math.abs(gen.nextInt()) % 255);
      g2.setColor(c);
      Ellipse2D.Double ball = new Ellipse2D.Double(x[i], y[i], 20, 20);
      g2.fill(ball);
      g2.draw(ball);
    }

  } // end draw

}