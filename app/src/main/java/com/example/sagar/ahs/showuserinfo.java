package com.example.sagar.ahs;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class showuserinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showuserinfo);

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
                ListView m_listview = (ListView) findViewById(R.id.list_view);
                final Cursor cursor1 = db.query("fieldinfo",
                        new String[]{"_id", "Area","Measure_Unit","CropGrown"},
                        null,
                        null, null, null, null
                );

                CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                        android.R.layout.simple_list_item_2,
                        cursor1,
                        new String[]{"CropGrown"},
                        new int[]{android.R.id.text1}, 0);

                m_listview.setAdapter(listAdapter);
                m_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {


                    }});


    }
}
