1            decr
{}
                                
1 ~ ~ r 1    move to right
1 _ _ L 2    move back to rightmost bit

2 0 1 L 2    change 0's to 1's until reach a 1
2 _ _ r 3
2 1 0 L 4    change first 1 to 0, starts carry
4 0 0 r 4    0 + 1 + carry = 0
4 1 1 r 4    1 + 1 + carry = 1
4 _ _ r 5
