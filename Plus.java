import java.util.Map;
/**
 *@author Einat Heletz.
 *@version 1.8.0_201
 */
public class Plus extends BinaryExpression implements Expression {

    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Plus(Expression leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Plus(String leftExp, String rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right number in the calculate tree
     */
    public Plus(double leftExp, double rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Plus(Expression leftExp, String rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp left number in the calculate tree
     */
    public Plus(Expression leftExp, double rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Plus(String leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }
    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Plus(double leftExp, String rightExp) {
        super(leftExp, rightExp);
    }
    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right number in the calculate tree
     */
    public Plus(String leftExp, double rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Plus(double leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }

    /**
     Evaluate the expression using the variable values provided
     in the assignment, and return the result. If the expression
     contains a variable which is not in the assignment, an exception
     is thrown.
     * @param assignment map of variable
     * @return result of the plus
     * @throws Exception if the expression
     * contains a variable which is not in the assignment
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.getLeftExp().evaluate(assignment) + this.getRightExp().evaluate(assignment);
    }

    /**
     * Returns string representation of the expression.
     * @return representation of the expression.
     */
    public String toString() {
        return "(" + this.getLeftExp().toString() + " + " + this.getRightExp().toString() + ")";
    }

    /**
     * This function add plus to the new expression.
     * @param expLeft the left expression
     * @param expRight the right expression
     * @return new expression after the replace.
     */
    public Expression assignBinary(Expression expLeft, Expression expRight) {
        return new Plus(expLeft, expRight);
    }

    /**
     * This function Returns the expression tree resulting from differentiating
     *  the current expression relative to variable `var.
     * @param var current expression relative to variable
     * @return the expression tree resulting from differentiating
     */
    public Expression differentiate(String var) {
        //differentiate g'+f'
        Expression diff = new Plus(this.getLeftExp().differentiate(var), this.getRightExp().differentiate(var));
        return diff;
    }

    /**
     * Returned a simplified version of the current expression.
     * @return simplified expression
     */
    public Expression simplify() {
        Expression simplifyLeft = this.getLeftExp().simplify();
        Expression simplifyRight = this.getRightExp().simplify();
        //check if there is variable in the expressions
        if (simplifyLeft.getVariables().isEmpty() && simplifyRight.getVariables().isEmpty()) {
            try {
                //calculate the expression
                return new Num(simplifyLeft.evaluate() + simplifyRight.evaluate());

            } catch (Exception n) {
                ;
            }
        }
        // 0 + x
        try {
            if (simplifyLeft.evaluate() == 0) {
                return simplifyRight;
            }
        } catch (Exception e) {
            ;
        }
        // x + 0
        try {
            if (simplifyRight.evaluate() == 0) {
                return simplifyLeft;
            }
        } catch (Exception e) {
            ;
        }
        //in any other case
        return new Plus(simplifyLeft, simplifyRight);
    }
}
