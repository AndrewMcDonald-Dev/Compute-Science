// p1613.s
int x = 5;
void main()
{
   int x = 7;
   int f;
   print("7 = ");
   println(x);
   print("5 = ");
   println(::x);
   print("11 = ");
   ::f();
}
void f()
{
   println(11);
}
