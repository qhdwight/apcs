import java.util.*;

/**
 * {@link Queue} which implements priority with elements.
 * Elements with a high priority will be put before elements with lower priorities.
 * The priority of an element is determined based on a {@link Comparator}
 *
 * @param <E> Type of element to hold
 */
public class PriorityQueue<E extends Comparable<E>> extends AbstractQueue<E> {

    private ArrayList<E> m_Elements;
    private Comparator<E> m_Comparator;

    /**
     * Create a blank {@link PriorityQueue} with an initial capacity of zero.
     * This will use natural ordering for sorting elements.
     */
    public PriorityQueue() {
        this(0);
    }

    /**
     * Create a {@link PriorityQueue} with a given starting capacity.
     * This will use natural ordering for sorting elements.
     *
     * @param capacity Starting capacity
     */
    public PriorityQueue(final int capacity) {
        this(capacity, Comparator.naturalOrder());
    }

    /**
     * Create a {@link PriorityQueue} with a given starting capacity and {@link Comparator}
     *
     * @param capacity Starting capacity
     * @param comparator Comparator to compare two elements
     */
    public PriorityQueue(final int capacity, final Comparator<E> comparator) {
        m_Elements = new ArrayList<>(capacity);
        m_Comparator = comparator;

    }

    /**
     * Creates a {@link PriorityQueue} that contains the following elements from the collection given
     *
     * @param collection Collection to add elements from
     */
    public PriorityQueue(final Collection<E> collection) {
        m_Elements = new ArrayList<>(collection.size());
        m_Comparator = Comparator.naturalOrder();
        for (final E e : collection) offer(e);
    }

    /**
     * Given another {@link PriorityQueue}, merge its elements with this one
     *
     * @param other Other {@link PriorityQueue} to merge into this one
     */
    public void mergeWith(final PriorityQueue<E> other) {
        m_Elements.addAll(other);
        sort();
    }

    /**
     * Find the element at the upper index. This element will be placed at its correct position,
     * and elements that are smaller will be placed before and greater elements will be placed after.
     * The index of where the element remains at the end is returned, as we need to know where to keep sorting from.
     *
     * @param lower Lower bounding index
     * @param upper Upper bounding index
     * @return Index of the element that was used to partition
     */
    private int partition(final int lower, final int upper) {
        // Use the upper boundary as the partition index
        final E pivot = m_Elements.get(upper);
        int i = lower - 1;
        for (int j = lower; j < upper; j++) {
            // Test if element is greater than pivot, it should be moved to the right towards pivot
            if (m_Elements.get(j).compareTo(pivot) > 0) {
                i++;
                // Swap values at indices i and j
                final E temp = m_Elements.get(i);
                m_Elements.set(i, m_Elements.get(j));
                m_Elements.set(j, temp);
            }
        }
        // Swap pivot (which is at end) to its proper position in the array (i + 1)
        final int pivotIndex = i + 1;
        final E temp = m_Elements.get(pivotIndex);
        m_Elements.set(pivotIndex, m_Elements.get(upper));
        m_Elements.set(upper, temp);
        return pivotIndex;
    }

    /**
     * Recursive Quick Sort with a given lower and upper boundary.
     * This uses {@link #partition(int, int)} to divide by the element at the upper index given.
     *
     * @param lower Lower bounding index
     * @param upper Upper bounding index
     */
    private void sort(final int lower, final int upper) {
        // Make sure we have a legitimate partition (not of size zero or less)
        if (lower < upper) {
            final int partitionIndex = partition(lower, upper);
            // Sort lower and upper partitions that were created
            sort(lower, partitionIndex - 1);
            sort(partitionIndex + 1, upper);
        }
    }

    /**
     * Given that the elements of the {@link PriorityQueue} are currently unsorted, sort ALL of them using Quick Sort.
     */
    private void sort() {
        sort(0, m_Elements.size() - 1);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                return i < m_Elements.size();
            }

            @Override
            public E next() {
                return m_Elements.get(i++);
            }
        };
    }

    @Override
    public int size() {
        return m_Elements.size();
    }

    /**
     * Insert the given element into the queue.
     * Based on the priority, it is put into the queue accordingly.
     * This employs the strategy of looping through every element to find the first element with a lower priority,
     * and then inserting the element before that element.
     *
     * @param element Element to insert
     * @return Always true
     */
    @Override
    public boolean offer(final E element) {
        final int size = m_Elements.size();
        for (int i = 0; i < size; i++) {
            if (m_Comparator.compare(element, m_Elements.get(i)) > 0) {
                m_Elements.add(i, element);
                return true;
            }
        }
        // Element is at the very end
        m_Elements.add(element);
        return true;
    }

    /**
     * Test if a given object exists in this {@link PriorityQueue}
     *
     * @param o Object to test if is contained
     * @return Whether or not this {@link PriorityQueue} contains the given object
     */
    @Override
    public boolean contains(final Object o) {
        for (final E element : m_Elements) {
            if (element.equals(o))
                return true;
        }
        return false;
    }

    /**
     * See {@link #offer(Comparable)}, this acts as a wrapper
     *
     * @param e Element to add
     * @return Always true
     */
    @Override
    public boolean add(final E e) {
        return offer(e);
    }

    /**
     * Get the next element in the queue.
     *
     * @return Next element
     */
    @Override
    public E poll() {
        final E first = peek();
        m_Elements.remove(0);
        return first;
    }

    /**
     * Search this {@link PriorityQueue} for the given element and remove it if found.
     *
     * @param o Object to remove
     * @return Whether or not object was found and removed
     */
    @Override
    public boolean remove(final Object o) {
        final int size = m_Elements.size();
        for (int i = 0; i < size; i++) {
            if (m_Elements.get(i).equals(o)) {
                m_Elements.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Convert this {@link PriorityQueue} into an array.
     *
     * @return Array representing this {@link PriorityQueue}
     */
    @Override
    public Object[] toArray() {
        final int size = m_Elements.size();
        final Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = m_Elements.get(i);
        }
        return array;
    }

    /**
     * Get the {@link Comparator} that this {@link PriorityQueue} uses for ordering elements.
     *
     * @return The {@link Comparator} used for ordering elements
     */
    public Comparator<? super E> comparator() {
        return m_Comparator;
    }

    @Override
    public void clear() {
        m_Elements.clear();
    }

    /**
     * Get and remove the next element in the queue.
     *
     * @return Next element that got removed
     */
    @Override
    public E peek() {
        return m_Elements.get(0);
    }

    @Override
    public String toString() {
        return m_Elements.toString();
    }
}
