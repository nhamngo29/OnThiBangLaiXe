package com.example.OnThiBangLaiXe;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.Adapter.TheLoaiCauHoiAdapter;
import com.example.OnThiBangLaiXe.Interface.RecyclerViewInterface;
import com.example.OnThiBangLaiXe.Model.BienBao;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.example.OnThiBangLaiXe.Model.LoaiCauHoi;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;
import java.util.List;

import c.e.O.custom.MyDB;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RecyclerViewInterface {
    NavigationView navView;
    LinearLayout loBienBao;
    LinearLayout loFb;
    LinearLayout loSaHinh;
    LinearLayout loMeo;
    LinearLayout loThiThu;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ArrayList<function> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DBHandler dbHandler;
    MyDB myDB;



    List<LoaiCauHoi> dsLoaiCauHoi = new ArrayList<>();
    List<BienBao> dsBienBao = new ArrayList<>();
    List<CauHoi> dsCauHoi=new ArrayList<>();
    static TheLoaiCauHoiAdapter tlchAdapter;
    DatabaseReference csdlVersion = database.getReference("Version");


    ValueEventListener vel;
    String DB_PATH_SUFFIX="/databases/";

    String DATABASE_NAME= "db.db";
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference=storage.getReference();
    static ProgressBar pbTienDo;
    static TextView txtSoCau;
    static TextView txtKetQua;
    static TextView txtSafety;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        myDB=new MyDB(getApplicationContext());
        khoiTaoControl();
        dbHandler = new DBHandler(this);
        if(isNetworkConnected()) {
            kiemTraPhienBan();
        }
        loadDBToDanhSach();

        setSupportActionBar(toolbar);
        navView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setCheckedItem(R.id.item_Home);
        khoiTaoSuKien();

        RecyclerView rv = findViewById(R.id.rvTheLoaiCauHoi);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(tlchAdapter);

        pbTienDo = findViewById(R.id.pbTheLoaiCauHoi);
        txtSoCau = findViewById(R.id.txtSoCau);
        txtKetQua = findViewById(R.id.txtKetQua);
        txtSafety = findViewById(R.id.txtSafety);
        setProgress();
    }
    public static void setProgress()
    {
        pbTienDo.setMax(DanhSach.getDsCauHoi().size());

        int progess = 0;
        int correct = 0;

        for (CauHoi ch : DanhSach.getDsCauHoi())
        {
            if (ch.getDaTraLoiDung() != 0)
            {
                if (ch.getDaTraLoiDung() == 1)
                {
                    correct++;
                }

                progess++;
            }
        }

        pbTienDo.setProgress(progess);
        txtSoCau.setText(progess + "/" + DanhSach.getDsCauHoi().size() + " câu");
        txtKetQua.setText(correct + " câu đúng, " + (progess - correct) + " câu sai");

        if (progess == 0)
        {
            txtSafety.setText("0%");
        }
        else
        {
            txtSafety.setText((int)((correct / (float) progess) * 100) + "%");
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
                    Log.e("Có phiên bản mới","");
                    myDB.capNhatDatabase();
                    myDB.downloadWithBytes("BienBao");
                    myDB.downloadWithBytes("CauHoi");
//                    capNhatDatabase();
//                    downloadWithBytes("BienBao");
//                    downloadWithBytes("CauHoi");
                    dbHandler.UpdateVersion(snapshot.getValue(int.class));
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
    //Load db vaof danh danh sach
    private void loadDBToDanhSach()
    {
        DanhSach.setDsCauHoi(dbHandler.docCauHoi());
        DanhSach.setDsBienBao(dbHandler.docBienBao());
        DanhSach.setDsLoaiBienBao(dbHandler.docLoaiBienBao());
        DanhSach.setDsDeThi(dbHandler.docDeThi());
        DanhSach.setDsCauTraLoi(dbHandler.docCauTraLoi());
        // Setup RecycleView
        dsLoaiCauHoi.add(new LoaiCauHoi(1, "ico_fire", "Câu hỏi điểm liệt"));
        dsLoaiCauHoi.add(new LoaiCauHoi(2, "ico_car", "Kỹ thuật lái xe"));
        dsLoaiCauHoi.add(new LoaiCauHoi(3, "ico_trafficligh", "Khái niệm và quy tăc"));
        dsLoaiCauHoi.add(new LoaiCauHoi(4, "ico_account", "Văn hóa và đạo đức"));
        dsLoaiCauHoi.add(new LoaiCauHoi(5, "ico_truck", "Nghiệp vụ vận tải"));
        tlchAdapter = new TheLoaiCauHoiAdapter(dsLoaiCauHoi, this,this);
    }
    private void stop()
    {
        csdlVersion.removeEventListener(vel);
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
        loThiThu.setOnClickListener(view -> {
            Intent init=new Intent(this, DeThiActivity.class);
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
        loThiThu=findViewById(R.id.loThiThu);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }


    @Override
    public void onItemClick(int postion) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    //sự kiên cho nav menu item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.item_HoTro)
        {
            Uri number =Uri.parse("tel:0336669999");
            Intent callIntent=new Intent(Intent.ACTION_DIAL,number);
            startActivity(callIntent);
        }
        if(id==R.id.item_OnThiLyThuyet)
        {
            //chuyển đến tất các các câu trong ôn thi

        }
        if(id==R.id.item_ThongTin)
        {
            //Thông tin ứng dụng
        }
        return super.onOptionsItemSelected(item);
    }
}