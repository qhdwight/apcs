Priority Queue by Quintin Dwight
================================

Introduction
------------

Queues are a data structure that are first in last out, which means the order of the elements are kept the way they are put in.
In a priority queue, each element can be compared to eachother and elements with a higher value are dequeued before elements of a lower value regardless of the order they were put in.

Specifications
--------------

This class meets the specifications of a Java PriorityQueue given at https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html

Errors
------

There are no errors that I detected while running the tests found in Main.java

Overview of Code
----------------

The PriorityQueue class has an internal ArrayList that holds elements of a given generic type.
This type must extend Comparable so that a Comparator can be used to determine priority.
Sorting of priories is done when an element is added into the array.
The next element of the queue is stored in the beginning of the ArrayList, and last to come out is stored at the end.
A merge of two queues can be done, a recursive Quick Sort is used where the upper bound is used as the partition index.

Acknowledgement
---------------

https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html
https://en.wikipedia.org/wiki/Priority_queue
https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html
https://www.geeksforgeeks.org/quick-sort/
