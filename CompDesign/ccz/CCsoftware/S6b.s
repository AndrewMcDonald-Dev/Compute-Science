// S6b.s test case (link object code with S6a.s)
int e = 30;
void g(int a, int b, int c)
{
   int x = 40, y = +50;
   int z = -60;
   print("90 = ");
   println(a + b + c + x + y + z);
}
