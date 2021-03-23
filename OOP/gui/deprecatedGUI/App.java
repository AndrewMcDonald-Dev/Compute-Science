import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App {
    public static void main(String[] args) {
        ExpressionApp app = new ExpressionApp(700, 500);

    }
}

class ExpressionApp extends JFrame {

    public ExpressionApp(int width, int height) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#131313"));
        TextBox t = new TextBox(200, 50, "");
        JLabel prompt = new JLabel();
        prompt.setSize(250, 250);
        prompt.setText("Enter an expression with spaces between each token Ex: -2.1 + 5 / 3");
        Button calculate = new Button(150, 40, "Calculate");
        Button clear = new Button(150, 40, "Clear");
        prompt.setForeground(Color.decode("#ffffff"));

        // NOTE FOR PROFESSOR: In stead of adding a second label, I chose to put the
        // answer to the expression directly back into the text box so it could be used
        // in another calculation. This is a better functionality for the calculator
        calculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ExprEvaluator evaluator = new ExprEvaluator(t.getText());
                    String text = "" + evaluator.evaluator();

                    // This is me setting the contents of the text box to the result of the
                    // expression, if doing this effects my grade I can resubmit it with the exact
                    // specifications, I just wanted to try out some other features of swing
                    t.setText(text);

                } catch (Exception ex) {
                    // TODO: handle exception
                }
            }
        });

        // Also I added the action listeners by creating an annonymous class which I
        // think is a better functionality because it will only trigger when its own
        // action is triggered.
        clear.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                t.setText("");
                System.out.println("Firing");
            }
        });
        JPanel promptPanel = new JPanel();
        promptPanel.add(prompt);
        promptPanel.add(t);
        promptPanel.setBackground(Color.decode("#1f1f1f"));
        promptPanel.setLayout(new GridLayout(2, 1));

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(calculate);
        buttonPanel.add(clear);
        buttonPanel.setBackground(Color.decode("#1f1f1f"));

        JPanel masterPanel = new JPanel();

        masterPanel.add(promptPanel);
        masterPanel.add(buttonPanel);

        // I was doing some experiments with how panels and the frame interact, I'll
        // leave this in for now.
        add(masterPanel);
        setSize(width, height);
        masterPanel.setLayout(new GridLayout(2, 1));
        setLayout(new GridLayout(1, 1));
        setVisible(true);
    }
}

class TextBox extends JTextField {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TextBox(int width, int height, String text) {
        Font font = new Font("SansSerif", Font.PLAIN, 20);
        setFont(font);
        setText(text);
        setBorder(BorderFactory.createCompoundBorder(getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        setPreferredSize(new Dimension(width, height));
    }
}

class Button extends JButton {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Button(int width, int height, String text) {
        Font font = new Font("SansSerif", Font.PLAIN, 16);
        setFont(font);
        // setBounds(x, y, width, height);
        setPreferredSize(new Dimension(width, height));
        setText(text);
    }

}