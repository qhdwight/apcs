package logic;

public class Conditional extends AbstractOperator {

    public Conditional(LogicalSentence left, LogicalSentence right) {

        super(left, right);
    }

    @Override
    public boolean evaluate(TruthAssignment ta) {

        return !left.evaluate(ta) || right.evaluate(ta);
    }
}
