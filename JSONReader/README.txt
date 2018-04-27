Specifications
==============

This program has the ability to read text from a file, which can possibly introduce many exceptions, interpret it as JSON and parse it into a HashMap.
If the JSON string obtained is invalid, the program will raise an IllegalArgumentException.
Based on the exceptions, a log statement will be given to the user to explain what exactly went wrong.

Challenges
==========

One challenge I had was the fact that a key can have a value of another object, so splitting the string must be met with precaution.
A string can only be split by a character if the correct level (visualized by tabs) is met.
This is used to distinguish splitting elements of an array from properties since they both uses commas.

Errors
======

The program has not been tested extensively, and because some recursion is used there might be a case where an infinite loop is reached.

Acknowledgements
================
https://www.json.org/ - Specifications of JSON were read here
https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html - How to use regex to split and match on a String
https://docs.oracle.com/javase/tutorial/essential/io/file.html - Reading/writing a file in Java
