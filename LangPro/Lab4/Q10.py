# Q7.py parser
# Grammar:
#    <S> -> 'a'<B>
#    <S> -> 'a'<C>
#    <B> -> 'b''b''b'
#    <C> -> 'c''c''c'
# This is a bad grammar because it is not LL(1)
#
#    <S> -> 'a'<X>
#    <X> -> <B>
#    <X> -> <C>
#    <B> -> 'b''b''b'
#    <C> -> 'c''c''c'
#
import sys   # needed to access command line arg

#global variables
tokenindex = -1
token = ''

def main():
    try:
        parser()         # call the parser
    except RuntimeError as emsg:
        print(emsg)

def advance():
    global tokenindex, token
    tokenindex += 1     # move tokenindex to next token
    # check for null string or end of string
    if len(sys.argv) < 2 or tokenindex >= len(sys.argv[1]):
        token = ''      # signal the end by returning null string
    else:
        token = sys.argv[1][tokenindex]

def consume(expected):
    if expected == token:
        advance()
    else:
        raise RuntimeError('Expecting ' + expected)

def parser():
    advance()   # prime token with first character
    S()         # call function for start symbol
    # test if end of input string
    if token != '': 
        print('Garbage following <S>-string')

def S():
    consume('a')
    if token == 'b':
        B()
    elif token == 'c':
        C()
    else:
        raise RuntimeError('Expected a b or c')

def B():
    advance()
    consume('b')
    consume('b')

def C():
    advance()
    consume('c')
    consume('c')

main()