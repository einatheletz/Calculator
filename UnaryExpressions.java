import java.util.List;
/**
 *@author Einat Heletz.
 *@version 1.8.0_201
 */
public abstract class UnaryExpressions extends BaseExpression {
    private Expression exp;

    /**
     * This function is the constructor.
     * @param exp Expression
     */
    public UnaryExpressions(Expression exp) {
        this.exp = exp;
    }

    /**
     * This function is the constructor.
     * @param exp variable
     */
    public UnaryExpressions(String exp) {
        this.exp = new Var(exp);
    }

    /**
     * This function is the constructor.
     * @param exp number
     */
    public UnaryExpressions(double exp) {
        this.exp = new Num(exp);
    }

    /**
     * This function return the expression.
     * @return the expression
     */
    public Expression getExp() {
        return this.exp;
    }

    /**
     * Returns a list of the variables in the expression.
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {
        return exp.getVariables();
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
        return assignUnary(this.exp.assign(var, expression));
    }

    /**
     * This function do specific assign.
     * @param expression expression
     * @return the new expression
     */
    public abstract Expression assignUnary(Expression expression);
}
