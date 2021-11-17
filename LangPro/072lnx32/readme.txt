                          Installation
                          

Quick Start
-----------

To install the automata package, simply unzip it into a folder of 
your own choosing.  

On a Mac or Linux machine, make the f, p, and t programs 
executable with the chmod command (see the Mac/Linux section 
below). Optionally, put the current directory in the PATH 
environment variable (see Mac/Linux section below).


Using the cd command
--------------------

In order to invoke the automata programs, you have to make the 
folder that contains the automata programs the current folder. To 
do this, use the cd command as follows:  

On a Windows system, click on all programs, accessories, and 
command prompt. The command prompt program will then display the 
command line prompt. At the command line prompt, enter cd 
followed by a space. Do NOT hit Enter.  Then drag the automata 
folder to the right of the cd command. The automata folder name 
will then be appended to the cd command. Now hit Enter.  Your 
automata folder will then be the current folder. 

On a Mac or Linux machine, invoke the terminal program (it is in the
utilities folder on a Mac). The terminal program will then display
the command line prompt. At the command line prompt, enter cd 
followed by a space. Do NOT hit Enter.  Then drag the automata 
folder to the right of the cd command. The automata folder name 
will then be appended to the cd command. Now hit Enter.  Your 
automata folder will then be the current folder.    


Windows Installation
--------------------
No additional steps are needed after unzipping the automata package 
into a folder of your own choosing. 

To invoke a program, first invoke the command prompt program, and 
make your automata folder the current folder. Then on the command 
line, you can invoke the f, p, or t programs.  For example, to 
invoke the f program so that it processes the odd.fa automaton, 
enter on the command line,

f odd


Macintosh/Linux Installation
----------------------------

After upzipping the automata package into a folder of your own 
choosing, invoke the terminal program (on the Mac, it is in the 
utilities folder). Make the folder that contains the automata 
programs current using the cd command.  Then make the automata 
programs executable by entering 

chmod 700 * 

You can then invoke the f, p, and t programs.  However, when 
invoking f, p, or t, you will have to prefix the program name with 
"./".  For example, to invoke the f program so that it processes 
the odd.fa automaton, enter at the command line prompt

./f odd


To set up Mac/Linux so you do not have to use "./"         
------------------------------------------------- 

The operating systems on the Mac and Linux systems by default do 
not have the current directory in the PATH environment variable.  
The PATH environment variable contains the list of folders that 
are searched when on the command line you invoke a program.  So 
when your automata folder is the current folder, and you invoke 
f, p, or t, the operating system will not find the program you
are invokeing.

Solution: Prefix each program invocation with "./" (the period "." 
represents the current directory), as shown above.

Better Solution: Put the current directory in the PATH environment 
variable. Then the "./" prefix is not needed.

Windows always searches the current directory in addition to 
every directory in the PATH environment variable. So no modification 
of the PATH variable is needed on a Windows system. 


Modifying the PATH environment variable (Mac and Linux only)
-------------------------------------------------------------

Bring up the terminal program (it is in the utilities folder on a 
Mac).

Create a text file named .bash_profile that contains the single 
line 

PATH=.:$PATH

To do this with nano, a simple text editor on the Mac and Linux, 
on the command line, enter 

nano .bash_profile

Note the period at the beginning of the file name.

Then enter

PATH=.:$PATH

Then exit (control-x) and save the file.

To make .bash_profile executable, enter on the command line,

chmod 700 *

Exit the terminal program, and then restart it.  You now should 
not have to prefix your invocations of f, p, and t with "./". If, 
however, the OS still cannot find a program in the current 
directory, then enter on the command line, 

cp .bash_profile .bashrc
chmod 700 *

