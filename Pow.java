import java.util.Map;
/**
 *@author Einat Heletz.
 *@version 1.8.0_201
 */
public class Pow extends BinaryExpression implements Expression {
    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Pow(Expression leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Pow(String  leftExp, String rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right number in the calculate tree
     */
    public Pow(double leftExp, double rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Pow(Expression leftExp, String rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp left number in the calculate tree
     */
    public Pow(Expression leftExp, double rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Pow(String leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Pow(double leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }
    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Pow(double leftExp, String rightExp) {
        super(leftExp, rightExp);
    }
    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right number in the calculate tree
     */
    public Pow(String leftExp, double rightExp) {
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
        if (Double.isNaN(Math.pow(this.getLeftExp().evaluate(assignment), this.getRightExp().evaluate(assignment)))) {
            throw new Exception("Error: Cannot calculate. Negetive number under the root.");
        }

        return Math.pow(this.getLeftExp().evaluate(assignment), this.getRightExp().evaluate(assignment));
    }

    /**
     * Returns string representation of the expression.
     * @return representation of the expression.
     */
    public String toString() {
        return "(" + this.getLeftExp().toString() + "^" + this.getRightExp().toString() + ")";
    }

    /**
     * This function add plus to the new expression.
     * @param expLeft the left expression
     * @param expRight the right expression
     * @return new expression after the replace.
     */
    public Expression assignBinary(Expression expLeft, Expression expRight) {
        return new Pow(expLeft, expRight);
    }

    /**
     * This function Returns the expression tree resulting from differentiating
     *  the current expression relative to variable `var.
     * @param var current expression relative to variable
     * @return the expression tree resulting from differentiating
     */
    public Expression differentiate(String var) {
        // (f^g)'= f^g(f'*g/f +g'*lnf)
        Expression div = new Div(this.getRightExp(), this.getLeftExp());
        Expression multPartOne = new Mult(this.getLeftExp().differentiate(var), div);
        Expression log = new Log(new Var("e"), this.getLeftExp());
        Expression multPartTwo = new Mult(this.getRightExp().differentiate(var), log);
        Expression plus = new Plus(multPartOne, multPartTwo);
        Expression diff = new Mult(this, plus);
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
                return new Num(Math.pow(simplifyLeft.evaluate(), simplifyRight.evaluate()));

            } catch (Exception e) {
                ;
            }
        }
        try {
            //x^0
            if (simplifyRight.evaluate() == 0) {
                return new Num(1);
            }
        } catch (Exception e) {
            ;
        }
        try {
            //x^1
            if (simplifyRight.evaluate() == 1.0) {
                return simplifyLeft;
            }
        } catch (Exception e) {
            ;
        }
        try {
            //1^x
            if (simplifyLeft.evaluate() == 1.0) {
                return new Num(1);
            }

        } catch (Exception e) {
            ;
        }
        //in any other case
        return new Pow(simplifyLeft, simplifyRight);
    }
}
