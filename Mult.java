import java.util.Map;
/**
 *@author Einat Heletz.
 *@version 1.8.0_201
 */
public class Mult extends BinaryExpression implements Expression {
    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Mult(Expression leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Mult(String  leftExp, String rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right number in the calculate tree
     */
    public Mult(double leftExp, double rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Mult(Expression leftExp, String rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp left number in the calculate tree
     */
    public Mult(Expression leftExp, double rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Mult(String leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }
    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Mult(double leftExp, String rightExp) {
        super(leftExp, rightExp);
    }
    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right number in the calculate tree
     */
    public Mult(String leftExp, double rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Mult(double leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }

    /**
     Evaluate the expression using the variable values provided
     in the assignment, and return the result. If the expression
     contains a variable which is not in the assignment, an exception
     is thrown.
     * @param assignment map of variable
     * @return result of the mult
     * @throws Exception if the expression
     * contains a variable which is not in the assignment
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.getLeftExp().evaluate(assignment) * this.getRightExp().evaluate(assignment);
    }

    /**
     * Returns string representation of the expression.
     * @return representation of the expression.
     */
    public String toString() {
        return "(" + this.getLeftExp().toString() + " * " + this.getRightExp().toString() + ")";
    }

    /**
     * This function add plus to the new expression.
     * @param expLeft the left expression
     * @param expRight the right expression
     * @return new expression after the replace.
     */
    public Expression assignBinary(Expression expLeft, Expression expRight) {
        return new Mult(expLeft, expRight);
    }

    /**
     * This function Returns the expression tree resulting from differentiating
     *  the current expression relative to variable `var.
     * @param var current expression relative to variable
     * @return the expression tree resulting from differentiating
     */
    public Expression differentiate(String var) {
        //g'h + h'g'
        Expression diffLeft = this.getLeftExp().differentiate(var);
        Expression diffRight = this.getRightExp().differentiate(var);
        Expression diff = new Plus(new Mult(diffLeft, this.getRightExp()), new Mult(diffRight, this.getLeftExp()));
        return diff;
    }
    /**
     * Returned a simplified version of the current expression.
     * @return simplified expression
     */
    public Expression simplify() {
        //simplify the two expressions
        Expression simplifyLeft = this.getLeftExp().simplify();
        Expression simplifyRight = this.getRightExp().simplify();
        //check if there is variables , if not calculate the number
        if (simplifyLeft.getVariables().isEmpty() && simplifyRight.getVariables().isEmpty()) {
            try {
                return new Num(simplifyLeft.evaluate() * simplifyRight.evaluate());

            } catch (Exception n) {
                ;
            }
        }
        try {
            //x * 1
            if (simplifyLeft.evaluate() == 1.0) {
                return simplifyRight;
            }
        } catch (Exception e) {
            ;
        }
        try {
            //1 * x
            if (simplifyRight.evaluate() == 1.0) {
                return simplifyLeft;
            }
        } catch (Exception e) {
            ;
        }
        try {
            //x*0
            if (simplifyLeft.evaluate() == 0.0) {
                return new Num(0);
            }
        } catch (Exception e) {
            ;
        }
        try {
            if (simplifyRight.evaluate() == 0.0) {
                return new Num(0);
            }
        } catch (Exception e) {
            ;
        }
        //in any other case
        return new Mult(simplifyLeft, simplifyRight);
    }
}
