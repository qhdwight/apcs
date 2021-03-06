/**
 * Sorts an array of type {@link T} by comparing adjacent elements repeatedly and adjusting their position using {@link Sorter#swap(Object[], int, int)}.
 *
 * @param <T> Type that implements {@link Comparable}
 */
public class BubbleSort<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public T[] sort(T[] input) {

        // Iterate over every element and stop on the second to last one (since we compare j+1)
        for (int i = 0; i < input.length-1; i++) {

            // Last book will be pushed to the end, then second to last, etc.. so we can subtract i
            for (int j = 0; j < input.length-i-1; j++) {

                // Compare adjacent elements and swap them if they are out of order
                if (input[j].compareTo(input[j+1]) > 0)
                    Sorter.swap(input, j, j+1);
            }
        }

        return input;
    }
}
