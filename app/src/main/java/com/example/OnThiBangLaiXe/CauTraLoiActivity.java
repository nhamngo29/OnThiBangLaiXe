package com.example.OnThiBangLaiXe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.OnThiBangLaiXe.Adapter.CauHoiAdapter;
import com.example.OnThiBangLaiXe.Adapter.CauTraLoiAdapter;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.CauTraLoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class CauTraLoiActivity extends AppCompatActivity {
    ViewPager2 vp;
    TextView txtTitle;
    TabLayout tabLayout;
    BottomNavigationView bnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi);

        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("Đề thi thử");
        bnv=findViewById(R.id.bottomNavigationView);
        // Mã loại câu hỏi
        int maDeThi = getIntent().getIntExtra("MaDeThi", 0);

        vp = findViewById(R.id.vp);

        List<CauTraLoi> dsCauTraLoi = new ArrayList<>();
        dsCauTraLoi.add(new CauTraLoi(1,1,"A"));
        dsCauTraLoi.add(new CauTraLoi(1,1,"A"));
        dsCauTraLoi.add(new CauTraLoi(1,1,"A"));
        dsCauTraLoi.add(new CauTraLoi(1,1,"A"));

        // Thêm vòng lặp hoặc phương thức để lấy ds câu hỏi của loại câu hỏi này ra

        vp.setAdapter(new CauTraLoiAdapter(dsCauTraLoi, this));
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setVisibility(View.VISIBLE);

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
    }
}