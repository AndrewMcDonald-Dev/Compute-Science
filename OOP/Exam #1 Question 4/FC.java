import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FC extends JFrame implements ActionListener{  
   
   //class constants 
   private static final long serialVersionUID = 1L;
   public static final int WIDTH = 800;
   public static final int HEIGHT = 500;
   public static final int NUMBER_OF_DIGITS = 30;

   private JTextField inputF;
   private JTextField inputC;
   private JTextField outputF;
   private JTextField outputC;

   public static void main(String[] args){
     FC fc = new FC();  //declare a Calculator object
      fc.setVisible(true);
   }
     //constructor for Calculator
   public FC(){
      //Declaring all items needed
      inputF = new JTextField("enter temperature F");
      Font inputFFont = new Font("SansSerif", Font.PLAIN, 20);
      inputF.setSize(800, 200);
      inputF.setFont(inputFFont);
      inputF.setBackground(Color.decode("#ffffff"));
      inputF.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      
      
      inputC = new JTextField("enter temperature C");
      Font inputCFont = new Font("SansSerif", Font.PLAIN, 20);
      inputC.setFont(inputCFont);
      inputC.setSize(800, 200);
      inputC.setForeground(Color.decode("#ffffff"));
      inputC.setBackground(Color.decode("#000000"));
      inputC.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      
      outputF = new JTextField("Farenheit");
      Font outputFFont = new Font("SansSerif", Font.PLAIN, 20);
      outputF.setFont(outputFFont);
      outputF.setSize(800, 200);
      outputF.setForeground(Color.decode("#1f1f1f"));
      outputF.setBackground(Color.decode("#ffffff"));
      outputF.setBorder(javax.swing.BorderFactory.createEmptyBorder());


      outputC = new JTextField("Celsius");
      Font outputCFont = new Font("SansSerif", Font.PLAIN, 20);
      outputC.setFont(outputCFont);
      outputC.setSize(800, 200);
      outputC.setForeground(Color.decode("#ffffff"));
      outputC.setBackground(Color.decode("#000000"));
      outputC.setBorder(javax.swing.BorderFactory.createEmptyBorder());

      JButton convertFC = new JButton("Convert F to C");
      convertFC.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            double input = Double.parseDouble(inputF.getText());
            input -= 32;
            input *= 5;
            input /= 9;
            String text = " " + input;
            outputC.setText(text);
         }
      });

      JButton convertCF = new JButton("Convert C to F");
      convertCF.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            double input = Double.parseDouble(inputC.getText());
            input += 32;
            input *= 9;
            input /= 5;
            String text = " " + input;
            outputF.setText(text);
         }
      });

      JButton clear = new JButton("Clear");
      clear.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            inputF.setText("enter temperature F");
            inputC.setText("enter temperature F");
            outputF.setText("Farenheit");
            outputC.setText("Celsius");
         }
      });


      //Declaring and setting all panels needed
      JPanel farenheitPanel = new JPanel();
      farenheitPanel.add(inputF);
      farenheitPanel.setBackground(Color.decode("#ffffff"));
      BorderLayout border = new BorderLayout();
      farenheitPanel.setLayout(border);

      JPanel farenheit2Panel = new JPanel();
      farenheit2Panel.add(outputF);
      farenheit2Panel.setBackground(Color.decode("#ffffff"));
      farenheit2Panel.setLayout(border);
      
      JPanel celsiusPanel = new JPanel();
      celsiusPanel.add(inputC);
      celsiusPanel.setBackground(Color.decode("#000000"));
      celsiusPanel.setLayout(border);
      
      JPanel celsius2Panel = new JPanel();
      celsius2Panel.add(outputC);
      celsius2Panel.setBackground(Color.decode("#000000"));
      celsius2Panel.setLayout(border);

      JPanel buttonPanel = new JPanel();
      buttonPanel.add(convertFC);
      buttonPanel.add(convertCF);
      buttonPanel.add(clear);
      buttonPanel.setBackground(Color.decode("#ffff"));


      //Used for building the layout
      JPanel inAndOutPanel = new JPanel();

      inAndOutPanel.add(farenheitPanel);
      inAndOutPanel.add(farenheit2Panel);
      inAndOutPanel.add(celsiusPanel);
      inAndOutPanel.add(celsius2Panel);

      add(inAndOutPanel);
      setSize(WIDTH,HEIGHT);
      inAndOutPanel.setLayout(new GridLayout(2,2));
      add(buttonPanel);
      setLayout(new GridLayout(2, 1));

   }
   

   @Override
   public void actionPerformed(ActionEvent arg0) {
        // Do something her
   }
}