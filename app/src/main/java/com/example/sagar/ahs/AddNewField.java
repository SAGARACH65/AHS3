package com.example.sagar.ahs;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddNewField extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_field);

    }
    public void savefield(View view){

       // Button buttonX = (Button)findViewById(R.id.button);


        EditText nof=(EditText) findViewById(R.id.editText);
        String name_of_field = nof.getText().toString();

        EditText area_value=(EditText) findViewById(R.id.editText1);
        //getting interger from text field
        int val = Integer.parseInt( area_value.getText().toString() );



        Spinner datatype=(Spinner) findViewById(R.id.spinner2);
        String area_type=String.valueOf(datatype.getSelectedItem());


        storetodatabase(name_of_field,val,area_type);
        Intent callnew =new Intent(this,homescreenjava.class);
        startActivity(callnew);
    }

    private void storetodatabase(String name,int area_vlaue,String type){
        SQLiteOpenHelper AgroDatabase=new AgroDatabase(this);
        SQLiteDatabase database = AgroDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("FieldName", name);
        values.put("Area", area_vlaue);
        values.put("Measure_Unit",type);
        database.insert("fieldinfo", null, values);
        database.close();



    }

}
