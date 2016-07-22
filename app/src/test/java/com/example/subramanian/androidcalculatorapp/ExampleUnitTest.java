package com.example.subramanian.androidcalculatorapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Aagam Shah (u5696695)
 */
public class ExampleUnitTest {

    @Test
    public void operatorPrecedence() throws Exception {
        assertEquals(new CalculatorController("(9+6)*2/3^2%10").executeMethods(), "3.3333333");
    }

    @Test
    public void invalidInput() throws Exception {
        assertEquals(new CalculatorController("((9+6").executeMethods(), "Please check input"); //Number of opening and closing brackets are not the same
        assertEquals(new CalculatorController(")9+6(").executeMethods(), "Please check input"); //Number of opening and closing brackets are the same but the order is incorrect
        assertEquals(new CalculatorController("9*+5").executeMethods(), "Please check input"); //Two operators cannot be used together
        assertEquals(new CalculatorController("9+5.2").executeMethods(), "14.2"); //A number cannot have more than one decimal points
        assertEquals(new CalculatorController("9+(-2)").executeMethods(), "7.0"); //Correct use of unary operator
        assertEquals(new CalculatorController("9+-2").executeMethods(), "Please check input"); //Incorrect use of unary operator
    }
}