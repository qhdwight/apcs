/**
 * Sorts an array of type {@link T} by finding the smallest element and swapping it to the front of the array repeatedly.
 *
 * @param <T> Type that implements {@link Comparable}
 */
public class SelectionSort<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public T[] sort(T[] input) {

        // Iterate over every index
        for (int i = 0; i < input.length-1; i++) {

            int minIndex = i;

            // Find next minimum element
            for (int j = i+1; j < input.length; j++) {
                if (input[j].compareTo(input[minIndex]) < 0)
                    minIndex = j;
            }

            // Swap it to the next spot in the front
            Sorter.swap(input, minIndex, i);
        }

        return input;
    }
}
