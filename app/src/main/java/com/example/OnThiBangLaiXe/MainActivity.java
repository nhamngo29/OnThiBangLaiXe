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

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView nav_view;
    LinearLayout lo_BienBao,lo_fb,lo_sahinh,lo_meo;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    GridView gvFuntion;
    ArrayList<function> arrayList;
    FunctionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        init();
        setSupportActionBar(toolbar);
        nav_view.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav_view.setCheckedItem(R.id.item_Home);
        Menu menu = nav_view.getMenu();
        event();
    }
    private void event()
    {
        lo_BienBao.setOnClickListener(view -> {
            Intent init=new Intent(MainActivity.this,BienBaoActivity.class);
            startActivity(init);

        });
        lo_fb.setOnClickListener(view -> {
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.facebook.com/nhamngoo.29/"));
            startActivity(intent);
        });
        lo_sahinh.setOnClickListener(view -> {
//                Intent intent=new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://hoclaixe12h.com/meo-thi-sa-hinh-bang-lai-xe-may-a1/"));
//                startActivity(intent);
            Intent init=new Intent(MainActivity.this,SaHinhActivity.class);
            startActivity(init);
        });
        lo_meo.setOnClickListener(view -> {
            Intent init=new Intent(MainActivity.this,MeoActivity.class);
            startActivity(init);
        });
    }
    private void init()
    {
        drawerLayout = findViewById(R.id.drawerlayout);
        nav_view = findViewById(R.id.nav_Main);
        toolbar = findViewById(R.id.toolbar);
        arrayList=new ArrayList<>();
        lo_BienBao= findViewById(R.id.lo_BienBao);
        lo_fb= findViewById(R.id.lo_fb);
        lo_sahinh= findViewById(R.id.lo_sahinh);
        lo_meo= findViewById(R.id.lo_meo);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Menu menu = nav_view.getMenu();
        Intent intent;
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}