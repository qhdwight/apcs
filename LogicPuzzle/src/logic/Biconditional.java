package logic;

public class Biconditional extends AbstractOperator {

    public Biconditional(LogicalSentence left, LogicalSentence right) {

        super(left, right);
    }

    @Override
    public boolean evaluate(TruthAssignment ta) {

        return left.evaluate(ta) == right.evaluate(ta);
    }
}
