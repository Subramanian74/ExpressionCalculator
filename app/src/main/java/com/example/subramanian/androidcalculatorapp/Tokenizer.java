package com.example.subramanian.androidcalculatorapp;

import java.util.ArrayList;

/**
 * Class to tokenize an input expression.
 * Return operators +, -, *, /, %, ^, (, )
 * Returns float numbers as a.b, where a and b are integers and '.' is the decimal point.
 *
 * @author Subramanian Arunachalam
 * @version 1.0
 * @since 8th May, 2016
 */
public class Tokenizer {
    /*Declaring class variables*/
    public static ArrayList<Character> delimiters;
    public static ArrayList<String> tokens;

    /**
     * Constructor for Tokenizer class.
     * Calls addDelimiters() method to populate delimiters into an array list
     */
    Tokenizer(){
        delimiters = new ArrayList<>();
        tokens = new ArrayList<>();
        addDelimiters();
    }

    /**
     * Method which returns tokens as an array list of Strings
     * Accepts the expression string as input
     *
     * @param exp input expression
     * @return array list of tokens
     */
    public ArrayList<String> tokenize(String exp){
        String temp= "";

        /*Loop to traverse the input string*/
        for(int count=0;count<exp.length();count++) {
            /*Extracting one character from the string*/
            char term = exp.charAt(count);

            /*Checking if the character is a delimeter*/
            if(isDelimiter(term)){
                /*Checking for unary minus operator at the start of the expression*/
                if(term=='-' && count==0){
                    /*Adding unary minus to a temporary string*/
                    temp+=exp.charAt(0);
                }
                /*Checking for unary minus operator after the start of a sub-expression*/
                else if(term=='-' && !tokens.isEmpty()){
                    if(tokens.get(tokens.size()-1).equals("(")){
                        /*Adding unary minus to a temporary string*/
                        if(temp==""){
                            temp+=exp.charAt(count);
                        }
                        else {
                            tokens.add(temp);
                            temp="";
                            tokens.add(Character.toString(term));
                        }
                    }
                    else {
                        if(temp!=""){
                            tokens.add(temp);
                            temp="";
                        }
                        tokens.add(Character.toString(term));
                    }
                }
                /*Checking if temporary string is empty. If not, it contains a float number*/
                else {
                    if(temp!=""){
                        tokens.add(temp);
                        temp = "";
                    }
                    /*Adding all other delimiters*/
                    tokens.add(Character.toString(term));
                }
            }

            /*Checking if the character is a '.' or an integer number*/
            else if(exp.charAt(count)=='.' || (Character.getNumericValue(exp.charAt(count))>=0 && Character.getNumericValue(exp.charAt(count))<=9)){
                /*Adding it to a temporary string*/
                temp+=exp.charAt(count);
                /*Checking if end of expression has been reached, to add the last number (if any)*/
                if(exp.length()-1==count) {
                    tokens.add(temp);
                    temp = "";
                }
            }
        }
        return tokens;
    }

    /**
     * Method to check if the input character is a delimeter
     *
     * @param term character input
     * @return boolean result
     */
    public static boolean isDelimiter(char term){
        boolean result=false;

        /*Checking if character is a delimeter*/
        if(delimiters.contains(term)){
            result=true;
        }
        else{
            result=false;
        }

        return result;
    }

    /**
     * Method to add all valid delimiters to a delimiter array list
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
    }
}
/*Tokenizer class ends here*/