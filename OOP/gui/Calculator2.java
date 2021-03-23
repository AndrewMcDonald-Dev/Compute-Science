import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Calculator2 extends JFrame implements ActionListener{  

   //class constants 
   public static final int WIDTH = 800;
   public static final int HEIGHT = 600;
   public static final int NUMBER_OF_DIGITS = 30;

   private JTextField input;
   private JTextField output;

   public static void main(String[] args){
     Calculator2 calc = new Calculator2();  //declare a Calculator object
      calc.setVisible(true);
   }
     //constructor for Calculator
   public Calculator2(){
      //Declaring all items needed
      input = new JTextField("Enter Expression");
      Font inputFont = new Font("SansSerif", Font.PLAIN, 40);
      input.setSize(800, 200);
      input.setFont(inputFont);
      input.setBackground(Color.decode("#ffff00"));
      input.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      
      
      output = new JTextField("Answer");
      Font outputFont = new Font("SansSerif", Font.PLAIN, 40);
      output.setFont(outputFont);
      output.setSize(400, 200);
      output.setBackground(Color.decode("#33CCFF"));
      output.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      
      JTextArea prompt = new JTextArea("Enter a correct arithmetic expression, \nNegative numbers should be enclosed in parenthesis \nSpaces are allowed");
      Font font = new Font("SansSerif", Font.ITALIC, 20);
      prompt.setFont(font);
      prompt.setSize(1000, 200);
      prompt.setForeground(Color.decode("#1f1f1f"));
      prompt.setBackground(Color.decode("#FFAFAF"));

      JButton calculate = new JButton("Calculate");
      calculate.addActionListener(this);

      JButton reset = new JButton("Reset");
      reset.addActionListener(this);


      //Declaring and setting all panels needed
      JPanel inputPanel = new JPanel();
      inputPanel.add(input);
      inputPanel.setBackground(Color.decode("#ffff00"));
      BorderLayout border = new BorderLayout();
      inputPanel.setLayout(border);
      
      JPanel outputPanel = new JPanel();
      outputPanel.add(output);
      outputPanel.setBackground(Color.decode("#33CCFF"));
      outputPanel.setLayout(border);
      
      JPanel promptPanel = new JPanel();
      promptPanel.add(prompt);
      promptPanel.setBackground(Color.decode("#FFAFAF"));
      promptPanel.setLayout(border);

      JPanel buttonPanel = new JPanel();
      buttonPanel.add(calculate);
      buttonPanel.add(reset);
      buttonPanel.setBackground(Color.GREEN);


      //Used for building the layout
      JPanel masterPanel = new JPanel();

      masterPanel.add(inputPanel);
      masterPanel.add(outputPanel);
      masterPanel.add(promptPanel);
      masterPanel.add(buttonPanel);

      add(masterPanel);
      setSize(WIDTH,HEIGHT);
      masterPanel.setLayout(new GridLayout(4, 1));
      setLayout(new GridLayout(1, 1));

   }

     //event handler
   
   public void actionPerformed(ActionEvent e){
      String actionCommand = e.getActionCommand();
      if(actionCommand.equals("Calculate")){
            LR equation = new LR(input.getText());
            String text = " " + equation.evaluator();
            
            output.setText(text);
         
      }
      else if(actionCommand.equals("Reset"))
      {
            input.setText("Enter Expression");
            output.setText("Answer");
      }
   }
}  