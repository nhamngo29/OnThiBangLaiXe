package com.example.OnThiBangLaiXe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

        try {
            holder.ivTheLoaiCauHoi.setImageResource(context.getResources().getIdentifier(
                    tlch.getHinh(), "drawable", context.getPackageName()));
        } catch (Exception e)
        {
            holder.ivTheLoaiCauHoi.setImageResource(R.drawable.ico_exam);
        }

        holder.ten.setText(tlch.getTenLoaiCauHoi());
        holder.soCauHoi.setText(tlch.getSoCauHoiDaTraLoi() + "/" + tlch.getSoCau() + " câu");
        holder.ketQua.setText(tlch.getSoCauDung() + " câu đúng, "
                + (tlch.getSoCau() - tlch.getSoCauDung()) + " câu sai");
        holder.pbKetQua.setMax(tlch.getSoCau());
        holder.pbKetQua.setProgress(tlch.getSoCauHoiDaTraLoi());
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
        private ProgressBar pbKetQua;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivTheLoaiCauHoi = itemView.findViewById(R.id.ivTheLoaiCauHoi);
            ten = itemView.findViewById(R.id.txtTheLoaiCauHoi);
            soCauHoi = itemView.findViewById(R.id.txtSoCau);
            ketQua = itemView.findViewById(R.id.txtKetQua);
            pbKetQua = itemView.findViewById(R.id.pbTheLoaiCauHoi);
        }
    }
}
