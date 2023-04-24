package com.example.OnThiBangLaiXe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.CauHoiActivity;
import com.example.OnThiBangLaiXe.Interface.RecyclerViewInterface;
import com.example.OnThiBangLaiXe.Model.LoaiCauHoi;
import com.example.OnThiBangLaiXe.R;

import java.util.List;

public class TheLoaiCauHoiAdapter extends RecyclerView.Adapter<TheLoaiCauHoiAdapter.ViewHolder>
{
    private final RecyclerViewInterface recyclerViewInterface;
    private List<LoaiCauHoi> dsLoaiCauHoi;
    private Context context;

    public TheLoaiCauHoiAdapter(List<LoaiCauHoi> dsLoaiCauHoi, Context context,RecyclerViewInterface recyclerViewInterface) {
        this.dsLoaiCauHoi = dsLoaiCauHoi;
        this.context = context;
        this.recyclerViewInterface=recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_theloaicauhoi, parent, false),recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoaiCauHoi tlch = dsLoaiCauHoi.get(position);

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

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CauHoiActivity.class);
            intent.putExtra("MaLoaiCH", tlch.getMaLoaiCH());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dsLoaiCauHoi.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView ivTheLoaiCauHoi;
        private TextView ten;
        private TextView soCauHoi;
        private TextView ketQua;
        private ProgressBar pbKetQua;
        public ViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            ivTheLoaiCauHoi = itemView.findViewById(R.id.ivTheLoaiCauHoi);
            ten = itemView.findViewById(R.id.txtTheLoaiCauHoi);
            soCauHoi = itemView.findViewById(R.id.txtSoCau);
            ketQua = itemView.findViewById(R.id.txtKetQua);
            pbKetQua = itemView.findViewById(R.id.pbTheLoaiCauHoi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface!=null)
                    {
                        int pos=getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION)
                        {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
