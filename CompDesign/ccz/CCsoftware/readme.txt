                                                 readme.txt

                       J1 Software Package
                           Version 1.8
                Copyright (c) 2012 Anthony J. Dos Reis
          
DISCLAIMER                                                    

The J1 Software Package is provided to you on an "as is" basis 
for instructional purposes only, without any warranties, 
expressed or implied.  Neither the author nor the publisher of 
the J1 Software Package shall be liable in any event for 
incidental, consequential, special, or punitive damages in 
connection with, or arising out of the distribution, performance, 
or use of the J1 Software Package.               
                                                                 
================================================================= 
WHAT'S NEW

A finite automaton simulator (f.exe, fmac, flnx) has been added.  
The p and t simulators have been modified slightly so all three 
simulators--f, p, and t--are used is a similar way and produce 
similar output.  The files that define the automata (called 
"definition files") now should all have the extension ".fa", 
".pda", or ".tm" for DFAs, PDAs, and TMs, respectively.  The 
tapes files all have the extension ".t".  p now allows the stack 
to be initialized with an optional command line argument.  For more 
information, see f.txt, fa.txt, p.txt pda.txt, t.txt, turing.txt. 

The execution times for each instruction have been adjusted to 
more realistic values.  For example, the neg instruction now 
takes less time than the mult instruction.

Additional supplementary material has been added to pda.txt and 
turing.txt.

================================================================= 
CONTACTING AUTHOR 

   email:          dosreist@newpaltz.edu  (note "t" at end of dosreis) 

   web address:    www.cs.newpaltz.edu/~dosreis

                   (that's newpaltz, NOT newplatz)

================================================================= 
PLATFORMS SUPPORTED

     Microsoft Windows
     X86 Linux
     Macintosh OS X

=================================================================
INSTALLATION

Note:  To replace one version with another, simply replace the 
files of the old version in the J1 directory with the files from 
the new version.  To uninstall, simply erase the J1 directory. 

Create a directory on your hard disk for the J1 Software Package 
(a specific name for the directory is not required, but "J1" is 
suggested).  Then unarchive the distribution file to your J1 
directory.  

The J1 executables for 

     Windows (a.exe, e.exe l.exe, jlib.exe)
     MacIntosh (a, e, l, jlib)
     Linux (a, e, l, jlib)

are in the J1 subdirectories winExecutables, macExecutables, and 
lnxExecutables, respectively.  

The executables for Windows and the Mac are ALSO in the J1 
directory.  Thus, on a Windows system, to execute a program 


     1) Bring up the command line by executing the Command 
        Prompt program (click on Start, All Programs, Accessories, 
        Command Prompt).

     2) Make the J1 directory the current directory using the
        appropriate cd command.

     3) Enter the program name (a, e, or l) on the command line.

Similarly, on a Mac, 

     1) Bring up the command line by executing the Terminal 
        Program (in the Utilities folder).

     2) Make the J1 directory the current directory using the
        appropriate cd command.

     3) Enter the program name (a, e, or l) on the command line.


On a Linux system,

     1) Copy the Linux executables in the lnxExecutables
        subdirectory up to the J1 directory (this will overlay the
        Mac executables in the J1 directory).

     2) Bring up the command line by executing the Terminal 
        Program.

     3) Make the J1 directory the current directory using the
        appropriate cd command.

     4) Enter the program name (a, e, or l) on the command line.

On a MacIntosh or Linux system, you may have to precede the 
program name with "./", if your current directory is not in the 
search path (this requirement is explained below).  For example, 
on a Mac, to assembly the program in sample.a, if entering 

     a sample.a

does not work, then enter

     ./a sample.a



For non-Microsoft platforms: permissions mode

If you get the message "permission denied" or similar when you 
attempt to execute a program or script in the J1 Software 
Package, you will have to change the permissions mode of each 
program and script to allow its execution.  To do this, enter 

     chmod 700 *

This command gives read, write and execution permission to EVERY 
file, even to non-executable files.  Optionally, you can then 
remove execution permission from the files that are non-
executable with 

     chmod 600 *.*



For non-Microsoft platforms: search path

Your search path may already be set up to include the current 
directory (which allows programs in the current directory to be 
found and executed by the operating system).  You can determine 
your search path by entering 

     echo $PATH

Look for a "." in the string that is displayed ("." represents 
the current directory).

If the current directory is not on the search path, you will get 
a message such as "command not found" when you attempt to invoke a 
program in the current directory.  If you get such a message, you 
must take one of the following actions:

Explicitly specify the current directory whenever you invoke a 
program or script in the J1 Software Package.  To do this, prefix 
the program or script name you enter on the command line with ./  
For example, to invoke e, you would enter 

     ./e

Alternatively, enter the command sequence below to add the 
current directory to the search path (you can then invoke a 
program or script in the J1 Software Package by simply entering 
its name on the command line whenever your J1 directory is the 
current directory).  The command sequence to use depends on the 
shell you are using.  To determine the shell you are using, enter 

     echo $SHELL

on the command line.  If you are on Linux or the Macintosh OS X, 
you are probably using bash.  However, these systems may be set 
up with another shell--so you will have to check your particular 
system to determine the shell in use. 

If you are using bash, to add the current directory (i.e., ".") 
to the search path, enter

     PATH=.:$PATH
     export PATH 

These commands place the current directory (represented by ".") 
at the beginning of the PATH string.  This string determines the 
search path.

You can have your shell AUTOMATICALLY execute the commands to add 
the current directory to the search path each time you login or 
start the terminal program.  Then you do not have to re-enter 
them every time you login or start the terminal program.  To do 
this for bash, put a file named .bash_profile that contains 

     PATH=.:$PATH
     export PATH 

into your home directory.  If .bash_profile already exists, 
insert the two commands above at the end of the .bash_profile 
file.  Do the same for a file named .bashrc

Note that the file names ".bash_profile" and ".bashrc" start 
with a period.  A leading period in a file name makes the file 
"hidden" (i.e., its name will not be displayed by the ls command 
unless the -a argument is used).  For example, to see all the 
files--including the hidden files--in your home directory, enter 

     ls -a

on the command line when you are in your home directory.

.bash_profile is called a "login script" because it is 
automatically executed during a login.  To create or modify a 
login script, you can use a simple text editor, such as joe, 
nano, or pico.  One of these editors is probably installed on 
your system.  For example, to create or modify .bash_profile with 
joe, enter 

     joe .bash_profile

With nano, enter

     nano .bash_profile

With pico, enter

     pico .bash_profile



Potential name conflicts

     l and jlib are identical programs.  If entering l does not 
invoke the library program (it may invoke the ls utility on non-
Microsoft systems), then use jlib instead of l. 

     The order of appearance of directories in the search path 
does not matter if all the programs, batch files, and scripts on 
your system have unique names.  If a program, batch file, or 
script is invoked for which an identically named program, batch 
file, or script exists elsewhere on your system, the operating 
system will execute the first program, batch file, or script with 
the required name it finds as it searches the directories in the 
search path.  It searches the directories in the order listed in 
the search path.  Thus, directories listed earlier are given 
preference over directories that are listed later. 

     Some of the programs, batch files, and scripts in the J1 
Software Package have common names.  You may have programs, batch 
files, or scripts elsewhere on your system that are identically 
named.  Thus, when you attempt to invoke a program, batch file, 
or script in the J1 Software Package, you may, in fact, invoke 
one of these other programs, batch files, or scripts, depending 
on the search path in effect.  The PATH commands described above 
place the current directory FIRST in the search path, so you 
should always be able to invoke the programs in your current 
directory.  If, however, you are using a different search path, 
and a name conflict occurs, you can simply rename the program in 
the J1 directory for which a conflict occurs to a unique name.  
For example, if you have a name conflict with "e", you can rename 
e to "myveryowne".  Alternatively, you can change your search 
path so that the directory containing the programs you wish to 
execute is first.  



OPTIONAL: INVOKING PROGRAMS FROM OUTSIDE THE J1 DIRECTORY

If you want to run the programs in the J1 Software Package when 
your J1 directory is NOT the current directory, you have to place 
your J1 directory on the search path.  You should also set the 
J1DIR environment variable.

For Microsoft platforms (assuming the J1 directory is C:\J1): 
enter

     SET PATH=C:\J1;%PATH%      
     SET J1DIR=C:\J1



For non-Microsoft platforms (assuming the J1 directory is right 
below your home directory):


For bash, enter

     PATH=~/J1:$PATH 
     export PATH
     J1DIR=~/J1
     export J1DIR

"~" in the bash command represents your home directory. Thus, if 
your J1 directory is right below your home directory, its 
complete pathname is provided by ~/J1. 

You can have your shell automatically execute these commands any 
time you boot or login.  To do this with older Microsoft 
platforms, put the first set of commands above in the 
autoexec.bat file in your root directory.  With Windows, set the 
PATH and J1DIR variables with the following sequence of mouse 
clicks: Control Panel, Performance and Maintenance, System, 
Advanced, Environment Variables (or some variation of this 
sequence, depending on the particular Windows system you have).  
Then make the required changes.  With bash, put the bash commands 
above in the files named .bash_profile and .bashrc.  Place these 
files in your your home directory. 
================================================================= 
RUNNING THE PROGRAMS IN THE J1 SOFTWARE PACKAGE 

The best way to run the programs in the J1 Software Package is 
from the command line.  Place your system in command line mode by 
invoking the appropriate command or terminal program for your 
system (on Windows, run the Command prompt program; on the 
Macintosh and Linux, run the Terminal utility).  Make your J1 
directory the current directory by entering 

     cd  completepathnameofyourJ1directory

substituting the complete pathname of your J1 directory for 
"completepathnameofyourJ1directory" in the command above.  On 
non-Microsoft systems, you can use "~" to represent your home 
directory.  For example, if "J1" is the name of your J1 directory 
and it is right below your home directory, then the following 
command makes your J1 directory the current directory: 

     cd ~/J1

Here "~/J1" represents the complete pathname of the J1 directory. 
You can also use a relative pathname in the cd command.  For 
example, if you are in your home directory and the J1 directory 
is immediately below your home directory, you can make the J1 
directory current by entering 

     cd J1

Here "J1" is the pathname of the J1 directory, relative to the 
current directory (your home directory).  

On a Macintosh or Linux system, you can type 

     cd 

followed by one space and then drag the icon representing your J1 
directory to the immediate right of the "cd " you just typed.  
The full pathname of your J1 directory will then appear in the cd 
command.  Then hit the Enter key.  With this technique, you do 
not have to type in the name of your J1 directory when you use 
the cd command. 

Once your J1 directory is the current directory, you can invoke a 
program in the J1 Software Package by simply entering on the 
command line the program's name and any arguments.  If you have 
any problems running programs, refer to the INSTALLATION 
SECTION above.

=================================================================
USER INTERFACE

When any program in this package is invoked without the required 
arguments, it prompts the user for them.  The user can then enter 
these arguments and any additional desired arguments.  

For all the programs in this package, a help screen is displayed 
if /h, -h, /?, or -? appears on the command line.  On some 
platforms (i.e., the non-Microsoft platforms), "/?" or "-?" will 
not work because of the special way the command shell interprets 
the question mark on the command line.  On these systems, use /h 
or -h.  

     
=================================================================
PACKING LIST


PROGRAMS
  a             Assembler/linker
  e             J1 interpreter
  l             Library creator
  jlib          Identical to l.  Use in case of a name conflict.

  Note: the a, e, and l programs support both the stack and register 
  instruction sets.

  To download JavaCC, go to
  http://javacc.java.net

  To download BYACC/J,  go to
  http://byaccj.sourceforge.net

  To download Jflex, go to
  http://jflex.de

  
DOCUMENTATION

  a.txt             Documentation on a assembler/linker program
  e.txt             Documentation on e interpreter program
  l.txt             Documentatin on the l library maker
  readme.txt        This document
  register.txt      Summary of the register instruction set
  stack.txt         Summary of the stack instruction set
 


START-UP CODE

  sup.o             Start-up code for the stack instruction set
  rup.o             Start-up code for the register instruction set


COMPILERS

  Compilers which output assembly code in stack instruction set:

    S1.java         S1 hand-written version
    S1j.jj          S1 JavaCC version
    S1y.y           S1 Byacc/j version
    S1ly.y          S1 jflex version
    S1l.l           jflex yylex for S1ly.y
    


FIGURES FROM TEXTBOOK
  
  ArgsTokenMgr.java
  Fig0612.java
  Fig0804.java
  Fig0901.java
  Fig1001.java
  Fig1004.java
  Fig1010.java
  Fig1704.java
  make.java (Fig. 18.7)
  match.java (Fig. 18.11)
  NFAState.java (Fig. 18.4)
  Token.java (Fig. 10.8)

  fig1103.a
  fig1104.a
  fig1109.a
  fig1110.a
  fig1111.a
  fig1112.a

  Fig1319.jj
  Fig1320.jj
  Fig1321.jj
  Fig1322.jj
  Fig1323.jj
  Fig1507.jj
  Fig1508.jj
  Fig1509.jj

  Fig1603.s

  Fig2302.y
  Fig2304.y
  Fig2305.y
  Fig2309.y

  Fig2314.l
  Fig2315.l


SOURCE PROGRAMS FOR TESTING COMPILERS

  S1.s
  S2.s
  S3.s
  S4.s
  S5a.s
  S5b.s
  S6a.s
  S6b.s


TEST FILES FOR PROBLEMS

  f.txt
  grep.txt
  p1501.s
  p1502.s
  p1521.s
  p1611.s
  p1612.s
  p1613.s
  p2324.txt
  p2325.txt
  p2326.txt
  p2327.txt
  prefix.txt
  S1414.s
  test.txt


TRANSLATION GRAMMARS

  S1.tg
  S2.tg
  S5.tg (with omissions)
  R1.tg


AUTOMATA PACKAGES

  fa.zip            Finite automaton package
  pda.zip           Pushdown automaton package
  tm.zip            Turing machine package


MISCELLANEOUS

  017.___           Platform markers (win, lnx, mac, ppc)
  ___.chk           chk files used by the e program
  testtm.jj         for testing features of JavaCC token managers
  testp.jj          for testing features of JavaCC parsers
  m1.s              for illustrating linking with sup
  m2.s              for illustrating linking with sup


=================================================================
FILE EXTENSIONS

Extension                    Type of File

none                Linux/Macintosh executable program or script 
a                   J1 assembly language source file
rsp                 Response file for the a and l programs
bat                 Windows batch file
chk                 chk file (used by the e program)
class               Java class
e                   J1 executable file
eee                 e program name file
exe                 Windows executable program
java                Java source
jj                  JavaCC input file
l                   Library file
log                 log file for the e program
lst                 list file produced by a assembler program
num                 line-numbered files
o                   J1 object file
s                   Source code for testing compilers
tg                  Translation grammar
txt                 Documentation or reference
y                   BYACC/J input file 
