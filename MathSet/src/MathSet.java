import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MathSet<E> implements Set<E> {

    private E[] m_Container;
    private int m_Size;

    public MathSet() {
        this(0);
    }

    public MathSet(final int size) {
        initArray(size);
    }

    public MathSet(final Collection<? extends E> c) {
        this(c.size());
        addAll(c);
    }

    private void initArray(int size) {
        m_Container = (E[])new Object[size];
    }

    @Override
    public int size() {
        return m_Size;
    }

    @Override
    public boolean isEmpty() {
        return m_Size == 0;
    }

    @Override
    public boolean contains(final Object o) {
        for (int i = 0; i < m_Size; i++) {
            if (o.equals(m_Container[i]))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int i;
            @Override
            public boolean hasNext() {
                return i < m_Size;
            }
            @Override
            public E next() {
                return m_Container[i++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        final E[] a = (E[])new Object[m_Size];
        System.arraycopy(m_Container, 0, a, 0, m_Size);
        return a;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < m_Size; i++) {
            builder.append(m_Container[i].toString());
            // Note: Final element must have no comma and a closing bracket
            if (i < m_Size - 1) builder.append(", ");
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        // Note: Fits specifications under: https://docs.oracle.com/javase/7/docs/api/java/util/Set.html#toArray(T[])
        final Object[] array = a.length > m_Size ? a : (T[])new Object[m_Size];
        System.arraycopy(m_Container, 0, array, 0, m_Size);
        return (T[])array;
    }

    @Override
    public boolean add(final E e) {
        if (contains(e)) {
            return false;
        } else {
            if (m_Size + 1 > m_Container.length) {
                /* Copy contents to temporary array, increase size of internal array, and copy back */
                final E[] temp = (E[])new Object[m_Size];
                System.arraycopy(m_Container, 0, temp, 0, m_Size);
                // Note: If the initial size is zero then doubling it will not change the size
                initArray(m_Size == 0 ? 1 : m_Size * 2);
                System.arraycopy(temp, 0, m_Container, 0, m_Size);
            }
            m_Container[m_Size++] = e;
            return true;
        }
    }

    @Override
    public boolean remove(final Object o) {
        for (int i = 0; i < m_Size; i++) {
            if (o.equals(m_Container[i])) {
                return removeAtIndex(i);
            }
        }
        return false;
    }

    public boolean removeAtIndex(final int i) {
        if (i < m_Size && i >= 0) {
            // Create array of elements that come after the element to remove
            final E[] temp = (E[])new Object[m_Size - i - 1];
            System.arraycopy(m_Container, i + 1, temp, 0, m_Size - i - 1);
            // Shifted elements after removed element over to fill the gap
            System.arraycopy(temp, 0, m_Container, i, m_Size - i - 1);
            // Remove the last element and decrease the size of the array
            m_Container[--m_Size] = null;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        boolean containsAll = true;
        for (final Object o : c)
            containsAll &= contains(o);
        return containsAll;
    }

    @Override
    public boolean addAll(final Collection<? extends E> c) {
        boolean changed = false;
        for (final E e : c)
            changed |= (add(e));
        return changed;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        boolean changed = false;
        // Iterate over every element and test if it is in given collection. If not, remove it.
        // Note: Starting size is cached at beginning because it will change with the remove function.
        // Note: j is used to keep track of offsets caused by removing elements
        for (int i = 0, j = 0, size = m_Size; i < size; i++) {
            if (!c.contains(m_Container[i - j])) {
                if (removeAtIndex(i - j)) {
                    changed = true;
                    j++;
                }
            }
        }
        return changed;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        boolean changed = false;
        for (final Object e : c)
            changed |= (remove(e));
        return changed;
    }

    @Override
    public void clear() {
        m_Size = 0;
    }

    /**
     * Copy values of this math set into another one.
     * @return Cloned math set
     */
    public MathSet<E> copy() {
        final MathSet<E> clone = new MathSet<>(m_Size);
        clone.addAll(this);
        return clone;
    }

    /**
     * Obtain the union of both of the math sets, the set that contains all elements from both sets.
     * @param other Math set to create a union with
     * @return Union with the set provided
     */
    public MathSet<E> union(final MathSet<E> other) {
        final MathSet<E> union = new MathSet<>(size() + other.size());
        union.addAll(this);
        union.addAll(other);
        return union;
    }

    /**
     * Obtain the intersection of both math sets, only contains shared elements between both.
     * @param other Math set to create a difference with
     * @return Intersection with the set provided
     */
    public MathSet<E> intersection(final MathSet<E> other) {
        final MathSet<E> intersection = copy();
        intersection.retainAll(other);
        return intersection;
    }
}
