package com.example.OnThiBangLaiXe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.Adapter.TheLoaiCauHoiAdapter;
import com.example.OnThiBangLaiXe.Interface.RecyclerViewInterface;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.example.OnThiBangLaiXe.Model.LoaiCauHoi;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RecyclerViewInterface {
    NavigationView navView;
    LinearLayout loBienBao, loCauSai, loFb, loSaHinh, loMeo, loTienDoOnTap, loThiThu, lo_save,lo_HaySai;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ArrayList<function> arrayList;
    DBHandler dbHandler;
    List<LoaiCauHoi> dsLoaiCauHoi = new ArrayList<>();
    static TheLoaiCauHoiAdapter tlchAdapter;
    static ProgressBar pbTienDo;
    static TextView txtSoCau, txtKetQua, txtSafety;
    TextView txtCauSai, txtLuu, txtThiThu,txtHaySai;
    static private int luu, sai,haysai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println(DanhSach.getLoaiBang());
        khoiTaoControl();
        dbHandler = new DBHandler(this);
        loadDBToDanhSach();
        setSupportActionBar(toolbar);
        navView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setCheckedItem(R.id.item_Home);
        khoiTaoSuKien();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.item_HoTro:
                        Uri number = Uri.parse("tel:0336669999");
                        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                        startActivity(callIntent);
                        return true;
                    case R.id.item_OnThiLyThuyet:
                        loTienDoOnTap.callOnClick();
                        return true;
                    case R.id.item_ThongTin:
                        Intent init = new Intent(MainActivity.this, WebActivity.class);
                        init.putExtra("URL", "file:///android_asset/html/ChinhSach.html");
                        init.putExtra("Name", "Sa hình");
                        startActivity(init);
                        return true;
                    case R.id.item_Home:
                        drawer.closeDrawers();
                        navView.isLaidOut();
                        return true;
                    case R.id.item_BienBao:
                        loBienBao.callOnClick();
                        return true;
                    case R.id.item_Meo:
                        loMeo.callOnClick();
                        return true;
                    case R.id.item_SaHinh:
                        loSaHinh.callOnClick();
                        return true;
                    case R.id.item_loaiBang:
                        startActivity(new Intent(MainActivity.this, SplashActivity.class));
                        MainActivity.this.finish();
                        return true;
                }
                return false;
            }
        });
        RecyclerView rv = findViewById(R.id.rvTheLoaiCauHoi);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(tlchAdapter);

        pbTienDo = findViewById(R.id.pbTheLoaiCauHoi);
        txtSoCau = findViewById(R.id.txtSoCau);
        txtKetQua = findViewById(R.id.txtKetQua);
        txtSafety = findViewById(R.id.txtSafety);
        setProgress();

        txtThiThu = findViewById(R.id.txtThiThu);
        txtCauSai = findViewById(R.id.txtCauSai);
        txtLuu = findViewById(R.id.txtLuu);
        txtHaySai=findViewById(R.id.txtHaySai);
        txtThiThu.setText(DanhSach.getDsDeThi().size() - 1 + " đề");

        luu = dbHandler.docCauHoiLuu().size();
        txtLuu.setText(luu + " câu");
        sai = dbHandler.docCauHoiSai().size();
        txtCauSai.setText(sai + " câu");
        haysai=dbHandler.docCauHaySai().size();
        txtHaySai.setText(haysai+" câu");
    }

    public static void setProgress() {
        pbTienDo.setMax(DanhSach.getDsCauHoi().size());

        int progess = 0;
        int correct = 0;

        for (CauHoi ch : DanhSach.getDsCauHoi()) {
            if (ch.getDaTraLoiDung() != 0) {
                if (ch.getDaTraLoiDung() == 1) {
                    correct++;
                }

                progess++;
            }
        }

        pbTienDo.setProgress(progess);
        txtSoCau.setText(progess + "/" + DanhSach.getDsCauHoi().size() + " câu");
        txtKetQua.setText(correct + " câu đúng, " + (progess - correct) + " câu sai");

        if (progess == 0) {
            txtSafety.setText("0%");
        } else {
            txtSafety.setText((int) ((correct / (float) progess) * 100) + "%");
        }
    }

    private void loadDBToDanhSach() {
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
        tlchAdapter = new TheLoaiCauHoiAdapter(dsLoaiCauHoi, this, this);
    }

    private void khoiTaoSuKien() {
        loBienBao.setOnClickListener(view -> {
            Intent init = new Intent(this, BienBaoActivity.class);
            startActivity(init);
        });
        loFb.setOnClickListener(view -> {
            Intent intent = new Intent();
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
            Intent init = new Intent(this, WebActivity.class);
            init.putExtra("URL", "file:///android_asset/html/tips600.html");
            init.putExtra("Name", "Mẹo ôn thi");
            startActivity(init);
        });
        loThiThu.setOnClickListener(view -> {
            Intent init = new Intent(this, DeThiActivity.class);
            startActivity(init);
        });
        loTienDoOnTap.setOnClickListener(view -> {
            Intent intent = new Intent(this, CauHoiActivity.class);
            startActivity(intent);
        });

        loCauSai.setOnClickListener(view -> {
            if (sai != 0) {
                Intent intent = new Intent(this, CauSaiActivity.class);
                startActivity(intent);
            } else {
                dailog("Thông báo", "Bạn chưa có câu sai nào.!");
            }
        });


        lo_save.setOnClickListener(view -> {
            if (luu != 0) {
                Intent intent = new Intent(this, CauLuuActivity.class);
                startActivity(intent);
            } else {
                dailog("Thông báo", "Bạn chưa có câu hỏi nào đã lưu.!");
            }
        });
        lo_HaySai.setOnClickListener(view -> {
            if (haysai != 0) {
                Intent intent = new Intent(this, HaySaiActivity.class);
                startActivity(intent);
            } else {
                dailog("Thông báo", "Bạn chưa có câu hỏi.!");
            }
        });
    }

    private void khoiTaoControl() {
        drawerLayout = findViewById(R.id.drawerlayout);
        navView = findViewById(R.id.nav_Main);
        toolbar = findViewById(R.id.toolbar);
        arrayList = new ArrayList<>();
        loBienBao = findViewById(R.id.lo_BienBao);
        loFb = findViewById(R.id.lo_fb);
        loSaHinh = findViewById(R.id.lo_sahinh);
        loMeo = findViewById(R.id.lo_meo);
        loThiThu = findViewById(R.id.loThiThu);
        loTienDoOnTap = findViewById(R.id.layout_tienDoOnTap);
        loCauSai = findViewById(R.id.loCauSai);
        lo_save = findViewById(R.id.lo_save);
        lo_HaySai=findViewById(R.id.lo_HaySai);
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
    protected void onResume() {
        super.onResume();
        txtThiThu.setText(DanhSach.getDsDeThi().size() - 1 + " đề");
        luu = dbHandler.docCauHoiLuu().size();

        txtLuu.setText(luu + " câu");
        sai = dbHandler.docCauHoiSai().size();

        txtCauSai.setText(sai + " câu");
    }

    void dailog(String Titel, String Content) {

        View alerCustomDailog = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_alter_dailog, null);
        AlertDialog.Builder alertDailog = new AlertDialog.Builder(MainActivity.this);
        alertDailog.setView(alerCustomDailog);
        Button btn_ok;
        TextView tvTitlte, tvContent;
        btn_ok = alerCustomDailog.findViewById(R.id.btn_ok);
        tvTitlte = alerCustomDailog.findViewById(R.id.tv_title);
        tvContent = alerCustomDailog.findViewById(R.id.tv_content);
        tvTitlte.setText(Titel);
        tvContent.setText(Content);
        final AlertDialog dialog = alertDailog.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        btn_ok.setOnClickListener(view -> dialog.cancel());
    }
}