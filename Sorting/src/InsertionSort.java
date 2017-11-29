/**
 * Sorts an array of type {@link T} by iterating over it and comparing a focused element to everything before it and making the swap if necessary.
 *
 * @param <T> Type that implements {@link Comparable}
 */
public class InsertionSort<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public T[] sort(T[] input) {

        // Iterate over array, start at second index
        for (int i = 1; i < input.length; i++) {

            // Compare element at j with the one left to it and swap if they are out of order
            for (int j = i; j > 0; j--) {

                if (input[j].compareTo(input[j-1]) < 0) {
                    Sorter.swap(input, j, j-1);
                }
            }
        }

        return input;
    }
}