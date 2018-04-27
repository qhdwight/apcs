package logic;

/**
 * AND operator
 */
public class Conjunction extends AbstractOperator {

    public Conjunction(LogicalSentence left, LogicalSentence right) {

        super(left, right);
    }

    @Override
    public boolean evaluate(TruthAssignment ta) {

        return left.evaluate(ta) && right.evaluate(ta);
    }
}
