package com.example.pritesh.bmi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yashwant on 6/3/2016.
 */
public class BmiDatabase {


    private static final String DATABASE_NAME = "bmi";
    private static final int DATABASE_VERSION = 1;



    private static final String DATABASE_REGISTER = "register";

    public static final String KEY_ID = "id";

    public static final String KEY_NAME = "name";
    public static final String KEY_DOB = "dob";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_CITY = "city";
    public static final String KEY_PINCODE = "pincode";
    public static final String KEY_AGE = "age";




    private static final String DATABASE_BMI = "bmi";

    public static final String KEY_BID = "bid";

    public static final String KEY_HIGHT = "hight";
    public static final String KEY_WEIGHT = "weight";
    public static final String KEY_BMI = "bmi";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";
    public static final String KEY_DAY = "day";
    public static final String KEY_DIFF = "diff";


    private Dbhelper oHelper;
    private final Context ocontext;
    private SQLiteDatabase oDtabase;



    private static class Dbhelper extends SQLiteOpenHelper {

        public Dbhelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(" CREATE TABLE " + DATABASE_REGISTER + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT NOT NULL, " + KEY_DOB + " TEXT NOT NULL, " + KEY_GENDER + "  TEXT NOT NULL, "
                    + KEY_ADDRESS + " TEXT NOT NULL, " + KEY_CITY + " TEXT NOT NULL, " + KEY_PINCODE +  " TEXT NOT NULL, " + KEY_AGE + " TEXT NOT NULL);"
            );


            db.execSQL(" CREATE TABLE " + DATABASE_BMI + " (" + KEY_BID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_HIGHT + " TEXT, " + KEY_WEIGHT + " TEXT, " + KEY_BMI + "  TEXT, " + KEY_DATE +  " TEXT, " + KEY_TIME + " TEXT, " + KEY_DAY + " TEXT, " + KEY_DIFF + " TEXT);"
            );


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXIST" + DATABASE_REGISTER);
            onCreate(db);

        }
    }

    public BmiDatabase(Context c) {
        ocontext = c;
        oHelper = new Dbhelper(c);
        oDtabase = oHelper.getWritableDatabase();
    }

    public BmiDatabase open() {
        oHelper = new Dbhelper(ocontext);
        oDtabase = oHelper.getWritableDatabase();
        oDtabase = oHelper.getReadableDatabase();
        return this;

    }

    public void close() {

        oHelper.close();

    }


    public long createntry(String name2, String dob2, String gender, String addresss2, String city2, String pincode2,String age) {

        ContentValues lcv = new ContentValues();

        lcv.put(KEY_NAME,name2);
        lcv.put(KEY_DOB,dob2);
        lcv.put(KEY_GENDER,gender);
        lcv.put(KEY_ADDRESS,addresss2);
        lcv.put(KEY_CITY,city2);
        lcv.put(KEY_PINCODE,pincode2);
        lcv.put(KEY_AGE,age);

        return oDtabase.insert(DATABASE_REGISTER, null, lcv);

    }



    public long bmientry(String hig, String weig, String mybm,String formattedDate,String day,String time,String diff) {

        ContentValues lcv = new ContentValues();

        lcv.put(KEY_HIGHT,hig);
        lcv.put(KEY_WEIGHT,weig);
        lcv.put(KEY_BMI,mybm);
        lcv.put(KEY_DATE,formattedDate);
        lcv.put(KEY_DAY,day);
        lcv.put(KEY_TIME,time);
        lcv.put(KEY_DIFF,diff);

        return oDtabase.insert(DATABASE_BMI, null, lcv);

    }



    public Cursor getdata() {

        Cursor cp25 =  oDtabase.rawQuery("SELECT * FROM bmi order by bid DESC limit 1 ", null);


        return cp25;

    }


    public long cretweightentry(String weight1,String myheight, String time1, String date1,String mybm,String day,String dff) {



        ContentValues lcv = new ContentValues();


        lcv.put(KEY_WEIGHT,weight1);
        lcv.put(KEY_HIGHT,myheight);
        lcv.put(KEY_DATE,date1);
        lcv.put(KEY_TIME,time1);
        lcv.put(KEY_BMI,mybm);
        lcv.put(KEY_DAY,day);
        lcv.put(KEY_DIFF,dff);


        return oDtabase.insert(DATABASE_BMI, null, lcv);


    }




    public Cursor getalldata() {



        Cursor cp25 =  oDtabase.rawQuery("SELECT * FROM bmi order by bid DESC ", null);

        return cp25;

    }





    public Cursor valid(String date1) {

        Cursor c = oDtabase.rawQuery("SELECT * FROM " + DATABASE_BMI + " WHERE " + KEY_DATE + "='" + date1  +"'" , null);

        return c;
    }



    public void updateweightentry(int id, String weight1, String myheight, String time1, String date1, String mybm, String day, String wtt) {



        ContentValues lcv = new ContentValues();


        lcv.put(KEY_WEIGHT,weight1);
        lcv.put(KEY_HIGHT,myheight);
        lcv.put(KEY_DATE,date1);
        lcv.put(KEY_TIME,time1);
        lcv.put(KEY_BMI,mybm);
        lcv.put(KEY_DAY,day);
        lcv.put(KEY_DIFF,wtt);

        oDtabase.update(DATABASE_BMI, lcv, KEY_BID + "=" + id, null);




    }



    public Cursor getweight(String pii) {

        Cursor c = oDtabase.rawQuery("SELECT * FROM " + DATABASE_BMI + " WHERE " + KEY_BID + "='" + pii  +"'" , null);

        return c;


    }


    public Cursor getgen() {


        Cursor cp1 = oDtabase.query(DATABASE_REGISTER, null, null, null, null, null, null);

        return cp1;

    }




}
