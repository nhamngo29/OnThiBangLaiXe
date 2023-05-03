package com.example.OnThiBangLaiXe;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.example.OnThiBangLaiXe.Adapter.CauHoiAdapter;
import com.example.OnThiBangLaiXe.Adapter.CauTraLoiAdapter;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.CauTraLoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ChiTietKetQuaActivity extends AppCompatActivity {
    public ViewPager2 vp;
    TextView txtTitle;
    BottomNavigationView bnv;
    Toolbar toolbarBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi);
        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("Câu hỏi ôn thi");
        toolbarBack =findViewById(R.id.toolbarBack);

        // Mã loại câu hỏi
        int maLoaiCH = getIntent().getIntExtra("MaLoaiCH", 1);
        bnv = findViewById(R.id.bottomNavigationView);

        vp = findViewById(R.id.vp);
        DBHandler db = new DBHandler(this);
        List<CauTraLoi> dsCauTraLoi = new ArrayList<>();

        int maDeThi = getIntent().getIntExtra("MaDeThi", 0);
        for (CauTraLoi ctl:DanhSach.getDsCauTraLoi())
        {
            if(ctl.getMaDeThi()==maDeThi)
                dsCauTraLoi.add(new CauTraLoi(ctl.getMaDeThi(),ctl.getMaCH(),null));
        }

        vp.setAdapter(new CauTraLoiAdapter(dsCauTraLoi, this, true));
        toolbarBack.setNavigationOnClickListener(view -> onBackPressed() );

        Menu menu = bnv.getMenu();
        MenuItem menuItem = menu.findItem(R.id.tiSummit);
        menuItem.setVisible(false);

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
            }
            return false;
        });

        menu.setGroupCheckable(0, false, true);

        // Thêm vòng lặp hoặc phương thức để lấy ds câu hỏi của loại câu hỏi này ra
    }

    @Override
    public void onBackPressed() {
        MainActivity.tlchAdapter.notifyDataSetChanged();
        super.onBackPressed();
    }
}