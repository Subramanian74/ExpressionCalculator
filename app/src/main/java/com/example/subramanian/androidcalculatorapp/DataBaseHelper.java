package com.example.subramanian.androidcalculatorapp;

/**
 * Sqlite Database that will store expressions and results.
 *
 * @author Maria L Garcia De La Hidalga (u5743226)
 * @version 1.0
 * @since 12th May, 2016
 * @References:
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class to connect with SQLite Database
 * @author Maria L Garcia De La Hidalga
 * @version 1.1
 * @since 19th May, 2016
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String db_name = "Memory";
    private static final int db_version = 1;

    public static class table{
        private static String table_name = "Expressions";
        private static String column_id = "id";
        private static String column_expression = "expression";
        private static String column_result = "result";
    }

    private static final String db_create_statement = "create table if not exists " + table.table_name + " ( " + table.column_id +
            " integer primary key autoincrement, " + table.column_expression + " text not null, " + table.column_result +
            " text not null );" ;

    public DataBaseHelper(Context context){
        super(context, db_name, null, db_version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create_statement);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + table.table_name);
        onCreate(db);
    }


    /* Database CRUD Operations (Create, Read, Update and Delete) */


    //Create = Adding new expressions
    public void addExpression(String exp, String result){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(table.column_expression, exp);
        values.put(table.column_result, result);

        db.insert(table.table_name, null, values);
        db.close();
    }


    //Read = getting a calculation (Id + Expression + Result)
    public Calculation getCalculation (int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(table.table_name, new String[] {table.column_id, table.column_expression,
                table.column_result}, table.column_id + "=?", new
                String[] { String.valueOf(id) }, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        // For Testing purposes use:
       // Log.i("TEST", "getExpression: " + cursor.getString(1) + " getResult: " + cursor.getString(2));

        Calculation calc = new Calculation(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        return calc;
    }


    //Read = getting all Calculations as a list
    public List<Calculation> getAllCalculations() {

        List<Calculation> calculationList = new ArrayList<Calculation>();

        String select = "Select * from " + table.table_name;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);

        if(cursor.moveToFirst()){

            do {
                Calculation calc = new Calculation();
                calc.setId(Integer.parseInt(cursor.getString(0)));
                calc.setExpression(cursor.getString(1));
                calc.setResult(cursor.getString(2));

                calculationList.add(calc);
            } while (cursor.moveToNext());
        }

        return calculationList;
    }

    //Read = A count of Calculations
    public int getCalculationsCount() {
        String count = "select * from " + table.table_name;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(count, null);
        cursor.close();

        return cursor.getCount();
    }

    //Update = changing a calculation
    public int updateCalculation(int id, String expression, String result){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(table.column_expression, expression);
        values.put(table.column_result, result);

        return db.update(table.table_name, values, table.column_id + " = ?",
                new String[]{String.valueOf(id)});
    }

    //Delete = deleting a calculation
    public void deleteCalculation(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table.table_name, table.column_id + " =? ",
                new String[]{String.valueOf(id)});
        db.close();
        //Log.i("TEST", "deleted: " + String.valueOf(id));
    }

    //Clean table from ALL records! must be treated carefully
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + table.table_name);
        db.close();
        Log.i("TEST", "Database is Empty ");
    }


    //Get last inserted Calculation
    public int getLastId (){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + table.column_id + " from "
                + table.table_name + " order by " + table.column_id + " desc limit 1 ", null);

        if(cursor != null)
            cursor.moveToFirst();

        // For Testing purposes use:
        //Log.i("TEST", "ID " + cursor.getString(0) );


        int id = Integer.parseInt(cursor.getString(0));

        return id;

    }


}