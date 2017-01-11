package com.example.sagar.ahs;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
//int values;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);

        //this portion is for running the registration portion only once
     pref = getSharedPreferences("testapp", MODE_PRIVATE);
        editor = pref.edit();
        String getStatus=pref.getString("register", "nil");



        if(getStatus.equals("true")){
            Intent callnew =new Intent(this,homescreenjava.class);
            startActivity(callnew);

        }
        else
        {


            editor.putString("register","true");
            editor.commit();
        }
           // });

    }
    public void click(View view)
    {

        //Button buttonX = (Button)findViewById(R.id.button2);





        EditText name=(EditText) findViewById(R.id.editText2);
        String value = name.getText().toString();

        Spinner zone=(Spinner) findViewById(R.id.spinner);

        String zoneselected=String.valueOf(zone.getSelectedItem());


        storetodatabase(value,zoneselected);
        Intent callnew =new Intent(this,homescreenjava.class);
        startActivity(callnew);
    }
    private void storetodatabase(String value,String zonesected){
    SQLiteOpenHelper AgroDatabase=new AgroDatabase(this);
        SQLiteDatabase database = AgroDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("User_Name", value);
        values.put("Location", zonesected);
        values.put("CurrentNoFields",0);
        database.insert("userinfo", null, values);
        database.close();



    }


}

