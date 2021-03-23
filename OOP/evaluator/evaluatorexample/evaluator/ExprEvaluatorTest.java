

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
 
    //initialize several expressions, but do not evaluate them immediately
    ExprEvaluator expr1 = new ExprEvaluator(); //construct an expression via keyboard
    ExprEvaluator expr2 = new ExprEvaluator("3+4."); //enter an expression via string parameter
    ExprEvaluator expr3 = new ExprEvaluator("(3.+4*(1+2*(4+3*(2-_7)))-1)/2.0"); 
   
    //display the input for expr1
    System.out.println(expr1.getExpression() + '\n');
    expr1.evaluator(); //evaluate expr1

    expr2.evaluator(); //evaluate expr2; the result will appear as an int value

    System.out.println(expr3.evaluator()); 
    //the evaluator() method will display the result as an int value
    //the value is also returned as a double to the print statement
                                    
    expr1 = new ExprEvaluator("3.1415927"); //a single number is also an expression
    System.out.println(Math.pow(expr1.evaluator(),2)); //the call to evaluator displays 
                                                       //the value, which is then squared by pow

    expr2 = new ExprEvaluator("3.1415927*3.1415927"); 
    expr2.evaluator(); //we can compare (to 7 places) the Java value using pow to the value using evaluator 
   
  }

}



