package com.example.sagar.ahs;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Addition3 extends AppCompatActivity {
    private String name;
    private String Area;
    private String M_unit;
    private String harvest_day;
    private String Crop_grown;
    private String nitromessage;

private String info;
    String fn,mcn,scn;//mcn=mainCropName
    public void movetosecond(View view)
    {

        Intent intent = new Intent(this, Addition2.class);
        Bundle extras = new Bundle();

        extras.putString("name",name);
        extras.putString("Area",Area);
        extras.putString("Measurement Unit",M_unit);
        extras.putString("Crop planted",Crop_grown);
        extras.putString("Growth_End_Date",harvest_day);
        extras.putString("nitromessage",nitromessage);
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
         extras.putString("Crop planted",Crop_grown);
        extras.putString("nitromessage",nitromessage);
        extras.putString("Growth_End_Date",harvest_day);
        intent.putExtras(extras);
        startActivity(intent);
        finish();
    }


    public void fertilizerMain(View view){
        Intent intent = new Intent(this,InfoDisplay.class);
     Bundle xx=new Bundle();
        xx.putString("info","fertilizerMain");
        xx.putString("crop",mcn);
        intent.putExtras(xx);
        startActivity(intent);

    }
    public void fertilizerSide(View view){
        Intent intent = new Intent(this,InfoDisplay.class);
        Bundle xx=new Bundle();
        xx.putString("info","fertilizerSide");
        xx.putString("crop",scn);
        intent.putExtras(xx);
        startActivity(intent);


    }
    public void plantMain(View view){

        Intent intent = new Intent(this,InfoDisplay.class);
        Bundle xx=new Bundle();
        xx.putString("info","plantMain");
        xx.putString("crop",mcn);
        intent.putExtras(xx);
        startActivity(intent);

    }
    public void plantSide(View view){

        Intent intent = new Intent(this,InfoDisplay.class);
        Bundle xx=new Bundle();
        xx.putString("info","plantSide");
        xx.putString("crop",scn);
        intent.putExtras(xx);
        startActivity(intent);

    }
    public void storageMain(View view){

        Intent intent = new Intent(this,InfoDisplay.class);
        Bundle xx=new Bundle();
        xx.putString("info","storageMain");
        xx.putString("crop",mcn);
        intent.putExtras(xx);
        startActivity(intent);

    }
    public void storageSide(View view){

        Intent intent = new Intent(this,InfoDisplay.class);
        Bundle xx=new Bundle();
        xx.putString("info","storageSide");
        xx.putString("crop",scn);
        intent.putExtras(xx);
        startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition3);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        name= extras.getString("name");
        Area= extras.getString("Area");
        M_unit= extras.getString("Measurement Unit");
         Crop_grown=extras.getString("Crop planted");
        harvest_day=extras.getString("Growth_End_Date");
        nitromessage=extras.getString("nitromessage");



        TextView tv1=(TextView) findViewById(R.id.textView50);
        tv1.setText(name);
        TextView tv2=(TextView) findViewById(R.id.textView51);
        tv2.setText(Area);
        TextView tv3=(TextView) findViewById(R.id.textView55);
        tv3.setText(M_unit);
        TextView tv4=(TextView) findViewById(R.id.textView53);
        tv4.setText(Crop_grown);
        TextView tv5=(TextView) findViewById(R.id.tv122);
        tv5.setText(harvest_day);

        SQLiteOpenHelper AgroDatabase = new AgroDatabase(this);
        SQLiteDatabase db = AgroDatabase.getReadableDatabase();
        Cursor cursor1 = db.query("fieldinfo",
                new String[]{"_id","FieldName", "CropGrown","SideCrop"},
                null,
                null, null, null, null
        );


        if(cursor1.moveToFirst()){
            do {
                fn = cursor1.getString(1);
                mcn=cursor1.getString(2);
                scn=cursor1.getString(3);
                if(fn.equals(name)){
                    break;

                }
                cursor1.moveToNext();
            }while(!cursor1.isAfterLast());
        }


    }
}
