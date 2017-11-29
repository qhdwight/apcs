import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Integer[] input = {3, 1, 4, 1, 5, 9};

        Sorter<Integer> sorter = new SelectionSort<>();

        sorter.sort(input);

        System.out.println(Arrays.toString(input));
    }
}
