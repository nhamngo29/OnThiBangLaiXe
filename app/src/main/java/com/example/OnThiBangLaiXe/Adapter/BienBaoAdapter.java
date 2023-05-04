package com.example.OnThiBangLaiXe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.ChiTietBienBaoActivity;
import com.example.OnThiBangLaiXe.Model.BienBao;
import com.example.OnThiBangLaiXe.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class BienBaoAdapter extends RecyclerView.Adapter<BienBaoAdapter.ViewHolder>
{
    private List<BienBao> dsBienBao;
    private Context context;

    public BienBaoAdapter(List<BienBao> dsBienBao, Context context) {
        this.dsBienBao = dsBienBao;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_bien_bao, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BienBao bb = dsBienBao.get(position);

        try {
            File f = new File(context.getDataDir() + "/app_images/", bb.getHinhAnh());
            Log.d("path", f.getAbsolutePath());
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            holder.ivBienBao.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            holder.ivBienBao.setImageResource(R.drawable.ico_exam);
        }

        holder.txtMaBienBao.setText(bb.getMaBB());
        holder.txtTieuDeBienBao.setText(bb.getTieuDe());
        holder.txtNoiDungBienBao.setText(bb.getNoidung());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChiTietBienBaoActivity.class);
            intent.putExtra("MaBB", bb.getMaBB());
            intent.putExtra("TieuDe", bb.getTieuDe());
            intent.putExtra("NoiDung", bb.getNoidung());
            intent.putExtra("HinhAnh", bb.getHinhAnh());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dsBienBao.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private final ImageView ivBienBao;
        private final TextView txtMaBienBao;
        private final TextView txtTieuDeBienBao;
        private final TextView txtNoiDungBienBao;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBienBao = itemView.findViewById(R.id.ivBienBao);
            txtMaBienBao = itemView.findViewById(R.id.txtMaBienBao);
            txtTieuDeBienBao = itemView.findViewById(R.id.txtTieuDeBienBao);
            txtNoiDungBienBao = itemView.findViewById(R.id.txtNoiDungBienBao);
        }
    }
}
