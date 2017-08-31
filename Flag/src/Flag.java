import javax.swing.*;
import java.awt.*;

public class Flag extends JPanel {

    private static final float
        FLAG_WIDTH_RATIO = 1.9f, UNION_HEIGHT_RATIO = 7f/13f, UNION_WIDTH_RATIO = 0.76f,
        STAR_DIAMETER_RATIO = 0.0616f, STAR_GAP_X_RATIO = 0.063f, STAR_GAP_Y_RATIO = 0.054f;
    private static final float STAR_POINTS = 5, STAR_ANGLE = (float)(2.0*Math.PI / (STAR_POINTS * 2.0));
    private static final int NUMBER_OF_STRIPES = 13;
    private static final Color UNION_COLOR = new Color(0, 33, 71), STRIPE_COLOR = new Color(187, 19, 62);

    public static void main(String[] args) {

        JFrame frame = new JFrame("Flag");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Flag flag = new Flag();

        frame.add(flag);

        frame.pack();

        frame.setVisible(true);
    }

    /**
     * Default constructor for the Flag object
     */
    private Flag()
    {
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        Graphics2D g2 = (Graphics2D)g;

        // Get the correct height, this makes sure the flag is never cut off
        final float height = Math.min(getHeight(), getWidth() / FLAG_WIDTH_RATIO);

        drawAmericanFlag(g2, height);
    }

    @Override
    public Dimension getPreferredSize() {

        return new Dimension(190*4, 100*4);
    }

    /**
     * Draws an entire U.S. flag. Uses {@link #drawStarGrid}, {@link #drawRectangle}, and {@link #drawStripes}.
     *
     * @param g2 The graphics object used in order to draw
     * @param height The height of the flag
     */
    private void drawAmericanFlag(Graphics2D g2, final float height) {

        // Draw background
        final float width = height * FLAG_WIDTH_RATIO;
        drawRectangle(g2, Color.WHITE, 0, 0, width, height);

        // Draw stripes
        final float stripeHeight = height / NUMBER_OF_STRIPES;
        drawStripes(g2, STRIPE_COLOR, width, stripeHeight);

        // Draw union rectangle
        final float unionWidth = height * UNION_WIDTH_RATIO, unionHeight = height * UNION_HEIGHT_RATIO;
        drawRectangle(g2, UNION_COLOR, 0, 0, unionWidth, unionHeight);

        // Draw both grid of stars
        final float starGapX = height * STAR_GAP_X_RATIO, starGapY = height * STAR_GAP_Y_RATIO, starDiameter = height * STAR_DIAMETER_RATIO;
        drawStarGrid(g2, Color.WHITE, starGapX, starGapY, 6, 5, starGapX*2.0f, starGapY*2.0f, starDiameter);
        drawStarGrid(g2, Color.WHITE, starGapX*2.0f, starGapY*2.0f, 5, 4, starGapX*2.0f, starGapY*2.0f, starDiameter);
    }

    /**
     * Draws stripes with a given color.
     *
     * @param g2 The graphics object used in order to draw
     * @param c The color of the stripes
     * @param width The width of the flag
     * @param stripeHeight The height of the stripe
     */
    private void drawStripes(Graphics2D g2, final Color c, final float width, final float stripeHeight) {

        g2.setColor(c);

        // Draw each stripe given the number of them, add two
        // each time so that the stripes are spaced out
        for (int y = 0; y < NUMBER_OF_STRIPES; y += 2) {

            // Draw the actual bar with the correct scaling
            g2.fillRect(0, (int)(y * stripeHeight), (int)width, (int)stripeHeight);
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
    private void drawRectangle(Graphics2D g2, final Color c, final float x, final float y, final float width, final float height) {

        g2.setColor(c);

        g2.fillRect((int)x, (int)y, (int)width, (int)height);
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
    private void drawStarGrid(Graphics2D g2, final Color c, final float x, final float y, final int width, final int height, final float starGapX, final float starGapY, final float starDiameter) {

        g2.setColor(c);

        // Loop through grid
        for (int xi = 0; xi < width; xi++) {

            for (int yi = 0; yi < height; yi++) {

                // Get coordinates of the star using the current row
                // and column, at the end add the x and y offset
                final int
                    starX = (int)(xi * starGapX + x),
                    starY = (int)(yi * starGapY + y);

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
    private void drawStar(Graphics2D g2, final int x, final int y, final float diameter) {

        Polygon p = new Polygon();

        final float radius = diameter / 2.0f;

        for (int i = 0; i < STAR_POINTS * 2; i++) {
            // Get angle for this point. The subtraction of 0.5 is to make sure that
            // the star is horizontally symmetrical
            final double angle = STAR_ANGLE * (i - 0.5f);
            // Get radius of point, whether it is exterior or interior point based on whether or not i is odd/even.
            // This makes sure that adjacent points are different radii but are the same every other.
            final float r = i % 2 == 0 ? radius : radius / 2.1f;
            // Convert from polar coordinates to cartesian and add the offsets
            final int
                pointX = (int)(Math.cos(angle) * r) + x,
                pointY = (int)(Math.sin(angle) * r) + y;
            // Add point to polygon
            p.addPoint(pointX, pointY);
        }
        // Draw polygon filled
        g2.fillPolygon(p);
    }
}
