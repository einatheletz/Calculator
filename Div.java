import java.util.Map;
/**
 *@author Einat Heletz.
 *@version 1.8.0_201
 */
public class Div extends BinaryExpression implements Expression {
    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Div(Expression leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Div(String  leftExp, String rightExp) {
        super(leftExp, rightExp);
    }
    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Div(double leftExp, String rightExp) {
        super(leftExp, rightExp);
    }
    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right number in the calculate tree
     */
    public Div(String leftExp, double rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right number in the calculate tree
     */
    public Div(double leftExp, double rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp right variable in the calculate tree
     */
    public Div(Expression leftExp, String rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left expression in the calculate tree
     * @param rightExp left number in the calculate tree
     */
    public Div(Expression leftExp, double rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left variable in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Div(String leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }

    /**
     * This function is the constructor.
     * @param leftExp left number in the calculate tree
     * @param rightExp right expression in the calculate tree
     */
    public Div(double leftExp, Expression rightExp) {
        super(leftExp, rightExp);
    }

    /**
     Evaluate the expression using the variable values provided
     in the assignment, and return the result. If the expression
     contains a variable which is not in the assignment, an exception
     is thrown.
     * @param assignment map of variable
     * @return result of the div
     * @throws Exception if the expression
     * contains a variable which is not in the assignment
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (this.getRightExp().evaluate(assignment) == 0) {
            throw new Exception("Error: dividing by zero");
        }
        return this.getLeftExp().evaluate(assignment) / this.getRightExp().evaluate(assignment);
    }

    /**
     * Returns string representation of the expression.
     * @return representation of the expression.
     */
    public String toString() {
        return "(" + this.getLeftExp().toString() + " / " + this.getRightExp().toString() + ")";
    }

    /**
     * This function add div to the new expression.
     * @param expLeft the left expression
     * @param expRight the right expression
     * @return new expression after the replace.
     */
    public Expression assignBinary(Expression expLeft, Expression expRight) {
        return new Div(expLeft, expRight);
    }

    /**
     * This function Returns the expression tree resulting from differentiating
     *  the current expression relative to variable `var.
     * @param var current expression relative to variable
     * @return the expression tree resulting from differentiating
     */
    public Expression differentiate(String var) {
        //f'g - g'f / g^2
        Expression numeratorPartOne = new Mult(this.getLeftExp().differentiate(var), this.getRightExp());
        Expression numeratorPartTwo = new Mult(this.getRightExp().differentiate(var), this.getLeftExp());
        Expression denominator = new Pow(this.getRightExp(), new Num(2));
        Expression numerator = new Minus(numeratorPartOne, numeratorPartTwo);
        return new Div(numerator, denominator);
    }

    /**
     * Returned a simplified version of the current expression.
     * @return simplified expression
     */
    public Expression simplify() {
        //simplify the two expressions
        Expression leftExp = this.getLeftExp().simplify();
        Expression rightExp = this.getRightExp().simplify();
        //check if there is variables , if not calculate the number
        if (leftExp.getVariables().isEmpty() && rightExp.getVariables().isEmpty()) {
            try {
                return new Num(leftExp.evaluate() / rightExp.evaluate());
            } catch (Exception e) {
                ;
            }
        }
        //if the expressions are equals return one
        if (leftExp.toString().equals(rightExp.toString())) {
            return new Num(1);
        }
        try {
            //if the right expression is one return the second expression
            if (rightExp.evaluate() == 1) {
                return leftExp;
            }
        } catch (Exception v) {
            ;
        }
        //in any other case
        return new Div(leftExp, rightExp);
    }
}
