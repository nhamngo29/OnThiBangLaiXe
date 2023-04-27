package com.example.OnThiBangLaiXe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnThiBangLaiXe.CauTraLoiActivity;
import com.example.OnThiBangLaiXe.DBHandler;
import com.example.OnThiBangLaiXe.Model.CauHoi;
import com.example.OnThiBangLaiXe.Model.CauTraLoi;
import com.example.OnThiBangLaiXe.Model.DanhSach;
import com.example.OnThiBangLaiXe.Model.DeThi;
import com.example.OnThiBangLaiXe.R;

import java.util.ArrayList;
import java.util.List;

public class DeThiAdapter extends RecyclerView.Adapter<DeThiAdapter.ViewHolder>
{
    private List<DeThi> dsDeThi;
    private Context context;
    DBHandler db;
    public DeThiAdapter(List<DeThi> dsDeThi, Context context) {
        this.context = context;
        this.dsDeThi = dsDeThi;
        db=new DBHandler(context);
    }

    @NonNull
    @Override
    public DeThiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DeThiAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_de_thi, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DeThiAdapter.ViewHolder holder, int position) {
        holder.txtTenDeThi.setText(dsDeThi.get(position).getTenDeThi());
        int CauTraLoiDung=0,CauTraLoiSai=0;
        List<CauTraLoi> dsCTL= db.getListCauTraLoiByMaDeThi(dsDeThi.get(position).getMaDeThi());
        Log.e("size",dsCTL.size()+"");
        for(CauTraLoi ctl:dsCTL)
        {
            CauHoi a=db.getCauHoiByID(ctl.getMaCH());
            if(a.getDapAnDung().equals(ctl.getDapAnChon()))
                CauTraLoiDung++;
            else if(ctl.getDapAnChon()==null||!a.getDapAnDung().equals(ctl.getDapAnChon()))
                CauTraLoiSai++;
        }
        if(CauTraLoiSai>=5)
        {
            holder.txtTenDeThi.setText("Rớt");
            holder.txtTenDeThi.setTextColor(Color.RED);
        }
        else if(CauTraLoiDung>=20&&CauTraLoiSai<5)
        {
            holder.txtTenDeThi.setText("ĐẬU");
            holder.txtTenDeThi.setTextColor(Color.GREEN);
        }
        if(CauTraLoiDung!=0||CauTraLoiSai!=0)
        {
            holder.ivSai.setVisibility(View.VISIBLE);
            holder.ivDung.setVisibility(View.VISIBLE);
            holder.txtSoCauDung.setVisibility(View.VISIBLE);
            holder.txtSoCauSai.setVisibility(View.VISIBLE);
            holder.txtSoCauDung.setText(CauTraLoiDung+"");
            holder.txtSoCauSai.setText(CauTraLoiSai+"");
        }
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CauTraLoiActivity.class);
            intent.putExtra("MaDeThi", dsDeThi.get(position).getMaDeThi());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return dsDeThi.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView txtTenDeThi,txtSoCauDung,txtSoCauSai;
        private final ImageView ivDung,ivSai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenDeThi = itemView.findViewById(R.id.txt_DeThi);
            txtSoCauDung=itemView.findViewById(R.id.txtSoCauDung);
            txtSoCauSai=itemView.findViewById(R.id.txtSoCauSai);
            ivDung=itemView.findViewById(R.id.ivCauDung);
            ivSai=itemView.findViewById(R.id.ivSai);

        }
    }
}