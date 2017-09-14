package logic;

/**
 * XOR operator
 */
public class ExclusiveDisjunction extends Conjunction {

    public ExclusiveDisjunction(LogicalSentence left, LogicalSentence right) {

        super(left, right);
    }

    @Override
    public boolean evaluate(TruthAssignment ta) {

        return left.evaluate(ta) != right.evaluate(ta);
    }
}
