package com.example.OnThiBangLaiXe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
            File f = new File(getDataDir() + "/app_images", intent.getStringExtra("HinhAnh"));
            Log.d("path", f.getAbsolutePath());
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            imgDetailBB.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
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