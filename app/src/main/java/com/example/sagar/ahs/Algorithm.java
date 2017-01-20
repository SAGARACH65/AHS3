package com.example.sagar.ahs;



import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Arrays;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class Algorithm  {

//solution from stack overflow of using dattabse in non activity class. LOOk later how this works
 private Context mContext;
    private int counter=0;
    private int indextop=0;
    private int indexsecond=0;
    int var1=0,var2=0;

    private String[] topCrops=new String[4];
    private String[] secondCrops=new String[4];



    public Algorithm(Context context) {
        this.mContext=context;
    }



    public String[] findBestCrop(String priority_crop, float tempentered,float phentered,
                                 float sunshineentered, float humidityentered, float nitrogenenteredd) {
        SQLiteOpenHelper AgroDatabase = new AgroDatabase(mContext);
        SQLiteDatabase db = AgroDatabase.getReadableDatabase();
        Cursor cursor = db.query("variables",
                new String[]{"_id", "Crop_Name", "Temp_Max", "Temp_Min",
                        "Humidity_Max", "Humidity_Min", "PH_Max", "PH_Min", "Time_To_Harvest", "Sunshine_Days"},
                "_id=?",
                null, null, null, null
        );
        if(priority_crop=="None") {

             if(cursor.moveToFirst()) {

                 do{
                    counter=0;
                     String named= cursor.getString(1);
                     float tempMax=cursor.getFloat(2);
                     float tempMin=cursor.getFloat(3);
                     float humidityMax=cursor.getFloat(4);
                     float humidityMin=cursor.getFloat(5);
                     float PHMax=cursor.getFloat(6);
                     float PHMin=cursor.getFloat(7);
                     float Sunshine=cursor.getFloat(9);

                        if(tempentered>tempMin&&tempentered<tempMax)
                            counter+=2;
                     if(humidityentered>humidityMin&&humidityentered<humidityMax)
                         counter++;
                     if(phentered>PHMin&&phentered<PHMax)
                         counter+=2;
                     if (sunshineentered>Sunshine)
                         counter++;

                     Log.d(TAG, "counter: "+counter);
                     if(counter>4){
                            topCrops[indextop]=named;
                         indextop++;
                     }
                    if(counter>3){
                        secondCrops[indexsecond]=named;
                        indexsecond++;
                    }
                 }while(!cursor.isAfterLast());


                 for(int i=0;i<(topCrops.length);i++){
                     if(topCrops[i]!=null) {

                        var1=1;
                         break ;
                     }

                 }
                 for(int i=0;i<(secondCrops.length);i++){
                     if(secondCrops[i]!=null) {

                         var2=1;
                         break ;
                     }

                 }
                 if(var1==1){
                     topCrops[indextop]="TOP";
                     return topCrops;

                    }
                 else if (var2==1) {
                     secondCrops[indexsecond]="SECOND";
                    return  secondCrops;
                 }
                 else {
                     return new String[]{null};

                 }



              }

        }
        else {

                if(cursor.moveToFirst()) {

                    do{
                    counter=0;
                    String named= cursor.getString(1);
                    float tempMax=cursor.getFloat(2);
                    float tempMin=cursor.getFloat(3);
                    float humidityMax=cursor.getFloat(4);
                    float humidityMin=cursor.getFloat(5);
                    float PHMax=cursor.getFloat(6);
                    float PHMin=cursor.getFloat(7);
                    float Sunshine=cursor.getFloat(9);
                        if(named==priority_crop) {
                            if (tempentered > tempMin && tempentered < tempMax)
                                counter += 2;
                            if (humidityentered > humidityMin && humidityentered < humidityMax)
                                counter++;
                            if (phentered > PHMin && phentered < PHMax)
                                counter += 2;
                            if (sunshineentered > Sunshine)
                                counter++;

                        }
                    if(counter>3){
                        topCrops[indextop]=named;
                        indextop++;
                    }


                }while(!cursor.isAfterLast());

                    for(int i=0;i<(topCrops.length);i++){
                        if(topCrops[i]!=null) {

                            var1=1;
                            break ;
                        }

                    }

                if(topCrops.length!=0){
                    topCrops[indextop]="TOPACCTOPRIORITY";
                    return topCrops;

                }

                else {
                    return new String[]{null};

                }



            }



        }
            //this line is not necessary just placed becoz IDE shows error without it
        return new String[]{null};


        }
















}