package com.malviya.loginpagethroughsqlitedatabase.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.malviya.loginpagethroughsqlitedatabase.R;
import com.malviya.loginpagethroughsqlitedatabase.constant.Constants;
import com.malviya.loginpagethroughsqlitedatabase.database.LoginDataBaseTable;
import com.malviya.loginpagethroughsqlitedatabase.utils.Utils;

/**
 * Created by Prafulla on 1/8/2017.
 */

public class Dashboard extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG="Dashboard";
    TextView mUserName,mPassword,mEmail,mAddress,mMobile;
    Button mLogout;
    LoginDataBaseTable loginDataBaseTable;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
        loginDataBaseTable= new LoginDataBaseTable(this);
        loginDataBaseTable= loginDataBaseTable.open();
        Log.d(TAG,"Dashboard onCreate()");
        init();
    }

    private void init() {
        Log.d(TAG, "Dashboard init()");
        mUserName = (TextView) findViewById(R.id.dashboard_textView_userName);
        mPassword = (TextView) findViewById(R.id.dashboard_textView_passwordName);
        mEmail = (TextView) findViewById(R.id.dashboard_textView_email);
        mAddress = (TextView) findViewById(R.id.dashboard_textView_address);
        mMobile = (TextView) findViewById(R.id.dashboard_textView_mobileNo);
        mLogout = (Button) findViewById(R.id.dashboard_btn_logout);
        mLogout.setOnClickListener(this);

        //fetch data
        Log.d(TAG, String.valueOf(getIntent().getStringExtra(Constants.PASSWORD)));
        Cursor cursor = loginDataBaseTable.getSingleData(getIntent().getStringExtra(Constants.PASSWORD));
        Log.d(TAG, String.valueOf(cursor.getCount()));
        Log.d(TAG, String.valueOf(cursor.getColumnCount()));
        if (cursor.moveToFirst()) {
            do {
              Log.d(TAG,cursor.getColumnName(0));
                Log.d(TAG,cursor.getColumnName(1));
                Log.d(TAG,cursor.getColumnName(2));
                Log.d(TAG,cursor.getColumnName(3));
                Log.d(TAG,cursor.getColumnName(4));
                Log.d(TAG,cursor.getColumnName(5));

                 mUserName.setText(cursor.getString(1));
                  mPassword.setText(cursor.getString(2));
                  mMobile.setText(cursor.getString(3));
                  mAddress.setText(cursor.getString(4));
                mEmail.setText(cursor.getString(5));


            } while (cursor.moveToNext());
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseTable.close();
        Log.d(TAG,"Dashboard onDestroy()");
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.dashboard_btn_logout:
                logout();
                break;
        }
    }

    private void logout() {
        Utils.navigateTo(this,Login.class);
    }
}
