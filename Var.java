import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *@author Einat Heletz.
 *@version 1.8.0_201
 */
public class Var implements Expression {
    private String variable;

    /**
     * This function is the constructor.
     * @param var the mark of the variable
     */
    public Var(String var) {
        this.variable = var;
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
         if (assignment.isEmpty() || !(assignment.containsKey(this.variable))) {
             throw new Exception("The variable is not in the variables table");
         }
         if (this.variable.equals("e")) {
             return Math.E;
         }
         if (this.variable.equals("pi")) {
             return Math.PI;
         }
         return assignment.get(this.variable);
     }

    /**
     * A convenience method.
     * @return result of the expression
     * @throws Exception if the expression
     * contains a variable which is not in the assignment
     */
      public double evaluate() throws Exception {
            return evaluate(null);
      }

    /**
    * Returns a list of the variables in the expression.
    * @return a list of the variables in the expression.
    */
    public List<String> getVariables() {
        List<String> variables = new ArrayList<String>();
        variables.add(this.variable);
        return variables;
    }
    /**
     * Returns string representation of the expression.
     * @return representation of the expression.
     */
    public String toString() {
        return this.variable;
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
            if (this.variable.equals(var)) {
                return expression;
            } else {
                return this;
            }
      }
    /**
     * This function Returns the expression tree resulting from differentiating
     *  the current expression relative to variable `var.
     * @param var current expression relative to variable
     * @return the expression tree resulting from differentiating
     */
     public Expression differentiate(String var) {
         if (this.variable.equals(var)) {
            return new Num(1);
         }
         return new Num(0);
     }

    /**
     * Returned a simplified version of the current expression.
     * @return simplified expression
     */
    public Expression simplify() {
        return this;
    }

}
