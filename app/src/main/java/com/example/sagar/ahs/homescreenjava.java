package com.example.sagar.ahs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by sagar on 1/10/2017.
 */

public class homescreenjava extends AppCompatActivity {

    public void addNewFields(View view) {

        Intent callnew =new Intent(this,AddNewField.class);
        startActivity(callnew);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
    }
}
