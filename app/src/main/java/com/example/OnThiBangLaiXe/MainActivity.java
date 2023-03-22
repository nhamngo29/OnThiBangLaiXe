package com.example.OnThiBangLaiXe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.function.Function;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView nav_view;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    GridView gvFuntion;
    ArrayList<function> arrayList;
    FunctionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        init();
        setSupportActionBar(toolbar);
        nav_view.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav_view.setCheckedItem(R.id.item_Home);
        Menu menu = nav_view.getMenu();
    }
    private void init()
    {
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        nav_view=(NavigationView) findViewById(R.id.nav_Main);
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        arrayList=new ArrayList<>();



    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Menu menu = nav_view.getMenu();
        Intent intent;
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}