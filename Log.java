import java.util.Map;
/**
 *@author Einat Heletz.
 *@version 1.8.0_201
 */
public class Log extends BinaryExpression implements Expression {
    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Log(Expression leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Log(String  leftExp, String rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right number in the calculate tree
     */
    public Log(double leftExp, double rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Log(Expression leftExp, String rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp left number in the calculate tree
     */
    public Log(Expression leftExp, double rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Log(String leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Log(double leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }
    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Log(double leftExp, String rightExp) {
        super(leftExp, rightExp);
    }
    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right number in the calculate tree
     */
    public Log(String leftExp, double rightExp) {
        super(leftExp, rightExp);
    }

    /**
     Evaluate the expression using the variable values provided
     in the assignment, and return the result. If the expression
     contains a variable which is not in the assignment, an exception
     is thrown.
     * @param assignment map of variable
     * @return result of the log
     * @throws Exception if the expression
     * contains a variable which is not in the assignment
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if ((this.getLeftExp().evaluate(assignment)) <= 0 || (this.getRightExp().evaluate(assignment)) <= 0) {
            throw new Exception("Error: log(0) is not defined.");
        }
        // log(1) = 0, need to make sure its not the denominator, cannot divide by 0.
        if ((this.getLeftExp().evaluate(assignment)) == 1 || Math.log(this.getRightExp().evaluate(assignment)) == 0) {
            throw new Exception("Error: cannot divide by log(1) (because log(1)=0)");
        }
        String result = Double.toString(
                Math.log(this.getLeftExp().evaluate(assignment))
                        / Math.log(this.getRightExp().evaluate(assignment)));

        if (result.equals("Infinity") || result.equals("-Infinity") || result.equals("NaN")) {
            throw new Exception("Error: cannot calculate log");
        }
        return  Math.log(this.getRightExp().evaluate(assignment)) / Math.log(this.getLeftExp().evaluate(assignment));
    }

    /**
     * Returns string representation of the expression.
     * @return representation of the expression.
     */
    public String toString() {
        return "log(" + this.getLeftExp().toString() + ", " + this.getRightExp().toString() + ")";
    }

    /**
     * This function add div to the new expression.
     * @param expLeft the left expression
     * @param expRight the right expression
     * @return new expression after the replace.
     */
    public Expression assignBinary(Expression expLeft, Expression expRight) {
        return new Log(expLeft, expRight);
    }

    /**
     * This function Returns the expression tree resulting from differentiating
     *  the current expression relative to variable `var.
     * @param var current expression relative to variable
     * @return the expression tree resulting from differentiating
     */
    public Expression differentiate(String var) {
        //lof(c,g)' = g'\ g*lnc
        Expression log = new Log(new Var("e"), this.getLeftExp());
        Expression mult = new Mult(log, this.getRightExp());
        return new Div(this.getRightExp().differentiate(var), mult);

    }
    /**
     * Returned a simplified version of the current expression.
     * @return simplify expression
     */
    public Expression simplify() {
        //simplify the two expressions
        Expression simplifyLeft = this.getLeftExp().simplify();
        Expression simplifyRight = this.getRightExp().simplify();
        //check if there is variables , if not calculate the number
        if (simplifyLeft.getVariables().isEmpty() && simplifyRight.getVariables().isEmpty()) {
            try {
                return new Num(Math.log(simplifyRight.evaluate()) / Math.log(simplifyLeft.evaluate()));

            } catch (Exception n) {
                ;
            }
        }
        //LOG(X,X) = 1
        if (simplifyLeft.toString().equals(simplifyRight.toString())) {
            return new Num(1);
        }
        //in any other case
        return new Log(simplifyLeft, simplifyRight);
    }
}
