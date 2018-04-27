import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GraphWindow extends JFrame {

    static class GraphPanel extends JPanel {

        private static final int k_NodeSize = 24, k_TextPadding = 4;
        private static final Font k_Font = new Font("Courier", Font.PLAIN, 20);

        private Graph<DrawableNode> m_Graph;

        public void drawGraph(final Graph<DrawableNode> graph) {
            m_Graph = graph;
            repaint();
        }

        @Override
        public void paintComponent(final Graphics g) {
            super.paintComponent(g);
            final Graphics2D g2 = (Graphics2D)g;
            g2.setFont(k_Font);
            for (final DrawableNode node : m_Graph) {
                for (final Map.Entry<DrawableNode, Integer> neighbor : m_Graph.getNeighboringNodes(node).entrySet()) {
                    g2.setStroke(new BasicStroke((int)Math.ceil(neighbor.getValue()/1.5)));
                    g2.drawLine(neighbor.getKey().getX(), neighbor.getKey().getY(), node.getX(), node.getY());
                    g2.setStroke(new BasicStroke(2));
                }
            }
            for (final DrawableNode node : m_Graph) {
                g2.setColor(Color.CYAN);
                g2.fillOval(node.getX() - k_NodeSize/2, node.getY() - k_NodeSize/2, k_NodeSize, k_NodeSize);
                g2.setColor(Color.BLACK);
                g2.drawOval(node.getX() - k_NodeSize/2, node.getY() - k_NodeSize/2, k_NodeSize, k_NodeSize);
            }
            for (final DrawableNode node : m_Graph) {
                final String label = Integer.toString(node.getIdentifier());
                g2.setColor(Color.WHITE);
                g2.fillRect(node.getX()-(k_Font.getSize() * label.length() / 4) - k_TextPadding, node.getY()-k_NodeSize-k_Font.getSize(), k_Font.getSize() * label.length() / 2 + k_TextPadding*2, k_Font.getSize() + k_TextPadding);
                g2.setColor(Color.BLACK);
                g2.drawRect(node.getX()-(k_Font.getSize() * label.length() / 4) - k_TextPadding, node.getY()-k_NodeSize-k_Font.getSize(), k_Font.getSize() * label.length() / 2 + k_TextPadding*2, k_Font.getSize() + k_TextPadding);
                g2.drawString(label, node.getX()-(k_Font.getSize() * label.length() / 4), node.getY() - k_NodeSize);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 400);
        }
    }

    private final GraphPanel fm_Panel;

    public GraphWindow(final Graph<DrawableNode> graph) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(fm_Panel = new GraphPanel());
        fm_Panel.drawGraph(graph);
        pack();
        setVisible(true);
    }
}
