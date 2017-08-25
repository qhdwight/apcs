I broke the flag project into five parts. One section draws the white background,
one section draws the red stripes, another draws the blue union rectangle,
another one draws all of the stars, and the last one draws a single star.

Originally, the sections that draw the background and the stripe were separated.
However, I changed it to be one section to compress it.
Then, I realized that this is not very modular,
what if your flag had a solid background and you don't want the stripes?
So, I changed it back to the original.

The section that draws the blue union rectangle is straightforward,
it takes the current height and scales it appropriately.

The section that draws all of the stars uses the section that can draw a single star.
I originally thought that I should use two sections for each grid of stars (one inset and out outset),
then I figured I could do it in one nested for loop.
Next, I think that I should make a function that draws a star grid and then use it twice,
this will make the function more modular.

For the single star section, I figured that I should use polar coordinates that convert to Cartesian coordinates.
The angle of the polar coordinate would rotate around a clock based on the amount of points in the star.

For all of the sections, I think that I should make them more generic so that they can be used to make different flags.
For example, each function should take parameters as opposed to using constant values.
