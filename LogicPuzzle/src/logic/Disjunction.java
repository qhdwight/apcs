package logic;

/**
 * OR operator
 */
public class Disjunction extends AbstractOperator {

    public Disjunction(LogicalSentence left, LogicalSentence right) {

        super(left, right);
    }

    @Override
    public boolean evaluate(TruthAssignment ta) {

        return left.evaluate(ta) || right.evaluate(ta);
    }
}
