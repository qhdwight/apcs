import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        final Integer[] input = { 3, 1, 4, 1, 5, 9, 2 };

        final Integer[] bubbleInput = input.clone(), insertionInput = input.clone(), selectionInput = input.clone();

        final Sorter<Integer> bubbleSort = new BubbleSort<>(), insertionSort = new InsertionSort<>(), selectionSort = new SelectionSort<>();

        bubbleSort   .sort(bubbleInput   );
        insertionSort.sort(insertionInput);
        selectionSort.sort(selectionInput);

        System.out.println(Arrays.toString(bubbleInput   ));
        System.out.println(Arrays.toString(insertionInput));
        System.out.println(Arrays.toString(selectionInput));
    }
}
