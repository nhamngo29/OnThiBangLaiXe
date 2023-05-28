package com.example.OnThiBangLaiXe;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.OnThiBangLaiXe.Adapter.CauTraLoiAdapter;
import com.example.OnThiBangLaiXe.Adapter.menuCauHoiAdapter;
import com.example.OnThiBangLaiXe.Model.CauTraLoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class ThiThuActivity extends AppCompatActivity {
    public static ViewPager2 vp;
    public static RecyclerView rvCauHoi;
    private CountDownTimer countDownTimer;
//    private long time=1140000;//19 phút
    private long time = 1200000;//10s dung de test
    TextView txtTitle,txtNopBai;
    private int maDeThi;
    TabLayout tabLayout;
    Toolbar toolbarBack;
    BottomNavigationView bnv;
    private DBHandler db;
    public static List<CauTraLoi> dsCauTraLoi;
    public static CauTraLoiAdapter ctlApdater;
    public static menuCauHoiAdapter menuAdapter;
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
        txtNopBai=findViewById(R.id.txtThiLai);
        txtNopBai.setVisibility(View.VISIBLE);
        txtNopBai.setText("Nộp bài");
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
        toolbarBack.setNavigationOnClickListener(view -> ThiThuActivity.this.onBackPressed());
        // Thêm vòng lặp hoặc phương thức để lấy ds câu hỏi của loại câu hỏi này ra

        ctlApdater = new CauTraLoiAdapter(dsCauTraLoi, this, false);
        vp.setAdapter(ctlApdater);
        tabLayout = findViewById(R.id.tab_layout);

        menuAdapter = new menuCauHoiAdapter(dsCauTraLoi, this);
        rvCauHoi.setAdapter(menuAdapter);
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
            }
            return false;
        });

        menu.setGroupCheckable(0, false, true);

        new TabLayoutMediator(tabLayout, vp, (tab, position)
                -> tab.setText("Câu " + (position + 1))).attach();

        txtNopBai.setVisibility(View.VISIBLE);
        txtNopBai.setOnClickListener(view -> nopBai());
    }

    private void ketThuc()
    {
        List<CauTraLoi> temp = new ArrayList<>();

        for (CauTraLoi ctl : DanhSach.getDsCauTraLoi())
        {
            if(ctl.getMaDeThi() == maDeThi)
            {
                if(ctl.getDapAnChon()==null||ctl.getDapAnChon().equals("null"))
                {
                    ctl.setDapAnChon("0");
                }
                temp.add(ctl);
            }
        }

        if (maDeThi != 0)
        {
            db.updateCauTraLoi(temp);
            DeThiActivity.dtAdapter.notifyDataSetChanged();
        }

        Intent intent = new Intent(this, KetQuaActivity.class);
        intent.putExtra("MaDeThi", maDeThi);
        startActivity(intent);
        finish();
    }

    private void nopBai()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ThiThuActivity.this);
        alertDialog.setTitle("Thông báo");
        alertDialog.setMessage("Bạn có chắn chắn muốn nộp bài không ?");
        alertDialog.setPositiveButton("Có", (dialogInterface, i) -> ketThuc());
        alertDialog.setNegativeButton("Không", (dialogInterface, i) -> {});
        alertDialog.show();
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
                final AlertDialog.Builder alertDialog=new AlertDialog.Builder(ThiThuActivity.this);
                alertDialog.setTitle("Thông báo");
                alertDialog.setMessage("Hết giờ");
                alertDialog.setPositiveButton("Xem kết quả", (dialogInterface, i) -> ketThuc());
                alertDialog.show();
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(ThiThuActivity.this);
        alertDialog.setTitle("Thông báo");
        alertDialog.setMessage("Dữ liệu bài thi đang làm sẽ không được lưu lại, bạn có chắc chắn muốn thoát?");
        alertDialog.setPositiveButton("Có", (dialogInterface, i) -> {
            countDownTimer.cancel();
            finish();
        });
        alertDialog.setNegativeButton("Không", (dialogInterface, i) -> {});
        alertDialog.show();
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