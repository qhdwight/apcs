package dijkstra;

public class Node {

    @Override
    public int hashCode() {
        return Integer.hashCode(m_Identifier);
    }

    private final int m_Identifier;

    public Node(final int identifier) {
        m_Identifier = identifier;
    }
}
