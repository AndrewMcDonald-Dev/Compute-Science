# spbt.py backtracking parser
# Grammar:
#    <S> -> <A><B><C>
#    <A> -> 'a'
#    <A> -> ^
#    <B> -> 'b'
#    <B> -> ^
#    <C> -> 'c'
#    <C> -> ^
import sys   # needed to access command line arg

#global variables
tokenindex = -1
curchar = ''

def main():
   parser()      # call the parser

def parser():
   advance()   # prime curchar with first character
   if S():
      if curchar == '': 
         print('String in language')
      else:
         print('Garbage following string')
   else:
      print('String not in language')

def S():
   return A() and B() and C()

def A():
   global tokenindex
   save = tokenindex
   if A1():
      return True
   else:
      tokenindex=save
      return A2()

def A1():
   return consume('a')
def A2():
   return True
   
def B():
   global tokenindex
   save = tokenindex
   if B1():
      return True
   else:
      tokenindex = save
      return B2()

def B1():
   consume('b')
def B2():
   return True

def C():
   global tokenindex
   save = tokenindex
   if C1():
      return True
   else:                  # backtrack if C1() doesn't work
      tokenindex = save
      return C2()

def C1():
   return consume('c')
def C2():
   return True

def advance():
   global tokenindex, curchar
   tokenindex += 1    # move tokenindex to next token
   # check for null string or end of string
   if len(sys.argv) < 2 or tokenindex >= len(sys.argv[1]):
      curchar = ''    # signal the end by returning ''
   else:
      curchar = sys.argv[1][tokenindex]

def consume(expected):
   if expected == curchar:
      advance()
      return True
   else:
      return False

main()
