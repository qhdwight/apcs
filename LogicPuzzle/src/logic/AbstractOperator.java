package logic;

/**
 * Base class for conjunctions with two propositions
 */
public abstract class AbstractOperator extends LogicalSentence {

    protected LogicalSentence left, right;

    public AbstractOperator(LogicalSentence left, LogicalSentence right) {

        this.left = left;
        this.right = right;
    }

    @Override
    public abstract boolean evaluate(TruthAssignment ta);
}
