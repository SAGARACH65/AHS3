package com.example.sagar.ahs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Addition3 extends AppCompatActivity {
    private String name;
    private String Area;
    private String M_unit;
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
    public void movetofirst(View view)
    {

        Intent intent = new Intent(this, Addition1.class);
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
        setContentView(R.layout.activity_addition3);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        name= extras.getString("name");
        Area= extras.getString("Area");
        M_unit= extras.getString("Measurement Unit");
         Crop_grown=extras.getString("Crop planted");


        TextView tv1=(TextView) findViewById(R.id.textView50);
        tv1.setText(name);
        TextView tv2=(TextView) findViewById(R.id.textView51);
        tv2.setText(Area);
        TextView tv3=(TextView) findViewById(R.id.textView55);
        tv3.setText(M_unit);
        TextView tv4=(TextView) findViewById(R.id.textView53);
        tv4.setText(Crop_grown);
    }
}
