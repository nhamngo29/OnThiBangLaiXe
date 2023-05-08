package com.example.OnThiBangLaiXe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import c.e.O.custom.MySharedPreferences;

public class SplashActivity extends AppCompatActivity {

    private static final String KEY_FIRST_INSTALL="KEY_FIRST_INSTALL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        MySharedPreferences mySharedPreferences=new MySharedPreferences(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mySharedPreferences.getBooleanValue(KEY_FIRST_INSTALL))
                {
                    startActivity(MainActivity.class);
                }
                else {
                    startActivity(OnBoardingActivity.class);
                    mySharedPreferences.putBooleanValue(KEY_FIRST_INSTALL,true);
                }
            }
        },50);
    }
    private void startActivity(Class<?> cls)
    {
        Intent intent=new Intent(this,cls);
        startActivity(intent);
        finish();
    }
}