package com.example.OnThiBangLaiXe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.OnThiBangLaiXe.Adapter.CauHoiAdapter;
import com.example.OnThiBangLaiXe.Adapter.CauTraLoiAdapter;
import com.example.OnThiBangLaiXe.Adapter.KetQuaAdapter;
import com.example.OnThiBangLaiXe.Adapter.menuCauHoiAdapter;
import com.example.OnThiBangLaiXe.Model.CauTraLoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class KetQuaActivity extends AppCompatActivity {
    public RecyclerView rvCauHoi;
    public RecyclerView rvCauHoiChiTiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);

        int maDeThi = getIntent().getIntExtra("MaDeThi", 0);

        List<CauTraLoi> dsCauTraLoi = new ArrayList<>();

        List<CauTraLoi> check = DanhSach.getDsCauTraLoi();

        for (CauTraLoi ctl: check)
        {
            if (ctl.getMaDeThi() == maDeThi)
                dsCauTraLoi.add(ctl);
        }

        Toolbar toolbarBack = findViewById(R.id.toolbarBack);

        toolbarBack.setNavigationOnClickListener(v -> onBackPressed());

        rvCauHoi = findViewById(R.id.rvCauHoi);
        rvCauHoi.setAdapter(new menuCauHoiAdapter(dsCauTraLoi, this));
        rvCauHoi.setLayoutManager(new GridLayoutManager(this, 15));

        rvCauHoiChiTiet = findViewById(R.id.rvCauHoiChiTiet);
        rvCauHoiChiTiet.setAdapter(new KetQuaAdapter(dsCauTraLoi, this));
        rvCauHoiChiTiet.setLayoutManager(new LinearLayoutManager(this));
    }
}