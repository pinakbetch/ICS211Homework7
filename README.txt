README

ICS 211 Homework 7

This homework program attempts to solve a 16 by 16 sudoku puzzle in a recursive way.

The idea is to go through each of the possible combinations of numbers in the empty
spaces by first checking which values are legal and then making a "possible solution
tree" out of those values. 

The reason that the method is considered recursive, is because it calls itself to 
get the job done. 


Most of the testing for this program was done with the provided test class. On top
of that testing, I also checked to make sure that the solver would fail when it 
should.


--------------

Discussion on the possible issues of using a method like this to solve harder sudokus.

The less filled out the sudoku is to start out, the more time the solver takes to 
solve the sudoku. For every extra empty spot there can be up to 16 times more solutions
which adds up very quickly. When running the first example sudoku through the solver,
it finishes in less than a second. The second one takes about 10 seconds and the third
one finishes in about 2 minutes. The final sudoku finishes in about 5 minutes. 

With this in mind it would be possible to have a particular sudoku with a very small
number of solutions take an extremely long time to solve.
