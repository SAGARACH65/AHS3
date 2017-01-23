package com.example.sagar.ahs;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConfirmCrop extends AppCompatActivity {
    private Bundle extras;
    private String name;
    private String Area;
    private  String M_unit;
    private String Crop_grown;
   private int days;
public void showviews(View view)
{
    //show those invisible vieews
    Spinner spineer=(Spinner) findViewById(R.id.spinner5);

    TextView tv3=(TextView) findViewById(R.id.textView34);
    TextView tv4=(TextView) findViewById(R.id.textView33);
    ImageButton tv5=(ImageButton) findViewById(R.id.imageButton);
    spineer.setVisibility(View.VISIBLE);
    tv3.setVisibility(View.VISIBLE);
    tv4.setVisibility(View.VISIBLE);
    tv5.setVisibility(View.VISIBLE);



}

public void confirmed(View view){

    Spinner spineer=(Spinner) findViewById(R.id.spinner4);
    String final_crop=String.valueOf(spineer.getSelectedItem());
    Spinner spineer1=(Spinner) findViewById(R.id.spinner5);
    String side_crop=String.valueOf(spineer1.getSelectedItem());

  //updating all THE NECEssary table in the all the database tables


    //first acesssing the harvest time of the crop
    SQLiteOpenHelper AgroDatabase = new AgroDatabase(this);
    SQLiteDatabase db = AgroDatabase.getWritableDatabase();
    Cursor cursor = db.query("variables",
            new String[]{"_id","Crop_Name","Time_To_Harvest"},
            null,
            null, null, null, null
    );


        if(cursor.moveToFirst()) {
            do {
                String obtnaamed = cursor.getString(1);
                days = cursor.getInt(2);


                if (obtnaamed.equals(final_crop)) {
                        break;

                }
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
            cursor.close();}
            //receiving current systems date
           /* Calendar now = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String nowDate = formatter.format(now.getTime());
            String[] separateCurrentDate = nowDate.split("-");
            String year = separateCurrentDate[0];
            String month = separateCurrentDate[1];
            String day = separateCurrentDate[2];
            int currentYear = Integer.parseInt(year);
            int currentMonth = Integer.parseInt(month);
            int currentDay = Integer.parseInt(day);*/
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss");
            String reg_date = df.format(c.getTime());

            c.add(Calendar.DATE, days);  // number of days to add
            String end_date = df.format(c.getTime());

//now updating the necessary values in the database
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
                values.put("CropGrown", final_crop);
                values.put("SideCrop",side_crop);
                values.put("Growth_Start_Date",reg_date);
                values.put("Growth_End_Date",end_date);
                db.update("fieldinfo",values,"_id"+"="+count ,null);
                break;
               }
            count++;
            cursor1.moveToNext();
        } while (!cursor1.isAfterLast());
        cursor1.close();}


    Intent intent = new Intent(this, Addition1_Second.class);
    extras.putString("name",name);
    extras.putString("Area",Area);
    extras.putString("Measurement Unit",M_unit);
    extras.putString("Crop planted",final_crop);
    extras.putString("Growth_End_Date",end_date);
    intent.putExtras(extras);
    startActivity(intent);
    finish();
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_crop);


        Intent intent = getIntent();
        extras = intent.getExtras();

        name= extras.getString("name");
        Area= extras.getString("Area");
        M_unit= extras.getString("Measurement Unit");
        Crop_grown=extras.getString("Crop planted");

        TextView tv1=(TextView) findViewById(R.id.textView26);


        TextView tv2=(TextView) findViewById(R.id.textView28);


String proxy;
        String info= extras.getString("Message");
        String crop=extras.getString("crops");
        String nitromessage=extras.getString("Nitrogen");
        if(nitromessage!=null) {
            tv2.setText(nitromessage);
        }
        else{
            nitromessage="Nitrogen levels are Good";
            tv2.setText(nitromessage);

        }
        switch(info){
            case "TOP":
                proxy="The Best Yielding Crops are:"+crop;
                    tv1.setText(proxy);
                break;
            case"SECOND":
                proxy="Best Yielding Crops are not available .Other good options:"+crop;
                tv1.setText(proxy);
                break;
            case"TOPACCTOPRIORITY":
                proxy="Your priority is good for planting"+crop;
                    tv1.setText(proxy);
                 break;
            case "NONE":
                proxy="Your priority will not produce good Yield";
                tv1.setText(proxy);

                break;
            default:
                break;
        }







        Spinner spineer=(Spinner) findViewById(R.id.spinner5);

        TextView tv3=(TextView) findViewById(R.id.textView34);
        TextView tv4=(TextView) findViewById(R.id.textView33);
        ImageButton tv5=(ImageButton) findViewById(R.id.imageButton);
        spineer.setVisibility(View.INVISIBLE);
        tv3.setVisibility(View.INVISIBLE);
        tv4.setVisibility(View.INVISIBLE);
        tv5.setVisibility(View.INVISIBLE);




    }
}
