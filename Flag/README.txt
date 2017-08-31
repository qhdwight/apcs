Introduction
------------

This program shows the American flag that can be resized within it's window.
It uses Java's swing library to draw the shapes.

Overview of Code
----------------

There are two classes, Main and Flag. Main sets up a JFrame and adds a new Flag class to it.
It also handles packing the Flag into the JFrame and making sure the window is visible.
The flag class contains a function that can draw the American flag as well as other utility functions.
These include ones to draw a rectangle, a grid of stars, a single star, and stripes.
The function that draws the American flag uses these utility functions separately for modularity.
It calculates the correct size for each component based on ratio constants and the current height of the flag.
The height of the flag is determines from either the width of the height of the frame to make it fit appropriately.
Since the JPanel overrides the paint function, it is called every time the window is resized.

The function that draws the star uses trigonometry and ratios to fill a polygon onto the Flag JPanel.
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

Challenges
----------

One of the major challenges I had was making the flag resize in a smart manner such that it is never cut off.
I realized that I could choose the minimum of the width and the height.
However, the width does not take into account the ratio, so you have to divide by that ratio.
Then, you get the proper height of the flag and can pass it to the draw American flag function.
