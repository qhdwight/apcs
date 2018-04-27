import java.awt.*;

/**
 * Class that draws the american flag with correct ratios.
 * It can also be resized, and it extends {@link AbstractFlag}
 */
public class AmericanFlag extends AbstractFlag {

    private static final double
        // Ratio constants for the American flag
        FLAG_WIDTH_RATIO = 1.9, UNION_HEIGHT_RATIO = 7.0/13.0, UNION_WIDTH_RATIO = 0.76,
        STAR_DIAMETER_RATIO = 0.0616, STAR_GAP_X_RATIO = 0.063, STAR_GAP_Y_RATIO = 0.054,
        STARTING_HEIGHT = 400.0;
    // Numerical constants for the American flag
    private static final int NUMBER_OF_STRIPES = 13, STAR_POINTS = 5;
    // American flag colors, no perfect match exists since original colors were on fabric, but these are close
    private static final Color UNION_COLOR = new Color(0, 33, 71), STRIPE_COLOR = new Color(187, 19, 62);

    /**
     * Default constructor for the AmericanFlag object
     */
    public AmericanFlag()
    {
        super(FLAG_WIDTH_RATIO, STARTING_HEIGHT);

        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    protected void drawFlag(Graphics2D g2, final double height) {

        drawAmericanFlag(g2, 0, 0, height);
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
        drawStripes(g2, STRIPE_COLOR, x, y, width, stripeHeight, NUMBER_OF_STRIPES);

        // Draw union rectangle
        final double unionWidth = height * UNION_WIDTH_RATIO, unionHeight = height * UNION_HEIGHT_RATIO;
        drawRectangle(g2, UNION_COLOR, x, y, unionWidth, unionHeight);

        // Draw both grid of stars
        final double starGapX = height * STAR_GAP_X_RATIO, starGapY = height * STAR_GAP_Y_RATIO, starDiameter = height * STAR_DIAMETER_RATIO;
        drawStarGrid(g2, Color.WHITE, x + starGapX, y + starGapY, 6, 5, starGapX*2.0, starGapY*2.0, STAR_POINTS, starDiameter);
        drawStarGrid(g2, Color.WHITE, x + starGapX*2.0, y + starGapY*2.0, 5, 4, starGapX*2.0, starGapY*2.0, STAR_POINTS, starDiameter);
    }
}
