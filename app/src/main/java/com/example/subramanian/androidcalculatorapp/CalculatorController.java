package com.example.subramanian.androidcalculatorapp;

import java.util.ArrayList;
import java.util.Stack;

import static java.lang.Character.isDigit;

/**
 * Controller class for calculator app
 * Calls methods to validate, tokenize, parse the input expression and create an expression tree.
 *
 * @author Subramanian Arunachalam
 * @since 7th May, 2015
 * @version 1.0
 */
public class CalculatorController {
    /*Declaring class variables*/
    String expression=new String();
    static Stack<Expression> expressionTree = new Stack<>();

    /**
     * Constructor for CalculatorController class
     * @param expression
     */
    CalculatorController(String expression){
        this.expression=expression;
    }

    /**
     * Method to call parser, tokenizer and tree generator.
     * Returns the result as a String value to the main activity.
     * Prints intermediate values to console.
     *
     * @author Subramanian Arunachalam
     * @return String final result
     */
    public String executeMethods(){
        System.out.println("Input Expression: "+expression);
        /*Creating a tokenizer object and tokenizing the input string*/
        Tokenizer tokenizerObject = new Tokenizer();
        ArrayList<String> tokens=tokenizerObject.tokenize(expression);

        /*Displaying Tokens to the console*/
        System.out.println("Tokens: "+tokens);
        boolean isValid = validate(tokens);

        if(!isValid) //Checks whether the input is valid. If not it displays an error
        {
            return "Please check input";
        }

        else
        {
            /*Calling shunting yard parser, that returns a postfix expression without brackets*/
            ShuntingYardParser shuntingYardParserObject = new ShuntingYardParser(tokens);
            ArrayList<String> shuntingYardResult = shuntingYardParserObject.parseToken();

        /*Displaying the postfix expression to the console*/
            System.out.println("Shunting yard result: " + shuntingYardResult);

        /*Calling the expression parser, which generates the expressions as Expression objects*/
            ExpressionParser expressionParserObject = new ExpressionParser(shuntingYardResult);

        /*Constructing the tree of expressions*/
            treeConstructor(shuntingYardResult, expressionParserObject);

        /*Displaying the expression tree to the console*/
            System.out.println("Expression List");
            for(int i=expressionTree.size()-1;i>=0;i--){
                System.out.println(expressionTree.get(i).show());
            }

        /*Computing the final result and returning the value as a string*/
            String res="";
            float result = 0.0f;
            Expression temp = expressionTree.pop();
            result=temp.evaluate();

            res+=result;
            return (res);

        }

    }

    /**
     * This method inputs a postfix expression and recursively constructs an expression tree
     * Returns a stack containing the tree.
     *
     * @author Subramanian Arunachalam
     * @param shuntingYardResult
     * @param object
     * @return Stack of expressions
     */
    public static Stack<Expression> treeConstructor (ArrayList<String> shuntingYardResult, ExpressionParser object){
        /*Checking if the postfix expression array list is empty*/
        if(shuntingYardResult.isEmpty()){
            /*Returning the final tree*/
            return expressionTree;
        }
        else{
            /*Getting the first token of the postfix expression*/
            String temp = shuntingYardResult.get(0);
            shuntingYardResult.remove(0);
            Expression e = object.parseToken(temp);
            if(e!=null){
                expressionTree.push(e);
            }
            /*Recursively calling treeConstructor() method with the remaining array list*/
            return treeConstructor(shuntingYardResult, object);
        }
    }

    /**
     * Method to validate input expression and return the result
     * Prompt the user to check their input.
     *
     * @author Aagam Shah
     * @return String error message
     */
    public boolean validate(ArrayList tokens) {

        /**
         * It validates the following conditions:
         * 1) Two operators cannot be entered together. For example, the user cannot enter 4++2.
         * 2) A unary minus can only be entered for the starting number, or for a number immediately after an open bracket. For example: -4.2+6.4*(-8.0/1.0) is a valid expression. 6+-4 is invalid.
         * 3) Every operator: +, -, *, /, %, and ^ must be both preceded and succeeded by a number (Except unary minus). For example: 4+2 is valid, 4+(-2) is valid but 4 +- 2 is invalid.
         * 4) A number can have at most 1 decimal point.
         *
         */

        int i;
        for(i=0;i<tokens.size()-1;i++)
        {
            //Checks for operands and brackets
            if(tokens.get(i).equals("+") || tokens.get(i).equals("-") || tokens.get(i).equals("*") ||
                    tokens.get(i).equals("/") || tokens.get(i).equals("%") || tokens.get(i).equals("^") ||
                    tokens.get(i).equals("(") || tokens.get(i).equals(")"))
            {
                //Condition for open bracket ["("]
                if(tokens.get(i).equals("(")) {
                    if (tokens.get(i + 1).equals("+") || tokens.get(i + 1).equals("/") || tokens.get(i + 1).equals("*") || tokens.get(i + 1).equals(")") ||
                            tokens.get(i + 1).equals("%") || tokens.get(i + 1).equals("^")) {
                        System.out.println("Error");
                        return false;
                    }
                }
                //Condition for closed bracket [")"]
                else if(tokens.get(i).equals(")")){
                    if(!(tokens.get(i + 1).equals("-") || tokens.get(i + 1).equals("+") || tokens.get(i + 1).equals("/") || tokens.get(i + 1).equals("*") ||
                            tokens.get(i + 1).equals(")") || tokens.get(i + 1).equals("%") || tokens.get(i + 1).equals("^"))) {
                        System.out.println("Error");
                        return false;
                    }
                }
                //Conditions for remaining operators
                else{
                    if((tokens.get(i + 1).equals("-") || tokens.get(i + 1).equals("+") || tokens.get(i + 1).equals("/") || tokens.get(i + 1).equals("*") ||
                            tokens.get(i + 1).equals(")") || tokens.get(i + 1).equals("%") || tokens.get(i + 1).equals("^")))
                    {
                        System.out.println("Error");
                        return false;
                    }
                }
            }

            //Condition for numbers to avoid more than one decimal points and invalid input
            else
            {
                try
                {
                    Float.parseFloat(tokens.get(i).toString());
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    return false;
                }

            }
        }

        /**
         * Every open bracket must have a closing bracket.
         */
        int counter=0;

        for(i=0;i<tokens.size()&& counter>=0 ;i++)
        {
            if(tokens.get(i).equals(")")){
                counter--;
            }
            else if(tokens.get(i).equals("(")){
                counter++;
            }
        }

        if(counter!=0){
            System.out.println("Error");
            return false;
        }

        return true;
    }
}
/*Calculator controller class ends here*/