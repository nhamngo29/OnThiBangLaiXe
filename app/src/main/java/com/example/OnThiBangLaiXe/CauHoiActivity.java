package com.example.OnThiBangLaiXe;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.OnThiBangLaiXe.Adapter.CauHoiAdapter;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class CauHoiActivity extends AppCompatActivity {
    ViewPager2 vp;
    TextView txtTitle;
    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi);

        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("Câu hỏi ôn thi");

        // Mã loại câu hỏi
        int maLoaiCH = getIntent().getIntExtra("MaLoaiCH", 0);

        bnv = findViewById(R.id.bottomNavigationView);

        vp = findViewById(R.id.vp);

        List<CauHoi> dsCauHoi = new ArrayList<>();

        CauHoi ch = new CauHoi();
        ch.setNoiDung("Đây là nội dung");
        ch.setDapAnA("Đây là đáp án A");
        ch.setDapAnB("Đây là đáp án B");
        ch.setDapAnC("Đây là đáp án C");
        ch.setDapAnDung("B");
        ch.setGiaiThich("Đây là giải thich");

        dsCauHoi.add(ch);
        dsCauHoi.add(ch);
        dsCauHoi.add(ch);

        vp.setAdapter(new CauHoiAdapter(dsCauHoi, this));


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
                    if (vp.getCurrentItem() < dsCauHoi.size() - 1)
                    {
                        vp.setCurrentItem(vp.getCurrentItem() + 1, true);
                    }
            }
            return false;
        });

        menu.setGroupCheckable(0, false, true);

        // Thêm vòng lặp hoặc phương thức để lấy ds câu hỏi của loại câu hỏi này ra
    }
}