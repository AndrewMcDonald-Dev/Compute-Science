import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ActionTester
{
  public static void main(String[] args)
  {
     JFrame frame = new JFrame();       //create a new Jframe window, equipped with borders
                                        //and with buttons for arithmetic operations. The window can be
                                        //programmed for "action" in carrying out an "event" (one or more).
    
     final int FIELD_WIDTH = 50;        //width (pixels)  
     final JTextField textField = new JTextField(FIELD_WIDTH);  //create a new textfield of width FIELD_WIDTH pixels
     textField.setText("Click an operation");  //specify the text to be displayed upon activation    

     JButton addButton = new JButton("Add");   
     /*create a new button to be labeled "Add", 
     an object in class JButton.
     Later this will be added to the frame.
     */
 
     /*Clicking the button is an "event" (or "event object"),
      which is sent to an "event handler" object called a
     "listener", which executes ("handles") the event. This is 
     called "firing" the event. 
     In detail, the listener object (automatically)
     invokes one of its methods with the
     button click event as the argument. (In some cases.
     there may be more than one listener for the event.)    
     Here is how we can declare a listener for clicking on addButton:
      
     TextSetter1 listenerForAddButton = new TextSetter1(); //create an action listener 
     addButton.addActionListener(listenerForAddButton); 
     
     In this way, listenerForAddButton is "registered" as a listener
     to receive events, like the click event, concerning addButton.
     
     The listener is an object in a class that must implement the
     ActionListener interface, which has only one method:
       
        public void actionPerformed(ActionEvent e)
     
     So the class TextSetter1 can be written as:

        public class TextSetter1 implements ActionListener{
          public void actionPerformed(ActionEvent e){
            textfield.setText("Addition"); //The response to the click on addButton
                                           //is to include text in the textfield
                                           //(not a realistic response).
                                           //This is how the event is "handled".
                                           //A more realistic response would be to add two
                                           //numbers previously entered via a text window
          }
        }
     

      Since we will only need class TextSetter1 once in the program,
      instead of defining it explicitly as above, we can make it "anonymous"
      (unnamed) by defining it implicitly inside some method and using it to make a
      single object, as follows:
     */
                          
     addButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent event){
            textField.setText("Addition");
         }
       });
     
      /*In this shorthand coding, we don't need to define
      the TextSetter1 object listenerForAddButton explicitly.
      */

     /*Now we have similar code for a second button*/
     JButton subButton = new JButton("Subtract");
     

     subButton.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent event){
            textField.setText("Subtraction");
         }
        });

     
     /*Declare other buttons, but do not implement them for action*/
     JButton multButton = new JButton("Multiply");
     JButton divButton = new JButton("Divide");
 
     /*Jframe is an example of a "container class":
     it is possible to add "component" objects to the
     window to create a new kind of object via "composition".
     How the components are arranged within the window
     is determined by a "layout manager". The FlowLayout
     manager positions components horizontally within the window/*/
 
     frame.setLayout(new FlowLayout());

     /*The Jframe "add" method can be used to add components,
     in this case, horizontally from left to right as
     they are added*/    
 
     frame.add(addButton);
     frame.add(subButton);
     frame.add(multButton);
     frame.add(divButton);
     frame.add(textField);

     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.pack(); //components will be densely packed within the window
     frame.setVisible(true);  //make the window visible
   }
} 