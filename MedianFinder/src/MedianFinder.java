import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MedianFinder {

    public static void main(String[] args) {
        // System.out.println(findMedianInt(new Integer[]{ 3, 1, 4, 1, 5, 9, 2, 6 }));
        final Integer[] array = {5, 4, 3, 2, 1};
        findMedian(array, 0, array.length-1);
        //Integer median = findMedian(array, 0, array.length-1);
        //System.out.println(Integer.toString(median));
    }

//    public static Integer findMedianInt(final Integer[] array) {
//        return findMedian(array, 0, array.length-1);
//    }

    private static <T> void swap(final T[] array, final int i1, final int i2) {
        final T temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    private static <T extends Comparable<T>> void findMedian(final T[] array, final int low, final int high) {
        if (low < high) {
            System.out.println(String.format("Low: %d, High: %d", low, high));
            final int pi = partition(array, low, high);
            System.out.println(String.format("Pivot: %d", pi));
            if (high - low == 1) System.out.println("Median: " + array[pi]);
//            if (pi > array.length/2) {
                findMedian(array, low, pi-1);
//            } else {
                findMedian(array, pi+1, high);
//            }
        }
    }

    private static <T extends Comparable<T>> int partition(final T[] array, final int low, final int high) {
        final T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        System.out.println(Arrays.toString(array));
        return i + 1;
    }
}
