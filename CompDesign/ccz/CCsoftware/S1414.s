   x = +3 + -2 + 1;
   y = x;
   z = x*(2 + y) + (((4099)));
   println(z + x + -2);
   println(4107);
   println("S1 test done");
// end of S1 test
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
// end of s2 test
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
// add support for increment and decrement operator
   x = 5;
   x++;
   print(x);
   println(" = 6");
   x--;
   print(x);
   println(" = 5");

   ++x;
   print(x);
   println(" = 6");
   --x;
   print(x);
   println(" = 5");

   print(--x);
   println( " = 4");

   x = 30;
   print(x--);
   println(" = 30");
   print(x);
   println("= 29");

   x = 7;
   print(++x);
   println(" = 8");

   x = -5;
   print(x++);
   println(" = -5");
   print(x);
   println(" = -4");

   x = 5;
   y = --x;
   print(y);
   println(" = 4");
   print(x);
   println(" = 4");

   x = 10;
   y = x++;
   print(y);
   println(" = 10");
   print(x);
   println(" = 11");
   println("increment/decrement operator test done");

