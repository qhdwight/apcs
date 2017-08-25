import javax.swing.*;
import java.awt.*;

public class Flag extends JPanel {

    private static final float
        FLAG_WIDTH_RATIO = 1.9f, UNION_HEIGHT_RATIO = 7f/13f, UNION_WIDTH_RATIO = 0.76f,
        STAR_DIAMETER_RATIO = 0.0616f, STAR_GAP_X_RATIO = 0.063f, STAR_GAP_Y_RATIO = 0.054f;
    private static final float STAR_POINTS = 5, STAR_ANGLE = (float)(2.0*Math.PI / (STAR_POINTS * 2.0));
    private static final int NUMBER_OF_STRIPES = 13, NUMBER_OF_STAR_ROWS = 11, NUMBER_OF_STARS_IN_LOW_COLUMN = 4, NUMBER_OF_STARS_IN_HIGH_COLUMN = 5;
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

        drawFlag(g2, height);
    }

    @Override
    public Dimension getPreferredSize() {

        return new Dimension(190*4, 100*4);
    }

    /**
     * Draws an entire flag. Uses {@link #drawStars}, {@link #drawStripes}, and {@link #drawUnion}.
     * @param g2 The graphics object used in order to draw
     * @param height The height of the flag
     */
    private void drawFlag(Graphics2D g2, final float height) {

        drawBackground(g2, height);
        drawStripes(g2, height);
        drawUnion(g2, height);
        drawStars(g2, height);
    }

    /**
     * Draws the red stripes on the flag
     * @param g2 The graphics object used in order to draw
     * @param height The height of the flag
     */
    private void drawStripes(Graphics2D g2, final float height) {

        // Get correct scaled height
        final float stripeHeight = height / NUMBER_OF_STRIPES;

        g2.setColor(STRIPE_COLOR);

        // Draw each stripe given the number of them, add two
        // each time so that the stripes are spaced out
        for (int y = 0; y < NUMBER_OF_STRIPES; y += 2) {

            // Draw the actual bar with the correct scaling
            g2.fillRect(0, (int)(y * stripeHeight), (int)(height * FLAG_WIDTH_RATIO), (int)stripeHeight);
        }
    }

    private void drawBackground(Graphics2D g2, final float height) {

        g2.setColor(Color.WHITE);

        g2.fillRect(0, 0, (int)(height * FLAG_WIDTH_RATIO), (int)height);
    }

    /**
     * Draw the union part of the flag
     * @param g2 The graphics object used in order to draw
     * @param height The height of the flag
     */
    private void drawUnion(Graphics2D g2, final float height) {

        // Get correct scale dimension
        final float unionWidth = height * UNION_WIDTH_RATIO, unionHeight = height * UNION_HEIGHT_RATIO;

        g2.setColor(UNION_COLOR);

        g2.fillRect(0, 0, (int)unionWidth, (int)unionHeight);

    }

    /**
     * Draws all fifty of the stars to the flag
     * @param g2 The graphics object used in order to draw
     * @param height The height of the flag
     */
    private void drawStars(Graphics2D g2, final float height) {

        g2.setColor(Color.WHITE);

        // Draw the grid of stars
        for (int x = 0; x < NUMBER_OF_STAR_ROWS; x++) {
            // Get whether or not index is even
            final boolean evenIndex = x % 2 == 0;
            // Find amount of stars in the column, it depends on
            // the index of the column, it alternates between 4 and 5
            final int starsInColumn = evenIndex ? NUMBER_OF_STARS_IN_HIGH_COLUMN : NUMBER_OF_STARS_IN_LOW_COLUMN;
            // Determine how many spaces to add at the top, columns with 4 spaces
            // have more padding at the top, columns with 5 only have 1 space
            final int addAmount = evenIndex ? 1 : 2;
            for (int y = 0; y < starsInColumn; y++) {
                // Get coordinates of star
                final int
                    starX = (int)((x + 1) * height * STAR_GAP_X_RATIO),
                    // Multiply y value by two because there are two spaces vertically
                    starY = (int)((y * 2f + addAmount) * height * STAR_GAP_Y_RATIO);
                // Draw the star at the provided x and y coordinates with the correct diameter
                drawStar(g2, starX, starY, height * STAR_DIAMETER_RATIO);
            }
        }
    }

    /**
     * Draws a five point star at the given coordinates with a given diameter.
     * Uses polar coordinates converted using trigonometry to find out the
     * vertices. Note: x and y are the middle of the star
     * @param g2 The graphics object used in order to draw
     * @param x The x coordinate of the middle of the star
     * @param y The y coordinate of the middle of the star
     * @param diameter The diameter of the star
     */
    private void drawStar(Graphics2D g2, final int x, final int y, final float diameter) {

        Polygon p = new Polygon();
        for (int i = 0; i < STAR_POINTS * 2; i++) {
            // Get angle for this point. The subtraction of 0.5 is to make sure that
            // the star is horizontally symmetrical
            final double angle = STAR_ANGLE * (i - 0.5f);
            // Get radius of point, whether it is exterior or interior point
            final float r = i % 2 == 0 ? diameter / 2.0f : diameter / 4.5f;
            // Convert from polar coordinates to cartesian
            final int
                pointX = (int)(Math.cos(angle) * r) + x,
                pointY = (int)(Math.sin(angle) * r) + y;
            // Add point to polygon
            p.addPoint(pointX, pointY);
        }
        // Draw polygon
        g2.fillPolygon(p);
    }
}
