package com.example.sagar.ahs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ConfirmCrop extends AppCompatActivity {
    private Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_crop);


        Intent intent = getIntent();
        extras = intent.getExtras();

        ///name= extras.getString("name");
        //Area= extras.getString("Area");


    }
}
