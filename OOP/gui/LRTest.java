import java.util.*;

/* program to test the expression evaluator; evaluates input arithmetic expressions, each
   represented as a string without blanks, and with negative numbers indicated by underscore.
   Declare an expression using either LR exp = new LR() 
   (and respond to the prompt), or as LR exp = new LR("expression string").
   The expression can be returned as a String using exp.getExpression().
   The expression can be evaluated using exp.evaluator(), which also displays the result
   rounded to 7 decimal places, supressing a decimal point if the result is an integer. 
   The computed value is also returned as a double.

   The user is also allowed to add spaces inbetween operands and numbers as well as before 
   and after parenthesis (except when using (-x) yo calculate negative numbers).

   The capability for utlizing negative numbers in a more intuitive manner has been Added
   instead of using _x the new method is (-x) with the first half (-x being absolutely
   necessary for the operation to work correctly.

   Exponentiation has been added with the form of "a^b" (and spaces can be used at any point
   without affecting the calculation)

   The calculator now evaluates from left to right.
*/

public class LRTest
{
 
   public static void main(String[] args)
   {
      Scanner kb = new Scanner(System.in);

      LR ex1 = new LR("1+2*3+4");
      System.out.print(ex1.getExpression() + '\n');
      ex1.evaluator();

      LR ex2 = new LR("1 + 2 * 3 + 4");
      System.out.print(ex2.getExpression() + '\n');
      ex2.evaluator();

      LR ex3 = new LR("1+(9/3)-4");
      System.out.print(ex3.getExpression() + '\n');
      ex3.evaluator();
      
      LR ex4 = new LR("121.75 + 3.25");
      System.out.print(ex4.getExpression() + '\n');
      ex4.evaluator();
      
      LR ex5 = new LR("4+3*(1+2.*(6/(3+3.))-1)+2.0");
      System.out.print(ex5.getExpression() + '\n');
      ex5.evaluator();

      LR ex6 = new LR("0.1*0.2");
      System.out.print(ex6.getExpression() + '\n');
      ex6.evaluator();

      LR ex7 = new LR("0.011*.022");
      System.out.print(ex7.getExpression() + '\n');
      ex7.evaluator();

      LR ex9 = new LR("3.1415927^2");
      System.out.print(ex9.getExpression() + '\n');
      ex9.evaluator();

      LR ex10 = new LR("3.14159^1+1");
      System.out.print(ex10.getExpression() + '\n');
      ex10.evaluator();

      LR ex11 = new LR("2^(2*(2+3))");
      System.out.print(ex11.getExpression() + '\n');
      ex11.evaluator();

      LR ex12 = new LR("(-3)^(-3)");
      System.out.print(ex12.getExpression() + '\n');
      ex12.evaluator();

      LR ex13 = new LR("(-(-(-(-1))))");
      System.out.print(ex13.getExpression() + '\n');
      ex13.evaluator();

      String input = "";
      LR ex14;
      System.out.print("Do you want to enter an expression?(y/n)  ");
      input = kb.nextLine();
      while(input.toLowerCase().equals("y")){
         System.out.print("Enter an expression: " + '\n');
         input = kb.nextLine();

         ex14 = new LR(input);
         System.out.print(ex14.getExpression() + '\n');
         ex14.evaluator();

         System.out.print("Do you want to enter an expression?(y/n)  ");
         input = kb.nextLine();
      }
      System.out.println("Goodbye.");
      kb.close();
   }

}



