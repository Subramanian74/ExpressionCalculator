package com.example.subramanian.androidcalculatorapp;

/** Class used to store expression + result on the database
 * @author Maria L Garcia De La Hidalga
 * @version 1.0
 * @since 13th May, 2016
 * @References:
 */
public class Calculation {

    private int id;
    private String expression;
    private String result;

    public Calculation() {
    }

    public Calculation(int id, String expression, String result) {
        this.id = id;
        this.expression = expression;
        this.result = result;
    }

    public Calculation(String expression, String result) {
        this.expression = expression;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
