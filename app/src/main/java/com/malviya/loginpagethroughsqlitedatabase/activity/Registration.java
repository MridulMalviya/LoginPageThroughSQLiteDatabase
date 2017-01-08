package com.malviya.loginpagethroughsqlitedatabase.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.malviya.loginpagethroughsqlitedatabase.R;
import com.malviya.loginpagethroughsqlitedatabase.database.LoginDataBaseTable;
import com.malviya.loginpagethroughsqlitedatabase.utils.Utils;

/**
 * Created by Prafulla on 1/8/2017.
 */

public class Registration extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG="Registration";
    EditText mUserName,mPassword,mEmail,mAddress,mMobile;
    Button mSave;
    LoginDataBaseTable loginDataBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registartion_layout);
        Log.d(TAG,"Registartion onCreate()");

        // create a instance of SQLite Database
        loginDataBase = new LoginDataBaseTable(this);
        loginDataBase=loginDataBase.open();

        init();
    }

    private void init() {
        Log.d(TAG,"Registartion init()");
        mUserName=(EditText)findViewById(R.id.reg_eT_userName);
        mPassword=(EditText)findViewById(R.id.reg_eT_passwordName);
        mMobile=(EditText)findViewById(R.id.reg_eT_mobileNo);
        mAddress=(EditText)findViewById(R.id.reg_eT_address);
        mEmail=(EditText)findViewById(R.id.reg_eT_email);

        // Get The Reference Of Buttons
        mSave=(Button) findViewById(R.id.reg_btn_save);
        mSave.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Registartion onDestroy()");
        // Close The Database
        loginDataBase.close();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.reg_btn_save:
                saveData();
                break;
        }
    }

    private void saveData() {
        Log.d(TAG,"Registartion saveData()");
       if(loginDataBase.insertRegistrationData(mUserName.getText().toString(),mPassword.getText().toString(),mMobile.getText().toString(),mAddress.getText().toString(),mEmail.getText().toString())){
           Toast.makeText(this,"Registration is successfully done",Toast.LENGTH_SHORT).show();
           Utils.navigateTo(Registration.this,Login.class);
        }
    }
}
