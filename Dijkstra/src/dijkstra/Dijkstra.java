package dijkstra;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Implement's Dijkstra's shortest path finding algorithm.
 */
public class Dijkstra {

    /**
     * Obtain the {@link Graph} we want to solve and make a {@link Dijkstra} instance to do such that.
     *
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        final Graph graph = getWikapediaGraph();
        final Dijkstra solver = new Dijkstra(graph, graph.getNode(0), graph.getNode(4));
        System.out.println(solver.solve());
    }

    /**
     * A test graph to see if the algorithm works.
     * This makes sure it does not take the seemingly easy path.
     *
     * @return The {@link Graph} instance
     */
    private static Graph getTestGraph() {
        final Graph graph = new Graph();
        final Node[] n = new Node[4];
        for (int i = 0; i < 4; i++)
            n[i] = new Node(i);
        graph.addBiDirectionalEdge(n[0], n[1], 2);
        graph.addBiDirectionalEdge(n[1], n[3], 1000);
        graph.addBiDirectionalEdge(n[0], n[2], 5);
        graph.addBiDirectionalEdge(n[2], n[3], 2);
        return graph;
    }

    /**
     * Get the graph as seen at https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
     *
     * @return {@link Graph} representing the one seen on Wikapedia Dijkstra's algorithm page
     */
    private static Graph getWikapediaGraph() {
        final Graph graph = new Graph();
        final Node[] n = new Node[6];
        for (int i = 0; i < 6; i++)
            n[i] = new Node(i);
        graph.addBiDirectionalEdge(n[0], n[1], 7);
        graph.addBiDirectionalEdge(n[0], n[2], 9);
        graph.addBiDirectionalEdge(n[0], n[5], 14);
        graph.addBiDirectionalEdge(n[1], n[2], 10);
        graph.addBiDirectionalEdge(n[1], n[3], 15);
        graph.addBiDirectionalEdge(n[2], n[3], 11);
        graph.addBiDirectionalEdge(n[2], n[5], 2);
        graph.addBiDirectionalEdge(n[3], n[4], 6);
        graph.addBiDirectionalEdge(n[4], n[5], 9);
        return graph;
    }

    private final Graph fm_Graph;
    private final Node fm_InputNode, fm_DestinationNode;

    /**
     * Create a new instance of the Dijkstra solder.
     * The {@link #solve()} function can be used to get the smallest distance between provided nodes.
     *
     * @param graphInput Graph to search
     * @param inputNode Node to start at
     * @param destinationNode Node to end at
     */
    public Dijkstra(final Graph graphInput, final Node inputNode, final Node destinationNode) {
        fm_Graph = graphInput;
        fm_InputNode = inputNode;
        fm_DestinationNode = destinationNode;
    }

    /**
     * Find the smallest distance between the {@link #fm_InputNode} and {@link #fm_DestinationNode} in graph {@link #fm_Graph}.
     * This implements Dijkstra's algorithm.
     *
     * @return Smallest distance between {@link #fm_InputNode} and {@link #fm_DestinationNode}.
     */
    public int solve() {
        // Create a list of unvisited nodes that includes everyone so far
        final HashSet<Node> unvisitedNodes = fm_Graph.getAllNodes();
        // Assign a temporary distance of "infinity" (really just max value of an integer)
        // Leave the distance at the beginning node to zero since we are currently there
        final HashMap<Node, Integer> distances = new HashMap<>();
        for (final Node node : fm_Graph)
            distances.put(node, node == fm_InputNode ? 0 : Integer.MAX_VALUE);
        Node currentNode = fm_InputNode;
        while (true) {
            // Analyze neighbors of current node, see if we can find shorter paths and update distances accordingly
            final HashMap<Node, Integer> neighbors = fm_Graph.getNeighboringNodes(currentNode);
            for (final HashMap.Entry<Node, Integer> neighbor : neighbors.entrySet()) {
                if (unvisitedNodes.contains(neighbor.getKey())) {
                    // See if the new distance through the current node is shorter. If it is, update value in distances hash map
                    final int newDistance = neighbor.getValue() + distances.get(currentNode);
                    if (newDistance < distances.get(neighbor.getKey()))
                        // Note, calling put again even when the key already exists rewrites the value
                        distances.put(neighbor.getKey(), newDistance);
                }
            }
            unvisitedNodes.remove(currentNode);
            // Find the node with the smallest tentative distance
            int min = Integer.MAX_VALUE;
            for (final HashMap.Entry<Node, Integer> node : distances.entrySet()) {
                if (unvisitedNodes.contains(node.getKey()) && node.getValue() < min) {
                    min = node.getValue();
                    currentNode = node.getKey();
                }
            }
            // If the next node is going to be the destination, we are finished
            if (currentNode == fm_DestinationNode)
                return distances.get(currentNode);
        }
    }
}
