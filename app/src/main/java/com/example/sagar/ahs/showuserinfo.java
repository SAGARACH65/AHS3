package com.example.sagar.ahs;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class showuserinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showuserinfo);

            try {
                //accesing the database for information
                SQLiteOpenHelper AgroDatabase = new AgroDatabase(this);
                SQLiteDatabase db = AgroDatabase.getReadableDatabase();
                Cursor cursor = db.query("userinfo",
                        new String[]{"User_Name", "Location", "CurrentNoFields"},
                        "_id=?",
                        new String[]{Integer.toString(1)}, null, null, null
                );

                TextView name =(TextView) findViewById(R.id.textView9);
                TextView loc =(TextView) findViewById(R.id.textView11);
                TextView nof=(TextView) findViewById(R.id.textView13);
                    if(cursor.moveToFirst())
                    {
                        String named= cursor.getString(0);
                        String locd=cursor.getString(1);
                        String nofd=cursor.getString(2);
                        name.setText(named);
                        loc.setText(locd);
                        nof.setText(nofd);
                    }
                cursor.close();
                db.close();
            }catch (Exception e)
            {
              //  Toast toast=Toasto.makeText("Database Unavailable",Toast.LENGTH_LONG);
                //        toast.show();
            }

    }
}
