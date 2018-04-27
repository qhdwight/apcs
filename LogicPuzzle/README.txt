Logical Puzzle Writeup by Quintin Dwight
========================================

Introduction
------------

This program implements the classes and functions to make the code in the Main class work,
which was provided by Mr. Kuszmual.

Specification
-------------

This program can evaluate logical sentences given truth assignments,
has utility to determine whether or not a string represents a legal logical sentence,
and can make a truth table for any amount of propositional constants.
It has all of the basic operators, including negation, disjunction, conjunction, conditional, bi-conditional, and some others.

Overview of Code
----------------

The PropositionalConstant class represents a constant with a name.
The TruthAssignment class extends a hash map that maps a propositional constant to a boolean value.
This is used for evaluating functions later.
The LogicalSentence class contains a reference to a propositional constant.
This is the base class for all operators, including the AbstractOperator class,
which has left and a right logical sentence fields.
All of the operator classes besides negation use this class as a base.
Each one of the classes that extend logical sentence have an evaluate function that takes a truth assignment as a parameter.
The logical sentence class is the only one that ever accesses the hash map inside of the truth assignment,
all of the other operators call evaluate on the logical sentences that they contain.

Timeline
--------

I started out by copying the code from the main function provided and seeing what was incorrect.
I then made all of the classes to satisfy the error messages, but all though the code ran,
it did not do anything meaningful. So, I made the classes work,
starting with the logical sentence class. This is the basis of all the other classes,
so then I worked on negation and some of the other operators.
I then imported my legal sentence checker from a previous project,
worked on the truth table code, and then made an abstract class for operators with two inputs.
Then, I added the remaining operators (conditional and bi-conditional).

Challenges
----------

One of the challenges I had was making sure that all of the operator extended logical sentence or a
subclass of it. This allows for the polymorphism in the main function.
I had some difficulties with matching the super constructor because a operator taking two inputs
cannot match the constructor of a logical sentence, which is just one constant.
So, I made a blank constructor in logical sentence which allowed it so that the subclass could just call the default constructor.

I also had a little trouble with the truth table. I remembered last year about the row number as a binary string trick,
but I could not remember the function. Eventually I found it.

Acknowledgement
---------------

I would like to acknowledge Mr. Kuszmaul for showing me last year that the boolean values for a
row in a truth table can be represented as that row number as a binary string.
