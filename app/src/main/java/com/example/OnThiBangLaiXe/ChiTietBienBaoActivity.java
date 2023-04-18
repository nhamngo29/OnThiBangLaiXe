package com.example.OnThiBangLaiXe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ChiTietBienBaoActivity extends AppCompatActivity {
    ImageView imgDetailBB;
    TextView txtIDlBB;
    TextView txtTitleDetailBB;
    TextView txtContentDetailBB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_bien_bao);
        addControl();
        Intent intent = getIntent();

        try {
            imgDetailBB.setImageResource(getResources().getIdentifier(
                    intent.getStringExtra("HinhAnh"), "drawable", getPackageName()));
        } catch (Exception e)
        {
            imgDetailBB.setImageResource(R.drawable.ico_exam);
        }

        txtTitleDetailBB.setText(intent.getStringExtra("TieuDe"));
        txtIDlBB.setText(intent.getStringExtra("MaBB"));
        txtContentDetailBB.setText(intent.getStringExtra("NoiDung"));
    }
    void addControl()
    {
        imgDetailBB = findViewById(R.id.imgDetailsBienBao);
        txtTitleDetailBB = findViewById(R.id.txtTitleDetailBB);
        txtIDlBB = findViewById(R.id.txtTitleIDlBB);
        txtContentDetailBB = findViewById(R.id.txtContentDetailBB);
        Toolbar toolbarBienBao = findViewById(R.id.toolbarBienBao);
        toolbarBienBao.setNavigationOnClickListener(view -> onBackPressed());
    }
}