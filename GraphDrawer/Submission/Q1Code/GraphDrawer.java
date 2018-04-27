public class GraphDrawer {

    public static void main(String[] args) {
        final Graph<DrawableNode> graph = new Graph<>();
        final int[][] positions = {{50, 180}, {320, 50}, {300, 340}};
        final DrawableNode[] n = new DrawableNode[3];
        for (int i = 0; i < n.length; i++) n[i] = new DrawableNode(i, positions[i][0], positions[i][1]);
        graph.addBiDirectionalEdge(n[0], n[1], 3);
        graph.addBiDirectionalEdge(n[1], n[2], 6);
        graph.addBiDirectionalEdge(n[2], n[0], 17);
        final GraphWindow graphWindow = new GraphWindow(graph);
    }
}
