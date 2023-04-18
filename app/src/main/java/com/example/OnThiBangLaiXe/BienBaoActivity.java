package com.example.OnThiBangLaiXe;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;
import com.example.OnThiBangLaiXe.Adapter.LoaiBienBaoAdapter;
import com.example.OnThiBangLaiXe.Model.BienBao;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BienBaoActivity extends AppCompatActivity {
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toolbar toolbarBienBao;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bien_bao);
        db=new DBHandler(this);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        toolbarBienBao = findViewById(R.id.toolbarBienBao);
        toolbarBienBao.setNavigationOnClickListener(view -> onBackPressed());
        List<BienBao> dsBienBao = new ArrayList<>();
        ViewPager2 vp = findViewById(R.id.vp);
        LoaiBienBaoAdapter lbbAdapter = new LoaiBienBaoAdapter(DanhSach.getDsLoaiBienBao(), this, dsBienBao);
        vp.setAdapter(lbbAdapter);

        new TabLayoutMediator(tabLayout, vp, (tab, position)
                -> tab.setText(DanhSach.getDsLoaiBienBao().get(position).getTenLoaiBB())).attach();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference csdlLoaiCauHoi = database.getReference("BienBao");


        csdlLoaiCauHoi.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("Firebase", "No internet" );
                for (int i = 0; i < snapshot.getChildrenCount(); i++)
                {
                    BienBao bb = snapshot.child(String.valueOf(i)).getValue(BienBao.class);

                    if (bb != null)
                    {
                        boolean existed = false;

                        for (BienBao check : dsBienBao)
                        {
                            if (Objects.equals(bb.getMaBB(), check.getMaBB()))
                            {
                                check.setTieuDe(bb.getTieuDe());
                                check.setHinhAnh(bb.getHinhAnh());
                                check.setNoidung(bb.getNoidung());
                                Log.d("Firebase", "Value is existed: " + bb.getMaBB());
                                existed = true;
                                break;
                            }
                        }

                        if (!existed)
                        {

                            if(db.findBBByID(bb.getMaBB().trim()))
                            {
                                db.updateBB(bb);
                            }
                            else
                            {
                                db.insertBB(bb);
                            }
                            dsBienBao.add(bb);
                            lbbAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}