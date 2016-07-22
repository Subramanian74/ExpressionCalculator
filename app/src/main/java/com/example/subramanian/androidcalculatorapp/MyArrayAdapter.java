package com.example.subramanian.androidcalculatorapp;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Inflating/filling up the list of calculations
 * @author Maria L Garcia De La Hidalga
 * @version 1.0
 * @since 13th May, 2016
 * @References:
 * */

public class MyArrayAdapter extends ArrayAdapter<Calculation> {

    public MyArrayAdapter(Context context, List<Calculation> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //Getting an instance of the inflater
        LayoutInflater inflater = (LayoutInflater)getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Saving the view
        View listItemView = convertView;

        //Checking if view exists
        if (null == convertView) {
            listItemView = inflater.inflate(
                    R.layout.image_list_item,
                    parent,
                    false);
        }

        TextView expression = (TextView)listItemView.findViewById(R.id.expression);
        TextView result = (TextView)listItemView.findViewById(R.id.result);


        //Getting calculation from x position
        Calculation item = getItem(position);

        expression.setText(item.getExpression() + " = ");
        result.setText(item.getResult());

        return listItemView;

    }


}
