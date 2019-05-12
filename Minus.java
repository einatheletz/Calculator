import java.util.Map;
/**
 *@author Einat Heletz.
 *@version 1.8.0_201
 */
public class Minus extends BinaryExpression implements Expression {
    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Minus(Expression leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Minus(String  leftExp, String rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right number in the calculate tree
     */
    public Minus(double leftExp, double rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Minus(Expression leftExp, String rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp left number in the calculate tree
     */
    public Minus(Expression leftExp, double rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Minus(String leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Minus(double leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Minus(double leftExp, String rightExp) {
        super(leftExp, rightExp);
    }
    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right number in the calculate tree
     */
    public Minus(String leftExp, double rightExp) {
        super(leftExp, rightExp);
    }

    /**
     Evaluate the expression using the variable values provided
     in the assignment, and return the result. If the expression
     contains a variable which is not in the assignment, an exception
     is thrown.
     * @param assignment map of variable
     * @return result of the minus
     * @throws Exception if the expression
     * contains a variable which is not in the assignment
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.getLeftExp().evaluate(assignment) - this.getRightExp().evaluate(assignment);
    }

    /**
     * Returns string representation of the expression.
     * @return representation of the expression.
     */
    public String toString() {
        return "(" + this.getLeftExp().toString() + " - " + this.getRightExp().toString() + ")";
    }

    /**
     * This function add plus to the new expression.
     * @param expLeft the left expression
     * @param expRight the right expression
     * @return new expression after the replace.
     */
    public Expression assignBinary(Expression expLeft, Expression expRight) {
        return new Minus(expLeft, expRight);
    }

    /**
     * This function Returns the expression tree resulting from differentiating
     *  the current expression relative to variable `var.
     * @param var current expression relative to variable
     * @return the expression tree resulting from differentiating
     */
    public Expression differentiate(String var) {
        //g' - h'
        Expression diff = new Minus(this.getLeftExp().differentiate(var), this.getRightExp().differentiate(var));
        return diff;
    }

    /**
     * Returned a simplified version of the current expression.
     * @return simplified expresion
     */
    public Expression simplify() {
        //simplify the two expressions
        Expression simplifyLeft = this.getLeftExp().simplify();
        Expression simplifyRight = this.getRightExp().simplify();
        //check if there is variables , if not calculate the number
        if (simplifyLeft.getVariables().isEmpty() && simplifyRight.getVariables().isEmpty()) {
            try {
                return new Num(simplifyLeft.evaluate() - simplifyRight.evaluate());

            } catch (Exception n) {
                ;
            }
        }
        if (this.getRightExp() instanceof  Neg) {
            Expression newExp = new Plus(this.getLeftExp(), new Neg(this.getRightExp()));
            return newExp.simplify();
        }
        //if the expressions are equals return zero
        if (simplifyLeft.toString().equals(simplifyRight.toString())) {
            return new Num(0);
        }
        //if one one of the expressions is zero
        try {
            if (simplifyLeft.evaluate() == 0) {
                return new Neg(simplifyRight);
            }
        } catch (Exception e) {
            ;
        }
        try {
            if (simplifyRight.evaluate() == 0) {
                return simplifyLeft;
            }
        } catch (Exception e) {
            ;
        }
        //in any other case
        return new Minus(simplifyLeft, simplifyRight);
    }
}
