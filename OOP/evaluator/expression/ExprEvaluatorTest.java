import java.util.Scanner;

/* program to test the expression evaluator; evaluates input arithmetic expressions, each
   represented as a string without blanks, and with negative numbers indicated by underscore.
   Declare an expression using either ExprEvaluator exp = new ExprEvaluator() 
   (and respond to the prompt), or as ExprEvaluator exp = new ExprEvaluator("expression string").
   The expression can be returned as a String using exp.getExpression().
   The expression can be evaluated using exp.evaluator(), which also displays the result
   rounded to 7 decimal places, supressing a decimal point if the result is an integer. 
   The computed value is also returned as a double.
*/

public class ExprEvaluatorTest
{
 
   public static void main(String[] args)
   {
      Scanner kb = new Scanner(System.in);
    
    System.out.println("Welcome to the Expression Evaluator!");
    System.out.println("Type exit at any time to stop the evaluator");
    System.out.println("Please type an infix expression without any spaces!");
      String input = "";
      ExprEvaluator expr1;
      while(!input.toLowerCase().equals("exit"))
      {
         System.out.print("Enter an expression > ");
         input = kb.nextLine();
         if(!input.toLowerCase().equals("exit"))
         {
            expr1 = new ExprEvaluator(input);
            expr1.evaluator(); 
         }
      }
    System.out.println("Goobye!");
  }

}



