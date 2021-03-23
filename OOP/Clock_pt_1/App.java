import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.Color;
import java.awt.event.*;

public class App {
    public static boolean started;

    static boolean isInt(String d) {
        try {
            Integer.parseInt(d);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        int WIDTH = 600;
        int HEIGHT = 600;

        JFrame frame = new JFrame();
        // Shape dimensions
        Clock shape = new Clock(0, 0, 600);

        ShapeIcon icon = new ShapeIcon(shape, WIDTH, HEIGHT);

        final JLabel label = new JLabel(icon);
        label.setOpaque(true);
        frame.setLayout(new FlowLayout());
        frame.add(label);

        // Hours
        JTextField hField = new JTextField("Hours");
        hField.setPreferredSize(new Dimension(130, 50));

        // Minutes
        JTextField mField = new JTextField("Minutes");
        mField.setPreferredSize(new Dimension(130, 50));

        // Seconds
        JTextField sField = new JTextField("Seconds");
        sField.setPreferredSize(new Dimension(130, 50));

        JButton hButton = new JButton("Set Hour");
        hButton.setPreferredSize(new Dimension(100, 50));
        hButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = hField.getText();
                if (isInt(text)) {
                    shape.setHour(Integer.parseInt(text));
                    label.repaint();
                }
            }
        });
        JButton mButton = new JButton("Set Minute");
        mButton.setPreferredSize(new Dimension(100, 50));
        mButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = mField.getText();
                if (isInt(text)) {
                    shape.setMinute(Integer.parseInt(text));
                    label.repaint();
                }
            }
        });
        JButton sButton = new JButton("Set Second");
        sButton.setPreferredSize(new Dimension(100, 50));
        sButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = sField.getText();
                if (isInt(text)) {
                    shape.setSecond(Integer.parseInt(text));
                    label.repaint();
                }
            }
        });
        started = false;
        JButton Start = new JButton("Start");
        Start.setPreferredSize(new Dimension(100, 50));
        Start.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                started = !started;
            }
        });

        JButton Reset = new JButton("Reset");
        Reset.setPreferredSize(new Dimension(100, 50));
        Reset.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                shape.setHour(0);
                shape.setMinute(0);
                shape.setSecond(0);
                label.repaint();
            }
        });

        JPanel test = new JPanel();
        test.setLayout(new FlowLayout());
        test.add(hField);
        test.add(hButton);
        test.add(mField);
        test.add(mButton);
        test.add(sField);
        test.add(sButton);
        test.add(Start);
        test.add(Reset);
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (started) {
                    int second = shape.getSecond();
                    int minute = shape.getMinutes();
                    int hour = shape.getHours();
                    label.repaint();
                    shape.setSecond(shape.getSecond() + 1);
                    if (second > 59) {
                        shape.setSecond(0);
                        shape.setMinute(minute + 1);
                    }

                    if (minute > 59) {
                        shape.setMinute(0);
                        shape.setHour(hour + 1);
                    }

                    if (hour > 11) {
                        shape.setHour(0);
                    }

                    label.repaint();

                }

            }
        };
        Timer t = new Timer(1000, taskPerformer);

        frame.add(test);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(WIDTH + 200, HEIGHT + 200);
        frame.setVisible(true);

        t.start();
    }
}

class Clock implements Moveable {
    private int x;
    private int y;
    private int width;

    private int seconds;
    private int minutes;
    private int hours;
    private int hAngle;
    private int mAngle;
    private int sAngle;
    private static int NOON = -90;

    public Clock(int x, int y, int width) {

        mAngle = 0;
        hAngle = 0;
        sAngle = 0;
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public void setSecond(int second) {
        this.seconds = second;
        sAngle = (6 * this.seconds) + NOON;
    }

    public int getSecond() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setMinute(int minute) {
        this.minutes = minute;
        mAngle = (6 * this.minutes) + NOON;
    }

    public void setHour(int hour) {
        this.hours = hour;
        hAngle = (30 * this.hours) + NOON;

    }

    public void translate(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void draw(Graphics2D g2) {
        Ellipse2D.Double clock = new Ellipse2D.Double(x, y, width, width);
        Point2D.Double endMinutes = new Point2D.Double(300 + (width * 0.5) * Math.cos(Math.toRadians(mAngle)),
                300 + (width * 0.5) * Math.sin(Math.toRadians(mAngle)));
        Point2D.Double endSeconds = new Point2D.Double(300 + (width * 0.4) * Math.cos(Math.toRadians(sAngle)),
                300 + (width * 0.4) * Math.sin(Math.toRadians(sAngle)));
        Point2D.Double endHours = new Point2D.Double(300 + (width * 0.3) * Math.cos(Math.toRadians(hAngle)),
                300 + (width * 0.3) * Math.sin(Math.toRadians(hAngle)));

        g2.setColor(Color.decode("#00AA00"));
        g2.fill(clock);
        g2.draw(clock);

        Shape minutes = new Line2D.Double(new Point(width / 2, width / 2), endMinutes);
        g2.setStroke(new BasicStroke(10));
        g2.setColor(Color.decode("#2f67b1"));
        g2.draw(minutes);

        Shape seconds = new Line2D.Double(new Point(width / 2, width / 2), endSeconds);
        g2.setStroke(new BasicStroke(4));
        g2.setColor(Color.decode("#d3598e"));
        g2.draw(seconds);
        Shape hours = new Line2D.Double(new Point(width / 2, width / 2), endHours);
        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.decode("#b12fa8"));
        g2.draw(hours);

    }
}

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