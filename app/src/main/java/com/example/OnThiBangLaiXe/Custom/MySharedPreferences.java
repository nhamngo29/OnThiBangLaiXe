package com.example.OnThiBangLaiXe.Custom;

import android.content.Context;
import android.content.SharedPreferences;

//đây sẽ là lưu nhưng lần lại thông tin khi người dùng install
public class MySharedPreferences {
    private static final String MY_SHARED_PREFERENCES="MY_SHARED_PREFERENCES";
    private Context mcontext;

    public MySharedPreferences(Context mcontext) {
        this.mcontext = mcontext;
    }
    //SHARED_PREFERENCES lưu dưới dạng key value nếu mà ông muốn lưu cái khác kiểu thì tạo ra cái mới
    public void putBooleanValue(String key,boolean value)
    {
        SharedPreferences sharedPreferences= mcontext.getSharedPreferences(MY_SHARED_PREFERENCES,0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
    public Boolean getBooleanValue(String key)
    {
        SharedPreferences sharedPreferences= mcontext.getSharedPreferences(MY_SHARED_PREFERENCES,0);
        return sharedPreferences.getBoolean(key,false);
    }
    public void puttIntValue(String key,int value)
    {
        SharedPreferences sharedPreferences= mcontext.getSharedPreferences(MY_SHARED_PREFERENCES,0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(key,value);
        editor.apply();
    }
    public int getIntValue(String key)
    {
        SharedPreferences sharedPreferences= mcontext.getSharedPreferences(MY_SHARED_PREFERENCES,0);
        return sharedPreferences.getInt(key,0);
    }
}
