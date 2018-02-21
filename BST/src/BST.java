/**
 * Binary search tree implementation. Each node has up to two children.
 * Left child node contains value less than or equal to parent and right child node is always greater.
 * Comparisons are evaluated using the {@link Comparable} interface.
 *
 * @param <T> Type of data to store, must implement {@link Comparable} interface
 */
public class BST<T extends Comparable<T>> {

    private T m_Datum;
    private BST<T> m_Left, m_Right;

    public BST(final T datum) {
        m_Datum = datum;
    }

    /**
     * Recursively print the tree in infix notation
     */
    public void printTree() {
        if (m_Right != null)
            m_Right.printTree();
        System.out.print(m_Datum.toString() + " ");
        if (m_Left != null)
            m_Left.printTree();
    }

    /**
     * Recursive implementation for finding the max depth of the tree.
     * Leafs always have a length of one, null children will be considered of length zero.
     * Every time we transverse down the tree we add one depth point,
     * as seen before the {@link Math#max(int, int)} function.
     * {@link Math#max(int, int)} is used because we want the longest depth of the tree.
     *
     * @return Maximum depth of the tree
     */
    public int depth() {
        if (isLeaf()) return 1;
        else return 1 + Math.max(m_Left == null ? 0 : m_Left.depth(), m_Right == null ? 0 : m_Right.depth());
    }

    /**
     * Test whether or not this node is a leaf: whether or not it has any children
     *
     * @return Whether or not this node is a leaf
     */
    private boolean isLeaf() {
        return m_Left == null && m_Right == null;
    }

    /**
     * This is a wrapper for {@link #insert(BST)} as that creates the node.
     *
     * @param datum Value for this node
     */
    public void insert(final T datum) {
        final BST<T> node = new BST<>(datum);
        insert(node);
    }

    /**
     * Appropriately insert a node into the tree based on its value.
     * Using the {@link Comparable} interface to determine which side,
     * if it is -1 or 0 then the node is inserted to the left, otherwise,
     * it must be 1, and the node is inserted to the right.
     * If the left/right node has already been created, call this function on them.
     *
     * @param node Node to insert
     */
    public void insert(final BST<T> node) {
        final int compare = m_Datum.compareTo(node.getDatum());
        if (compare <= 0) {
            if (m_Left == null) {
                m_Left = node;
            } else {
                m_Left.insert(node);
            }
        } else {
            if (m_Right == null) {
                m_Right = node;
            } else {
                m_Right.insert(node);
            }
        }
    }

    @Override
    public String toString() {
        return m_Datum.toString();
    }

    public T getDatum() {
        return m_Datum;
    }

    public BST<T> getLeft() {
        return m_Left;
    }

    public BST<T> getRight() {
        return m_Right;
    }
}
