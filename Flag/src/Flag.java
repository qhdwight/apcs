import javax.swing.*;
import java.awt.*;

/**
 * Class that can draw the american flag, and also provides other functions to draw different flags.
 * <br/><br/>
 * Private functions:
 * <ul>
 *     <li>{@link #drawAmericanFlag}</li>
 *     <li>{@link #drawRectangle}</li>
 *     <li>{@link #drawStripes}</li>
 *     <li>{@link #drawStarGrid}</li>
 *     <li>{@link #drawStar}</li>
 * </ul>
 */
public class Flag extends JPanel {

    private static final double
        // Ratio constants for the American flag
        FLAG_WIDTH_RATIO = 1.9, UNION_HEIGHT_RATIO = 7.0/13.0, UNION_WIDTH_RATIO = 0.76,
        STAR_DIAMETER_RATIO = 0.0616, STAR_GAP_X_RATIO = 0.063, STAR_GAP_Y_RATIO = 0.054,
        // Double constants for the American flag
        STAR_POINTS = 5, STAR_ANGLE = (2.0*Math.PI) / (STAR_POINTS * 2.0),
        STAR_RADIUS_RATIO = Math.cos(Math.PI - STAR_ANGLE*4.0)/(Math.cos(STAR_ANGLE*2.0));
    // Numerical constants for the American flag
    private static final int NUMBER_OF_STRIPES = 13;
    // American flag colors, no perfect match exists since original colors were on fabric, but these are close
    private static final Color UNION_COLOR = new Color(0, 33, 71), STRIPE_COLOR = new Color(187, 19, 62);

    /**
     * Default constructor for the Flag object
     */
    public Flag()
    {
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        Graphics2D g2 = (Graphics2D)g;

        // Get the correct height, this makes sure the flag is never cut off
        final double height = Math.min(getHeight(), getWidth() / FLAG_WIDTH_RATIO);

        drawAmericanFlag(g2, 0, 0, height);
    }

    @Override
    public Dimension getPreferredSize() {

        return new Dimension(190*4, 100*4);
    }

    /**
     * Draws an entire U.S. flag. Uses {@link #drawStarGrid}, {@link #drawRectangle}, and {@link #drawStripes}.
     *
     * @param g2 The graphics object used in order to draw
     * @param x The top left x coordinate of the flag
     * @param y The top left y coordinate of the flag
     * @param height The height of the flag
     */
    private void drawAmericanFlag(Graphics2D g2, final double x, final double y, final double height) {

        // Draw background
        final double width = height * FLAG_WIDTH_RATIO;
        drawRectangle(g2, Color.WHITE, x, y, width, height);

        // Draw stripes
        final double stripeHeight = height / NUMBER_OF_STRIPES;
        drawStripes(g2, STRIPE_COLOR, x, y, width, stripeHeight);

        // Draw union rectangle
        final double unionWidth = height * UNION_WIDTH_RATIO, unionHeight = height * UNION_HEIGHT_RATIO;
        drawRectangle(g2, UNION_COLOR, x, y, unionWidth, unionHeight);

        // Draw both grid of stars
        final double starGapX = height * STAR_GAP_X_RATIO, starGapY = height * STAR_GAP_Y_RATIO, starDiameter = height * STAR_DIAMETER_RATIO;
        drawStarGrid(g2, Color.WHITE, x + starGapX, y + starGapY, 6, 5, starGapX*2.0, starGapY*2.0, starDiameter);
        drawStarGrid(g2, Color.WHITE, x + starGapX*2.0, y + starGapY*2.0, 5, 4, starGapX*2.0, starGapY*2.0, starDiameter);
    }

    /**
     * Draws stripes with a given color.
     *
     * @param g2 The graphics object used in order to draw
     * @param c The color of the stripes
     * @param x The top left x coordinate of the stripes
     * @param y The top left y coordinate of the stripes
     * @param width The width of the flag
     * @param stripeHeight The height of the stripe
     */
    private void drawStripes(Graphics2D g2, final Color c, final double x, final double y, final double width, final double stripeHeight) {

        g2.setColor(c);

        // Draw each stripe given the number of them, add two
        // each time so that the stripes are spaced out
        for (int iy = 0; iy < NUMBER_OF_STRIPES; iy += 2) {

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
    private void drawRectangle(Graphics2D g2, final Color c, final double x, final double y, final double width, final double height) {

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
     * @param starDiameter The diameter of the star
     */
    private void drawStarGrid(Graphics2D g2, final Color c, final double x, final double y, final int width, final int height, final double starGapX, final double starGapY, final double starDiameter) {

        g2.setColor(c);

        // Loop through grid
        for (int xi = 0; xi < width; xi++) {

            for (int yi = 0; yi < height; yi++) {

                // Get coordinates of the star using the current row
                // and column, at the end add the x and y offset
                final int
                    starX = floor(xi * starGapX + x),
                    starY = floor(yi * starGapY + y);

                drawStar(g2, starX, starY, starDiameter);
            }
        }
    }

    /**
     * Draws a five point star at the given coordinates with a given diameter.
     * Uses polar coordinates converted using trigonometry to find out the
     * vertices. Note: x and y are the middle coordinates of the star.
     *
     * @param g2 The graphics object used in order to draw
     * @param x The x coordinate of the middle of the star
     * @param y The y coordinate of the middle of the star
     * @param diameter The diameter of the star
     */
    private void drawStar(Graphics2D g2, final int x, final int y, final double diameter) {

        Polygon p = new Polygon();

        final double radius = diameter / 2.0;

        for (int i = 0; i < STAR_POINTS * 2; i++) {
            // Get angle for this point. The subtraction of 0.5 is to make sure that
            // the star is horizontally symmetrical
            final double angle = STAR_ANGLE * (i - 0.5);
            // Get radius of point, whether it is exterior or interior point based on whether or not i is odd/even (that's what modulus does).
            // This makes sure that adjacent points are different radii but are the same every other.
            final double r = i % 2 == 0 ? radius : radius / STAR_RADIUS_RATIO;
            // Convert from polar coordinates to cartesian and add the offsets
            final int
                pointX = floor(Math.cos(angle) * r + x),
                pointY = floor(Math.sin(angle) * r + y);
            // Add point to polygon
            p.addPoint(pointX, pointY);
        }
        // Draw polygon filled
        g2.fillPolygon(p);
    }

    /**
     * Takes a double and returns the floor of it as an integer.
     *
     * @param d The double to round
     * @return The integer value of the floor
     */
    private static int floor(double d) {
        return (int)Math.floor(d);
    }
}
