import java.util.Map;
/**
 *@author Einat Heletz.
 *@version 1.8.0_201
 */
public class Sin extends UnaryExpressions implements Expression  {

    /**
     * This function is the constructor.
     * @param exp Expression
     */
     public Sin(Expression exp) {
         super(exp);
     }

    /**
     * This function is the constructor.
     * @param exp number
     */
    public Sin(double exp) {
        super(exp);
    }

    /**
     * This function is the constructor.
     * @param exp variable
     */
    public Sin(String exp) {
        super(exp);
    }

    /**
     Evaluate the expression using the variable values provided
     in the assignment, and return the result. If the expression
     contains a variable which is not in the assignment, an exception
     is thrown.
     * @param assignment map of variable
     * @return result of the sin
     * @throws Exception if the expression
     * contains a variable which is not in the assignment
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
            return Math.sin((this.getExp().evaluate(assignment)));
    }

    /**
     * Returns string representation of the expression.
     * @return representation of the expression.
     */
    public String toString() {
        return "sin(" + this.getExp().toString() + ")";
    }

    /**
     * This function add sin to the new expression.
     * to finish the action of assign
     * @param exp expression
     * @return new expression after the replace.
     */
    public Expression assignUnary(Expression exp) {
        return new Sin(exp);
    }
    /**
     * This function Returns the expression tree resulting from differentiating
     *  the current expression relative to variable `var.
     * @param var current expression relative to variable
     * @return the expression tree resulting from differentiating
     */
    public Expression differentiate(String var) {
        //differentiate cos(g)*g'
        Expression cosDiff = new Cos(this.getExp());
        Expression insideDiff = this.getExp().differentiate(var);
        Expression diff = new Mult(cosDiff, insideDiff);
        return diff;
    }

    /**
     * Returned a simplified version of the current expression.
     * @return simplified expression
     */
    public Expression simplify() {
        //if there is not variable
        try {
            double simplify = Double.parseDouble(this.getExp().simplify().toString());
            return new Num(Math.sin(simplify));
        } catch (NumberFormatException e) {
            ;
        }
        //any other case
        return new Sin(this.getExp().simplify());
    }
}
