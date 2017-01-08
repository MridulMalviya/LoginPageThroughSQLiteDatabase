package com.malviya.loginpagethroughsqlitedatabase.utils;

import android.app.Activity;
import android.content.Intent;

import com.malviya.loginpagethroughsqlitedatabase.constant.Constants;
import com.malviya.loginpagethroughsqlitedatabase.database.LoginDataBaseTable;

/**
 * Created by Prafulla on 1/8/2017.
 */

public class Utils {
    public static void navigateTo(Activity pActivity,Class pClass){
        Intent intent = new Intent(pActivity,pClass);
        pActivity.startActivity(intent);
        pActivity.finish();
    }
    public static boolean passwordCheck(LoginDataBaseTable loginDataBaseTable,String mobile, String password){
        String pwd =loginDataBaseTable.getPassword(mobile);
        return password.equals(pwd);
    }

    public static void navigateTo1(Activity pActivity,Class pClass,String pwd){
        Intent intent = new Intent(pActivity,pClass);
        intent.putExtra(Constants.PASSWORD,pwd);
        pActivity.startActivity(intent);
        pActivity.finish();
    }
}
