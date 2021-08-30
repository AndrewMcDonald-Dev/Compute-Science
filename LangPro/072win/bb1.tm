// bb1.tm 
// This is a 1-state 2-symbol busy beaver.  The one state is 
// state 1 (state 2 is not counted since its only purpose is to halt 
// the operation of the Turing machine).  The two symbols are 
// blank (represented by _ in the quintuples) and 1.  The keyword 
// notrace suppresses the trace output.  This keyword is not necessary 
// for this busy beaver because the size of its trace is minimal. 
// However, for busy beavers with more states, the output can be 
// considerable, causing both runtime and the size of the tog file 
// to be excessive.  The keyword finalconfig causes the 
// configuration of the machine when it halts to be displayed 
// and written to the tog file.

1            notrace finalconfig
{}           no final states

1 _ 1 r 2    
