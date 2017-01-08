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

public class Login extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG="Login";
    EditText mMobile,mPwd;
    Button mSignIn,mSingUp;
    LoginDataBaseTable loginDataBaseTable;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        Log.d(TAG,"Login onCreate()");
        loginDataBaseTable = new LoginDataBaseTable(this);
        loginDataBaseTable=loginDataBaseTable.open();
        init();
    }

    private void init() {
        Log.d(TAG,"Login init()");
        mMobile=(EditText)findViewById(R.id.eTLogin);
        mPwd=(EditText)findViewById(R.id.eTPassword);
        mSignIn=(Button)findViewById(R.id.login_btn_signIn);
        mSingUp=(Button)findViewById(R.id.login_btn_signUp);
        mSignIn.setOnClickListener(this);
        mSingUp.setOnClickListener(this);
    }

    private void credentialsCheck() {
        Log.d(TAG,"Login credentialsCheck()");
        if(Utils.passwordCheck(loginDataBaseTable,mMobile.getText().toString(),mPwd.getText().toString())){
            Log.d(TAG,"Login passwordCheck()");
            Toast.makeText(this,"Successfully Login",Toast.LENGTH_SHORT).show();
            Utils.navigateTo1(Login.this,Dashboard.class,mPwd.getText().toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Login onDestroy()");
        loginDataBaseTable.close();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.login_btn_signIn:
                credentialsCheck();
                break;
            case R.id.login_btn_signUp:
                Utils.navigateTo(this,Registration.class);
                break;
        }
    }
}
