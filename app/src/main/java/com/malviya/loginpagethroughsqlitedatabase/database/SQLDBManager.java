package com.malviya.loginpagethroughsqlitedatabase.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Prafulla on 1/8/2017.
 */

public class SQLDBManager extends SQLiteOpenHelper {
    private static final String TAG="SQLDBManager";
    private static final String DATABASE_NAME="login.db";
    private static final int DATABASE_VERSION=1;

    //Create Database through constructor
    public SQLDBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG,"Database Created");
    }
    //Create Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LoginDataBaseTable.CREATE_LOGIN_TABLE);
        Log.d(TAG,"Login Table Created");
    }
    //Upgrade Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS "+LoginDataBaseTable.LOGIN_TABLE);
        onCreate(db);
        Log.d(TAG,"Login Table Upgraded");
    }
}
