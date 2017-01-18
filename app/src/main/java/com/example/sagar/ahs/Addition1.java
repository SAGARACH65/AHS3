package com.example.sagar.ahs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Addition1 extends AppCompatActivity {
     private String name;
     private String Area;
    private  String M_unit;
    private String Crop_grown;
    public void moveToSecond(View view)
    {

        Intent intent = new Intent(this, Addition2.class);
        Bundle extras = new Bundle();

        extras.putString("name",name);
        extras.putString("Area",Area);
        extras.putString("Measurement Unit",M_unit);
         extras.putString("Crop planted",Crop_grown);
        intent.putExtras(extras);
        startActivity(intent);
        finish();
    }
    public void moveToThird(View view)
    {

        Intent intent = new Intent(this, Addition3.class);
        Bundle extras = new Bundle();

        extras.putString("name",name);
        extras.putString("Area",Area);
        extras.putString("Measurement Unit",M_unit);
        extras.putString("Crop planted",Crop_grown);
        intent.putExtras(extras);
        startActivity(intent);
        finish();

    }
    public  void onCheckBoxClick(View view)
    {




    }
    public void syncDevice(View view)
    {

    }
    public void findCrop(View view)
    {



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addition1);
        //taking data from bundle of intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        name= extras.getString("name");
         Area= extras.getString("Area");
         M_unit= extras.getString("Measurement Unit");
         Crop_grown=extras.getString("Crop planted");


        TextView tv1=(TextView) findViewById(R.id.textView16);
        tv1.setText(name);
        TextView tv2=(TextView) findViewById(R.id.textView17);
        tv2.setText(Area);
        TextView tv3=(TextView) findViewById(R.id.textView23);
        tv3.setText(M_unit);
        TextView tv4=(TextView) findViewById(R.id.textView19);
        tv4.setText(Crop_grown);


        //for the checkbox partial visibility of rainfall portion
        final TextView tv6=(TextView) findViewById(R.id.textbox100);


        final EditText et1=(EditText) findViewById(R.id.editText8);
        et1.setVisibility(View.INVISIBLE);
        tv6.setVisibility(View.INVISIBLE);

        CheckBox cb1=(CheckBox) findViewById(R.id.checkBox2);

        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv6.setVisibility(View.VISIBLE);
                et1.setVisibility(View.VISIBLE);
            }
        });


    }


}
