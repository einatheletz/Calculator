import java.util.ArrayList;
import java.util.List;
/**
 *@author Einat Heletz.
 *@version 1.8.0_201
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression leftExp;
    private Expression rightExp;

    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public BinaryExpression(Expression leftExp, Expression rightExp) {
        this.leftExp = leftExp;
        this.rightExp = rightExp;
    }

    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public BinaryExpression(String  leftExp, String rightExp) {
        this.leftExp = new Var(leftExp);
        this.rightExp = new Var(rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right number in the calculate tree
     */
    public BinaryExpression(double leftExp, double rightExp) {
        this.leftExp = new Num(leftExp);
        this.rightExp = new Num(rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public BinaryExpression(Expression leftExp, String rightExp) {
        this.leftExp = leftExp;
        this.rightExp = new Var(rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp left number in the calculate tree
     */
    public BinaryExpression(Expression leftExp, double rightExp) {
        this.leftExp = leftExp;
        this.rightExp = new Num(rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public BinaryExpression(String leftExp, Expression rightExp) {
        this.leftExp = new Var(leftExp);
        this.rightExp = rightExp;
    }

    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public BinaryExpression(double leftExp, String rightExp) {
        this.leftExp = new Num(leftExp);
        this.rightExp = new Var(rightExp);
    }
    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right number in the calculate tree
     */
    public BinaryExpression(String leftExp, double rightExp) {
        this.leftExp = new Var(leftExp);
        this.rightExp = new Num(rightExp);
    }
    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public BinaryExpression(double leftExp, Expression rightExp) {
        this.leftExp = new Num(leftExp);
        this.rightExp = rightExp;
    }

    /**
     * This function return the left expression.
     * @return left Expression
     */
    public Expression getLeftExp() {
        return this.leftExp;
    }

    /**
     * This function return the right expression.
     * @return right Expression
     */
    public Expression getRightExp() {
        return this.rightExp;
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
        return assignBinary(this.getLeftExp().assign(var, expression), this.getRightExp().assign(var, expression));
    }

    /**
     * Returns a list of the variables in the expression.
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {
        List<String> variablesLeft = this.getLeftExp().getVariables();
        List<String> variablesRight = this.getRightExp().getVariables();
        List<String> variables = new ArrayList<String>();
        variables.addAll(variablesLeft);
        for (String var : variablesRight) {
            if (!variables.contains(var)) {
                variables.add(var);
            }
        }
        return variables;
    }

    /**
     * This function do specific assign.
     * @param expLeft the left expression
     * @param expRight the right expression
     * @return new expression after the replace.
     */
    public abstract Expression assignBinary(Expression expLeft, Expression expRight);


}
