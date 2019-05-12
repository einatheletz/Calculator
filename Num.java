import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *@author Einat Heletz.
 *@version 1.8.0_201
 */
public class Num implements Expression {
    private double num;

    /**
     * This function is the constructor.
     * @param num new number
     */
    public Num(double num) {
        this.num = num;
    }
    /**
     Evaluate the expression using the variable values provided
     in the assignment, and return the result. If the expression
     contains a variable which is not in the assignment, an exception
     is thrown.
     * @param assignment map of variable
     * @return result of the expression
     * @throws Exception if the expression
     * contains a variable which is not in the assignment
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.num;
    }

    /**
     * A convenience method.
     * @return result of the expression
     * @throws Exception if the expression
     * contains a variable which is not in the assignment
     */
     public double evaluate() throws Exception {
         return this.num;
     }

    /**
     * Returns a list of the variables in the expression.
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {
        List<String> variables = new ArrayList<String>();
        return variables;
    }

    /**
     * Returns string representation of the expression.
     * @return representation of the expression.
     */
    public String toString() {
        return Double.toString(this.num);
    }

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     * @param var variable to replaced with the provided expression.
     * @param expression provided expression replaced the provided variable
     * @return new expression after the replace.
     */
    public Expression assign(String var, Expression expression) {
        return this;
    }

    /**
     * This function Returns the expression tree resulting from differentiating
     *  the current expression relative to variable `var.
     * @param var current expression relative to variable
     * @return the expression tree resulting from differentiating
     */
    public Expression differentiate(String var) {
        return new Num(0);
    }

    /**
     * Returned a simplified version of the current expression.
     * @return simplified expression.
     */
    public Expression simplify() {
        return this;
    }
}
