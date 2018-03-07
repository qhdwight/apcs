package dijkstra;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Graph implements Iterable<Node> {

    @Override
    public Iterator<Node> iterator() {
        return getAllNodes().iterator();
    }

    private final HashMap<Node, HashMap<Node, Integer>> m_Graph;

    public Graph() {
        m_Graph = new HashMap<>();
    }

    public void addEdge(final Node a, final Node b, final int d) {
        if (m_Graph.containsKey(a)) {
            m_Graph.get(a).put(b, d);
        } else {
            final HashMap<Node, Integer> map = new HashMap<>();
            map.put(b, d);
            m_Graph.put(a, map);
        }
    }

    public HashSet<Node> getAllNodes() {
        final HashSet<Node> allNodes = new HashSet<>();
        for (final HashMap.Entry<Node, HashMap<Node, Integer>> connection : m_Graph.entrySet()) {
            allNodes.add(connection.getKey());
            allNodes.addAll(connection.getValue().keySet());
        }
        return allNodes;
    }
}
