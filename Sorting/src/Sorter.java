/**
 * Interface for an object that can sort an array of type {@link T}.
 * Implementing classes use {@link #sort(Comparable[])} and can use helper method {@link #swap(Object[], int, int)}.
 *
 * @param <T> Type that implements {@link Comparable}
 */
public interface Sorter<T extends Comparable<T>> {

    /**
     * Directly sort an array of any type.
     *
     * @param input Array to sort
     * @return Sorted array
     */
    public T[] sort(T[] input);

    /**
     * Swap the elements at two provided inputs.
     *
     * @param input Input array
     * @param f_index Index of first element to swap
     * @param s_index Index of second element to swap
     * @param <V> Type of the array
     */
    public static <V> void swap(V[] input, int f_index, int s_index) {

        final V f_value = input[f_index], s_value = input[s_index];
        input[f_index] = s_value;
        input[s_index] = f_value;
    }
}
