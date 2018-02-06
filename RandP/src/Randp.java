import java.util.Random;

/**
 * Given an upper bound, returns non-repeating random integers from one to that value.
 */
public class Randp {

    private final int[] m_Numbers;
    private int m_NumbersLeft;
    private final Random m_Random;

    /**
     * Create a new random object with a given amount of numbers.
     *
     * @param upper Upper bound of the random number
     */
    public Randp(final int upper) {
        m_Numbers = new int[upper];
        m_NumbersLeft = upper;
        initializeNumbers();
        // Initialize random object with unique seed (the current time)
        m_Random = new Random(System.currentTimeMillis());
    }

    /**
     * Initialize the number array with ascending integers starting at one.
     */
    private void initializeNumbers() {
        // Create array of ascending numbers
        for (int i = 0; i < m_NumbersLeft; i++)
            m_Numbers[i] = i + 1;
    }

    /**
     * Obtain the next non-repeated integer.
     *
     * @return A random integer that is guaranteed to not be repeated, zero if no integers are left
     */
    public int nextInt() {
        if (m_NumbersLeft >= 1) {
            final int index = m_Random.nextInt(m_NumbersLeft);
            --m_NumbersLeft;
            // Swap to end of array
            final int randomInt = m_Numbers[index];
            m_Numbers[index] = m_Numbers[m_NumbersLeft];
            m_Numbers[m_NumbersLeft] = randomInt;
            return randomInt;
        } else {
            // Return zero when all random numbers have been requested
            return 0;
        }
    }
}
