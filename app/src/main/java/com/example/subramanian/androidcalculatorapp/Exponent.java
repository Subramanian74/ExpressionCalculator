package com.example.subramanian.androidcalculatorapp;

/**
 * Concrete class Exponent, which is called when the user enters a '^' operator in the input expression
 * Implements Expression interface
 *
 * @author Subramanian Arunachalam
 * @version 1.0
 * @since 12th May, 2016
 */
public class Exponent implements Expression {
    /*Declaring class variables, as Expression objects*/
    Expression e1;
    Expression e2;

    /**
     * Constructor for Exponent class
     * @param e1
     * @param e2
     */
    Exponent(Expression e1, Expression e2){
        this.e1=e1;
        this.e2=e2;
    }

    /**
     * This method returns the expression with a '^' prefix
     * @return String
     */
    @Override
    public String show() {
        String expString = "^"+e1.show()+" "+e2.show();
        return expString;
    }

    /**
     * Concrete method inputs two Expression objects as parameters and
     * performs a exponent operation. Returns the result.
     * @return float result
     */
    @Override
    public float evaluate() {
        float n1=e1.evaluate();
        float n2=e2.evaluate();
        float res = (float)Math.pow(n1, n2);
        return(res);
    }
}
/*Exponent class ends here*/