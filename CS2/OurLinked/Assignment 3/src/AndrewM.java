import java.util.*;
import java.io.*;
public class AndrewM {
    public static void main(String[] args) throws IOException {
        /*Scanner in=new Scanner(new File("in.txt"));
        while(in.hasNextLine()){
            String s=in.next();
            System.out.println("InFix: " + s);
            String s1=postFixMaker(s);
            System.out.println("Postfix: " + s1);
            System.out.println("Solved: " + postFixCalculator(s1));
            System.out.println("----------------------------------------");
        }
        System.out.println(postFixCalculator("3 6 + 2 4 - * 7 + $"));*/
        remaindering(121);

    }

    public static int precedence(char c){
        //bases the operators on the order of operations higher number = higher precedence.
        switch(c){
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
            case '%':
            case '@': //negation goes here because it is basically *-1
                return 2;

            case '^':
            case '#':
                return 3;

            default:
                return -1;
        }
    }

    public static String postFixMaker(String s){
        //Will be the final result
        String out="";

        //The stack is used for storing data necessary for connecting parenthesis
        Stack<Character> stack = new Stack<>();

        //cycles through the whole string
        for(int i=0;i<s.length();i++){
            //selects the character at this point in the loop
            char c = s.charAt(i);

            //identifies if the character is an operand and if so simply attaches it to the out string
            if(Character.isDigit(c)) {
                out += c;

                //makes sure multi digit numbers stay together
                if (!Character.isDigit(s.charAt(i + 1))) {
                    out += " ";
                }
            }

            //loads up the stack with ( for usage in the next else to complete () pairs
            else if(c=='(')
                stack.push(c);

            //Checks the to see if the ) follows an identifiable (
            else if(c==')'){

                //connects all takes whatever was before the ) and adds it to the out string
                while(!stack.isEmpty() && stack.peek() != '(')
                    out+=stack.pop() + " ";

                //if the previous while loop does not cover all of the cases than
                //it is an error
                if(!stack.isEmpty() && stack.peek() != '(')
                    return "Invalid Expression  1";

                //if it is some other sort of situation not covered by any previously used check
                //this just pops the stack and clears it up
                else
                    stack.pop();

            //grabs the rest of the cases which have to be the operators
            }else{

                //Checks if the stack is not empty and checks the precedence of the character
                // based on the order of operations set in the other method
                while(!stack.isEmpty() && precedence(c)<=precedence(stack.peek())){

                    //catches if the amount of ( is too many returning an invalid
                    //note: this seems to have been taken care of when completing the pairs but that
                    //only checks if there are not too many )
                    if(stack.peek()=='(')
                        return "Invalid Expression  2";

                    //adds the operator with the highest precedence
                    out += stack.pop() + " ";
                }
                //if the character is of higher precedence that that of top of the stack
                //it is then added to the top of the stack
                stack.push(c);
            }
        }
        //all of the operators that are not popped/added in the main for loop
        //is then cycled through in this as a catch all
        while(!stack.isEmpty()){

            //catches if the amount of ( is too many returning an invalid
            //note: this seems to have been taken care of when completing the pairs but that
            //only checks if there are not too many )
            if(stack.peek()=='(')
                return "Invalid Expression 3";

            //adds all of the final operators that have already been sorted
            //based on precedence
            out += stack.pop() + " ";
        }
        //returns the string with the closing $ char
        return out+'$';
    }

    public static Number postFixCalculator(String s){
        //Splits input string on all spaces and places it in an array.
        String[] Arr=s.split(" ");

        //For storage of the operands before each operator
        Stack<Double> numbers =new Stack<>();

        //Cycles through Array
        for (String item : Arr) {
            double value = 0;
            switch (item) {

                //All cases that utilize two numbers before the operator
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                case "^":

                    //Saves the two numbers before the operator
                    double right = numbers.pop();
                    double left = numbers.pop();
                    switch (item) {
                        case "+":
                            value = left + right;
                            break;
                        case "-":
                            value = left - right;
                            break;
                        case "*":
                            value = left * right;
                            break;
                        case "/":
                            if (right == 0) {
                                System.out.println("Can not divide by 0.");
                                System.exit(0);
                            }
                            value = left / right;
                            break;
                        case "%":
                            if (right == 0) {
                                System.out.println("Can not divide by 0.");
                                System.exit(0);
                            }
                            value = left % right;
                            break;
                        case "^":
                            value = Math.pow(left, right);
                            break;
                        default:
                            break;
                    }
                    //puts the value into the stack
                    numbers.push(value);
                    break;

                //All numbers that utilize one number before the operator
                case "@":
                case "#":
                    double num = numbers.pop();
                    switch (item) {
                        case "@":
                            value = num * (-1);
                            break;
                        case "#":
                            if (num < 0) {
                                System.out.println("Cannot take the square root of a negative number.");
                                System.exit(0);
                            }
                            value = Math.sqrt(num);
                            break;
                        default:
                            break;
                    }
                    numbers.push(value);
                    break;

                //Catches the $ at the end of the postFix and ends the program
                case "$":
                    break;

                //This is the way that the numbers are stored
                default:
                    numbers.push(Double.parseDouble(item));
                    break;
            }
        }
        //Returns the value variable
        return numbers.pop();
    }
    public static void remaindering(int n){
        Stack<Integer> numbers =new Stack<>();
        if(n/9 == 0)
            numbers.push(n%9);
        while(n/9 != 0) {
            numbers.push(n % 9);
            n = n / 9;
            if(n/9 ==0)
                numbers.push(n%9);
        }
        while(!numbers.isEmpty())
            System.out.print(numbers.pop());
    }
}
