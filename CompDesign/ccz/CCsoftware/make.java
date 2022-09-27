  public NFAState make(int op, NFAState p, NFAState q)
  {
    // s  is new start state; a is new accept state
    NFAState s, a;

    switch(op)
    {
      case OR:
        s = new NFAState();
        a = new NFAState();
        s.arrow1 = p;        // make s point to p and q
        s.arrow2 = q;
        // make accept states of p and q NFAs point to a
        p.acceptState.arrow1 = a;
        q.acceptState.arrow1 = a;
        s.acceptState = a;   // make a the accept state
        return s;
      case CONCAT:
        ...
      default:
        throw new RuntimeException("Bad call of make");
    }  
  }
  //----------------------------------------
  public NFAState make(int op, Token t)
  {
    // s is new start state; a is new acccept state
    NFAState s, a;  

    switch(op)
    {
      case CHAR:
        s = new NFAState();
        a = new NFAState();
        s.arrow1 = a;        // make s point to a
        s.label1 = t.image.charAt(0);
        s.acceptState = a;   // make a the accept state
        return s;
      case PERIOD:
        ...
      default:
        throw new RuntimeException("Bad call of maker");
    }
  }
  //----------------------------------------
  public NFAState make(int op, NFAState p)
  {
    // s is new start state; a is new accept state
    NFAState s, a;

    switch(op)
    {
      case STAR:
        ...
      default:
        throw new RuntimeException("Bad call of make");
     }
  }
