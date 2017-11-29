public class SelectionSort<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public T[] sort(T[] input) {

        for (int i = 0; i < input.length-1; i++) {

            int minIndex = i;

            for (int j = i+1; j < input.length; j++) {
                if (input[j].compareTo(input[minIndex]) < 0)
                    minIndex = j;
            }

            Sorter.swap(input, minIndex, i);
        }

        return input;
    }
}
