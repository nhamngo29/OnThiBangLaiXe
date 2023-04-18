package com.example.OnThiBangLaiXe;

import static com.example.OnThiBangLaiXe.Model.DanhSach.getDsBienBao;
import static com.example.OnThiBangLaiXe.Model.DanhSach.setDsBienBao;
import static com.example.OnThiBangLaiXe.Model.DanhSach.setDsLoaiBienBao;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;
import com.example.OnThiBangLaiXe.Adapter.LoaiBienBaoAdapter;
import com.example.OnThiBangLaiXe.Model.BienBao;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BienBaoActivity extends AppCompatActivity {
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toolbar toolbarBienBao;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bien_bao);
        db=new DBHandler(this);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        toolbarBienBao = findViewById(R.id.toolbarBienBao);
        toolbarBienBao.setNavigationOnClickListener(view -> onBackPressed());
        ViewPager2 vp = findViewById(R.id.vp);
        LoaiBienBaoAdapter lbbAdapter = new LoaiBienBaoAdapter(DanhSach.getDsLoaiBienBao(), this, getDsBienBao());
        vp.setAdapter(lbbAdapter);
        new TabLayoutMediator(tabLayout, vp, (tab, position)
                -> tab.setText(DanhSach.getDsLoaiBienBao().get(position).getTenLoaiBB())).attach();
    }
}