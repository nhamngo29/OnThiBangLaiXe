package com.example.OnThiBangLaiXe;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.example.OnThiBangLaiXe.Adapter.CauHoiAdapter;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class CauHoiActivity extends AppCompatActivity {
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
        toolbarBack = findViewById(R.id.toolbarBack);

        // Mã loại câu hỏi
        int maLoaiCH = getIntent().getIntExtra("MaLoaiCH", 1);
        bnv = findViewById(R.id.bottomNavigationView);

        vp = findViewById(R.id.vp);
        DBHandler db = new DBHandler(this);
        List<CauHoi> dsCauHoi = new ArrayList<>();

        for(CauHoi a:DanhSach.getDsCauHoi())
        {

            if(a.getMaLoaiCH()==maLoaiCH)
            {
                dsCauHoi.add(a);
            }
        }


        vp.setAdapter(new CauHoiAdapter(dsCauHoi, this));
        toolbarBack.setNavigationOnClickListener(view -> onBackPressed() );

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
                    if (vp.getCurrentItem() < dsCauHoi.size() - 1)
                    {
                        vp.setCurrentItem(vp.getCurrentItem() + 1, true);
                    }
            }
            return false;
        });

        menu.setGroupCheckable(0, false, true);
    }

    @Override
    public void onBackPressed() {
        MainActivity.tlchAdapter.notifyDataSetChanged();
        super.onBackPressed();
    }
}