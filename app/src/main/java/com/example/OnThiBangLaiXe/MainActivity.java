package com.example.OnThiBangLaiXe;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.Adapter.TheLoaiCauHoiAdapter;
import com.example.OnThiBangLaiXe.Model.BienBao;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.example.OnThiBangLaiXe.Model.LoaiCauHoi;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
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
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DBHandler dbHandler;
    List<LoaiCauHoi> dsLoaiCauHoi = new ArrayList<>();
    List<BienBao> dsBienBao = new ArrayList<>();
    TheLoaiCauHoiAdapter tlchAdapter;
    DatabaseReference csdlVersion = database.getReference("Version");


    ValueEventListener vel;
    String DB_PATH_SUFFIX="/databases/";

    String DATABASE_NAME= "db.db";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        processCopy();
        khoiTaoControl();
        setSupportActionBar(toolbar);
        navView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setCheckedItem(R.id.item_Home);
        khoiTaoSuKien();
        dbHandler = new DBHandler(this);
        // Setup RecycleView
        dsLoaiCauHoi.add(new LoaiCauHoi(1, "ico_fire", "Câu hỏi điểm liệt"));
        dsLoaiCauHoi.add(new LoaiCauHoi(2, "ico_car", "Kỹ thuật lái xe"));
        dsLoaiCauHoi.add(new LoaiCauHoi(3, "ico_trafficligh", "Khái niệm và quy tăc"));
        dsLoaiCauHoi.add(new LoaiCauHoi(4, "ico_account", "Văn hóa và đạo đức"));
        dsLoaiCauHoi.add(new LoaiCauHoi(5, "ico_truck", "Nghiệp vụ vận tải"));

        tlchAdapter = new TheLoaiCauHoiAdapter(dsLoaiCauHoi, this);

        RecyclerView rv = findViewById(R.id.rvTheLoaiCauHoi);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(tlchAdapter);
        if(isNetworkConnected()) {
            kiemTraPhienBan();
            Log.d("Nam", "Connect Internet");
        }
        else {
            DanhSach.setDsLoaiBienBao(dbHandler.docLoaiBienBao());
            DanhSach.setDsBienBao(dbHandler.docBienBao());
            Log.d("Nam", "No Internet");
        }

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
    private boolean kiemTraPhienBan()
    {
        final boolean[] isLastestVersion = {true};
        final int[] ver = {0};
        vel = csdlVersion.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                isLastestVersion[0] = dbHandler.isLastestVersion(snapshot.getValue(int.class));
                if (!isLastestVersion[0])
                {
                    Log.d("Firebase", "Is the lastest version");
                    dbHandler.UpdateVersion(snapshot.getValue(int.class));
                    capNhatDatabase();

                }
                else {
                    DanhSach.setDsLoaiBienBao(dbHandler.docLoaiBienBao());
                    DanhSach.setDsBienBao(dbHandler.docBienBao());
                    Log.d("Firebase", "Not is the lastest version");
                }
                stop();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                isLastestVersion[0] = true;
            }
        });

        return isLastestVersion[0];
    }

    private void stop()
    {
        csdlVersion.removeEventListener(vel);
    }

    private void capNhatDatabase()
    {
        DatabaseReference csdlLoaiCauHoi = database.getReference("LoaiCauHoi");
        //Đọc loại câu hỏi
        csdlLoaiCauHoi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i = 0; i < dataSnapshot.getChildrenCount(); i++)
                {
                    Log.e("Loai",i+"");
                    LoaiCauHoi tlch = dataSnapshot.child(String.valueOf(i)).getValue(LoaiCauHoi.class);

                    if (tlch != null)
                    {
                        boolean existed = false;

                        for (LoaiCauHoi check : dsLoaiCauHoi)
                        {
                            if (tlch.getMaLoaiCH() == check.getMaLoaiCH())
                            {
                                check.setTenLoaiCauHoi(tlch.getTenLoaiCauHoi());
                                Log.d("Firebase", "Value is existed: " + tlch.getMaLoaiCH());
                                existed = true;
                                break;
                            }
                        }

                        if (!existed)
                        {
                            DanhSach.setDsLoaiBienBao(dbHandler.docLoaiBienBao());
                            tlchAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference csdlBienBao = database.getReference("BienBao");
        //Đọc loại câu hỏi
        csdlBienBao.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i = 0; i < dataSnapshot.getChildrenCount(); i++)
                {
                    BienBao tlbb = dataSnapshot.child(String.valueOf(i)).getValue(BienBao.class);

                    if (tlbb != null)
                    {
                        if(dbHandler.findBBByID(tlbb.getMaBB()))
                        {
                            dbHandler.updateBB(tlbb);
                        }
                        else
                        {
                            dbHandler.insertBB(tlbb);
                        }
                        dsBienBao.add(tlbb);
                    }
                }
                DanhSach.setDsBienBao(dbHandler.docBienBao());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void khoiTaoSuKien()
    {
        loBienBao.setOnClickListener(view -> {
            Intent init = new Intent(this, BienBaoActivity.class);
            startActivity(init);
        });
        loFb.setOnClickListener(view -> {
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.facebook.com/nhamngoo.29/"));
            startActivity(intent);
        });
        loSaHinh.setOnClickListener(view -> {
            Intent init = new Intent(this, WebActivity.class);
            init.putExtra("URL", "file:///android_asset/html/practice_exam.html");
            init.putExtra("Name", "Sa hình");
            startActivity(init);
        });
        loMeo.setOnClickListener(view -> {
            Intent init=new Intent(this, WebActivity.class);
            init.putExtra("URL", "file:///android_asset/html/tips600.html");
            init.putExtra("Name", "Mẹo ôn thi");
            startActivity(init);
        });
    }
    private void khoiTaoControl()
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
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
    private void processCopy() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists())
        {
            try{CopyDataBaseFromAsset();
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