import java.util.Scanner;
import java.util.Stack;

//class for evaluating arithmetic expressions as double values, which are displayed rounded to 7 places,
//with decimal points supressed for whole numbers. The unrounded double value is returned.
public class ExprEvaluator
{
  Scanner kb =  new Scanner(System.in);

  //class-wide variables:
  //Since expressions are evaluated one at a time, all evaluations can use the same two stacks.
  private static Stack<Character> A = new Stack<Character>(); //stack for operators
  private static Stack<Double> B = new Stack<Double>(); //stack for operands

  //class-wide methods:

  //method to print a double value that is an integer as an int
  public static void expressionOutput(double x)
  {
    if(x == (double)Math.round(x)) //if the answer is a whole number,
                                   //display without decimal point
    {
       int intAns = (int)x;
       System.out.println("value = " + intAns + '\n');
    }
    else
    {
       System.out.println("value = " + x + '\n');
    }
  }


  /*Expressions are evaluated from right to left, using a stack A of arithmetic
  operators and a stack B of operands. In this method, a single operation is
  evaluated by popping the current operator from A, its 2 operands from B, and by then
  performing the operation and pushing the result onto B as a double value.*/
  
  private static void eval()
  {
      char op = A.pop(); //current operator
      double opnd1 = B.pop(); //operands
      double opnd2 = B.pop();
      double val = 0.0;
      switch (op) //evaluate
      {
        case '+':
          val = opnd1 + opnd2;
          break;
        case '-':
          val = opnd1 - opnd2;
          break;
        case '*':
          val = opnd1 * opnd2;
          break;
        case '/':
          val = opnd1/opnd2;
          break;
        case '^':
        val = Math.pow(opnd2, opnd1);
      }
      B.push(val); //push result onto B
  }

  /* In this method, a parenthesized subexpression is
  evaluated by evaluating its operations on Stack A top-down
  until a right parenthesis is encountered on stack A;
  the right parenthesis is also popped from A*/
  
  private static void evalDown()
  {
    do
    {
      eval();
    }while((A.size()>0) && (A.peek() != '('));
    if((A.size()>0) && (A.peek() == '('))
    {
      A.pop();
    }
  }
  
 
  //This method compares the current operator token in the input string to the operator
  //on top of stack A to determine precedence.
  private static boolean prec(char token, Stack<Character> StackA)
  {
    char topOp = StackA.peek();
    if((token == '^')||(token == '*') || (token == '/'))
    {
      return true; //token has precedence, so it will be pushed onto A
    }
    else
    {
      if((topOp == '*') || (topOp == '/'))
      {
        return false; //operator at top of A has precedence
      }
      else
      {
        return true; //equal low-precedence operators or token is ')',
                     // which is always pushed onto A
      }
    }
  }

  //variables for an ExprEvaluator object
  private String e;
  private int p; //pointer to characters within the expression string e
 
  //constructor 
  public ExprEvaluator()
  {
    System.out.println("enter an expression");
    e = kb.nextLine(); //input an arithmetic expression as a line of keyboard input.
    e = e.replace(" ", "");
    p = 0;      
  }

  //parameterized constructor
  public ExprEvaluator(String ee)
  {
    e = ee;
    e = e.replace(" ", "");
    p = 0;  
  }

  public String getExpression()
  {
    return e;
  }


  //If a substring of e whose rightmost character is at position p 
  //represents a number (possibly with a decimal point, possibly negative), 
  //return the numerical value of the substring as a double value and
  //re-position p just to the left of the substring.
 
  private double formNum()
  {
    double total = 0.0;
    int count = 0;
    int flag = 0;
    double mult = 1.0;
    char c,d;
    do
    {
      c = e.charAt(p); //examine the current character in the string (from right to left)
      if(c == '.')
      {
        flag = 1; //set a flag to remember that the character is a decimal point
      }
      else
      {
      //if the current character is a digit, convert to its
      //int value and include it in the value corresponding to the string.
        if((c >= '0') && (c<= '9')) 
        {
          total = total * 10 + (c-'0');
          mult = mult * 10.0;
          if(flag == 0)
          {
           count++; //count the number of digits to the right of a possible decimal point
          }
        }
        else
        {
          if(c == '_') 
          {
            total = -total; //an underscore character represents a negative value
          }
        }
      }
      p++; //Prepare to move to the next character to the left. 
           //This is a private non-static method because it changes the member variable p
      d = '?';
      if(p < e.length())
      {
         d = e.charAt(p); //the next character to the left
      }
    }while((p < e.length()) && (((d<='9')&&(d>='0'))||(d=='_')||(d=='.')));//check for a valid character
    
    if(flag==1)
    {
     total = total/Math.pow(10.0,count*1.0);
     //compute the value taking into account
                                             //the number of decimal places
    }
    return total;
  }

  //This method uses the 2-stack approach to evaluate an expression (from right to left).
  //The result is rounded to 7 decimal places and displayed as explained in expressionOutput() above.
  //The original double value (unrounded) is returned to the calling program.
  //The code could be made more efficient (but more difficult to read) by using if-else-if-else...
  //as in formNum(); here we (unnecessarily) execute each if statement.
  public double evaluator() 
  {
 
    char token; //current token in the input expression
 
    //loop to scan the string right to left
    do
    {    
      token = e.charAt(p);
      if(token == '(')
      {
          A.push(token); //always push a right parenthesis to delimit a subexpression
          p++;      
      }
      
      //if the token is a left parenthesis, 
      //evaluate the corresponding subexpression 
      if(token == ')' )
      {
        evalDown();
        p++; //move beyond the left parenthesis
      }


      //If the token is an arithmetic operator of higher precedence
      //than the topmost operator on stack A, push the token onto A.    
      if((token=='+')||(token=='-')||(token=='*')||(token=='/')||(token=='^'))
      {          
        if((A.size() == 0) || (prec(token, A) == true))
        {
          A.push(token);
          p++;         
        }
        //If the token is an arithmetic operator of lower precedence than that
        //at the top of the stack, then evaluate the operation at the top of stack A.
        else
        {
          eval();
        }
      }
      
      //if the token is the rightmost  digit of a number or a decimal point on the right,
      //form the number as a double and push onto stack B
      if(((token<='9')&&(token>='0'))||(token=='.')||(token=='_'))
      {   
         B.push(formNum());
      }
    }while(p < e.length());//continue to scan from right to left


    //after completely scanning the input string, evaluate any remaining operations
    while(A.size()>0) 
    {
      eval();
    }
    
    double x = B.pop();

    //round the result to 7 places and then display
    expressionOutput((double)Math.round(x*10000000)/10000000.0); 

    return x; //return the original double value
  } //end of evaluator


} //end of class
