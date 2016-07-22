package com.example.subramanian.androidcalculatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Main activity class for Android Calculator app
 * Evaluation for an expression starts from here.
 *
 * @author Subramanian Arunachalam
 * @author Maria L Garcia De La Hidalga
 * @author Aagam Shah
 * @version 1.1
 * @since 12th May, 2016
 * @References: Gabriel Raymundo, Calculadora Android,
 * https://www.youtube.com/watch?v=m-qVfUz25Es
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView screen;
    String print;

    //buttons
    boolean decimal = false;
    boolean add = false;
    boolean sub = false;
    boolean mult = false;
    boolean div = false;
    boolean perc = false;

    //Databse
    DataBaseHelper db = new DataBaseHelper(this);

    //Intents
    Intent back_from_OpenList;
    Intent go_to_OpenList;

    //String
    String exp;

    //last Calculation Id
    int Id;
    Calculation last;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        screen=(TextView) findViewById(R.id.screen);

        try {
            Id = (int) db.getLastId();
            last = db.getCalculation(Id);
        }catch(Exception ex){
            last = null;
        }
        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);

        try {
            screen.setText(last.getExpression());
        }catch (Exception ex){
            screen.setText("");
        }


        //Numbers
        Button n0 = (Button) findViewById(R.id.zero);
        n0.setOnClickListener(this);
        Button n1 = (Button) findViewById(R.id.one);
        n1.setOnClickListener(this);
        Button n2 = (Button) findViewById(R.id.two);
        n2.setOnClickListener(this);
        Button n3 = (Button) findViewById(R.id.three);
        n3.setOnClickListener(this);
        Button n4 = (Button) findViewById(R.id.four);
        n4.setOnClickListener(this);
        Button n5 = (Button) findViewById(R.id.five);
        n5.setOnClickListener(this);
        Button n6 = (Button) findViewById(R.id.six);
        n6.setOnClickListener(this);
        Button n7 = (Button) findViewById(R.id.seven);
        n7.setOnClickListener(this);
        Button n8 = (Button) findViewById(R.id.eight);
        n8.setOnClickListener(this);
        Button n9 = (Button) findViewById(R.id.nine);
        n9.setOnClickListener(this);

        //operations
        Button add = (Button) findViewById(R.id.addition);
        add.setOnClickListener(this);
        Button subtract = (Button) findViewById(R.id.subtraction);
        subtract.setOnClickListener(this);
        Button multiply = (Button) findViewById(R.id.multiplication);
        multiply.setOnClickListener(this);
        Button divide = (Button) findViewById(R.id.division);
        divide.setOnClickListener(this);
        Button percentile = (Button) findViewById(R.id.percentile);
        percentile.setOnClickListener(this);
        Button exponent = (Button) findViewById(R.id.exp);
        exponent.setOnClickListener(this);

        //others
        Button decimal = (Button) findViewById(R.id.decimal);
        decimal.setOnClickListener(this);
        Button open = (Button) findViewById(R.id.open);
        open.setOnClickListener(this);
        Button close = (Button) findViewById(R.id.close);
        close.setOnClickListener(this);

        //edit
        Button clearEntry = (Button) findViewById(R.id.memory);
        clearEntry.setOnClickListener(this);
        Button clearAll = (Button) findViewById(R.id.clear);
        clearAll.setOnClickListener(this);
        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(this);

        //Setting up the intents
        go_to_OpenList = new Intent(MainActivity.this, OpenList.class);

        back_from_OpenList = getIntent();
        exp = back_from_OpenList.getStringExtra("Exp");
        screen.setText(exp);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        print = screen.getText().toString();
        int selection = v.getId();

        //Checkiing which button has been clicked

        try{
            switch (selection){

                case R.id.zero:
                    screen.setText(print + "0");
                    break;
                case R.id.one:
                    screen.setText(print +"1");
                    break;
                case R.id.two:
                    screen.setText(print + "2");
                    break;
                case R.id.three:
                    screen.setText(print + "3");
                    break;
                case R.id.four:
                    screen.setText(print + "4");
                    break;
                case R.id.five:
                    screen.setText(print + "5");
                    break;
                case R.id.six:
                    screen.setText(print + "6");
                    break;
                case R.id.seven:
                    screen.setText(print + "7");
                    break;
                case R.id.eight:
                    screen.setText(print + "8");
                    break;
                case R.id.nine:
                    screen.setText(print + "9");
                    break;


                case R.id.decimal:
                    if (decimal == false){
                        screen.setText( print + ".");
                        decimal = true;
                    }else{return;}
                    break;
                case R.id.open:
                    screen.setText(print + "(");
                    break;
                case R.id.close:
                    screen.setText(print + ")");
                    break;

                case R.id.addition:
                    screen.setText(print + "+");
                    add = true;
                    decimal = false;
                    break;
                case R.id.subtraction:
                    screen.setText(print + "-");
                    sub = true;
                    decimal = false;
                    break;
                case R.id.multiplication:
                    screen.setText(print + "*");
                    mult = true;
                    decimal = false;
                    break;
                case R.id.division:
                    screen.setText(print + "/");
                    div = true;
                    decimal = false;
                    break;
                case R.id.percentile:
                    screen.setText(print + "%");
                    perc = true;
                    decimal = false;
                    break;
                case R.id.exp:
                    screen.setText(print + "^");
                    decimal = false;
                    break;


                case R.id.clear:
                    screen.setText("");
                    decimal = false;
                    break;
                case R.id.delete:
                    if (print.equals("error")){
                        screen.setText("");
                        decimal = false;
                        break;
                    }else{
                    String x = print.substring(0,(print.length() - 1));
                    screen.setText(x);
                    break;}

                //MEMORY
                case R.id.memory:
                    startActivity(go_to_OpenList);
                    break;

                //Submit
                case R.id.submit:
                if(print.contains("+")||print.contains("-")||print.contains("*")||print.contains("/")||print.contains("^")||print.contains("%")) {
                    String expression = screen.getText().toString();
                    CalculatorController object = new CalculatorController(expression);
                    String result = object.executeMethods();
                    db.addExpression(expression, result);
                    screen.setText(result);
                }else{
                    screen.setText(print);
                }
            }
        }
        catch (Exception e){
            System.out.println("Exception: "+e.getLocalizedMessage());
            screen.setText("error");
        }

    }


    @Override
    public void onBackPressed() {
        exp = screen.getText().toString();
        if (exp == ""){
            finish();
        }
        else {
            db.addExpression(exp, "");
            finish();
        }
    }


}

/*Main activity class ends here*/