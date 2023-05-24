package com.example.OnThiBangLaiXe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.example.OnThiBangLaiXe.Adapter.CauHoiAdapter;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HaySaiActivity extends AppCompatActivity {
    public ViewPager2 vp;
    TextView txtTitle;
    BottomNavigationView bnv;
    Toolbar toolbarBack;
    List<CauHoi> dsCauHoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_sai);
        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("Câu hay sai");
        toolbarBack = findViewById(R.id.toolbarBack);
        toolbarBack.setNavigationOnClickListener(view -> onBackPressed() );
        bnv = findViewById(R.id.bottomNavigationView);
        vp = findViewById(R.id.vp);
        DBHandler db = new DBHandler(this);
        dsCauHoi = new ArrayList<>();
        dsCauHoi=db.docCauHaySai();
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
        Log.e("Back","back");
        MainActivity.tlchAdapter.notifyDataSetChanged();
        MainActivity.setProgress();
        super.onBackPressed();
    }
}