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
import android.widget.TextView;

public class Addition1_Second extends AppCompatActivity {
    private String name;
    private String Area;
    private  String M_unit;
    private String Crop_grown;
    private String harvest_day;
    private Bundle extras;

    public void moveToSecond(View view)
    {

        Intent intent = new Intent(this, Addition2.class);
        extras = new Bundle();

        extras.putString("name",name);
        extras.putString("Area",Area);
        extras.putString("Measurement Unit",M_unit);
        extras.putString("Growth_End_Date",harvest_day);
        extras.putString("Crop planted",Crop_grown);
        intent.putExtras(extras);
        startActivity(intent);
        finish();
    }
    public void moveToThird(View view)
    {

        Intent intent = new Intent(this, Addition3.class);
        extras = new Bundle();

        extras.putString("name",name);
        extras.putString("Area",Area);
        extras.putString("Measurement Unit",M_unit);
        extras.putString("Crop planted",Crop_grown);
        extras.putString("Growth_End_Date",harvest_day);
        intent.putExtras(extras);
        startActivity(intent);
        finish();

    }
    public void cancle(View view){
        SQLiteOpenHelper AgroDatabase = new AgroDatabase(this);
        SQLiteDatabase db = AgroDatabase.getWritableDatabase();
       //removing the previous data
        Cursor cursor1 = db.query("fieldinfo",
                new String[]{"_id","FieldName", "CropGrown","SideCrop", "Growth_Start_Date","Growth_End_Date"},
                null,
                null, null, null, null
        );

        if(cursor1.moveToFirst()) {
            int count=1;// as value of the row starts from 1 in database
            do {
                String fName = cursor1.getString(1);


                if (fName.equals(name)) {
                    ContentValues values = new ContentValues();
                    values.put("FieldName",name);
                    values.put("CropGrown", "NA");
                    values.put("SideCrop","");
                    values.put("Growth_Start_Date","");
                    values.put("Growth_End_Date","");
                    db.update("fieldinfo",values,"_id"+"="+count ,null);
                    break;
                }
                count++;
                cursor1.moveToNext();
            } while (!cursor1.isAfterLast());
            cursor1.close();}
        //calling homescreen
        Intent callnew =new Intent(this,homescreenjava.class);
        callnew.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(callnew);
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition1__second);


        Intent intent = getIntent();
        extras = intent.getExtras();


        name= extras.getString("name");
        Area= extras.getString("Area");
        M_unit= extras.getString("Measurement Unit");
        Crop_grown=extras.getString("Crop planted");
        harvest_day=extras.getString("Growth_End_Date");


        TextView tv1=(TextView) findViewById(R.id.textView16);
        tv1.setText(name);
        TextView tv2=(TextView) findViewById(R.id.textView17);
        tv2.setText(Area);
        TextView tv3=(TextView) findViewById(R.id.textView23);
        tv3.setText(M_unit);
        TextView tv4=(TextView) findViewById(R.id.textView19);
        tv4.setText(Crop_grown);
        TextView tv5=(TextView) findViewById(R.id.tv122);
        tv5.setText(harvest_day);

        /*Button button =(Button)findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent dbmanager = new Intent(getApplicationContext(),AndroidDatabaseManager.class);
                startActivity(dbmanager);
            }
        });

*/

    }
}
