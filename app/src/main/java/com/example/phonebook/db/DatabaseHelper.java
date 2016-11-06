package com.example.phonebook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ON-ONE on 10/23/2016.
 * Database ตัวช่วย
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private  static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "phonebook"; //ชื่อฐานข้อมูล
    private static final int DATABASE_VERSION = 4;//เวอร์ชั่น

    public static final String TABLE_NAME = "phonebook";
    public static final String COL_ID ="_id";
    public static final String COL_NAME ="name";
    public static final String COL_PHONE ="phone";
    public static final String COL_IMAGE ="image";

    private static final String SQL_CREATE_TABLE=
            "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_NAME + " TEXT,"
            + COL_IMAGE + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override//ทำครั้งเดียวตอนไม่มีฐานข้อมูล
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE); //เรียกเพื่อส้รางตารางเปล่า
        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, "android");//ใส่ค่า
        cv.put(COL_PHONE, "111-111");//ใส่ค่า
        cv.put(COL_IMAGE, "ic_android.png");
        db.insert(TABLE_NAME, null,cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ios");
        cv.put(COL_PHONE, "222-222");
        cv.put(COL_IMAGE, "ic_ios.png");
        db.insert(TABLE_NAME, null,cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "Windows");
        cv.put(COL_PHONE, "333-333");
        cv.put(COL_IMAGE, "ic_windows.png");
        db.insert(TABLE_NAME, null,cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "Linux");
        cv.put(COL_PHONE, "444-444");
        cv.put(COL_IMAGE, "ic_linux.png");
        db.insert(TABLE_NAME, null,cv);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade(): oldVersion = "+ oldVersion + ", newVersion ="+ newVersion);
        db.execSQL("DROP TABLE " +TABLE_NAME);
        db.execSQL(SQL_CREATE_TABLE);
        insertInitialData(db);

    }
}



/*สร้างตาราง
    CREATE TABEL phonebook(
          _id INTEGER PRIMARY KEY AUTOINCREMENT<บังคับ>,
          name TEXT,
          phone TEXT
    )
    */
