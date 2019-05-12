import java.util.Map;
import java.util.List;
/**
 *@author Einat Heletz.
 *@version 1.8.0_201
 */
public interface Expression {
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
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method.
     * @return result of the expression
     * @throws Exception if the expression
     * contains a variable which is not in the assignment
     */
    double evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     * @return a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * Returns string representation of the expression.
     * @return representation of the expression.
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     * @param var variable to replaced with the provided expression.
     * @param expression provided expression replaced the provided variable
     * @return new expression after the replace.
     */
    Expression assign(String var, Expression expression);

    /**
     * This function Returns the expression tree resulting from differentiating
     *  the current expression relative to variable `var.
     * @param var current expression relative to variable
     * @return the expression tree resulting from differentiating
     */
    Expression differentiate(String var);

    /**
     * Returned a simplified version of the current expression.
     * @return simplify expression
     */
    Expression simplify();
}
