package com.malviya.loginpagethroughsqlitedatabase.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.malviya.loginpagethroughsqlitedatabase.constant.Constants;

/**
 * Created by Prafulla on 1/8/2017.
 */

//Login Credentials Constant

public class LoginDataBaseTable {
    Cursor cursor=null;

    // Context of the application using the database.
    private final Context context;

    // Database open/upgrade helper
    private SQLDBManager dbHelper;

    // Variable to hold the database instance
    private SQLiteDatabase db;

    //Login Constant Created
    static final String LOGIN_TABLE = "login";
    private static final String LOGIN_ID = "id";
     private static final String LOGIN_USER_NAME = "username";
     private static final String LOGIN_PASSWORD = "password";
    private static final String LOGIN_MOBILE = "mobile";
    private static final String LOGIN_ADDRESS = "address";
    private static final String LOGIN_EMAIL_ID = "email";

    //Create Login Table
    static final String CREATE_LOGIN_TABLE = "create table " + LOGIN_TABLE
            + "(" + LOGIN_ID + " integer primary key autoincrement, "
            + LOGIN_USER_NAME + " text, "
            + LOGIN_PASSWORD + " text, "
            + LOGIN_MOBILE + " text, "
            + LOGIN_EMAIL_ID + " text, "
            + LOGIN_ADDRESS + " text )";

    //Constructor
    public LoginDataBaseTable(Context context) {
        this.context = context;
    }

    public LoginDataBaseTable open() throws SQLException {
        dbHelper =new SQLDBManager(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    //Insert Data into table
    public boolean insertRegistrationData(String userName, String password, String mobile, String address, String email) {
        Log.d("KEY",userName);

        ContentValues contentValues = new ContentValues();
        contentValues.put(LOGIN_USER_NAME, userName);
        contentValues.put(LOGIN_PASSWORD, password);
        contentValues.put(LOGIN_EMAIL_ID,email );
        contentValues.put(LOGIN_MOBILE, mobile);
        contentValues.put(LOGIN_ADDRESS, address);

        // Insert the row into your table
        db.insert(LOGIN_TABLE, null, contentValues);
        return true;
    }

    //Update Data into Table
    public boolean updateRegistrationData(String id, String userName, String password, String mobile, String address, String email) {
        String whereClause = Constants.ID + "=?";
        String[] where = new String[]{id};
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOGIN_ID, id);
        contentValues.put(LOGIN_USER_NAME, userName);
        contentValues.put(LOGIN_PASSWORD, password);
        contentValues.put(LOGIN_EMAIL_ID,email );
        contentValues.put(LOGIN_MOBILE, mobile);
        contentValues.put(LOGIN_ADDRESS, address);

        // update the row into your table
        db.update(LOGIN_TABLE, contentValues, whereClause, where);
        return true;
    }

    //Delete data from table
    public boolean deleteRegistrationData(String id){
        String whereClause = Constants.ID + "=?";
        String[] where = new String[]{id};

        // delete the row into your table
        db.delete(LOGIN_TABLE,whereClause,where);
        return true;
    }

    //get single row from the table
    public String getPassword(String mobile){
        String str =null;
        String whereClause = Constants.ID + "=?";
        String[] where = new String[]{mobile};
        try{
         cursor = db.query(LOGIN_TABLE,null,whereClause,where,null,null,null);
        }catch (Exception e){
            e.getMessage();
        }
        if(cursor!=null)
        {
            if (cursor.getCount() < 1) {
                cursor.close();
                return "Data is no exist";
            }
            cursor.moveToFirst();
              str = cursor.getString(cursor.getColumnIndex(Constants.PASSWORD));
            cursor.close();
        }
        return str;
    }

    //get single row from the table
    public Cursor getSingleData(String pwd){
        String whereClause = Constants.PASSWORD + "=?";
        String[] where = new String[]{pwd};
        Cursor cursor = db.query(LOGIN_TABLE,null,whereClause,where,null,null,null);
       return cursor;

    }
    //get all row from the table
    public Cursor getAllDataOfRegistartion( ){
        Cursor cursor = db.query(LOGIN_TABLE,null,null,null,null,null,null);
        return cursor;
    }

}
