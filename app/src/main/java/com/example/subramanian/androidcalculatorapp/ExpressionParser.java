package com.example.subramanian.androidcalculatorapp;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class parses a postfix expression and generates an expression tree.
 * Recursively returns individual expressions based on the operators,
 * to a stack in the calling method.
 *
 * @author Subramanian Arunachalam
 * @version 1.0
 * @since 8th May, 2016
 */
public class ExpressionParser extends ParserDecorator {
    /*Declaring class attributes*/
    Expression outputExp; //Returns this value to the calling method
    String inputString;
    public static ArrayList<Character> delimiters;
    ArrayList<String> inputExpression; //This attribute is unused
    public static ArrayList<String> tempList = new ArrayList<>();
    public static Stack<Float> tempStack = new Stack<>();

    /**
     * Constructor for Expression Parser class.
     * Initializes delimiters and output expression
     * @param inputExpression the list of input expressions
     */
    ExpressionParser(ArrayList<String> inputExpression) {
        super(inputExpression);
        outputExp=null;
        inputString="";
        delimiters = new ArrayList<>();
        addDelimiters();
    }

    /**
     * This method is implemented from Parser Decorator class but is unused here
     * @return null
     */
    @Override
    public ArrayList<String> parseToken() {
        return null;
    }

    /**
     * Method to parse a postfix string and return an Expression object, depending on the operator
     * @param str Postfix expression string
     * @return Expression
     */
    @Override
    Expression parseToken(String str) {
        /*Taking the first character*/
        char term = str.charAt(0);
        /*Checking if it is an operator*/
        if(delimiters.contains(term)){
            /*Checking for unary minus*/
            if(str.length()==1){
                /*Popping the recent two elements from the temporary stack*/
                float num1 = tempStack.pop();
                float num2 = tempStack.pop();
                float result = 0.0f;

                /*For addition*/
                if(term=='+'){
                    result=num2+num1;
                    tempStack.push(result);
                    outputExp = new Addition(new Number(num2), new Number (num1));
                    return outputExp;
                }
                /*For subtraction*/
                else if(term=='-'){
                    result=num2-num1;
                    tempStack.push(result);
                    outputExp = new Subtraction(new Number(num2), new Number (num1));
                    return outputExp;
                }
                /*For multiplication*/
                else if(term=='*'){
                    result=num2*num1;
                    tempStack.push(result);
                    outputExp = new Multiplication(new Number(num2), new Number (num1));
                    return outputExp;
                }
                /*For division*/
                else if(term=='/'){
                    result=num2/num1;
                    tempStack.push(result);
                    outputExp = new Division(new Number(num2), new Number (num1));
                    return outputExp;
                }
                /*For modulus*/
                else if(term=='%'){
                    result=num2%num1;
                    tempStack.push(result);
                    outputExp = new Modulus(new Number(num2), new Number (num1));
                    return outputExp;
                }
                /*For exponent*/
                else if(term=='^'){
                    result=(float)Math.pow(num2, num1);
                    tempStack.push(result);
                    outputExp = new Exponent(new Number(num2), new Number (num1));
                    return outputExp;
                }
                /*Error case*/
                else {
                    System.out.println("Error");
                }
            }
            /*Otherwise, it is a negative float value and is pushed to the stack*/
            else {
                float tempNum = Float.parseFloat(str);
                tempStack.push(tempNum);
            }

        }
        /*Otherwise, it is a float value and is pushed to the stack*/
        else {
            float tempNum = Float.parseFloat(str);
            tempStack.push(tempNum);
        }

        return null;
    }

    /**
     * This method adds valid delimiters
     */
    public void addDelimiters(){
        delimiters.add('+');
        delimiters.add('-');
        delimiters.add('*');
        delimiters.add('/');
        delimiters.add('%');
        delimiters.add('^');
    }
}
/*Expression Parser class ends here*/