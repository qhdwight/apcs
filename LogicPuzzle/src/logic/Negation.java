package logic;

/**
 * Negation operator for a logical sentence
 */
public class Negation extends LogicalSentence {

    private LogicalSentence sentence;

    public Negation(LogicalSentence sentence) {

        super(sentence.getConstant());

        this.sentence = sentence;
    }

    @Override
    public boolean evaluate(TruthAssignment ta) {

        return !sentence.evaluate(ta);
    }
}
