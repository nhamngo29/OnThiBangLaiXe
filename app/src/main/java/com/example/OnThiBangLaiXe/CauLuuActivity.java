package com.example.OnThiBangLaiXe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.OnThiBangLaiXe.Adapter.SaveQuestionAdapter;
import com.example.OnThiBangLaiXe.Model.CauHoi;

import java.util.ArrayList;
import java.util.List;

public class CauLuuActivity extends AppCompatActivity {

    private DBHandler db;
    private List<CauHoi> cauHois;
    private ImageView imageBack;
    private RecyclerView rcv;
    private SaveQuestionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_luu);
        init();
        getCauHoi();
        showData();
        setOnclick();
    }
    private void init() {
        db = new DBHandler(this);
        cauHois = new ArrayList<>();
        adapter = new SaveQuestionAdapter(this, cauHois);

        imageBack = findViewById(R.id.imageBack);
        rcv = findViewById(R.id.rcvSaveCauHoi);
    }

    private void getCauHoi() {
        String sql = "Select * from CauHoi where luu = 1";
        Cursor cursor = db.getData(sql);
        while(cursor.moveToNext()) {
            int maCauHoi = Integer.parseInt(cursor.getString(0));
            int maLoaiCauHoi = Integer.parseInt(cursor.getString(1));
            int maLoaiBang = cursor.getInt(2);
            String noiDung = cursor.getString(3);
            String hinhAnh = cursor.getString(4);
            String dapAnA = cursor.getString(5);
            String dapAnB = cursor.getString(6);
            String dapAnC = cursor.getString(7);
            String dapAnD = cursor.getString(8);
            String dapAnDung = cursor.getString(9);
            String giaiThich = cursor.getString(10);
            int luu = Integer.parseInt(cursor.getString(11));
            int haySai = Integer.parseInt(cursor.getString(12));
            int daTraLoi = Integer.parseInt((cursor.getString(13) == null) ? "0" : "0");
            CauHoi cauHoi = new CauHoi(
                    maCauHoi, maLoaiCauHoi, maLoaiBang, noiDung, hinhAnh,
                    dapAnA, dapAnB, dapAnC, dapAnD, dapAnDung, giaiThich,
                    luu, haySai, daTraLoi
            );
            cauHois.add(cauHoi);
        }
    }

    private void showData() {
        adapter.setCauHois(cauHois);
        rcv.setAdapter(adapter);
    }

    private void setOnclick() {
        imageBack.setOnClickListener(view -> finish());
    }
}