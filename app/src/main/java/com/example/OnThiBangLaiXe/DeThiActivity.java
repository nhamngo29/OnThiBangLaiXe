package com.example.OnThiBangLaiXe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.OnThiBangLaiXe.Adapter.DeThiAdapter;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.example.OnThiBangLaiXe.Model.DeThi;

import java.util.ArrayList;
import java.util.List;

public class DeThiActivity extends AppCompatActivity {
    List<DeThi> dsDeThi;
    public static DeThiAdapter dtAdapter;
    Toolbar toolbarBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_thi);

        dsDeThi = new ArrayList<>();
        toolbarBack =findViewById(R.id.toolbarBack);




        dtAdapter = new DeThiAdapter(DanhSach.getDsDeThi(), this);


        RecyclerView rv = findViewById(R.id.rvDeThi);
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setAdapter(dtAdapter);
        init();
        event();

    }
    void event()
    {
        toolbarBack.setNavigationOnClickListener(view -> onBackPressed());
    }
    void init()
    {

        toolbarBack =findViewById(R.id.toolbarBack);
    }

}