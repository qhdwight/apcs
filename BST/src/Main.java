public class Main {

    private static final int ITERATIONS = 1000, NUMBER_UPPER_BOUND = 10000;

    public static void main(String[] args) {
        printAverageDepth(ITERATIONS, NUMBER_UPPER_BOUND);
    }

    /**
     * Find the average depth of trees that have randomly inserted numbers from 0 to the upper bound given.
     * Numbers do not repeat.
     *
     * @param iterations Amount depths to test and average out
     * @param numberUpperBound Upper bound of numbers put into tree
     */
    private static void printAverageDepth(final int iterations, final int numberUpperBound) {
        int total = 0;
        for (int j = 0; j < iterations; j++) {
            final BST<Integer> tree = new BST<>(5);
            final RandP randP = new RandP(numberUpperBound);
            for (int i = 0; i < numberUpperBound; i++) {
                final int number = randP.nextInt();
                tree.insert(number);
            }
            if (j == 0) tree.printTree();
            total += tree.depth();
        }
        System.out.println("");
        System.out.println(String.format("Average depth: %f", (float)total / iterations));
    }
}
