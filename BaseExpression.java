import java.util.HashMap;
import java.util.Map;
/**
 *@author Einat Heletz.
 *@version 1.8.0_201
 */
public abstract class BaseExpression implements Expression {

    /**
    Evaluate the expression using the variable values provided
    in the assignment, and return the result. If the expression
    contains a variable which is not in the assignment, an exception
    is thrown.
    *@return result of the expression
    * @throws Exception if the expression
    * contains a variable which is not in the assignment
    */
    public double evaluate() throws Exception {
        Map<String, Double> assignment =  new HashMap<>();
        return evaluate(assignment);
    }
}
