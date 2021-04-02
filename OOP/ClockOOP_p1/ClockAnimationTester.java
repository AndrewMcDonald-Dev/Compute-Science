import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class ClockAnimationTester{
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

        int WIDTH = 800;
        int HEIGHT = 610;

        JFrame frame = new JFrame();
        // Shape dimensions
        ClockShape shape = new ClockShape(100, 0, 600);

        ShapeIcon icon = new ShapeIcon(shape, WIDTH, HEIGHT);

        final JLabel label = new JLabel(icon);
        label.setOpaque(true);
        label.setBackground(Color.decode("#33ccff"));
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
                    if(minute < 0 )
                        shape.setHourSmall(0);
                    if(minute >= 60){
                        shape.setMinute(0);
                        shape.setHour(hour + 1); 
                        shape.setPart(0);
                    }else if(minute / 12 > 0)
                        shape.setHourSmall(minute/12);
                    
                    if (hour > 11) 
                        shape.setHour(0);

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

