AmericanFlag Project by Quintin Dwight
======================================

Introduction
------------

This program shows the American flag that can be resized within it's window.
It uses Java's swing library to draw the shapes.

Specification
-------------

This program meets the specifications of the assignment,
it draws an American flag with correct proportions that can be scaled.
The ratios were taken from http://www.usflag.org/flag.specs.html.
The colors are close to the real rife ones, but they cannot be matched perfectly.

Errors
------

The function that gets the point of the stars does not have the correct inner ratio for a star that does not have five points.

Overview of Code
----------------

There are three classes, Main, AbstractFlag, and AmericanFlag. Main sets up a JFrame and adds a new AmericanFlag class to it.
It also handles packing the AmericanFlag into the JFrame and making sure the window is visible.
The abstract class contains functions for drawing common flag items.
It also handles scaling the flag properly given the size.
The american flag class contains a function that can draw the American flag as well as other utility functions.
It extends the abstract class so that it has the drawing functions.
These include ones to draw a rectangle, a grid of stars, a single star, and stripes.
The function that draws the American flag uses these utility functions separately for modularity.
It calculates the correct size for each component based on ratio constants and the current height of the flag.
The height of the flag is determines from either the width of the height of the frame to make it fit appropriately.
Since the JPanel overrides the paint function, it is called every time the window is resized.

The function that gets a star polygon uses trigonometry and ratios to find the points.
As a general overview, it uses an increasing angle and specific radius as polar coordinates for the points of the star,
then uses trigonometry to convert to cartesian coordinates which go into the Polygon.

Changes over Time
-----------------
My code went over several major reworks. First of all, I split my function into more general parts.
For example, the code that draws the union and the background were both drawing rectangles,
so I compressed them into a single function that draws a rectangle with a specific color.
Another example is the stars on the flag, previously I had one function to draw both grids.
However, I opted to go for another function that draws a single grid of stars and call it twice.
In addition to this, I added a color parameter to each function so that it can be used for different flag designs.
Finally, I made an abstract class so that other flags can extend it and and have utility functions ready.

Challenges
----------

One of the major challenges I had was making the flag resize in a smart manner such that it is never cut off.
I realized that I could choose the minimum of the width and the height.
However, the width does not take into account the ratio, so you have to divide by that ratio.
Then, you get the proper height of the flag and can pass it to the draw American flag function.

Another problem I had was getting the correct inner radius ratio for a five point star.
I opted to hard code the ratio in, but this means that the ratio is only correct for stars with five points.
