package com.example.subramanian.androidcalculatorapp;

/**
 * Expression interface, with show() and evaluate() as abstract methods.
 * Concrete classes Number, Addition, Subtraction, Multiplication, Division, Modulus and Exponent implement this interface.
 *
 * @author Subramanian Arunachalam
 * @version 1.0
 * @since 8th May, 2016
 */
public interface Expression {
    /**
     * Abstract method to return the current Expression in String format
     * @return String expression
     */
    String show();

    /**
     * Abstract method to evaluate the result of an expression and return a float value
     * @return float result of the expression
     */
    float evaluate();
}
/*Expression Interface ends here*/