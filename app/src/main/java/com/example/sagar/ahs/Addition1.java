package com.example.sagar.ahs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static android.content.ContentValues.TAG;
import static java.lang.Integer.parseInt;

public class Addition1 extends AppCompatActivity {

    private boolean check=true;
     private String name;
     private String Area;
    private  String M_unit;
    private String Crop_grown;
    private Bundle extras;
    private String harvest_day;
    private String nitromessage;
    Algorithm al1= new Algorithm(Addition1.this);
    public void moveToSecond(View view)
    {

        Intent intent = new Intent(this, Addition2.class);
        extras = new Bundle();

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
    public void moveToThird(View view)
    {

        Intent intent = new Intent(this, Addition3.class);
        extras = new Bundle();

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

    public void syncDevice(View view)
    {
        BlueToothController bc1=new BlueToothController();


    }

    public void findCrop(View view) {
        //getting the environmental variable values
        Spinner prioritycrop = (Spinner) findViewById(R.id.spinner3);
        String priority_name = String.valueOf(prioritycrop.getSelectedItem());

        EditText temp = (EditText) findViewById(R.id.editText3);
        float tempEntered = Float.parseFloat(temp.getText().toString());


        EditText ph = (EditText) findViewById(R.id.editText4);
        float phEntered = Float.parseFloat(ph.getText().toString());

        EditText sunshine = (EditText) findViewById(R.id.editText5);
        float sunshineEntered = Float.parseFloat(sunshine.getText().toString());

        EditText humidity = (EditText) findViewById(R.id.editText6);
        float humidityEntered = Float.parseFloat(humidity.getText().toString());

        EditText nitrogen = (EditText) findViewById(R.id.editText7);
        float nitrogenentered = Float.parseFloat(nitrogen.getText().toString());


        //checking if rainfall needs to be selected or not
        CheckBox cb2 = (CheckBox) findViewById(R.id.checkBox2);
        if (cb2.isSelected()) {


            EditText rainfall = (EditText) findViewById(R.id.editText8);
            float rainfallentered = Float.parseFloat(rainfall.getText().toString());
            String[] Crop_Result = al1.findBestCrop(priority_name, tempEntered, phEntered, sunshineEntered, humidityEntered, nitrogenentered);

            // TextView tv1=(TextView) findViewById(R.id.textView24);
            //  tv1.setText(s1+s2);
            unpackandsend(Crop_Result, nitrogenentered);
        } else {

            String[] Crop_Result = al1.findBestCrop(priority_name, tempEntered, phEntered, sunshineEntered, humidityEntered, nitrogenentered);

            //String s1=Crop_Result[0];
            //String s2=Crop_Result[1];
            //TextView tv1=(TextView) findViewById(R.id.textView24);
            //tv1.setText(s1+s2);
            unpackandsend(Crop_Result, nitrogenentered);

            //TextView tv1=(TextView) findViewById(R.id.textView24);


        }
    }


    public void unpackandsend(String []Crop_Result,float nitrogen_entered){
        String nitromessage=null;
        String joiner=" ";
        byte checker=0;
        int k;
        if(nitrogen_entered<30){

            nitromessage="Nitrogen Content is Low.Plant Legumonistic Crops Alongside";
        }
        else if(nitrogen_entered>100){
            nitromessage="Too much Nitrogen in Soil.Plant Corn to minimize it";

        }


        for(int i=0;i<(Crop_Result.length);i++){
           // TextView tv1=(TextView) findViewById(R.id.textView16);
          //  tv1.setText(Crop_Result[0]);

           if("TOP".equals(Crop_Result[i])){
                        k=(i-1);
                 for( i=0;i<=(k);i++){
                     joiner=joiner+" "+Crop_Result[i];
                 }
               Intent intent = new Intent(this, ConfirmCrop.class);
               extras.putString("Message","TOP");
               extras.putString("crops",joiner );
               extras.putString("Nitrogen",nitromessage);
               intent.putExtras(extras);
               startActivity(intent);
               finish();
            }
            else if("SECOND".equals(Crop_Result[i])){
                k=i-1;
               for( i=0;i<=(k);i++){
                   joiner=joiner+" "+Crop_Result[i];
               }
               Intent intent = new Intent(this, ConfirmCrop.class);
               extras.putString("Message","SECOND");
               extras.putString("crops",joiner );
               extras.putString("Nitrogen",nitromessage);
               intent.putExtras(extras);
               startActivity(intent);
               finish();
            }
            else if("TOPACCTOPRIORITY".equals(Crop_Result[i])){
                k=i-1;
               for( i=0;i<=(k);i++){
                   joiner=joiner+" "+Crop_Result[i];
               }
               Intent intent = new Intent(this, ConfirmCrop.class);
               extras.putString("Message","TOPACCTOPRIORITY");
               extras.putString("crops",joiner );
               extras.putString("Nitrogen",nitromessage);
               intent.putExtras(extras);
               startActivity(intent);
                finish();
           }
            else if("SelectedNotGood".equals(Crop_Result[i])){
               k=i-1;
               for( i=0;i<=(k);i++){
                   joiner=joiner+" "+Crop_Result[i];
               }
               Intent intent = new Intent(this, ConfirmCrop.class);
               extras.putString("Message","NONE");
               extras.putString("crops",joiner );
               extras.putString("Nitrogen",nitromessage);
               intent.putExtras(extras);
               startActivity(intent);
                finish();
           }

            }
          //code if no item



        }




    //private Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addition1);
        //taking data from bundle of intent
        Intent intent = getIntent();
         extras = intent.getExtras();

        name= extras.getString("name");
         Area= extras.getString("Area");
         M_unit= extras.getString("Measurement Unit");
         Crop_grown=extras.getString("Crop planted");
        nitromessage=extras.getString("nitromessage");

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


        //for the checkbox partial visibility of rainfall portion
        final TextView tv6=(TextView) findViewById(R.id.textbox100);


        final EditText et1=(EditText) findViewById(R.id.editText8);
        et1.setVisibility(View.INVISIBLE);
        tv6.setVisibility(View.INVISIBLE);

        CheckBox cb1=(CheckBox) findViewById(R.id.checkBox2);


        Button button =(Button)findViewById(R.id.button6);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent dbmanager = new Intent(getApplicationContext(),AndroidDatabaseManager.class);
                startActivity(dbmanager);
            }
        });
        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check) {
                    tv6.setVisibility(View.VISIBLE);
                    et1.setVisibility(View.VISIBLE);
                    check=false;
                }
                else
                {
                    tv6.setVisibility(View.INVISIBLE);
                    et1.setVisibility(View.INVISIBLE);
                        check=true;
                }
                }
        });







    }


}
