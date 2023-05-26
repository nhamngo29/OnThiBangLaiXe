package com.example.OnThiBangLaiXe;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.Adapter.DeThiAdapter;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.example.OnThiBangLaiXe.Model.DeThi;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

public class DeThiActivity extends AppCompatActivity {
    List<DeThi> dsDeThi;
    public static DeThiAdapter dtAdapter;

    Toolbar toolbarBack;
    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_thi);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        dsDeThi=DanhSach.getDsDeThi();
        toolbarBack =findViewById(R.id.toolbarBack);
        dtAdapter = new DeThiAdapter(dsDeThi, this);
        RecyclerView rv = findViewById(R.id.rvDeThi);
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setAdapter(dtAdapter);
        TextView txtTilte = findViewById(R.id.txtTitle);
        init();
        event();
    }
    void event()
    {
        toolbarBack.setNavigationOnClickListener(view -> onBackPressed());
    }
    void init()
    {
        toolbarBack =findViewById(R.id.toolbarBack);
    }

}