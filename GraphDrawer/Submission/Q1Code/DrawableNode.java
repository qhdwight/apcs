public class DrawableNode extends Node {

    private int x, y;

    /**
     * Create a new {@link Node} with the given identifier.
     *
     * @param identifier A {@link Integer} identifier.
     */
    public DrawableNode(int identifier, int x, int y) {
        super(identifier);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
