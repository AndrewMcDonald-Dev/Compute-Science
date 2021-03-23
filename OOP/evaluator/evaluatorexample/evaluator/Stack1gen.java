//Stack1gen.java
//array implementation of stack class

public class Stack1gen<T>
{
  int MAX = 30; //maximum number of stack entries

  private int top;
  private T[] stack; //array to hold the stack data

  //default constructor
  public Stack1gen()
  {
    top = MAX; //initialize an empty stack
    stack = (T[]) new Object[MAX];
  }

  //copy constructor
//  public Stack1(Stack1 s)
//  {
//    top = s.top;
//    for(int i = top; i<=MAX-1; i++)
//   {
//      stack[i] = s.stack[i];
//    }
//  }

  public void push(T y)  //push data item y onto the stack
  {
    assert(top > 0); //check that there is room on the stack
    top = top -1;
    stack[top] = y;
  }

  public T pop() //pop the top item from the stack and return it
  {
    assert(top < MAX); //check that there is data on the stack
    T x = stack[top];
    top = top +1;
    return x;
  }

  public int getSize()
  {
     return MAX-top;
  }

  public T getTop()
  {
    assert(top < MAX);
    return stack[top];
  }

// public void printStack()  //print the contents of the stack, from
// top to bottom, one item per line, without popping the stack items.


}
                  
