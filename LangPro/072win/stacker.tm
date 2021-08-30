1                 stacker
{}

1 0 0 r 1        move to right of input
1 1 1 r 1
1 # # r 1

1 _ _ L 2       move back to leftmost symbol

2 0 _ L 3       got 0  erase and go to state 3
2 1 _ L 6       got 1  erase go to state 6
2 # _ L 8       all done go to state 8

3 0 0 L 3       bottom bit is 0
3 1 1 L 3
3 # # L 4       cross over to top (i.e., left) number

4 ~ ~ L 4
4 _ z r 5
4 0 z r 5       0 on 0 write z
4 1 d r 5       1 on 0 write d

6 0 0 L 6       bottom bit is 1
6 1 1 L 6
6 # # L 7

7 ~ ~ L 7
7 _ u r 5
7 0 u r 5      0 on 1 write u
7 1 t r 5      1 on 1 write t

5 ~ ~ R 5      go right 
5 _ _ L 2      move back to leftmost symbol and repeat process

8 ~ ~ L 8
8 0 z L 8      process any remaining bits in top number
8 1 d L 8
8 _ _ R 9      all done so halt
