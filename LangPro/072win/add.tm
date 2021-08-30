1           add two binary numbers in stacked form
{}

1 ~ ~ r 1   to to right of input
1 _ _ L 2   move back to rightmost symbol

2 z 0 l 2   0+0 = 0                         no carry
2 d 1 l 2   1+0 = 1
2 u 1 l 2   0+1 = 2
2 t 0 l 3   1+1 = 0 carry = 1

3 d 0 l 3   1+0 + carry = 0 carry = 1       carry
3 u 0 l 3   0+1 + carry = 0 carry = 1
3 t 1 l 3   1+1 + carry = 1 carry = 1
3 z 1 l 2   0+0 + carry = 1 carry = 0
3 _ 1 L 2   

