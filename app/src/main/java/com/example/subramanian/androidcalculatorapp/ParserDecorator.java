package com.example.subramanian.androidcalculatorapp;

import java.util.ArrayList;

/**
 * Decorator abstract class for Android Calculator
 * Contains an Expression class variable
 *
 * @author Subramanian Arunachalam (u5683507)
 * @version 1.0
 * @since 8th May, 2016
 */
public abstract class ParserDecorator {
    Expression outputExp;
    ArrayList<String> tokens;

    ParserDecorator(ArrayList<String> tokens) {
        this.outputExp=null;
        this.tokens=tokens;
    }

    /**
     * Abstract method to parse a postfix string and return expressions.
     * Used in Expression Parser class only.
     *
     * @param str Postfix expression string
     * @return Expression type object depending on the operator
     */
    abstract Expression parseToken(String str);

    /**
     * Abstract method to parse an array list of tokens to generate a postfix expression
     * Used in Shunting Yard parser class only.
     *
     * @return ArrayList of tokens, that represent a postfix expression of the input
     */
    abstract ArrayList<String> parseToken();
}
/*Parser Decorator class ends here*/