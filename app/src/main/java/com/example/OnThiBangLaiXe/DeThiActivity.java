package com.example.OnThiBangLaiXe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.OnThiBangLaiXe.Adapter.DeThiAdapter;
import com.example.OnThiBangLaiXe.Model.DeThi;

import java.util.ArrayList;
import java.util.List;

public class DeThiActivity extends AppCompatActivity {
    List<DeThi> dsDeThi;
    DeThiAdapter dtAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_thi);

        dsDeThi = new ArrayList<>();

        DeThi dt1 = new DeThi(0, "Đề 1");
        DeThi dt2 = new DeThi(1, "Đề 2");

        dsDeThi.add(dt1);
        dsDeThi.add(dt2);

        dtAdapter = new DeThiAdapter(dsDeThi, this);

        RecyclerView rv = findViewById(R.id.rvDeThi);

        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setAdapter(dtAdapter);
    }
}