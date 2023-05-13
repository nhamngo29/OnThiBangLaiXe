package com.example.OnThiBangLaiXe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.OnThiBangLaiXe.Model.CauTraLoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;

import java.util.ArrayList;
import java.util.List;

public class KetQuaActivity extends AppCompatActivity {
    DBHandler db;
    int type=0, maDeThi;
    Button btnAll,btnTrue,btnFalse,btnNull;
    public TextView ThiLai;
    List<CauTraLoi> dsCTL;
    public int getType() {
        return type;
    }

    void init()
    {
        btnAll=findViewById(R.id.btnAll);
        btnTrue=findViewById(R.id.btnTrue);
        btnFalse=findViewById(R.id.btnFalse);
        btnNull=findViewById(R.id.btnNull);
        ThiLai=findViewById(R.id.txtThiLai);
        ThiLai.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);
        Intent intent = getIntent();
        maDeThi =intent.getIntExtra("MaDeThi",1);
        db=new DBHandler(this);
        Toolbar toolbarBack;
        toolbarBack = findViewById(R.id.toolbarBack);
        toolbarBack.setNavigationOnClickListener(view -> onBackPressed());
        init();
        int soCauDung=0,soCauSai=0,soCauChuaTraLoi=0;
        dsCTL = new ArrayList<>();

        for (CauTraLoi ctl : DanhSach.getDsCauTraLoi())
        {
            if (ctl.getMaDeThi() == maDeThi)
            {
                dsCTL.add(ctl);
            }
        }

        for (CauTraLoi ctl:dsCTL)
        {
                if(ctl.getDapAnChon()==null||ctl.getDapAnChon().equals("null"))
                    soCauChuaTraLoi++;
                else if(ctl.getDapAnChon().equals(db.getCauHoiByID(ctl.getMaCH()).getDapAnDung())||ctl.getDapAnChon()==db.getCauHoiByID(ctl.getMaCH()).getDapAnDung())
                    soCauDung++;
                else
                    soCauSai++;
        }
        btnTrue.setText(soCauDung+"");
        btnFalse.setText(soCauSai+"");
        btnNull.setText(soCauChuaTraLoi+"");
        senDataToFrm(dsCTL);
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senDataToFrm(dsCTL);
            }
        });
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<CauTraLoi> a=new ArrayList<>();
                for (CauTraLoi ctl:dsCTL)
                {
                    if(ctl!=null&&ctl.getDapAnChon()!=null)
                    {
                            if(ctl.getDapAnChon().equals(db.getCauHoiByID(ctl.getMaCH()).getDapAnDung()))
                                a.add(ctl);
                    }
                }
                senDataToFrm(a);
            }
        });
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<CauTraLoi> a=new ArrayList<>();
                for (CauTraLoi ctl:dsCTL)
                {
                    if(ctl!=null&&ctl.getDapAnChon()!=null)
                    {
                        if(!ctl.getDapAnChon().equals(db.getCauHoiByID(ctl.getMaCH()).getDapAnDung()))
                            a.add(ctl);
                    }
                }
                senDataToFrm(a);
            }
        });
        btnNull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<CauTraLoi> a=new ArrayList<>();
                for (CauTraLoi ctl:dsCTL)
                {
                    if(ctl.getDapAnChon()==null||ctl.getDapAnChon().equals("null"))
                        a.add(ctl);
                }
                senDataToFrm(a);
            }
        });
        ThiLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(KetQuaActivity.this, ThiThuActivity.class);
                intent.putExtra("MaDeThi", maDeThi);
                startActivity(intent);
                finish();
            }
        });
    }
    void senDataToFrm(List<CauTraLoi> a)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fm,new ResultFragment(a));
        fragmentTransaction.commit();
        init();
    }
}