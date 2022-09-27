// p1612.s
void main()
{
   int x = 2;
   x = x + 1;
   int y = 3;
   y = y + 1;
   int z = 100;
   {
      int z = 4;
      z = z + 1;
      print("5 = ");
      println(z);
   }
   print("3 = ");
   println(x);
   print("4 = ");
   println(y);
   print("100 = ");
   println(z);
}
