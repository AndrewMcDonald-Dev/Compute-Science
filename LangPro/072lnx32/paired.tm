// paired.tm
// This Turing machine accepts {a**nb**n : n >= 0}
// Format of a quintuple:
//     CurrentState CurrentInput NewSymbol Move NextState

0             Start state
{5}           Set of final states (i.e., accept states}

0 _ _ R 5     Use state 0 to check for ^ input
0 a x R 2
1 a x R 2     mark a
2 a a R 2     now move right to matching b
2 y y R 2
2 b y L 3     mark matching b
3 y y L 3     now move left first marked a
3 a a L 3
3 x x R 1     start cycle again
1 y y R 4     check if all b's have been marked
4 y y R 4
4 _ _ R 5     if all b's marked, go to final state 5