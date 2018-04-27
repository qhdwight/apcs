Dijkstra Implementation by Quintin Dwight
=========================================

Introduction
------------

Graph theory and transversal comes up a lot in computer science. Especially in social media related fields,
which friends we have, how connected we are to them, etc. Graphs can also be used to represent roads in a city and
connecting flights. One common problem arises, how to transverse these effectively, and finding the smallest distance
between two nodes/places can prove very useful. This is where Dijkstra's algorithm comes into play.

Questions and Answers
=====================

The following graph is used for answers below: {{{0,11},{1,22},{3,44}}, {{1,32},{2,43},{3,27}},{{0,10}},{{2,22},{3,55}}}

Q1: The distance from node one to node two is 43 in the example.
Q2: See Node.java for the constructor
Q3: See Dijkstra.java main function for construction of example on Wikapedia
Q5: The additional function needed was getNeighboringNodes().
Q6: See project

Specifications
--------------

This program meets the specifications described at https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm.
It uses Dijkstra's original algorithm, not the later developed one that uses priority queues.
It finds the shortest path between two given nodes in a graph.

Errors
------

No errors were discovered with my two test cases, however I do believe that further, extensive testing could
unearth some errors in the code. I especially think edge (heh) cases might mess up some parts of the code.

Overview of Code
----------------

There are three main classes, Dijkstra.java, Graph.java, and Node.java. The Dijkstra class is were all the algorithm is,
where as graph and node provide an interface for dealing with graphs. Graph also has helper functions used by the
algorithm such as getNeighboringNodes.

Challenges
==========

One major challenge that I had was understanding the middle looping part of the algorithm.
I had not realized that sometimes going back to unvisited nodes was required.

Further Development
===================

In the future, I plan to add support for sending back the actual path use to find the shortest distance.

Acknowledgement
---------------

https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
https://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
https://www.youtube.com/watch?v=gdmfOwyQlcI
