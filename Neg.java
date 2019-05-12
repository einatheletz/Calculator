import java.util.Map;
/**
 *@author Einat Heletz.
 *@version 1.8.0_201
 */
public class Neg extends UnaryExpressions implements Expression {
    /**
     * This function is the constructor.
     * @param exp Expression
     */
    public Neg(Expression exp) {
        super(exp);
    }

    /**
     * This function is the constructor.
     * @param exp number
     */
    public Neg(double exp) {
        super(exp);
    }

    /**
     * This function is the constructor.
     * @param exp variable
     */
    public Neg(String exp) {
        super(exp);
    }

    /**
     Evaluate the expression using the variable values provided
     in the assignment, and return the result. If the expression
     contains a variable which is not in the assignment, an exception
     is thrown.
     * @param assignment map of variable
     * @return result of the neg
     * @throws Exception if the expression
     * contains a variable which is not in the assignment
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return -1 * (this.getExp().evaluate(assignment));
    }

    /**
     * Returns string representation of the expression.
     * @return representation of the expression.
     */
    public String toString() {
        return "(-" + this.getExp().toString() + ")";
    }

    /**
     * This function add sin to the new expression.
     * to finish the action of assign
     * @param exp expression
     * @return new expression after the replace.
     */
    public Expression assignUnary(Expression exp) {
        return new Neg(exp);
    }

    /**
     * This function Returns the expression tree resulting from differentiating
     *  the current expression relative to variable `var.
     * @param var current expression relative to variable
     * @return the expression tree resulting from differentiating
     */
    public Expression differentiate(String var) {
        Expression negDiff  = new Mult(new Num(-1), this.getExp());
        return negDiff.differentiate(var);
    }

    /**
     * Returned a simplified version of the current expression.
     * @return simplified expression
     */
    public Expression simplify() {
        Expression simplify = this.getExp().simplify();
        //check if there is variable in the expression
        if (simplify.getVariables().isEmpty()) {
            //if not calculate the expression
            try {
                return new Num(-1 * simplify.evaluate());
            } catch (Exception e) {
                ;
            }
        }
        //if the exp is neg also
        if (this.getExp() instanceof  Neg) {
           return ((Neg) this.getExp()).getExp().simplify();
        }
        //in any other case
        return new Neg(this.getExp().simplify());
    }
}
