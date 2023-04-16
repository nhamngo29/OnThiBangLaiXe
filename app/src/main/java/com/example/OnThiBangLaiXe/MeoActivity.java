package com.example.OnThiBangLaiXe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.widget.Toolbar;

public class MeoActivity extends AppCompatActivity {
    WebView wb;
    Toolbar toolbarBack;
    WebSettings myWebSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meo);
        wb=(WebView) findViewById(R.id.wbMeo);
        myWebSettings=wb.getSettings();
        myWebSettings.setJavaScriptEnabled(true);
        myWebSettings.setDomStorageEnabled(true);
        wb.loadUrl("file:///android_asset/html/tips600.html");
        wb.setWebViewClient(new WebViewClient());
        toolbarBack=(Toolbar) findViewById(R.id.toolbarBack);
        toolbarBack.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}