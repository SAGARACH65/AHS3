package com.example.sagar.ahs;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AgroDatabase extends SQLiteOpenHelper {
         private  static  String DB_NAME="AgroData";
    private  static  String TABLE_VARIABLES="variables";
    private  static  String TABLE_USERINFO="userinfo";
    private static String TABLE_FIELDINFO="fieldinfo";

    private static int DB_VERSION=1;

    AgroDatabase(Context context)
    {super(context,DB_NAME,null,DB_VERSION);//null is for cursors//sqlite helper classes constructor is being called

    }

    @Override
    public void onCreate(SQLiteDatabase db){//Sqlitedatabase class gives us access to database
    db.execSQL("CREATE TABLE "+TABLE_VARIABLES+"("
            +"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "Crop_Name TEXT,"
            + "Temp_Max INTEGER,"
            + "Temp_Min INTEGER,"
            + "Humidity_Max INTEGER,"
            + "Humidity_Min INTEGER,"
            + "PH_Max INTEGER,"
            + "PH_Min INTEGER,"
            + "Rain_Max INTEGER,"
            + "Rain_Min INTEGER,"
            + "Nitrogen_Max INTEGER,"
            + "Nitrogen_Min INTEGER);");
        insert_Data(db,"Rice",25,35,66,77,7,6,150,30,45,77);
        db.execSQL("CREATE TABLE "+TABLE_USERINFO+"("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "User_Name TEXT,"
                + "Location TEXT,"
                + "CurrentNoFields INTEGER);");
        db.execSQL("CREATE TABLE "+TABLE_FIELDINFO+"("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "FieldName TEXT,"
                + "Area TEXT,"
                + "Measure_Unit TEXT,"
                + "CropGrown  TEXT,"
                +"Growth_Date   INTEGER,"
                +"Growth_Month   INTEGER,"
                +"Growth_Year   INTEGER);");


    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){


    }


private static void insert_Data(SQLiteDatabase db,String name,int tempmax,int tempmin,int humiditymax,
                               int humidity_min,int phmax,int phmin,int rainmax,int rainmin,int nitrogenmax,int nitrogenmin){


    ContentValues crop_data= new ContentValues();
    crop_data.put("Crop_Name",name);
    crop_data.put("Temp_Max",tempmax);
    crop_data.put("Temp_Min",tempmin);
    crop_data.put("Humidity_Max",humiditymax);
    crop_data.put("Humidity_Min",humidity_min);
    crop_data.put("PH_Max",phmax);
    crop_data.put("PH_Min",phmin);
    crop_data.put("Rain_Max",rainmax);
    crop_data.put("Rain_Min",rainmin);
    crop_data.put("Nitrogen_Max",nitrogenmax);
    crop_data.put("Nitrogen_Min",nitrogenmin);
    db.insert("variables",null,crop_data);

}







}
