package com.example.OnThiBangLaiXe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.OnThiBangLaiXe.Adapter.CauHoiAdapter;
import com.example.OnThiBangLaiXe.Adapter.CauTraLoiAdapter;
import com.example.OnThiBangLaiXe.Adapter.menuCauHoiAdapter;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.CauTraLoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class CauTraLoiActivity extends AppCompatActivity {
    public static ViewPager2 vp;
    public static RecyclerView rvCauHoi;
    private CountDownTimer countDownTimer;
//    private long time=1140000;//19 phút
    private long time=10000;//10s dung de test
    TextView txtTitle,txtNopBai;

    TabLayout tabLayout;
    Toolbar toolbarBack;
    BottomNavigationView bnv;
    private DBHandler db;
    int maDeThi;
    private List<CauTraLoi> dsCauTraLoi;

    @Override
    protected void onStart() {
        super.onStart();
        startTime();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi);
        txtTitle = findViewById(R.id.txtTitle);
        txtNopBai=findViewById(R.id.txtNopBai);
        txtNopBai.setVisibility(View.VISIBLE);
        bnv=findViewById(R.id.bottomNavigationView);
        toolbarBack =findViewById(R.id.toolbarBack);
        // Mã loại câu hỏi
        maDeThi = getIntent().getIntExtra("MaDeThi", 0);
        db=new DBHandler(this);
        vp = findViewById(R.id.vp);
        rvCauHoi = findViewById(R.id.rvCauHoi);
        dsCauTraLoi=new ArrayList<>();
        for (CauTraLoi ctl:DanhSach.getDsCauTraLoi())
        {
            if(ctl.getMaDeThi()==maDeThi)
                dsCauTraLoi.add(new CauTraLoi(ctl.getMaDeThi(),ctl.getMaCH(),null));
        }
        toolbarBack.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(CauTraLoiActivity.this);
                alertDialog.setTitle("Thông báo");
                alertDialog.setMessage("Dữ liệu bài thi đang làm sẽ không được lưu lại,bạn có chắc chắn muốn thoát?");
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onBackPressed();
                    }
                });
                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.show();
            }
        });
        // Thêm vòng lặp hoặc phương thức để lấy ds câu hỏi của loại câu hỏi này ra

        vp.setAdapter(new CauTraLoiAdapter(dsCauTraLoi, this));
        tabLayout = findViewById(R.id.tab_layout);

        rvCauHoi.setAdapter(new menuCauHoiAdapter(dsCauTraLoi, this));
        rvCauHoi.setLayoutManager(new GridLayoutManager(this, 15));

        Menu menu = bnv.getMenu();

        bnv.setOnNavigationItemSelectedListener(item ->
        {
            switch (item.getItemId())
            {
                case R.id.tiBack:
                    if (vp.getCurrentItem() > 0)
                    {
                        vp.setCurrentItem(vp.getCurrentItem() - 1, true);
                    }
                    break;
                case R.id.tiForward:
                    if (vp.getCurrentItem() < dsCauTraLoi.size() - 1)
                    {
                        vp.setCurrentItem(vp.getCurrentItem() + 1, true);
                    }
                    break;
                case R.id.tiSummit:
                    // TODO
            }
            return false;
        });

        menu.setGroupCheckable(0, false, true);

//        new TabLayoutMediator(tabLayout, vp, (tab, position)
//                -> tab.setText(dsCauTraLoi.get(position).getTenLoaiBB())).attach();
        txtNopBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(CauTraLoiActivity.this);
                alertDialog.setTitle("Thông báo");
                alertDialog.setMessage("Bạn có chắn chắn muốn nộp bài không ?");
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Kết quả
                        NoiBai();

                    }
                });
                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.show();
            }
        });
    }
    void startTime()
    {
        countDownTimer=new CountDownTimer(time,1000) {
            @Override
            public void onTick(long l) {
                time=l;
                updateTime();
            }

            @Override
            public void onFinish() {
                txtTitle.setText("Hết giờ");
                final AlertDialog.Builder alertDialog=new AlertDialog.Builder(CauTraLoiActivity.this);
                alertDialog.setTitle("Thông báo");
                alertDialog.setMessage("Hết giờ");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Kết quả
                        NoiBai();
                    }
                });
                alertDialog.show();

            }
        }.start();
    }
    void NoiBai()
    {
        countDownTimer.cancel();
        db.updateCauTraLoi(dsCauTraLoi);
        Intent intent=new Intent(CauTraLoiActivity.this,KetQuaActivity.class);
        intent.putExtra("MaDeThi",maDeThi);

        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DeThiActivity.dtAdapter.notifyDataSetChanged();
        countDownTimer.cancel();
        startActivity(new Intent(CauTraLoiActivity.this,DeThiActivity.class));
    }

    void updateTime()
    {
        int minutes=(int)time/60000;
        int seconds=(int)time%60000/1000;
        String timeText;
        timeText=""+minutes;
        timeText+=":";
        if(seconds<10)
            timeText+="0";
        timeText+=seconds;
        txtTitle.setText(timeText);
    }
}