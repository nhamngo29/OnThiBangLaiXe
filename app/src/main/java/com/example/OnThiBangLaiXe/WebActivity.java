package com.example.OnThiBangLaiXe;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class WebActivity extends AppCompatActivity {
    WebView wb;
    Toolbar toolbarBack;
    WebSettings myWebSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meo);
        wb = findViewById(R.id.wbMeo);
        myWebSettings=wb.getSettings();
        myWebSettings.setJavaScriptEnabled(true);
        myWebSettings.setDomStorageEnabled(true);
        wb.loadUrl(getIntent().getStringExtra("URL"));
        wb.setWebViewClient(new WebViewClient());
        toolbarBack=(Toolbar) findViewById(R.id.toolbarBack);
        toolbarBack.setNavigationOnClickListener(view -> onBackPressed());

    }
}