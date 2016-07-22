package com.example.subramanian.androidcalculatorapp;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class implements the Shunting Yard algorithm, which takes in an infix expression (output of tokenizer)
 * and effectively returns a postfix expression without brackets.
 * It uses an operator stack, output queue and operator precedence to evaluate.
 *
 * @author Subramanian Arunachalam
 * @version 1.0
 * @since 8th May, 2015
 * @see https://en.wikipedia.org/wiki/Shunting-yard_algorithm
 */
public class ShuntingYardParser extends ParserDecorator {
    /*Declaring class variables*/
    ArrayList<String> inputExpression; //The input tokens
    Stack<String> opStack; //Stack to temporarily hold operators
    ArrayList<String> output; //The output queue, implemented as an Array List
    ArrayList<Character> precedenceOne; //Precedence 1 operators include + and -
    ArrayList<Character> precedenceTwo; //Precedence 2 operators include *, / and %
    ArrayList<Character> precedenceThree; //Precedence 3 operators include ^
    public static ArrayList<Character> delimiters; //List of valid delimiters

    /*outputExp is not used*/
    Expression outputExp;

    /**
     * Constructor for ShuntingYardParser class
     * Initializes input array list and delimiters
     * @param inputExpression array list of tokens
     */
    ShuntingYardParser(ArrayList<String> inputExpression){
        super(inputExpression);
        precedenceOne = new ArrayList<>();
        precedenceTwo = new ArrayList<>();
        precedenceThree = new ArrayList<>();
        delimiters = new ArrayList<>();
        addDelimiters();
        opStack = new Stack<>();
        output = new ArrayList<>();
        this.inputExpression=inputExpression;
    }

    /**
     * ParserDecorator method implemented but unused
     * @param str Postfix expression string
     * @return Expression
     */
    @Override
    Expression parseToken(String str) {
        //Unused
        return null;
    }

    /**
     * Method to implement shunting yard algorithm
     * @return Array list of postfix tokens
     */
    @Override
    public ArrayList<String> parseToken(){
        for(int i=0;i<tokens.size();i++){
            /*Getting the current element*/
            String temp=tokens.get(i);

            /*Checking if the current character is a delimiter*/
            if(delimiters.contains(temp.charAt(0))){
                /*Checking for float values with unary minus*/
                if(temp.length()==1){
                    /*If operator stack is empty, simply push the current operator*/
                    if(opStack.isEmpty()){
                        opStack.push(temp);
                    }
                    /*Otherwise, compare the current operator with the latest operator in the stack*/
                    else{
                        char prevOp=opStack.peek().charAt(0);
                        char currOp=temp.charAt(0);

                        /*Checking higher precedence. Popping lower precedence operator from stack,
                        adding to queue and pushing higher precedence operator to stack.*/
                        if(precedenceOne.contains(prevOp) && precedenceOne.contains(currOp) ||
                                (precedenceTwo.contains(prevOp) && precedenceTwo.contains(currOp))){
                            output.add(opStack.pop());
                            opStack.push(temp);
                        }

                        /*Checking higher precedence. Popping lower precedence operator from stack,
                        adding to queue and pushing higher precedence operator to stack.*/
                        else if(precedenceTwo.contains(prevOp) && precedenceOne.contains(currOp)){
                            output.add(opStack.pop());
                            opStack.push(temp);
                        }
                        /*For same precedence, no swap required.*/
                        else if(precedenceOne.contains(prevOp) && precedenceTwo.contains(currOp)){
                            opStack.push(temp);
                        }

                        /*If open bracket is encountered, push to stack*/
                        else if(currOp=='('){
                            opStack.push(temp);
                        }

                        /*If close bracket is found, pop stack till open bracket and add to queue*/
                        else if(currOp==')'){
                            String c="";

                            while(true){
                                c = opStack.pop();
                                if(c.equals("("))
                                    break;
                                else
                                    output.add(c);
                            }
                        }
                        /*For other cases, where operator need not be compared*/
                        else{
                            opStack.push(temp);
                        }
                    }
                }
                /*Pushing unary minus float value to output queue*/
                else {
                    output.add(temp);
                }

            }
            /*Pushing float number to the output queue*/
            else {
                output.add(temp);
            }
        }
        /*Adding remaining stack elements to queue*/
        while(!opStack.isEmpty()){
            output.add(opStack.pop());
        }

        return output;
    }

    /**
     * This method adds valid delimiters and sets operator precedences
     */
    public void addDelimiters(){
        delimiters.add('+');
        delimiters.add('-');
        delimiters.add('*');
        delimiters.add('/');
        delimiters.add('%');
        delimiters.add('^');
        delimiters.add('(');
        delimiters.add(')');
        precedenceOne.add('+');
        precedenceOne.add('-');
        precedenceTwo.add('*');
        precedenceTwo.add('/');
        precedenceTwo.add('%');
        precedenceThree.add('^');
    }
}
/*Shunting Yard Parser class ends here*/