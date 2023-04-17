package com.example.OnThiBangLaiXe;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.OnThiBangLaiXe.Adapter.BienBaoAdapter;
import com.example.OnThiBangLaiXe.Adapter.LoaiBienBaoAdapter;
import com.example.OnThiBangLaiXe.Model.BienBao;
import com.example.OnThiBangLaiXe.Model.LoaiBienBao;
import com.example.OnThiBangLaiXe.Model.LoaiCauHoi;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toolbar toolbarBienBao;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bien_bao);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        toolbarBienBao = findViewById(R.id.toolbarBienBao);
        toolbarBienBao.setNavigationOnClickListener(view -> onBackPressed());

        List<BienBao> dsBienBao = new ArrayList<>();

        List<LoaiBienBao> dsLoaiBienBao = new ArrayList<>();
        dsLoaiBienBao.add(new LoaiBienBao(1, "Biển báo cấm"));
        dsLoaiBienBao.add(new LoaiBienBao(2, "Biển báo nguy hiểm"));
        dsLoaiBienBao.add(new LoaiBienBao(3, "Biển báo hiệu lệnh"));

        ViewPager2 vp = findViewById(R.id.vp);
        LoaiBienBaoAdapter lbbAdapter = new LoaiBienBaoAdapter(dsLoaiBienBao, this, dsBienBao);
        vp.setAdapter(lbbAdapter);

        new TabLayoutMediator(tabLayout, vp, (tab, position)
                -> tab.setText(dsLoaiBienBao.get(position).getTenLoaiBB())).attach();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference csdlLoaiCauHoi = database.getReference("BienBao");

        csdlLoaiCauHoi.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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
                                check.setNoiDung(bb.getNoiDung());
                                Log.d("Firebase", "Value is existed: " + bb.getMaBB());
                                existed = true;
                                break;
                            }
                        }

                        if (!existed)
                        {
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