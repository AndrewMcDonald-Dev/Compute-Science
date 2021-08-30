1              ram
{}

1 ~ ~ r 1      move across #
1 # # r 2

2 0 z L 3      mark next bit
2 1 u L 3
2 _ b L 3

3 ~ ~ L 3      move back to count
3 # # L 4

4 0 1 L 4      decrement count
4 1 0 L 5
4 _ _ r 7      on zero count, go to state 7
5 0 0 L 5
5 1 1 L 5
5 _ _ r 6
 
6 ~ ~ r 6      move right to marked bit
6 z 0 r 2      change marked bit back
6 u 1 r 2
6 b _ r 2

7 ~ _ r 7      blank out everything up to marked bit 

7 z 0 r 8      changed marked bit back
7 u 1 r 8
7 b _ r 8

8 0 _ r 8      blank out everything to right of marked bit
8 1 _ r 8

