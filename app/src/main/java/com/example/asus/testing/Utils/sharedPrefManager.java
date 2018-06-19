package com.example.asus.testing.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class sharedPrefManager {
    public static final String NAMA_APLIKASI="MySchool";
    public static final String CEK_LOGIN="cekLogin";
    public static final String Username="username";
    public static final String Password="password";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public sharedPrefManager(Context context) {

        sharedPreferences=context.getSharedPreferences(NAMA_APLIKASI,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void setUsername(String key,String value){
        editor.putString(key,value);
        editor.commit();
    }

    public void setPassword(String key,String value){
        editor.putString(key,value);
        editor.commit();
    }

    public void setCekLogin(String key,boolean value){
        editor.putBoolean(key,value);
        editor.commit();
    }

    public Boolean getCekLogin() {
        return sharedPreferences.getBoolean(CEK_LOGIN,false);
    }

    public String getUsername() {
        return sharedPreferences.getString(CEK_LOGIN,"admin");
    }

    public String getPassword() {
        return sharedPreferences.getString(CEK_LOGIN,"1234");
    }
}
