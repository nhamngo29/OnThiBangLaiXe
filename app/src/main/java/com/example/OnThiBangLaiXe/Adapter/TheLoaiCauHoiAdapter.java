package com.example.OnThiBangLaiXe.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.Model.TheLoaiCauHoi;
import com.example.OnThiBangLaiXe.R;

import java.util.List;

public class TheLoaiCauHoiAdapter extends RecyclerView.Adapter<TheLoaiCauHoiAdapter.ViewHolder>
{
    private List<TheLoaiCauHoi> dsTheLoaiCauHoi;
    private Context context;

    public TheLoaiCauHoiAdapter(List<TheLoaiCauHoi> dsTheLoaiCauHoi, Context context) {
        this.dsTheLoaiCauHoi = dsTheLoaiCauHoi;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_theloaicauhoi, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoaiCauHoi tlch = dsTheLoaiCauHoi.get(position);
        holder.ivTheLoaiCauHoi.setImageResource(context.getResources().getIdentifier(
                tlch.getHinh(), "drawable", context.getPackageName()));
        holder.ten.setText(tlch.getTen());
    }

    @Override
    public int getItemCount() {
        return dsTheLoaiCauHoi.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView ivTheLoaiCauHoi;
        private TextView ten;
        private TextView soCauHoi;
        private TextView ketQua;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivTheLoaiCauHoi = itemView.findViewById(R.id.ivTheLoaiCauHoi);
            ten = itemView.findViewById(R.id.txtTheLoaiCauHoi);
        }
    }
}
