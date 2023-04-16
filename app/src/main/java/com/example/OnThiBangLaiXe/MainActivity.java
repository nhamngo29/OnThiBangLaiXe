package com.example.OnThiBangLaiXe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.Adapter.TheLoaiCauHoiAdapter;
import com.example.OnThiBangLaiXe.Model.TheLoaiCauHoi;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navView;
    LinearLayout loBienBao;
    LinearLayout loFb;
    LinearLayout loSaHinh;
    LinearLayout loMeo;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ArrayList<function> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setSupportActionBar(toolbar);
        navView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setCheckedItem(R.id.item_Home);
        event();

        List<TheLoaiCauHoi> dsTheLoaiCauHoi = new ArrayList<>();
        dsTheLoaiCauHoi.add(new TheLoaiCauHoi("ico_fire", "Câu hỏi điểm liệt"));
        dsTheLoaiCauHoi.add(new TheLoaiCauHoi("ico_car", "Kỹ thuật lái xe"));
        dsTheLoaiCauHoi.add(new TheLoaiCauHoi("ico_trafficligh", "Khái niệm và quy tăc"));
        dsTheLoaiCauHoi.add(new TheLoaiCauHoi("ico_account", "Văn hóa và đạo đức"));
        dsTheLoaiCauHoi.add(new TheLoaiCauHoi("ico_truck", "Nghiệp vụ vận tải"));

        RecyclerView rv = findViewById(R.id.rvTheLoaiCauHoi);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new TheLoaiCauHoiAdapter(dsTheLoaiCauHoi, this));
    }
    private void event()
    {
        loBienBao.setOnClickListener(view -> {
            Intent init=new Intent(MainActivity.this,BienBaoActivity.class);
            startActivity(init);

        });
        loFb.setOnClickListener(view -> {
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.facebook.com/nhamngoo.29/"));
            startActivity(intent);
        });
        loSaHinh.setOnClickListener(view -> {
            Intent init=new Intent(MainActivity.this,SaHinhActivity.class);
            startActivity(init);
        });
        loMeo.setOnClickListener(view -> {
            Intent init=new Intent(MainActivity.this,MeoActivity.class);
            startActivity(init);
        });
    }
    private void init()
    {
        drawerLayout = findViewById(R.id.drawerlayout);
        navView = findViewById(R.id.nav_Main);
        toolbar = findViewById(R.id.toolbar);
        arrayList=new ArrayList<>();
        loBienBao = findViewById(R.id.lo_BienBao);
        loFb = findViewById(R.id.lo_fb);
        loSaHinh = findViewById(R.id.lo_sahinh);
        loMeo = findViewById(R.id.lo_meo);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Menu menu = navView.getMenu();
        Intent intent;
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}