   x = +3 + -2 + 1;
   y = x;
   z = x*(2 + y) + (((4099)));
   println(z + x + -2);
   println(4107);
   println("S1 test done");
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
