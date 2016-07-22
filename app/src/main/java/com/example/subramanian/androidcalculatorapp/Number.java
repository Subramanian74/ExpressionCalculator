package com.example.subramanian.androidcalculatorapp;

/**
 * Concrete class Number, which is stores an input float number
 * Implements Expression interface
 *
 * @author Subramanian Arunachalam
 * @version 1.0
 * @since 8th May, 2016
 */
public class Number implements Expression {
    /*Declaring the class variables*/
    float number;

    /**
     * Constructor for Number class
     * @param number
     */
    Number(float number){
        this.number=number;
    }

    /**
     * This method returns the number as a String
     * @return String
     */
    @Override
    public String show() {
        String numberString = new String();
        numberString+=number;
        return numberString;
    }

    /**
     * This method returns the actual number to the calling method.
     * @return float result
     */
    @Override
    public float evaluate() {
        return number;
    }
}
/*Number class ends here*/