import java.util.Map;
/**
 *@author Einat Heletz.
 *@version 1.8.0_201
 */
public class Cos extends UnaryExpressions implements Expression {
    /**
     * This function is the constructor.
     * @param exp Expression
     */
    public Cos(Expression exp) {
        super(exp);
    }

    /**
     * This function is the constructor.
     * @param exp number
     */
    public Cos(double exp) {
        super(exp);
    }

    /**
     * This function is the constructor.
     * @param exp variable
     */
    public Cos(String exp) {
        super(exp);
    }

    /**
     Evaluate the expression using the variable values provided
     in the assignment, and return the result. If the expression
     contains a variable which is not in the assignment, an exception
     is thrown.
     * @param assignment map of variable
     * @return result of the cos
     * @throws Exception if the expression
     * contains a variable which is not in the assignment
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.cos((this.getExp().evaluate(assignment)));
    }

    /**
     * Returns string representation of the expression.
     * @return representation of the expression.
     */
    public String toString() {
        return "cos(" + this.getExp().toString() + ")";
    }

    /**
     * This function add cos to the new expression.
     * to finish the action of assign
     * @param exp expression
     * @return new expression after the replace.
     */
    public Expression assignUnary(Expression exp) {
        return new Cos(exp);
    }

    /**
     * This function Returns the expression tree resulting from differentiating
     *  the current expression relative to variable `var.
     * @param var current expression relative to variable
     * @return the expression tree resulting from differentiating
     */
    public Expression differentiate(String var) {
        //differentiate -sin(g)*g'
        Expression cosDiff = new Neg(new Sin(this.getExp()));
        Expression insideDiff = this.getExp().differentiate(var);
        Expression diff = new Mult(cosDiff, insideDiff);
        return diff;
    }

    /**
     * Returned a simplified version of the current expression.
     * @return simplify expression
     */
    public Expression simplify() {
        try {
            //if it number
            double simplify = Double.parseDouble(this.getExp().simplify().toString());
            return new Num(Math.cos(simplify));
        } catch (NumberFormatException e) {
            ;
        }
        return new Cos(this.getExp().simplify());
    }
}
