package com.example.sagar.ahs;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoDisplay extends AppCompatActivity {


    String name
            ,info
            ,Crop_name
            ,Fertilizer
            ,Storage
            ,Planting_Procedure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_display);

        TextView tv=(TextView) findViewById(R.id.textView48);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        name= extras.getString("crop");
        info=extras.getString("info");


        SQLiteOpenHelper AgroDatabase = new AgroDatabase(this);
        SQLiteDatabase db = AgroDatabase.getWritableDatabase();
        Cursor cursor = db.query("cropSuggestion",
                new String[]{"Crop_name", "Fertilizer", "Storage","Planting_Procedure"},
                null,
                null, null, null, null
        );
        //searching for info about the required crop
        if(cursor.moveToFirst()){
            do{
               Crop_name= cursor.getString(0);
              Fertilizer  =cursor.getString(1);
               Storage =cursor.getString(2);
               Planting_Procedure =cursor.getString(3);
                if(Crop_name.equals(name)){
                    break;
                }
                cursor.moveToNext();
            }while(!cursor.isAfterLast());
        }

        //displaying the apporopriate info based on the data from the intent
         //some of these are unnecessary but it will work. #tooLazyToEdit
            switch (info){
                case"fertilizerMain":
                    tv.setText(Fertilizer);
                 break;
                case"fertilizerSide":
                    tv.setText(Fertilizer);
                    break;
                case"plantMain":
                    tv.setText(Planting_Procedure);
                    break;
                case"plantSide":
                    tv.setText(Planting_Procedure);

                    break;
                case "storageMain":
                    tv.setText(Storage);
                    break;
                case "storageSide":
                    tv.setText(Storage);
                    break;
                default:
                    break;

            }
    }
}
