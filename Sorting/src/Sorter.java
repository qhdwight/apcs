/**
 * Interface for an object that can sort an array of type {@link T}.
 * Implementing classes implemnet {@link #sort(Comparable[])} and can use helper method {@link #swap(Object[], int, int)}.
 *
 * @param <T> Type that implements {@link Comparable}
 */
public interface Sorter<T extends Comparable<T>> {

    public T[] sort(T[] input);

    public static <V> void swap(V[] input, int f_index, int s_index) {

        final V f_value = input[f_index], s_value = input[s_index];
        input[f_index] = s_value;
        input[s_index] = f_value;
    }
}
