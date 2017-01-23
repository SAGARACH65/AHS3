package com.example.sagar.ahs;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Addition2 extends AppCompatActivity {
    private String name;
    private String Area;
    private String M_unit;
    private String harvest_day;
    private String Crop_grown;

    private String cg;
    private String sc;
    private String gd;
    private String gde;
    private String name1;
    public void movetothird(View view)
    {

            Intent intent = new Intent(this, Addition3.class);

            Bundle extras = new Bundle();

        extras.putString("name",name);
        extras.putString("Area",Area);
        extras.putString("Measurement Unit",M_unit);
         extras.putString("Crop planted",Crop_grown);
        extras.putString("Growth_End_Date",harvest_day);
        intent.putExtras(extras);
        startActivity(intent);
        finish();
    }
    public void movetofirst(View view)
    {
        Intent intent;
        if(Crop_grown.equals("NA")) {
             intent = new Intent(this, Addition1.class);
        }
        else{
             intent = new Intent(this, Addition1_Second.class);
        }
        Bundle extras = new Bundle();

        extras.putString("name",name);
        extras.putString("Area",Area);
        extras.putString("Measurement Unit",M_unit);
        extras.putString("Growth_End_Date",harvest_day);
         extras.putString("Crop planted",Crop_grown);
        intent.putExtras(extras);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition2);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        name= extras.getString("name");
        Area= extras.getString("Area");
        M_unit= extras.getString("Measurement Unit");
         Crop_grown=extras.getString("Crop planted");
        harvest_day=extras.getString("Growth_End_Date");


        TextView tv1=(TextView) findViewById(R.id.textView88);
        tv1.setText(name);
        TextView tv2=(TextView) findViewById(R.id.textView89);
        tv2.setText(Area);
        TextView tv3=(TextView) findViewById(R.id.textView99);
        tv3.setText(M_unit);
        TextView tv4=(TextView) findViewById(R.id.textView19);
        tv4.setText(Crop_grown);
        TextView tv5=(TextView) findViewById(R.id.tv122);
        tv5.setText(harvest_day);

        SQLiteOpenHelper AgroDatabase = new AgroDatabase(this);
        SQLiteDatabase db = AgroDatabase.getReadableDatabase();
        Cursor cursor1 = db.query("fieldinfo",
                new String[]{"_id","FieldName", "CropGrown","SideCrop", "Growth_Start_Date","Growth_End_Date"},
                null,
                null, null, null, null
        );

        if(cursor1.moveToFirst()){
            do{
                     name1=cursor1.getString(1);
                 cg=cursor1.getString(2);
                sc=cursor1.getString(3);
                gd=cursor1.getString(4);
               gde =cursor1.getString(5);

                if(name1.equals(name)){
                    break;

                }
                cursor1.moveToNext();
            }while(!cursor1.isAfterLast());

        }
        TextView tv6=(TextView) findViewById(R.id.textView42);
        TextView tv7=(TextView) findViewById(R.id.textView43);
        TextView tv8=(TextView) findViewById(R.id.textView44);
        TextView tv9=(TextView) findViewById(R.id.textView45);

        TextView tv10=(TextView) findViewById(R.id.textView38);
        TextView tv11=(TextView) findViewById(R.id.textView39);
        TextView tv12=(TextView) findViewById(R.id.textView40);
        TextView tv13=(TextView) findViewById(R.id.textView41);
        TextView tv14=(TextView) findViewById(R.id.textView37);
        if("NA".equals(Crop_grown)){

            tv6.setVisibility(View.INVISIBLE);
            tv7.setVisibility(View.INVISIBLE);
            tv8.setVisibility(View.INVISIBLE);
            tv9.setVisibility(View.INVISIBLE);
            tv10.setVisibility(View.INVISIBLE);
            tv11.setVisibility(View.INVISIBLE);
            tv12.setVisibility(View.INVISIBLE);
            tv13.setVisibility(View.INVISIBLE);
            tv14.setVisibility(View.VISIBLE);
        }
        else{ tv6.setText(cg);
            tv7.setText(sc);
            tv8.setText(gd);
            tv9.setText(gde);

            tv6.setVisibility(View.VISIBLE);
            tv7.setVisibility(View.VISIBLE);
            tv8.setVisibility(View.VISIBLE);
            tv9.setVisibility(View.VISIBLE);
            tv10.setVisibility(View.VISIBLE);
            tv11.setVisibility(View.VISIBLE);
            tv12.setVisibility(View.VISIBLE);
            tv13.setVisibility(View.VISIBLE);
            tv14.setVisibility(View.INVISIBLE);
        }





    }
}
