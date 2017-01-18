package com.example.sagar.ahs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Addition1 extends AppCompatActivity {
     private String name;
     private String Area;
    private  String M_unit;
    private String Crop_grown;
    public void movetosecond(View view)
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
    public void movetothird(View view)
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

    }
}
