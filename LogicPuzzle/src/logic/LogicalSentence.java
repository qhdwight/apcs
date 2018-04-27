package logic;

/**
 * Base class for a logical sentences and operators.
 * Contains a method for evaluating, usually overridden by subclasses.
 */
public class LogicalSentence {

    private PropositionConstant constant;

    public LogicalSentence(PropositionConstant constant) {

        this.constant = constant;
    }

    public LogicalSentence() {}

    public PropositionConstant getConstant() {
        return constant;
    }

    /**
     * Evaluate this statement with given truth assignments for each constant.
     * This should be overridden by operators.
     *
     * @param ta The truth assignment object
     * @return The logical evaluation of this sentence with the given truth assignments
     */
    public boolean evaluate(TruthAssignment ta) {

        return ta.get(constant);
    }
}
