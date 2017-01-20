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
            + "Temp_Max REAL,"
            + "Temp_Min REAL,"
            + "Humidity_Max REAL,"
            + "Humidity_Min REAL,"
            + "PH_Max REAL,"
            + "PH_Min REAL,"
            + "Rain REAL,"

            +"Time_To_Harvest INTEGER,"

            + "Sunshine_Days INTEGER);");
        insert_Data(db,"Rice",35,20,80,60,(float)6.5,(float)5.0,1400,150,6);
        insert_Data(db,"Wheat",12,30,60,50,(float)3.7,7,310,120,6);
        insert_Data(db,"Soybean",27,15,60,50,6,(float)6.8,60,100,6);
        insert_Data(db,"Strawberries",27,15,60,50,7,(float)3.7,310,120,6);
        insert_Data(db,"Peas",(float)24.1,(float)15.5,40,60,6,(float)7.5,0,70,6);
        insert_Data(db,"Potatoes",30,10,80,50,6,(float)4.5,0,110,6);
        insert_Data(db,"Pumpkin",32,21,60,40,7,(float)5.5,600,100,6);
        insert_Data(db,"Onions",35,21,60,40,7,6,0,100,6);
        insert_Data(db,"Corn",24,15,60,40,7,(float)5.5,0,75,6);
        insert_Data(db,"LimaBeans",24,18,60,40,7,6,0,80,6);


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


private static void insert_Data(SQLiteDatabase db,String name,float tempmax,float tempmin,float humiditymax,
                               float humidity_min,float phmax,float phmin,float rainmax,
                                int T2H,int Sunshine){



    ContentValues crop_data= new ContentValues();
    crop_data.put("Crop_Name",name);
    crop_data.put("Temp_Max",tempmax);
    crop_data.put("Temp_Min",tempmin);
    crop_data.put("Humidity_Max",humiditymax);
    crop_data.put("Humidity_Min",humidity_min);
    crop_data.put("PH_Max",phmax);
    crop_data.put("PH_Min",phmin);
    crop_data.put("Rain",rainmax);

    crop_data.put("Time_To_Harvest",T2H);
    crop_data.put("Sunshine)Days",Sunshine);
    //crop_data.put("Nitrogen_Max",N2max);
    //crop_data.put("Nitrogen_Min",N2min);
               db.insert("variables",null,crop_data);

}







}
