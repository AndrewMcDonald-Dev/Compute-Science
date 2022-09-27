// p1611.s
void main()
{
   int x = 2;
   x = x + 1;
   int y = 3;
   y = y + 1;
   {
      int z = 4;
      z = z + 1;
   }
   print("3 = ");
   println(x);
   print("4 = ");
   println(y);
   print("5 = ");
   println(z);
}
