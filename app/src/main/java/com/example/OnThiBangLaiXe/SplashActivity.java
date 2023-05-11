package com.example.OnThiBangLaiXe;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import c.e.O.custom.MyDB;
import c.e.O.custom.MySharedPreferences;

public class SplashActivity extends AppCompatActivity {

    private static final String KEY_FIRST_INSTALL="KEY_FIRST_INSTALL";
    public static ProgressDialog progressDialog;
    String DB_PATH_SUFFIX="/databases/";

    String DATABASE_NAME= "db.db";
    MyDB myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        processCopy();
        myDB=new MyDB(this);
        MySharedPreferences mySharedPreferences=new MySharedPreferences(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mySharedPreferences.getBooleanValue(KEY_FIRST_INSTALL))
                {
                    Log.e("SQL","123");
                    if(isNetworkConnected()) {
                        if (myDB.kiemTraPhienBan())
                        {
                            startActivity(MainActivity.class);
                        }
                    }

                }
                else {
                    if(isNetworkConnected())
                    {
                        myDB.kiemTraPhienBan();
                        startActivity(OnBoardingActivity.class);
                        mySharedPreferences.putBooleanValue(KEY_FIRST_INSTALL,true);
                    }else
                    {
                        Toast.makeText(SplashActivity.this, "vui lòng kết nội mạng để tải dữ liệu về sau khi tại về bạn có thể ngắt mạng để sự dụng ứng dụng.!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        },50);

    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni != null && ni.isConnected()) {
            return true;
        }
        else {
            return false;
        }
    }
    private void startActivity(Class<?> cls)
    {
        Intent intent=new Intent(this,cls);
        startActivity(intent);
        finish();
    }
    private void processCopy() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists())
        {
            try{
                CopyDataBaseFromAsset();
                Log.e("SQL","Đã Coppy đến database");
            }
            catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
        Log.e("SQL","Đã tồn tại");
    }
    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+ DATABASE_NAME;
    }
    //Coppy db vao database cua mays
    public void CopyDataBaseFromAsset() {

        try {
            InputStream myInput;
            myInput = getAssets().open(DATABASE_NAME);
            String outFileName = getDatabasePath();
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();
            OutputStream myOutput = new FileOutputStream(outFileName);
            int size = myInput.available();
            byte[] buffer = new byte[size];
            myInput.read(buffer);
            myOutput.write(buffer);
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}