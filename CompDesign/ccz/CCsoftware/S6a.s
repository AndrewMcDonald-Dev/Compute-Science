// S6a.s test case
// To create executable program and to execute, enter
// java S6 S6a
// java S6 S6b
// a S6a.a S6b.a sup
// e S6a /c
int x, z = -10, zero;
extern int e;
void main()
{
   int y, z;

   x = +4 + -2;
   y = x;
   z = x*(2 + y) + (((4099)));
   println(z + x + -2);
   println(4107);
   println("S1 test done");
//===============================================================
   f(x + zero + z - 4107, z);
   g(10, 20, e);
   h();
   println("S5 test done");
   println("Enter integer");
   readint(x);
   print("= ");
   println(x);
   print(i() + 2);
   println(" = 7");
   println("S6 test done");
}
int y = +2;
void f(int x, int z)
{
   int q;
   print("2 = ");
   println(y);

//==========================================================
// Add support for subtraction, division, null statement, 
// compound statement, print statement, and single-line
// comments.
   println((z - (x - 50)   // comment in middle of statement
        ) / 2 - x);
   println(2075);
   ;                       // null statement 
   {{;                     // compound statement
      {
      x = 10;  
      ;
      y = 20;
   }};}
   {}
   print(x);
   println(x);
   println(1010);
   println(y);
   println(20);
   x = 1 + (2 + (3+ 4));
   println(x);
   println(10);
   x = 1 + 2 + 3 + 4 + 5;
   println(x);
   println(15);
   println("S2 test done");
//==========================================================
// Add support, println with zero arguments, println and 
// print with string argument, cascaded assignment 
// statement, unary plus and minus, and readint statement.
   println("four lines follow");
   print("one line");
   println();
   println("one line");
   println("third line\nfourth line");
   x = y = z = + - - - - - + -7;
   print(x);
   print(" = ");
   print(y);
   print(" = ");
   print(z);
   print(" = ");
   println(7);
   z = -(+x - + + - + + - + + + + - + + - + + -y);
   print ("-14 = ");
   print(z);
   print(" = ");
   println(-(-(+(-(14))))); 
   // no comment in following statement because // inside string
   println("////Enter integer////");   
   readint(q);
   print("= ");
   println(q + + + + 1 - 1); 
   println("S3 test done"); 
//==========================================================
// Add support for while, do-while, if, if-else, and escape 
// sequences within a string.
   x = 1;
   println("2 1 on separate lines");
   while(x)               // nested while loops
   {
      y = 2;
      while (y)
      {
         println(y);
         y = y -  1;      
      }
      x = x - 1;
   }
   print("0 = ");
   while (x)
   {
      x = x - 1;
      y = x;
      println(y);
   }
   zero = x;
   while (zero)
      println("bug");
   x = 0 - 3;
   print("hello -3 up to hello -1\n");
   while (x)              // if inside while
   {
      if (x) print("hello ");
      else println("bug1");
      println(x);
      x = x + 1;
   }
   x = 2;
   print("bye 2 down to bye 1\n");
   do                     // do-while loop
   {
      print("bye ");
      println(x);
      x = x - 1;
   } while (x);
   x = 1;
   println("Next line should \
say \"hello\" with the quotes");
   if (x)                 // nested if
   if (x)
   if (x) 
   {
      print("\"hel");
      println("lo\"");
   }
   else 
   {
      println("bug3");
   }
   print("good = ");
   if (zero)             // execute else part 
   {
      println("bug4");
   }
   else 
      println("good");
   println("Next line should say \"bye\" without the quotes\nbye");
   println("\"3 backslashes and a double quote \\\\\\\" within a string\"");
   println("S4 test done");
}
void h()
{
   print("-8 = ");
   println(x + z);
}
int i()
{
    int x = 10, y = 20, z;

    print("1 = ");
    println(x < y);
    print("1 = ");
    println(x <= y);
    print("0 = ");
    println(x == y);
    print("1 = ");
    println(x != y);
    print("0 = ");
    println(x > y);
    print("0 = ");
    println(x >= y);

    x = 20;
    print("0 = ");
    println(x < y);
    print("1 = ");
    println(x <= y);
    print("1 = ");
    println(x == y);
    print("0 = ");
    println(x != y);
    print("0 = ");
    println(x > y);
    print("1 = ");
    println(x >= y);

    println("Enter integer");
    readint(x);
    print("= ");
    println(x);
    x = 30;
    print("0 = ");
    println(x < y);
    print("0 = ");
    println(x <= y);
    print("0 = ");
    println(x == y);
    print("1 = ");
    println(x != y);
    print("1 = ");
    println(x > y);
    print("1 = ");
    println(x >= y);
    print("5 = ");
    println(11 % 6);

    println("3 hello's and 3 goodbye's should follow");
    do
    {
       while (x) 
       {
         if (x == 27) break;
         println("hello");
         x = x - 1;
       }
       if (y == 17) 
          break;
       println("goodbye");
       y = y - 1;
    } while (y > 15);

    return x - 22;
}
