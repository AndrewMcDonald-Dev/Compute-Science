# c2.py compiler 
import sys, time 

class Token:
   def __init__(self, line, column, category, lexeme):
      self.line = line         # source program line number of the token
      self.column = column     # source program column in which token starts
      self.category = category # category of the token
      self.lexeme = lexeme     # token in string form

# global variables 
oufile = None      # output (i.e., asm lang program) file
source = ''        # receives entire source program
sourceindex = 0    # index into the source code in source
line = 0           # current line number 
column = 0         # current column number
tokens = []        # list of tokens produced by tokenizer
tokenindex = -1    # index into tokens list
token = None       # current token
prevchar = '\n'    # '\n' in prevchar signals start of new line
blankline = True
symbol = []        # list of variable names
value = []
tempcount = 0      # sequence number for temp asm variables
sign = +1
loc = []
needword = []
nextreg = 4

# constants
EOF           = 0      # end of file
PRINT         = 1      # 'print' keyword
UNSIGNEDINT   = 2      # unsigned integer
NAME          = 3      # identifier that is not a keyword
ASSIGNOP      = 4      # '=' assignment operator
LEFTPAREN     = 5      # '('
RIGHTPAREN    = 6      # ')'
PLUS          = 7      # '+'
MINUS         = 8      # '-'
TIMES         = 9      # '*'
NEWLINE       = 10     # end of line
ERROR         = 11     # if not any of the above, then error

# displayable names for each token category
catnames = ['EOF', 'PRINT', 'UNSIGNEDINT', 'NAME', 'ASSIGNOP',
            'LEFTPAREN', 'RIGHTPAREN', 'PLUS', 'MINUS',
            'TIMES', 'NEWLINE', 'ERROR']

# keywords and their token categories}
keywords = {'print': PRINT}

# one-character tokens and their token categories
smalltokens = {'=':ASSIGNOP, '(':LEFTPAREN, ')':RIGHTPAREN,
               '+':PLUS, '-':MINUS, '*':TIMES, '\n':NEWLINE, '':EOF}

#################
# main function #
#################
def main():
   global source, tokens, token, outfile, lines

   if len(sys.argv) == 3:
      try:
         infile = open(sys.argv[1], 'r')
         source = infile.read()   # read source code
      except IOError:
         print('Cannot read input file ' + sys.argv[1])
         sys.exit(1)

      try:
         outfile = open(sys.argv[2], 'w')
      except IOError:
         print('Cannot write to output file ' + sys.argv[2])
         sys.exit(1)
         
   else:
      print('Wrong number of command line arguments')
      print('Format: python c1.py <infile> <outfile>')
      sys.exit(1)

   if source[-1] != '\n':
      source = source + '\n'
   lines = source.splitlines()

   outfile.write('@ ' + time.strftime('%c') + '%34s' % 'Andrew McDonald\n')
   outfile.write('@ ' + 'Compiler    = ' + sys.argv[0] + '\n')
   outfile.write('@ ' + 'Input file  = ' + sys.argv[1] + '\n')
   outfile.write('@ ' + 'Output file = ' + sys.argv[2] + '\n')

   try:
      tokenizer()             
      # parse and and generate assembly language
      outfile.write(
         '@------------------------------------------- Assembler code\n')
      parser()
 
   # on an error, display an error message
   # token is the token object on which the error was detected
   except RuntimeError as emsg: 
     # output slash n in place of newline
     lexeme = token.lexeme.replace('\n', '\\n')
     print('\nError on '+ "'" + lexeme + "'" + ' line ' +
        str(token.line) + ' column ' + str(token.column))
     print(emsg)         # message from RuntimeError object
     outfile.write('\nError on '+ "'" + lexeme + "'" + ' line ' +
        str(token.line) + ' column ' + str(token.column) + '\n')
     outfile.write(str(emsg) + '\n') # message from RuntimeError object
#     outfile.write(emsg.args[0] + '\n') # message from RuntimeError object

   outfile.close()

####################
# tokenizer        #
####################
def tokenizer():
   global token
   curchar = ' '                       # prime curchar with space

   while True:
      # skip whitespace but not newlines
      while curchar != '\n' and curchar.isspace():
         curchar = getchar() # get next char from source program

      # construct and initialize token
      token = Token(line, column, None, '')  

      if curchar.isdigit():            # start of unsigned int?
         token.category = UNSIGNEDINT  # save category of token
         while True:
            token.lexeme += curchar    # append curchar to lexeme
            curchar = getchar()        # get next character
            if not curchar.isdigit():  # break if not a digit
               break

      elif curchar.isalpha() or curchar == '_':   # start of name?
         while True:
            token.lexeme += curchar    # append curchar to lexeme
            curchar = getchar()        # get next character
            # break if not letter, '_', or digit
            if not (curchar.isalnum() or curchar == '_'):
               break

         # determine if lexeme is a keyword or name of variable
         if token.lexeme in keywords:
            token.category = keywords[token.lexeme]
         else:
            token.category = NAME

      elif curchar in smalltokens:
         token.category = smalltokens[curchar]      # get category
         token.lexeme = curchar
         curchar = getchar()      # move to first char after the token

      else:                         
         token.category = ERROR    # invalid token 
         token.lexeme = curchar    # save lexeme
         raise RuntimeError('Invalid token')
      
      tokens.append(token)          # append token to tokens list
      if token.category == EOF:     # finished tokenizing?
         break

# getchar() gets next char from source and adjusts line and column
def getchar():
   global sourceindex, column, line, prevchar, blankline

   # check if starting a new line
   if prevchar == '\n':    # '\n' signals start of a new line
      line += 1            # increment line number                             
      column = 0           # reset column number
      blankline = True     # initialize blankline

   if sourceindex >= len(source): # at end of source code?
      column = 1                  # set EOF column to 1
      prevchar = ''
      return ''                   # null char signals end of source

   c = source[sourceindex] # get next char in the source program
   sourceindex += 1        # increment sourceindex to next character
   column += 1             # increment column number
   if not c.isspace():     # if c not whitespace then line not blank
      blankline = False    # indicate line not blank
   prevchar = c            # save current character

   # if at end of blank line, return space in place of '\n'
   if c == '\n' and blankline:
      return ' '
   else:
      return c             # return character to tokenizer()

##########################
# symbol table function  #
##########################
def enter(s, v, w):
   global nextreg
   if s in symbol:
      return symbol.index(s)
   # otherwise, add s to symbol and return its index
   if s.startswith('.i') or nextreg > 12:
      loc.append(None)
   else:
         loc.append('r' + str(nextreg))
         nextreg += 1

   index = len(symbol)
   symbol.append(s)
   value.append(v)
   needword.append(w)
   return index

####################
# parser functions #
####################
def advance():
   global token, tokenindex 
   tokenindex += 1
   if tokenindex >= len(tokens):
      raise RuntimeError('Unexpected end of file')
   token = tokens[tokenindex]

# advances if current token is the expected token
def consume(expectedcat):
   if (token.category == expectedcat):
      advance()
   else:
     raise RuntimeError('Expecting ' + catnames[expectedcat])

def parser():
   advance()     # advance to first token
   cg_prolog()   # generates prolog assembly code
   program()     # generates assembly code for program
   cg_epilog()   # generates epilog assembly code

def program():
   while token.category in [NEWLINE, NAME, PRINT]:
      # output source code as a comment
      outfile.write('\n@ ' + lines[token.line - 1] + '\n')
      stmt()   # parse statement
   if token.category != EOF: # garbage at end?
      raise RuntimeError('Expecting end of file')

def stmt():
   simplestmt()
   consume(NEWLINE)

def simplestmt():
   if token.category == NAME:
      assignmentstmt()
   elif token.category == PRINT:    
      printstmt() 
   else:
      raise RuntimeError('Expecting statement')

def assignmentstmt():
    leftindex  = enter(token.lexeme, '0', False)
    advance()
    consume(ASSIGNOP)
    rightindex = expr()                        
    cg_assign(leftindex, rightindex)

def printstmt():
    advance()
    consume(LEFTPAREN)
    exprindex = expr()
    cg_print(exprindex)
    consume(RIGHTPAREN)

def expr():
   leftindex  = term()
   while token.category == PLUS:
      advance()
      rightindex = term()
      leftindex  = cg_add(leftindex, rightindex)
   return leftindex

def term():
   global sign
   sign = +1
   leftindex = factor()
   while token.category == TIMES:
      advance()
      sign = +1
      rightindex  = factor()
      leftindex = cg_mul(leftindex, rightindex)
   return leftindex

def factor():
   global sign
   if token.category == PLUS:
      advance()
      return factor()
   elif token.category == MINUS:
      sign = -sign
      advance()
      return factor()
   elif token.category == UNSIGNEDINT:
      if sign == +1:
         index  = enter('.i' + token.lexeme, token.lexeme, False)
      else:
         index  = enter('.i_' + token.lexeme, '-' + token.lexeme, False)
      advance()
      return index
   elif token.category == NAME:
      index = enter(token.lexeme, '0', False)
      if sign == -1:
         index = cg_neg(index)
      advance()
      return index
   elif token.category == LEFTPAREN:
      consume(LEFTPAREN)
      savesign = sign
      index = expr()
      if savesign == -1:
         index = cg_neg(index)
      consume(RIGHTPAREN)
      return index
   else:
      raise RuntimeError('Expecting factor')

############################
# code generator functions #
############################
def cg_prolog():
   outfile.write('          .global main\n')
   outfile.write('          .text\n')
   outfile.write('main:\n')
   outfile.write('          push {lr}\n')

def cg_epilog():
   outfile.write('\n')
   outfile.write('          mov r0, #0\n')
   outfile.write('          pop {pc}\n\n')
   if needfmt0:
      outfile.write('.fmt0:      .asciz "%d\\n"\n')
   size = len(symbol)
   i = 0
   while i < size:
     outfile.write('%-10s' % (symbol[i] + ':') + 
                   '.word ' + value[i] + '\n')
     i += 1 
  
def cg_gettemp():
   global tempcount
   temp = '.t' + str(tempcount)
   tempcount += 1
   return enter(temp, '0', False)
  
def cg_assign(leftindex, rightindex):
   global tempcount

   if symbol[rightindex].startswith('.t'):
      tempcount -= 1

   if symbol[rightindex].startswith('.i'):
      constonright = True
   else:
      constonright = False
   if loc[rightindex] == None:
      regonright = False
   else:
      regonright = True
      rightreg = loc[rightindex]
   if loc[leftindex] == None:
      regonleft = False
   else:
      regonleft = True
      leftreg = loc[leftindex]

   
   if regonleft and regonright:
      outfile.write('      mov ' + leftreg + ', ' + rightreg + '\n')
   
   elif regonleft and constonright:
      outfile.write('      ldr ' + leftreg + ', ' + symbol[rightindex] + '\n')
      needword[rightindex] =True
   
   elif regonleft and not constonright:
      outfile.write('      ldr r0, =' + symbol[rightindex] + '\n')
      outfile.write('      ldr ' + leftreg + ', [r0]\n')
      needword[leftindex] =True

   elif regonright:
      outfile.write('      ldr r0, ='+ symbol[leftindex] + '\n')
      outfile.write('      str ' + rightreg + ', [r0]\n')
      needword[leftindex] = True

   elif constonright:
      outfile.write('      ldr r0, ' + symbol[rightindex] + '\n')
      outfile.write('      ldr r1, =' + symbol[leftindex] + '\n')
      outfile.write('      str r0, [r1]\n')
      needword[rightindex] = True
      needword[leftindex] = True

   else: 
      outfile.write('      ldr r0, =' + symbol[rightindex] + '\n')
      outfile.write('      ldr r0, [r0]\n')
      outfile.write('      ldr r1, =' + symbol[rightindex] + '\n')
      outfile.write('      str r0, [r1]\n')
      needword[rightindex] = True
      needword[leftindex] =True

  
def cg_print(index):
   global tempcount, needfmt0

   needfmt0 = True
   outfile.write('      ldr r0, =.fmt0\n')

   if symbol[index].startswith('.t'):
      tempcount -= 1

   if loc[index] == None:
      if symbol[index].startswith('.i'):
         outfile.write('      ldr r1, ' + symbol[index] + '\n')
      else:
         outfile.write('      ldr r1, =' + symbol[index] + '\n')
         outfile.write('      ldr r1, [r1]\n')
      needword[index] = True
   else:
      outfile.write('      mov r1, ' + loc[index] + '\n')
   outfile.write('      bl printf\n')

  
def cg_add(leftindex, rightindex):
   global tempcount

   if symbol[leftindex].startswith('.i') and symbol[rightindex].startswith('.i'):
      result = int(value[leftindex]) + int(value[rightindex])
      if result >= 0:
         return enter('.i' + str(result), str(result), False)
      else:
         return enter('.i_' + str(-result), str(result), False)

   if symbol[leftindex].startswith('.t'):
      tempcount -= 1
   if symbol[rightindex].startswith('.t'):
      tempcount -= 1
   
   leftreg = 'r0'
   rightreg ='r1'
   destreg = 'r0'

   if loc[leftindex] == None:
      if symbol[leftindex].startswith('.i'):
         output.write('       ldr r0, ' + symbol[leftindex] + '\n')
      else:
         outfile.write('      ldr r0, =' + symbol[leftindex] + '\n')
         outfile.write('      ldr r0, [r0]\n')
      needword[leftindex] = True
   else:
      leftreg = loc[leftindex]

   
   if loc[rightindex] == None:
      if symbol[rightindex].startswith('.i'):
         outfile.write('       ldr r1, ' + symbol[rightindex] + '\n')
      else:
         outfile.write('      ldr r1, =' + symbol[rightindex] + '\n')
         outfile.write('      ldr r1, [r1]\n')
      needword[rightindex] = True
   else:
      rightreg = loc[rightindex]

   tempindex = cg_gettemp()
   if symbol[tempindex] != None:
      destreg = loc[tempindex]
   outfile.write('      add ' + destreg + ', ' + leftreg + ', ' +rightreg + '\n')

   if symbol[tempindex] == None:
      outfile.write('      ldr r1, =' + symbol[tempindex] + '\n')
      outfile.write('      str r0, [r1]\n')
      needword[tempindex] = True
   return tempindex

   

def cg_mul(leftindex, rightindex):
   global tempcount

   if symbol[leftindex].startswith('.i') and symbol[rightindex].startswith('.i'):
      result = int(value[leftindex]) * int(value[rightindex])
      if result >= 0:
         return enter('.i' + str(result), str(result), False)
      else:
         return enter('.i_' + str(-result), str(result), False)

   if symbol[leftindex].startswith('.t'):
      tempcount -= 1
   if symbol[rightindex].startswith('.t'):
      tempcount -= 1
   
   leftreg = 'r0'
   rightreg ='r1'
   destreg = 'r0'

   if loc[leftindex] == None:
      if symbol[leftindex].startswith('.i'):
         outfile.write('       ldr r0, ' + symbol[leftindex] + '\n')
      else:
         outfile.write('      ldr r0, =' + symbol[leftindex] + '\n')
         outfile.write('      ldr r0, [r0]\n')
      needword[leftindex] = True
   else:
      leftreg = loc[leftindex]

   
   if loc[rightindex] == None:
      if symbol[rightindex].startswith('.i'):
         outfile.write('       ldr r1, ' + symbol[rightindex] + '\n')
      else:
         outfile.write('      ldr r1, =' + symbol[rightindex] + '\n')
         outfile.write('      ldr r1, [r1]\n')
      needword[rightindex] = True
   else:
      rightreg = loc[rightindex]

   tempindex = cg_gettemp()
   if symbol[tempindex] != None:
      destreg = loc[tempindex]
   outfile.write('      mul ' + destreg + ', ' + leftreg + ', ' +rightreg + '\n')

   if symbol[tempindex] == None:
      outfile.write('      ldr r1, =' + symbol[tempindex] + '\n')
      outfile.write('      str r0, [r1]\n')
      needword[tempindex] = True
   return tempindex

def cg_neg(index):
   global tempcount

   if symbol[index].startswith('.t'):
      tempcount -= 1
   if loc[index] == None:
      if symbol[index].startswith('.i'):
         outfile.write('      ldr r0, ' + symbol[index] + '\n')
      else:
         outfile.write('      ldr r0, =' + symbol[index] + '\n')
         outfile.write('      ldr r0, [r0]\n')
      needword[index] ==True
   else:
      outfile.write('      mov r0, ' + loc[index] + '\n')
   outfile.write('      neg r0, r0\n')
   tempindex = cg_gettemp()
   if loc[tempindex] == None:
      outfile.write('      ldr r1, =' + symbol[tempindex] + '\n')
      outfile.write('      str r0, [r1]\n')
      needword[tempindex] = True
   else:
      outfile.write('      mov ' + loc[tempindex] + ', r0\n')
   return tempindex

####################
# start of program #
####################
main()
