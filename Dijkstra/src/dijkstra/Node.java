package dijkstra;

/**
 * Wrapper class for an {@link Integer} to describe a node in a graph.
 */
public class Node {

    private final int fm_Identifier;

    /**
     * Create a new {@link Node} with the given identifier.
     *
     * @param identifier A {@link Integer} identifier.
     */
    public Node(final int identifier) {
        fm_Identifier = identifier;
    }

    public int getIdentifier() {
        return fm_Identifier;
    }

    // We need a hashing function since it is often used in a HashSet/HashMap
    @Override
    public int hashCode() {
        return Integer.hashCode(fm_Identifier);
    }

    // Make sure that equals function uses hashCode function
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Node && hashCode() == obj.hashCode();
    }
}
