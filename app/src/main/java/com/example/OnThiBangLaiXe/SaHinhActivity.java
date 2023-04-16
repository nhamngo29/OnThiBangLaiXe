package com.example.OnThiBangLaiXe;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SaHinhActivity extends AppCompatActivity {

    WebView wb;
    Toolbar toolbarBack;
    WebSettings myWebSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sa_hinh);
        wb=(WebView) findViewById(R.id.wbSaHinh);
        myWebSettings=wb.getSettings();
        myWebSettings.setJavaScriptEnabled(true);
        myWebSettings.setDomStorageEnabled(true);
        wb.loadUrl("file:///android_asset/html/practice_exam.html");
        wb.setWebViewClient(new WebViewClient());
        toolbarBack = findViewById(R.id.toolbarBack);
        toolbarBack.setNavigationOnClickListener(view -> onBackPressed());
    }
}