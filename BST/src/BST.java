public class BST<T extends Comparable<T>> {

    private T m_Datum;
    private BST<T> m_Left, m_Right;

    public BST(final T datum) {
        m_Datum = datum;
    }

    public void printTree() {

    }

    public int depth() {
        int depth = 1;
        if (isLeaf()) return depth;
        depth = Math.max(m_Left.depth(), m_Right.depth());
        return depth;
    }

    private boolean isLeaf() {
        return m_Left == null && m_Right == null;
    }

    public void insert(final T datum) {
        final BST<T> node = new BST<>(datum);
        final int compare = m_Datum.compareTo(datum);
        if (compare <= 0) m_Left = node;
        else m_Right = node;
    }   

    public void delete(final T datum) {

    }

    @Override
    public String toString() {
        return null;
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
