package com.example.OnThiBangLaiXe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.CauTraLoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;

import java.util.ArrayList;
import java.util.List;

import com.example.OnThiBangLaiXe.Fragment.ResultFragment;

public class KetQuaActivity extends AppCompatActivity {
    DBHandler db;
    int type=0, maDeThi;
    Button btnAll,btnTrue,btnFalse,btnNull;
    public TextView ThiLai, txtlyDo, txtKetQua;
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
        txtlyDo = findViewById(R.id.txtLyDo);
        txtKetQua = findViewById(R.id.txtResult);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);
        Intent intent = getIntent();
        maDeThi =intent.getIntExtra("MaDeThi",1);
        db=new DBHandler(this);
        dsCTL=new ArrayList<>();
        Toolbar toolbarBack;
        toolbarBack = findViewById(R.id.toolbarBack);
        toolbarBack.setNavigationOnClickListener(view -> onBackPressed());
        init();
        int soCauDung=0,soCauSai=0,soCauChuaTraLoi=0;
        if(maDeThi==0)
        {
            dsCTL.addAll(DanhSach.getDsCauTLRandom());
        }
        else {
            dsCTL=db.getListCauTraLoiByMaDeThi(maDeThi);
        }

        for (CauTraLoi ctl:dsCTL)
        {
                if(ctl.getDapAnChon().equals("0"))
                    soCauChuaTraLoi++;
                else if(ctl.getDapAnChon().equals(db.getCauHoiByID(ctl.getMaCH()).getDapAnDung()))
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
                        if(!ctl.getDapAnChon().equals(db.getCauHoiByID(ctl.getMaCH()).getDapAnDung())&&!ctl.getDapAnChon().equals("0"))
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
                    if(ctl.getDapAnChon()==null||ctl.getDapAnChon().equals("0"))
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

        if (soCauDung >= 21)
        {
            txtlyDo.setText("");
            txtKetQua.setText("ĐẬU");
            txtKetQua.setTextColor(Color.GREEN);
        }
        else
        {
            for (CauTraLoi ctl : dsCTL)
            {
                for (CauHoi ch : DanhSach.getDsCauHoi())
                {
                    if (ch.getMaCH() == ctl.getMaCH())
                    {
                        if (ch.getDapAnDung().equals(ctl.getDapAnChon()))
                        {
                            if (ch.getMaLoaiCH() == 1)
                            {
                                txtlyDo.setText("Sai câu điểm liệt");
                                return;
                            }
                        }
                    }
                }
            }
        }

    }
    void senDataToFrm(List<CauTraLoi> a)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fm,new ResultFragment(a));
        fragmentTransaction.commit();
        init();
    }
}