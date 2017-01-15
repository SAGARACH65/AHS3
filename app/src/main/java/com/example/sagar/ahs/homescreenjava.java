package com.example.sagar.ahs;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;



public class homescreenjava extends AppCompatActivity {
    private Cursor cursor;
    private SQLiteDatabase db;
    public void addNewFields(View view) {

        Intent callnew =new Intent(this,AddNewField.class);
        startActivity(callnew);

    }
    public void showUserInfo(View view){

        Intent callnew =new Intent(this,showuserinfo.class);
        startActivity(callnew);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        ListView m_listview = (ListView) findViewById(R.id.list_view);

        SQLiteOpenHelper AgroDatabase = new AgroDatabase(this);
         db = AgroDatabase.getReadableDatabase();
        cursor=db.query("fieldinfo",new String[]{"_id", "FieldName","Area"},null,null,null,null,null);
        CursorAdapter listAdapter= new SimpleCursorAdapter(this,
                                            android.R.layout.simple_list_item_1,
                                            cursor,
                                            new String[]{"FieldName"},
                                            new int[]{android.R.id.text1},0);

        m_listview.setAdapter(listAdapter);
        m_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(homescreenjava.this, Addition1.class);
                startActivity(intent);
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }


}
