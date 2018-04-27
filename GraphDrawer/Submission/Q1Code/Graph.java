import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Implements an adjacency array to define a graph with edges and nodes.
 */
public class Graph<TNode extends Node> implements Iterable<TNode> {

    private final HashMap<TNode, HashMap<TNode, Integer>> fm_Graph;

    public Graph() {
        fm_Graph = new HashMap<>();
    }

    @Override
    public Iterator<TNode> iterator() {
        return getAllNodes().iterator();
    }

    /**
     * Wrapper for adding a bi-directional edge to the graph.
     *
     * @param a First node
     * @param b Second node
     * @param d Distance between the two
     */
    public void addBiDirectionalEdge(final TNode a, final TNode b, final int d) {
        addOneDirectionalEdge(a, b, d);
        addOneDirectionalEdge(b, a, d);
    }

    /**
     * Add a single direction edge to the graph.
     *
     * @param a First node
     * @param b Second node
     * @param d Distance from the first to second
     */
    private void addOneDirectionalEdge(final TNode a, final TNode b, final int d) {
        if (fm_Graph.containsKey(a)) {
            fm_Graph.get(a).put(b, d);
        } else {
            final HashMap<TNode, Integer> map = new HashMap<>();
            map.put(b, d);
            fm_Graph.put(a, map);
        }
    }

    /**
     * Search through {@link #fm_Graph} and find a {@link Node} with the given identifier.
     *
     * @param identifier Number identifier of {@link Node}
     * @return {@link Node} in graph. null if it is not in here
     */
    public TNode getNode(final int identifier) {
        for (final TNode node : fm_Graph.keySet()) {
            if (node.getIdentifier() == identifier)
                return node;
        }
        return null;
    }

    /**
     * Get all {@link Node}'s described in {@link #fm_Graph}
     *
     * @return A {@link HashSet} of all {@link Node}'s
     */
    public HashSet<TNode> getAllNodes() {
        return new HashSet<>(fm_Graph.keySet());
    }

    /**
     * Get all the {@link Node}'s that neighbor this one.
     *
     * @param node {@link Node} to find neighbors of
     * @return Each neighboring {@link Node} and it's distance to given node
     */
    public HashMap<TNode, Integer> getNeighboringNodes(final TNode node) {
        return fm_Graph.get(node);
    }
}
