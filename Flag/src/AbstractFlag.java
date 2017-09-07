import javax.swing.*;
import java.awt.*;

/**
 * Base class for flag objects. Has a collection of drawing utilities.
 * <br/><br/>
 * Private functions:
 * <ul>
 *     <li>{@link #drawRectangle(Graphics2D, Color, double, double, double, double)}</li>
 *     <li>{@link #drawStripes(Graphics2D, Color, double, double, double, double, int)}</li>
 *     <li>{@link #drawStarGrid(Graphics2D, Color, double, double, int, int, double, double, int, double)}</li>
 *     <li>{@link #getStar(int, double)}</li>
 *     <li>{@link #drawStar(Graphics2D, Polygon, int, int)}</li>
 *     <li>{@link #drawStar(Graphics2D, int, int, int, double)}</li>
 * </ul>
 */
public abstract class AbstractFlag extends JPanel {

    private final double FLAG_WIDTH_RATIO, STARTING_HEIGHT;

    /**
     * Constructor for flag, needs a width ratio.
     *
     * @param flagWidthRatio The ratio between the height and the width of the flag
     */
    public AbstractFlag(final double flagWidthRatio, final double startingHeight) {

        FLAG_WIDTH_RATIO = flagWidthRatio;
        STARTING_HEIGHT = startingHeight;
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        // Get the correct height, this makes sure the flag is never cut off
        final double height = Math.min(getHeight(), getWidth() / FLAG_WIDTH_RATIO);

        drawFlag((Graphics2D)g, height);
    }

    @Override
    public Dimension getPreferredSize() {

        return new Dimension(floor(STARTING_HEIGHT*FLAG_WIDTH_RATIO), floor(STARTING_HEIGHT));
    }

    /**
     * Method that should be overriden by the extending class.
     *
     * @param g2 The graphics object used to draw
     * @param height The height of the flag
     */
    protected abstract void drawFlag(Graphics2D g2, final double height);

    /**
     * Draws stripes with a given color.
     *
     * @param g2 The graphics object used in order to draw
     * @param c The color of the stripes
     * @param x The top left x coordinate of the stripes
     * @param y The top left y coordinate of the stripes
     * @param width The width of the flag
     * @param stripeHeight The height of the stripe
     * @param numberOfStripes The number of stripes to draw
     */
    protected void drawStripes(Graphics2D g2, final Color c, final double x, final double y, final double width, final double stripeHeight, final int numberOfStripes) {

        g2.setColor(c);

        // Draw each stripe given the number of them, add two
        // each time so that the stripes are spaced out
        for (int iy = 0; iy < numberOfStripes; iy += 2) {

            // Draw the actual bar with the correct scaling
            g2.fillRect(floor(x), floor(iy * stripeHeight + y), floor(width), floor(stripeHeight));
        }
    }

    /**
     * Draws a solid rectangle.
     *
     * @param g2 The graphics object used in order to draw
     * @param c The color of the background
     * @param x The top left x coordinate of the rectangle
     * @param y to top left y coordinate of the rectangle
     * @param width The width of the background
     * @param height The height of the background
     */
    protected void drawRectangle(Graphics2D g2, final Color c, final double x, final double y, final double width, final double height) {

        g2.setColor(c);

        g2.fillRect(floor(x), floor(y), floor(width), floor(height));
    }

    /**
     * Draws a star of grids using {@link #drawStar}.
     *
     * @param g2 The graphics object used in order to draw
     * @param x The top left x coordinate of the star grid
     * @param y The top left y coordinate of the star grid
     * @param width The width of the grid
     * @param height The height of the grid
     * @param starGapX The gap in the x grid
     * @param starGapY The gap in the y grid
     * @param starPoints The amount of external points in the star
     * @param starDiameter The diameter of the star
     */
    protected void drawStarGrid(Graphics2D g2, final Color c, final double x, final double y, final int width, final int height, final double starGapX, final double starGapY, final int starPoints, final double starDiameter) {

        g2.setColor(c);

        Polygon p = getStar(starPoints, starDiameter);

        // Loop through grid
        for (int xi = 0; xi < width; xi++) {

            for (int yi = 0; yi < height; yi++) {

                // Get coordinates of the star using the current row
                // and column, at the end add the x and y offset
                final int
                    starX = floor(xi * starGapX + x),
                    starY = floor(yi * starGapY + y);

                drawStar(g2, p, starX, starY);
            }
        }
    }

    /**
     * Gets the polygon for a star.
     * Uses polar coordinates converted using trigonometry to find out the vertices.
     *
     * @param starPoints The number of points on the star
     * @param diameter The diameter of the star
     */
    protected Polygon getStar(final int starPoints, final double diameter) {

        Polygon p = new Polygon();

        final double
            outerRadius = diameter / 2.0,
            starAngle = Math.PI / starPoints,
            innerRadius = outerRadius / 2.618;

        for (int i = 0; i < starPoints * 2; i++) {
            // Get angle for this point. The subtraction of 0.5 is to make sure that
            // the star is horizontally symmetrical
            final double angle = starAngle * (i - 0.5);
            // Get radius of point, whether it is exterior or interior point based on whether or not i is odd/even (that's what modulus does).
            // This makes sure that adjacent points are different radii but are the same every other.
            final double r = i % 2 == 0 ? outerRadius : innerRadius;
            // Convert from polar coordinates to cartesian and add the offsets
            final int
                pointX = floor(Math.cos(angle) * r),
                pointY = floor(Math.sin(angle) * r);
            // Add point to polygon
            p.addPoint(pointX, pointY);
        }

        return p;
    }

    /**
     * Draws a star with given coordinates, amount of points, and diameter.
     *
     * @param g2 The graphics object used in order to draw
     * @param x The x coordinate of the middle of the star
     * @param y The y coordinate of the middle of the star
     * @param starPoints The number of external points to the star
     * @param starDiameter The diameter of the star
     */
    protected void drawStar(Graphics2D g2, final int x, final int y, final int starPoints, final double starDiameter) {

        Polygon p = getStar(starPoints, starDiameter);

        drawStar(g2, p, x, y);
    }

    /**
     * Internal method that draws a star from a given polygon. This polygon is translated by x and y.
     *
     * @param g2 The graphics object used in order to draw
     * @param starPolygon The star polygon object
     * @param x The x coordinate of the middle of the star
     * @param y The y coordinate of the middle of the star
     */
    private void drawStar(Graphics2D g2, Polygon starPolygon, final int x, final int y) {

        Polygon translatedPolygon = new Polygon();

        // Iterate over polygon and add x and y coordinates to shift it
        for (int i = 0; i < starPolygon.npoints; i++) {
            translatedPolygon.addPoint(starPolygon.xpoints[i] + x, starPolygon.ypoints[i] + y);
        }

        g2.fillPolygon(translatedPolygon);
    }

    /**
     * Takes a double and returns the floor of it as an integer.
     *
     * @param d The double to round
     * @return The integer value of the floor
     */
    private static int floor(final double d) {
        return (int)Math.floor(d);
    }
}
