import java.awt.*;
import java.util.*;
import java.awt.Color;
import java.awt.geom.*;

public class CT2Shape implements Moveable {
  private int width;

  int n;

  // variables for the position of each train
  private int[] x = new int[20]; // at most 20 trains
  private int[] y = new int[20];
  private int[] x2 = new int[20];
  private int[] y2 = new int[20];

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
  public CT2Shape(int x0, int y0, int w) {
    width = w;
    n = width / 40; // number of tracks, each 40 pixels wide
    // trackLimits
    // for train on track i, give coordinates of the center of the track (and of the
    // train)
    // when train is on right, left, top, or bottom section of track i

    for (int i = 0; i < n; i++) {
      radius[i] = ((width / 2) - 20 * i) - 10;
      // System.out.println(radius[i]);
      xmax[i] = width - 20 - 20 * i; // x-coordinate center of train on right
      xmin[i] = 20 * i; // x-coordinate of center on left
      ymax[i] = xmax[i]; // y-coordinate of center at bottom
      ymin[i] = xmin[i]; // y-coordinate of center at top
    }

    // each train starts from the lower left corner of its track
    for (int i = 0; i < n; i++) {
      // angles[i] = (int) (Math.random() * 361);
      x[i] = xmin[i];
      y[i] = ymax[i];
      x2[i] = xmin[i];
      y2[i] = ymax[i];
    }

  }// end constructor

  // implementation of Moveable

  public void translate(int dx, int dy) {
    for (int i = 0; i < n; i++) {
      angles[i] += dx;
    }

    // position clockwise trains
    for (int i = 0; i <= n - 1; i = i + 2) {
      x[i] = (int) ((290 + Math.cos(Math.toRadians(.3 * angles[i] * ((angles.length - i)  + 1))) * radius[i]));
      y[i] = (int) ((290 + Math.sin(Math.toRadians(.3 * angles[i] * ((angles.length - i) + 1))) * radius[i]));
      x2[i] = (int) ((290 + Math.cos(Math.toRadians(.3 * (angles[i]) * ((angles.length - i) + 1)) + angleOffset(radius[i])) * radius[i]));
      y2[i] = (int) ((290 + Math.sin(Math.toRadians(.3 * (angles[i]) * ((angles.length - i) + 1)) + angleOffset(radius[i])) * radius[i]));
    } // for

    // position counter-clockwise trains
    for (int i = 1; i <= n - 1; i = i + 2) {
      x[i] = (int) (290 + Math.cos(Math.toRadians(-(angles[i]) * ((angles.length - i) + 1) *.3)) * radius[i]);
      y[i] = (int) (290 + Math.sin(Math.toRadians(-(angles[i]) * ((angles.length - i) + 1) *.3)) * radius[i]);
      x2[i] = (int) (290 + Math.cos(Math.toRadians(-(angles[i]) * ((angles.length - i) + 1) *.3) + angleOffset(radius[i])) * radius[i]);
      y2[i] = (int) (290 + Math.sin(Math.toRadians(-(angles[i]) * ((angles.length - i) + 1) *.3) + angleOffset(radius[i])) * radius[i]);
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
      Ellipse2D.Double ball2 = new Ellipse2D.Double(x2[i], y2[i], 20, 20);
      g2.fill(ball2);
      g2.draw(ball2);
      g2.fill(ball);
      g2.draw(ball);
    }

  } // end draw

  private double angleOffset(double radius){
    return (18/ radius);
  }
}