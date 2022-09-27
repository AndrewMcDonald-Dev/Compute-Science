      // process input line in buf
      for (startIndex = 0; startIndex < buf.length(); 
                                           startIndex++)
      {
        currentStates.clear();
        currentStates.add(startState);
        bufIndex = startIndex;

        // apply substring starting at bufIndex to
        // NFA.  Exit on an accept, end of substring,
        // or trap state 
        while (true)
        {
          gotAccept = lambdaClosure();
          if (gotAccept          // accept state entered
            || bufIndex >= buf.length() // end substring
            || currentStates.size() == 0)  // trap state
            break;
          applyChar(buf.charAt(bufIndex++));
        }

        // display line if match occurred somewhere
        if (gotAccept)
        {
          System.out.println(buf);
          break;                      // go to next line
        }
      }   // end of for loop
