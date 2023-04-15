package com.example.OnThiBangLaiXe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.OnThiBangLaiXe.Model.BienBao;

public class DetailsBienBaoActivity extends AppCompatActivity {
    ImageView imgDetailBB;
    TextView txtIDlBB,txtTitleDetailBB,txtContentDetailBB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_bien_bao);
        addControl();
        Intent intent=getIntent();
        BienBao bb= (BienBao) intent.getSerializableExtra("bienbao");
        imgDetailBB.setImageResource(bb.getImg());
        txtTitleDetailBB.setText(bb.getTitle());
        txtIDlBB.setText(bb.getiD());
        txtContentDetailBB.setText(bb.getContent());
    }
    void addControl()
    {
        imgDetailBB=(ImageView) findViewById(R.id.imgDetailsBienBao);
        txtTitleDetailBB=(TextView) findViewById(R.id.txtTitleDetailBB);
        txtIDlBB=(TextView) findViewById(R.id.txtTitleIDlBB);
        txtContentDetailBB=(TextView) findViewById(R.id.txtContentDetailBB);
        Toolbar toolbarBienBao = (Toolbar) findViewById(R.id.toolbarBienBao);
        toolbarBienBao.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}