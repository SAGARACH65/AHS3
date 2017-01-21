package com.example.sagar.ahs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class ConfirmCrop extends AppCompatActivity {
    private Bundle extras;
    private String name;
    private String Area;
    private  String M_unit;
    private String Crop_grown;

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

  //Bundle extras;

    Intent intent = new Intent(this, Addition1_Second.class);
    extras.putString("name",name);
    extras.putString("Area",Area);
    extras.putString("Measurement Unit",M_unit);
    extras.putString("Crop planted",Crop_grown);
    extras.putString("Crop planted",final_crop);
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
                proxy="Best Yielding Crops are not available other options:"+crop;
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
