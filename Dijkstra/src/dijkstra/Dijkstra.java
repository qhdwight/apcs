package dijkstra;

import java.util.HashSet;

public class Dijkstra {

    public static void main(final String[] args) {
        final Graph graph = new Graph();
        final Node n0 = new Node(0), n1 = new Node(1), n2 = new Node(2);
        graph.addEdge(n0, n1, 5);
        graph.addEdge(n1, n2, 3);
        graph.addEdge(n0, n2, 7);
    }

    public static void solve(final Graph graph) {
        final HashSet<Node> unvisitedNodes = graph.getAllNodes();

    }
}

/*
Find a PATH in a GRAPH where each NODE in the graph is visited *exactly* once
 */