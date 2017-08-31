public class Intro {

    public static void main(String[] args) {

        System.out.println(ret());
        System.out.println(logic(true, true, true));
        System.out.println(stars(5));
        System.out.println(coins(12));
    }

    /**
     * Returns the integer 17
     * @return 17
     */
    private static int ret() {

        return 17;
    }

    /**
     * Returns the logical and of all of the parameters
     * @param a First constant
     * @param b Second constant
     * @param c Third constant
     * @return Output of the logical and
     */
    private static boolean logic(boolean a, boolean b, boolean c) {

        return a && b && c;
    }

    /**
     * Prints a triangle of asteriks
     * @param n Amount of rows to the triangle
     * @return The string that represents the triangle
     */
    private static String stars(int n) {

        StringBuilder stars = new StringBuilder();
        // Loop through each row. Start at one so that
        // there is one star in row one and so on
        for (int i = 1; i <= n; i++) {
            // Loop through each star in each row and append an asterik
            for (int j = 0; j < i; j++) {
                stars.append("*");
            }
            // Append a line break
            if (i < n) stars.append("\n");
        }
        return stars.toString();
    }

    /**
     * Returns the sum of the minimum amount of 2 and 5 cent coins
     * to make the amount of change n
     * @param n The amount of cents to exchange
     * @return The sum of the minimum amount of 2 and 5 cent coins
     */
    private static int coins(int n) {

        // Get amount of cents left over after using as
        // many five cent coins as you can
        int leftOver = n % 5;

        int numOfFives, numOfTwos;

        // Get how many five cent coins you can use
        numOfFives = n / 5;

        // If the left over is 1 or 3, we need to go back on one
        // five coin and use more two coins instead to make the exchange
        if (leftOver == 1 || leftOver == 3) numOfFives--;

        // If number of fives is negative, no combo is possible
        // Only the values 1 and 3 of n trigger this
        if (numOfFives < 0) return -1;

        // Find difference left over after using five cent coins
        int diff = n - numOfFives * 5;

        // Get the amount of two cent coins you need to make up the remaining change
        numOfTwos = diff / 2;

        return numOfFives + numOfTwos;
    }
}
