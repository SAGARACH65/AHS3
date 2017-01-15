package com.example.sagar.ahs;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.id;
import static java.lang.Integer.parseInt;
import static java.sql.DriverManager.println;

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
        int val = parseInt( area_value.getText().toString() );



        Spinner datatype=(Spinner) findViewById(R.id.spinner2);
        String area_type=String.valueOf(datatype.getSelectedItem());


        storetodatabase(name_of_field,val,area_type);



        //this portion is only to update the no of field portion

        SQLiteOpenHelper AgroDatabase = new AgroDatabase(this);
        SQLiteDatabase db = AgroDatabase.getWritableDatabase();
        Cursor cursor = db.query("userinfo",
                new String[]{"User_Name", "Location", "CurrentNoFields"},
                null,
                null, null, null, null
        );


        if(cursor.moveToFirst())
        {

            int nofd=cursor.getInt(2);
            String named= cursor.getString(0);
            String locd=cursor.getString(1);
            nofd++;


            ContentValues values = new ContentValues();
            values.put("CurrentNoFields",nofd);
            values.put("User_Name", named);
            values.put("Location", locd);
            db.update("userinfo",values,"_id=1" ,null);

        }
        cursor.close();
        db.close();

        Toast.makeText(this,
                "Field Added", Toast.LENGTH_LONG).show();


        //this code kills the current activity and starts a new
        Intent callnew =new Intent(this,homescreenjava.class);
        callnew.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(callnew);
        finish();


    }

    private void storetodatabase(String name,int area_vlaue,String type){
        String ss="NA";
        SQLiteOpenHelper AgroDatabase=new AgroDatabase(this);
        SQLiteDatabase database = AgroDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("FieldName", name);
        values.put("Area", area_vlaue);
        values.put("Measure_Unit",type);
        //values.put("CropGrown",ss);
        database.insert("fieldinfo", null, values);
        database.close();



    }

}
