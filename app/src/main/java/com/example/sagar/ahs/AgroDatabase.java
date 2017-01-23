package com.example.sagar.ahs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


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
      /*  db.execSQL("CREATE TABLE "+TABLE_VARIABLES+"("
                +"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Crop_Name TEXT,"
                + "Temp_Max INTEGER,"
                + "Temp_Min INTEGER,"
                + "Humidity_Max INTEGER,"
                + "Humidity_Min INTEGER,"
                + "PH_Max INTEGER,"
                + "PH_Min INTEGER,"
                + "Rain INTEGER,"

                +"Time_To_Harvest INTEGER,"

                + "Sunshine_Days INTEGER);");


*/
        db.execSQL("CREATE TABLE "+TABLE_USERINFO+"("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "User_Name TEXT,"
                + "Location TEXT,"
                +"CurrentNoFields INTEGER"
                +"Total Area TEXT"
                + "Crops_Grown TEXT);");
        db.execSQL("CREATE TABLE "+TABLE_FIELDINFO+"("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "FieldName TEXT,"
                + "Area TEXT,"
                + "Measure_Unit TEXT,"
                + "CropGrown TEXT,"
                + "SideCrop TEXT,"

                +"Growth_Start_Date TEXT,"
                +"Growth_End_Date TEXT);");
        /*insert_Data(db,"Rice",35,20,80,60,(float)6.5,(float)5.0,1400,150,6);
        insert_Data(db,"Wheat",12,30,60,50,(float)3.7,7,310,120,6);
        insert_Data(db,"Soybean",27,15,60,50,6,(float)6.8,60,100,6);
        insert_Data(db,"Strawberries",27,15,60,50,7,(float)3.7,310,120,6);
        insert_Data(db,"Peas",(float)24.1,(float)15.5,40,60,6,(float)7.5,0,70,6);
        insert_Data(db,"Potatoes",30,10,80,50,6,(float)4.5,0,110,6);
        insert_Data(db,"Pumpkin",32,21,60,40,7,(float)5.5,600,100,6);
        insert_Data(db,"Onions",35,21,60,40,7,6,0,100,6);
        insert_Data(db,"Corn",24,15,60,40,7,(float)5.5,0,75,6);
        insert_Data(db,"LimaBeans",24,18,60,40,7,6,0,80,6);
       */
        insert_Data(db,"Rice",35,20,80,60,6,5,1400,150,6);
        insert_Data(db,"Wheat",12,30,60,50,4,7,310,120,6);
        insert_Data(db,"Soybean",27,15,60,50,6,7,60,100,6);
        insert_Data(db,"Strawberries",27,15,60,50,7,4,310,120,6);
        insert_Data(db,"Peas",24,15,40,60,6,8,0,70,6);
        insert_Data(db,"Potatoes",30,10,80,50,6,4,0,110,6);
        insert_Data(db,"Pumpkin",32,21,60,40,7,5,600,100,6);
        insert_Data(db,"Onions",35,21,60,40,7,6,0,100,6);
        insert_Data(db,"Corn",24,15,60,40,7,5,0,75,6);
        insert_Data(db,"LimaBeans",24,18,60,40,7,6,0,80,6);
        insert_Data(db,"Cauliflower",27,16,80,70,7,6,0,75,6);
        insert_Data(db,"Carrots",29,7,60,50,8,6,0,70,6);
        insert_Data(db,"Cabbage",35,7,60,40,8,6,0,70,6);
        insert_Data(db,"Broccoli",26,16,50,40,8,6,0,70,6);
        insert_Data(db,"Turnip",30,15,50,40,7,5,0,55,6);
        insert_Data(db,"Spinach",23,10,50,40,7,6,0,40,4);
        insert_Data(db,"Raddish",32,7,60,50,7,6,0,90,6);
        insert_Data(db,"Lettuce",24,4,70,50,7,6,0,90,6);
        insert_Data(db,"Garlic",24,13,65,50,8,5,0,140,6);
        insert_Data(db,"Cucumber",35,22,60,50,8,5,0,60,6);




    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){


    }
/*
   private void insert_Data(SQLiteDatabase db,String name,float tempmax,float tempmin,float humiditymax,
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
        db.insert("variables",null,crop_data);
 //       database.close();
    }
*/
   private static void insert_Data(SQLiteDatabase db,String name,int tempmax,int tempmin,int humiditymax,
                            int humidity_min,int phmax,int phmin,int rainmax,
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
       crop_data.put("Sunshine_Days",Sunshine);
       db.insert("variables",null,crop_data);
      // db.close();
   }




//#not my code copied to see the contents of the table in database
    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }


    }
}
