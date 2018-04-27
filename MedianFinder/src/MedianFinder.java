/**
 * Code for finding a median of an array.
 * Reference code used from: https://www.geeksforgeeks.org/quick-sort/
 */
public class MedianFinder {

    public static void main(String[] args) {
        final Integer[] array = {1, 2, 3, 4, 5};
        final Integer median = findMedian(array, 0, array.length-1, array.length/2);
        System.out.println(Integer.toString(median));
    }

    /**
     * Given an array and two indices, swap the values.
     * @param array Array to swap in
     * @param i1 First index
     * @param i2 Second index
     * @param <T> Generic type for element
     */
    private static <T> void swap(final T[] array, final int i1, final int i2) {
        final T temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    /**
     * Find the median of an array.
     * @param array Array to find median in
     * @param low Lower bound, this should be zero at the start
     * @param high High bound, this should be array length minus one at the start
     * @param n Index to search for, for median this should be array length over two
     * @param <T> Generic type for element
     * @return The median
     */
    private static <T extends Comparable<T>> T findMedian(final T[] array, int low, int high, int n) {
        while (true) {
            final int pi = partition(array, low, high), length = pi - low + 1;
            if (n == length) {
                return array[pi];
            } else if (n < length) {
                high = pi - 1;
            } else {
                n -= length;
                low = pi + 1;
            }
        }
    }

    /**
     * Partition an array given a lower and upper bound. Partition point is chosen as the end.
     * @param array Array to partition
     * @param low Lower bound
     * @param high Upper bound
     * @param <T> Generic type for an element, must be comparable so we know how to partition
     * @return Index of the partition
     */
    private static <T extends Comparable<T>> int partition(final T[] array, final int low, final int high) {
        final T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high-1; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }
}
