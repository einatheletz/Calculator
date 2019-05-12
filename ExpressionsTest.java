import java.util.Map;
import java.util.TreeMap;
/**
 *@author Einat Heletz.
 *@version 1.8.0_201
 */
public class ExpressionsTest {
    /**
     * This is the main function.
     * @param args array of string
     * @throws Exception exception
     */
    public static void main(String [] args) throws Exception {
        //(2x) + (sin(4y)) + (e^x)
        Expression expression = new Plus(new Plus(new Mult(2, "x"),
                new Sin(new Mult(4, "y"))), new Pow("e", "x"));
        //print the expression
        System.out.println(expression);
        //crate a map
        Map<String, Double> map = new TreeMap<String, Double>();
        map.put("x", 2.0);
        map.put("y", 0.25);
        map.put("e", Math.E);
        //Print the value of the expression with (x=2,y=0.25,e=2.71).
        System.out.println(expression.evaluate(map));
        //Print the differentiated expression according to x.
        Expression diff = expression.differentiate("x");
        System.out.println(diff);
        //Print the value of the differentiated expression according to the map
        System.out.println(diff.evaluate(map));
        //Print the simplified differentiated expression.
        System.out.println(diff.simplify());

    }
}
