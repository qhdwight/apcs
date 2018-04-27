import java.util.concurrent.ThreadLocalRandom;

public class GraphDrawer {

    public static void main(String[] args) {
        final Graph<DrawableNode> graph = new Graph<>();
        final DrawableNode[] n = new DrawableNode[5];
        for (int i = 0; i < n.length; i++) n[i] = new DrawableNode(i, ThreadLocalRandom.current().nextInt(50, 350), ThreadLocalRandom.current().nextInt(50, 350));
        graph.addBiDirectionalEdge(n[0], n[1], 5);
        graph.addBiDirectionalEdge(n[1], n[2], 5);
        graph.addBiDirectionalEdge(n[2], n[3], 5);
        graph.addBiDirectionalEdge(n[3], n[0], 5);
        graph.addBiDirectionalEdge(n[0], n[4], 5);
        graph.addBiDirectionalEdge(n[3], n[4], 5);
        final GraphWindow graphWindow = new GraphWindow(graph);
    }
}
