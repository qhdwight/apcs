import java.util.HashSet;
import java.util.Set;

public class Vocabulary14 {

    /**
     * Given two sets, return the union of the two.
     * Since it is a set no two same values will be in the set.
     * @param s1 The first set
     * @param s2 The second set
     * @param <E> Generic element type
     * @return Set that is the union
     */
    private static <E> Set<E> union(final Set<E> s1, final Set<E> s2) {
        final Set<E> ret = new HashSet<>();
        ret.addAll(s1);
        ret.addAll(s2);
        return ret;
    }

    /**
     * Given two sets, return the intersection of the two.
     * For each element, check if the second set contains it and if it does add it to the return.
     * @param s1 The first set
     * @param s2 The second set
     * @param <E> Generic element type
     * @return Set that is the intersection
     */
    private static <E> Set<E> intersection(final Set<E> s1, final Set<E> s2) {
        final Set<E> ret = new HashSet<>();
        for (final E e : s1) {
            if (s2.contains(e)) ret.add(e);
        }
        return ret;
    }

    /**
     * Given a set that contains all, and a subset of it, return the compliment.
     * @param all All of the elements
     * @param s1 A set to take the compliment of
     * @param <E> Generic element type
     * @return Set that is the compliment
     */
    private static <E> Set<E> setDifference(final Set<E> all, final Set<E> s1) {
        final Set<E> ret = new HashSet<>();
        for (final E e : all) {
            if (!s1.contains(e)) ret.add(e);
        }
        return ret;
    }

    /**
     * Given a set of data, calculate the standard deviation.
     * @param data Data
     * @return The standard deviation
     */
    private static double standardDeviation(final double[] data) {
        double sum = 0, sd = 0;
        for (double d : data)
            sum += d;
        double mean = sum / data.length;
        for (double d : data)
            sd += Math.pow(d - mean, 2);
        return Math.sqrt(sd / data.length);
    }

    /**
     * Calculate the binomial coefficient of a polynomial with power n and place in the polynomial k.
     * This function uses recursion to calculate previous coefficients.
     * @param n Power of polynomial
     * @param k Position in polynomial
     * @return Binomial coefficient
     */
    private static long binomialCoefficient(final int n, final int k) {
        // Base cases
        if (n < k)
            return 0;
        if (k == 0 || n == k)
            return 1;
        // Recursive call
        return binomialCoefficient(n-1, k-1) + binomialCoefficient(n-1, k);
    }
}
