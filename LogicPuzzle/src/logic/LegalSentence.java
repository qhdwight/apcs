package logic;

/**
 * Holds functions useful for determining if a string represents a legal logical sentence.
 */
public class LegalSentence {

    private static final String[] OPERATORS = {"&", "|", "<=>", "=>"};

    private static final String LETTER_REGEX = "[a-zA-Z]+";

    /**
     * A recursive function to determine if a string represents a legal logical sentence.
     * Uses the function {@link #legalConstant} to determine if the constants are legal.
     *
     * @param sentence Potential logical sentence string
     * @return Whether or not the string represents a legal logical sentence
     */
     public static boolean legal(String sentence) {

         if (!legalParenthesis(sentence)) return false;

         // Get rid of parenthesis
        sentence = sentence.replaceAll("[()]", "");

         String next = "", constant = "";
         boolean hasOperator = false;
         // First loop goes over sentence characters
         CHAR_ITERATE: for (int i = 0; i < sentence.length(); i++) {
             // Second loop looks for OPERATORS in sentence
             for (String operator : OPERATORS) {
                 boolean match = true;
                 // Test if this character is the beginning and finishes an operator
                 for (int j = 0; j < operator.length() && i+j < sentence.length(); j++) {
                     if (sentence.charAt(i+j) != operator.charAt(j))
                         match = false;
                 }
                 // If this is a match get the constant and next sentence
                 if (match) {
                     hasOperator = true;

                     // Get the constant and the next string to evaluate
                     constant = sentence.substring(0, i);
                     next = sentence.substring(i+operator.length());

                     break CHAR_ITERATE;
                 }
             }
         }
         // If the string has an operator, evaluate constant and next string recursively to get legality.
         // Otherwise, it is treated like a single constant is is checked as one.
         return hasOperator ? legalConstant(constant) && legal(next) : legalConstant(sentence);
    }

    /**
     * Determines whether or not a string represents a legal logical constant.
     * It can contain negation operators, but they must be before the constant name to be legal.
     * The name of the constant can only consist of letters to be legal.
     *
     * @param constant String that potentially represents a legal logical constant
     * @return Whether or not the string represents a legal logical constant
     */
    public static boolean legalConstant(String constant) {

        // First check if string is empty
        if (constant.isEmpty()) return false;

        // Then check if string is only letters (without negations)
        if (!constant.replaceAll("~", "").matches(LETTER_REGEX)) return false;

        // Assume legal until disproved
        boolean legal = true;
        boolean encounteredNonNegateSymbol = false;

        // Check if negations are before constant name
        for (int i = 0; i < constant.length(); i++) {
            if (constant.charAt(i) != '~') encounteredNonNegateSymbol = true;
            else if (encounteredNonNegateSymbol) legal = false;
        }

        return legal;
    }

    /**
     * Tests if a string representing a legal sentence has balanced parenthesis
     *
     * @param sentence String that represents a logical sentence
     * @return Whether or not the strings are balanced
     */
    public static boolean legalParenthesis(String sentence) {
        // Count parenthesis to see if they are balance
        int balance = 0;
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) == '(') balance++;
            else if (sentence.charAt(i) == ')') balance--;
        }
        return balance == 0;
    }

    /**
     * Finds the index of the rightmost character in the first substring of the
     * argument that is enclosed by matching parenthesis and which contains no parenthesis.
     *
     * @param sentence A string that represents a logical sentence
     * @return The index of the rightmost character in the first substring of the
     * argument that is enclosed by matching parenthesis and which contains no parenthesis
     */
    public static int findMatch(String sentence, int n) {

        boolean inOpen = false;
        for (int i = 0; i < sentence.length(); i++) {

            final char character = sentence.charAt(i);
            if (character == '(') {
                inOpen = true;
            } else if (character == ')') {
                if (inOpen) {
                    return i-1;
                }
                inOpen = false;
            }
        }
        return -1;
    }
}
