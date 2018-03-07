SetStuff by Quintin Dwight
==========================

Introduction
------------

Our class is eventually going to implement dijkstra's node transversing algorithm to find the shortest path between two nodes.
These are some starter functions that focus on a narrow subset of the algorithm and general concepts.

Specification
-------------

This program meets all of the specifications listed at http://codingbat.com/home/201718palycs@gmail.com/setstuff.

Errors
------

There were no errors encountered based on the JUnit tests in SetStuffTest.java.
All tests have passed the given cases. Cases were chosen base of those in the CodingBat and what are corner cases.
One error could be the fact that anyContains is inefficient, it loops over the elements more than it needs to.

Overview of Code
----------------

The functions have specific documentation as they are a collection of different things.
All relate to dijkstra's algorithm somehow.

Challenges
----------

The union and intersection problems were tough because elements have to be in a specific order and cannot be repeated.
I ended up using Java's different sets to accomplish this task.

Acknowledgement
---------------

https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html
https://docs.oracle.com/javase/7/docs/api/java/util/LinkedHashSet.html
https://www.jetbrains.com/help/idea/configuring-testing-libraries.html
https://www.jetbrains.com/help/idea/creating-tests.html
