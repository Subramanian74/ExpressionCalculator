package com.example.subramanian.androidcalculatorapp;

/**
 * Display an ArrayList of Calculations when "M" is clicked
 * @author Maria L Garcia De La Hidalga
 * @version 1.0
 * @since 13th May, 2016
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class OpenList extends Activity {

    //View
    ListView list;
    //
    ArrayAdapter myAdapter;

    //Database
    DataBaseHelper db;

    //Others
    private List<Calculation> calculation_list;
    Calculation calc;
    String exp;
    String res;
    int id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_list);

        setTitle("Calculations");


            //getting list view
            list = (ListView)findViewById(R.id.listview);

            //Database
            db = new DataBaseHelper(this);
            calculation_list =  db.getAllCalculations();


            //converting objects from array into view items
            myAdapter = new MyArrayAdapter(this, db.getAllCalculations());
            list.setAdapter(myAdapter);


            //allowing click on items
            list.setClickable(true);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                /* when user clicks an item in the list, user will be redirected to
                * Main activity*/
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                    calc = (Calculation) list.getItemAtPosition(position);
                    exp = calc.getExpression();
                    res = calc.getResult();

                    //Send info of the expression clicked to screen
                    Intent data = new Intent(OpenList.this, MainActivity.class);
                    data.putExtra("Exp", exp);
                    setResult(RESULT_OK, data);
                    startActivity(data);


                }
            });


}}